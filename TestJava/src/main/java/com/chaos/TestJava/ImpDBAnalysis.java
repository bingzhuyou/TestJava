package com.chaos.TestJava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ImpDBAnalysis {
	private Connection con = null;// 创建一个数据库连接
	private PreparedStatement pre = null;// 创建预编译语句对象，一般都是用这个而不用Statement
	private ResultSet result = null;// 创建一个结果集对象

	public void initDB(String dbHost, String dbPort, String sid, String dbUser, String dbPwd) throws ClassNotFoundException, SQLException {

		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:" + "thin:@" + dbHost + ":" + dbPort + ":" + sid;
		con = DriverManager.getConnection(url, dbUser, dbPwd);
	}

	public void closeDB() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void statisticsQuery() throws SQLException {

		List<String> sqlList = new ArrayList<String>();
		List<PreparedStatement> stmtList = new ArrayList<PreparedStatement>();

		// act 表告警总数
		sqlList.add("select count(*) nbr from tfa_alarm_act " + "where event_time > to_date(?, 'SYYYY-MM-DD HH24:MI:SS')"
				+ " and event_time < to_date(?, 'SYYYY-MM-DD HH24:MI:SS')");
		// clr 表告警总数
		sqlList.add("select count(*) nbr from tfa_alarm_clr " + "where event_time > to_date(?, 'SYYYY-MM-DD HH24:MI:SS')"
				+ " and event_time < to_date(?, 'SYYYY-MM-DD HH24:MI:SS')");
		// sheet 表告警总数
		sqlList.add("select count(*) nbr from tfa_alarmsheetinfo " + "where event_time > to_date(?, 'SYYYY-MM-DD HH24:MI:SS')"
				+ " and event_time < to_date(?, 'SYYYY-MM-DD HH24:MI:SS')");

		// sheet 表工单总数
		sqlList.add("select count(distinct sheet_no) nbr from tfa_alarmsheetinfo " + "where event_time > to_date(?, 'SYYYY-MM-DD HH24:MI:SS')"
				+ " and event_time < to_date(?, 'SYYYY-MM-DD HH24:MI:SS')");

		// relation 表告警总数
		sqlList.add("select count(*) nbr from tfa_alarm_relation " + "where insert_time > to_date(?, 'SYYYY-MM-DD HH24:MI:SS')"
				+ " and insert_time < to_date(?, 'SYYYY-MM-DD HH24:MI:SS')");

		// relation 表主告警总数，包括同时是子告警的部分
		sqlList.add("select count(distinct orig_alarm_fp0) from tfa_alarm_relation " + "where insert_time > to_date(?, 'SYYYY-MM-DD HH24:MI:SS') "
				+ " and insert_time < to_date(?, 'SYYYY-MM-DD HH24:MI:SS')");
		// relation 表子告警总数，包括同时是主告警的部分
		sqlList.add("select count(distinct corr_alarm_fp0) from tfa_alarm_relation " + "where insert_time > to_date(?, 'SYYYY-MM-DD HH24:MI:SS') "
				+ " and insert_time < to_date(?, 'SYYYY-MM-DD HH24:MI:SS')");

		for (String sql : sqlList) {
			stmtList.add(con.prepareStatement(sql));
		}

		List<ArrayList<Integer>> statistics = new ArrayList<ArrayList<Integer>>();

		for (int i = 0; i < 70; i++) {
			ArrayList<Integer> onepiece = new ArrayList<Integer>();
			for (PreparedStatement stmt : stmtList) {
				onepiece.add(getCount(stmt, i));
			}
			statistics.add(onepiece);
		}

		for (ArrayList<Integer> one : statistics) {
			for (Integer v : one) {
				System.out.print("" + v + ",");
			}
			System.out.print("\n");
		}

		for (PreparedStatement stmt : stmtList) {
			if (stmt != null) {
				stmt.close();
			}
		}
	}

	public static int getCount(PreparedStatement pre, int i) throws SQLException {
		int rst = 0;
		String btime, etime;
		ResultSet result = null;

		String begDate = "2015-07-20";

		btime = getTimeStr(begDate, i);
		etime = getTimeStr(begDate, i + 1);

		btime += " 00:00:00";
		etime += " 00:00:00";

		pre.setString(1, btime);
		pre.setString(2, etime);
		result = pre.executeQuery();

		// System.out.println(btime + " " + etime);
		if (result.next())
			rst = result.getInt(1);

		result.close();

		return rst;
	}

	public static String getTimeStr(String begTime, int i) {
		Date dt = null;
		SimpleDateFormat df = (SimpleDateFormat) DateFormat.getDateInstance();

		String tStr = null;

		df.applyPattern("yyyy-MM-dd");
		try {
			long incSecond = 3600000 * 24 * (long) i;
			dt = df.parse(begTime);
			Date rt = new Date(dt.getTime() + incSecond);
			tStr = df.format(rt);

		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		return tStr;
	}

	public void getStatistics() {

	}

	public static void testOracle() {
		Connection con = null;// 创建一个数据库连接
		PreparedStatement pre = null;// 创建预编译语句对象，一般都是用这个而不用Statement
		ResultSet result = null;// 创建一个结果集对象
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");// 加载Oracle驱动程序
			System.out.println("开始尝试连接数据库！");
			String url = "jdbc:oracle:" + "thin:@10.12.1.80:1521:orcl";// 127.0.0.1是本机地址，XE是精简版Oracle的默认数据库名
			String user = "std_nmosdb";// 用户名,系统默认的账户名
			String password = "std_nmosoptr";// 你安装时选设置的密码
			con = DriverManager.getConnection(url, user, password);// 获取连接
			System.out.println("连接成功！");
			String btime = "2015-07-27 00:00:00";
			String etime = "2015-07-28 00:00:00";
			String sql = "select count(*) nbr from tfa_alarm_relation " + "where insert_time > to_date(?, 'SYYYY-MM-DD HH24:MI:SS')"
					+ " and insert_time < to_date(?, 'SYYYY-MM-DD HH24:MI:SS')";
			pre = con.prepareStatement(sql);// 实例化预编译语句
			pre.setString(1, btime);// 设置参数，前面的1表示参数的索引，而不是表中列名的索引
			pre.setString(2, etime);// 设置参数，前面的1表示参数的索引，而不是表中列名的索引
			result = pre.executeQuery();// 执行查询，注意括号中不需要再加参数
			while (result.next())
				// 当结果集不为空时
				System.out.println(result.getInt(1));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 逐一将上面的几个对象关闭，因为不关闭的话会影响性能、并且占用资源
				// 注意关闭的顺序，最后使用的最先关闭
				if (result != null)
					result.close();
				if (pre != null)
					pre.close();
				if (con != null)
					con.close();
				System.out.println("数据库连接已关闭！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String args[]) {

		ImpDBAnalysis is = new ImpDBAnalysis();

		try {
			is.initDB("10.12.1.80", "1521", "orcl", "std_nmosdb", "std_nmosoptr");
			is.statisticsQuery();
			is.closeDB();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
