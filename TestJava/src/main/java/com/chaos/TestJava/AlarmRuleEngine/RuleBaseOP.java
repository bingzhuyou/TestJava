package com.chaos.TestJava.AlarmRuleEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class RuleBaseOP<E> {
	public static Logger logger = LoggerFactory.getLogger(RuleBaseOP.class);

	public String version() {
		String algorithmBaseClassName = "";
		if (algorithmBase != null) {
			algorithmBaseClassName = this.algorithmBase.getClass().getSimpleName();
		}
		return this.getClass().getSimpleName() + "#" + algorithmBaseClassName;
	}

	public abstract void doFilter();

	public abstract void doFilter(String alarmId);

	public abstract void prefilter();

	public int CONDITIONCOUNT = 1665;
	public int FILTERCOUNT = 800;
	public boolean[] c;// = new boolean[CONDITIONCOUNT];
	public int[] resultlist1;// = new Integer[FILTERCOUNT];
	// static public Object[] CONSTDEF;// new Object[CONDITIONCOUNT];
	public Object[] CONSTDEF;// new Object[CONDITIONCOUNT];

	public int FIELDCOUNT = 300;
	public HashMap<String, Integer[]>[] map0 = new HashMap[FIELDCOUNT];
	public HashMap<String, Vector<Integer>>[] map1 = new HashMap[FIELDCOUNT];

	public String[][] map2 = new String[FIELDCOUNT][]; // 正则表达式字段

	int TEXTSEARCHMAXMATCH = FILTERCOUNT;// 500;
	int TEXTSEARCHKEYMAXCOUNT = 3000;
	int TEXTSEARCHKEYMAXSAMECOUNT = 200;

	// char[] source = null;
	public boolean[] b = new boolean[65536];
	public int[][] bb;// = new int[65536][TEXTSEARCHKEYMAXSAMECOUNT];

	String[] result;// = new String[TEXTSEARCHMAXMATCH];
	int resultlen = 0;
	// char[][] keyss;// = new char[TEXTSEARCHKEYMAXCOUNT][];
	// boolean[] delkeyss;// = new boolean[TEXTSEARCHKEYMAXCOUNT];
	// int[] keyss_patch;// = new char[TEXTSEARCHKEYMAXCOUNT][];

	HashMap<String, Integer> keys = new HashMap<String, Integer>();
	HashMap<String, Integer> values = new HashMap<String, Integer>();
	Vector<Integer> keysleng = new Vector<Integer>();
	int curri = 0;
	int curri_patch = 1;

	int ALARMIDSEARCHKEYMAXCOUNT = 2500;
	int ALARMIDSEARCHKEYMAXSAMECOUN = 2500;

	// public boolean b_98[] = new boolean[65536];
	// public int bb_98[][];// = new
	// int[65536][ALARMIDSEARCHKEYMAXSAMECOUN];

	// char[][] keyss_98;// = new char[ALARMIDSEARCHKEYMAXCOUNT][];
	// boolean[] delkeyss_98;// = new boolean[ALARMIDSEARCHKEYMAXCOUNT];
	// int[] keyss_98_patch;// = new char[TEXTSEARCHKEYMAXCOUNT][];
	// HashMap<String, Integer> keys_98 = new HashMap<String, Integer>();
	// HashMap<String, Integer> values_98 = new HashMap<String, Integer>();
	// Vector<Integer> keysleng_98 = new Vector<Integer>();
	// int curri_98 = 0;
	// int curri_98_patch = 1;

	public int filterfuncount = 0;

	public RuleBaseOP() {
		// initdatalen(200,1800,1400,1000,300,1000,1000);
	}

	public boolean initdatalen(int iConditionCount, int iFilterCount, int iTEXTSEARCHKEYMAXCOUNT,
			int iTEXTSEARCHKEYMAXSAMECOUNT, int iALARMIDSEARCHKEYMAXCOUNT, int iALARMIDSEARCHKEYMAXSAMECOUN) {
		CONDITIONCOUNT = iConditionCount;
		FILTERCOUNT = iFilterCount;

		c = new boolean[CONDITIONCOUNT];
		resultlist1 = new int[FILTERCOUNT];
		CONSTDEF = new Object[CONDITIONCOUNT];
		map0 = new HashMap[FIELDCOUNT];
		map1 = new HashMap[FIELDCOUNT];
		map2 = new String[FIELDCOUNT][]; // 正则表达式字段

		for (int i = 0; i < map1.length; i++) {
			map1[i] = new HashMap();
			map0[i] = new HashMap();
		}

		TEXTSEARCHMAXMATCH = FILTERCOUNT;// 500;
		TEXTSEARCHKEYMAXCOUNT = iTEXTSEARCHKEYMAXCOUNT;
		TEXTSEARCHKEYMAXSAMECOUNT = iTEXTSEARCHKEYMAXSAMECOUNT;

		bb = new int[65536][TEXTSEARCHKEYMAXSAMECOUNT];
		result = new String[TEXTSEARCHMAXMATCH];

		// keyss = new char[TEXTSEARCHKEYMAXCOUNT][];
		// delkeyss = new boolean[TEXTSEARCHKEYMAXCOUNT];
		// keyss_patch = new int[TEXTSEARCHKEYMAXCOUNT];
		// keyss_patch[0] = 0;

		ALARMIDSEARCHKEYMAXSAMECOUN = iALARMIDSEARCHKEYMAXSAMECOUN;
		ALARMIDSEARCHKEYMAXCOUNT = iALARMIDSEARCHKEYMAXCOUNT;

		// bb_98 = new int[65536][ALARMIDSEARCHKEYMAXSAMECOUN];
		// keyss_98 = new char[ALARMIDSEARCHKEYMAXCOUNT][];
		// delkeyss_98 = new boolean[ALARMIDSEARCHKEYMAXCOUNT];
		// keyss_98_patch = new int[ALARMIDSEARCHKEYMAXCOUNT];
		// keyss_98_patch[0] = 0;

		return true;// false;
	}

	TIC_NEGRP_LIST TIC_NEGRP_LISTMgr = null;

	public void setTIC_NEGRP_LIST(TIC_NEGRP_LIST TIC_NEGRP_LISTMgr1) {
		this.TIC_NEGRP_LISTMgr = TIC_NEGRP_LISTMgr1;
	}

	public Vector<Integer> resultlist = new Vector<Integer>();
	public StringBuffer results = new StringBuffer();

	public void printresult() {
		System.out.println("Match Filters:");
		for (int i = 0; i < resultlist.size(); i++) {
			System.out.print("," + resultlist.get(i));
		}
	}

	public String group(String v) {
		return v;
	}

	public boolean IN(Object v, int def_id, int def_type, String values) {
		if (def_type == 8) {
			logger.error("**************test_in ************object_v" + v + ",def_id=" + def_id + ",def_type="
					+ def_type + ",values=" + values);
		}

		String sv = String.valueOf(v);
		// 2013.12.25大小写不敏感需求
		sv = sv.toLowerCase();
		values = values.toLowerCase();

		if (def_type == 3) {// | split
			String[] vvv = values.split("\\|");
			for (int i = 0; i < vvv.length; i++) {
				String targetvv = vvv[i];
				int flag = 0;
				if (targetvv.indexOf(".*") > -1) {
					String[] targetvvv = targetvv.split("\\.\\*");
					for (int j = 0; j < targetvvv.length; j++) {
						if (sv.indexOf(targetvvv[j]) < 0) {
							break;
						}
						flag++;
					}
					if (flag == targetvvv.length) {
						return true;
					}
				} else {
					if (sv.indexOf(targetvv) > -1) {
						return true;
					}
				}
			}
			return false;
		}
		String[] vv = values.split(",");
		for (int i = 0; i < vv.length; i++) {
			String targetvv = vv[i];
			if (def_type == 5 && def_id == 8) {// ne_group->int_id
				targetvv = Util.trim(targetvv);
			}
			if (targetvv.equals(sv))
				return true;
		}
		return false;
	}

	public boolean IN(Object v, int def_id, int def_type, String group, String values) {
		return IN(v, def_id, def_type, values);
	}

	public boolean NOTIN(Object v, int def_id, int def_type, String values) {
		return !IN(v, def_id, def_type, values);
	}

	public boolean NOTIN(Object v, int def_id, int def_type, String group, String values) {
		return !IN(v, def_id, def_type, values);
	}

	public void ff(int iPos, int def_id, int k, boolean mode) {
		/*
		 * if (def_id > 100) { def_id = def_id % 1000 + 100; }
		 */
		try {
			def_id = algorithmBase.getPos(def_id);

			String[] vv = (String[]) CONSTDEF[iPos];
			for (int i = 0; i < vv.length; i++) {
				Vector<Integer> v = null;
				if (map1[def_id].containsKey(vv[i])) {
					v = map1[def_id].get(vv[i]);
				} else {
					v = new Vector<Integer>();
				}
				v.add(iPos);
				map1[def_id].put(vv[i], v);
			}
		} catch (Exception e) {
			logger.error("ff Exception.iPos:" + iPos + ",def_id:" + def_id + ",k:" + k, e);
		}
	}

	public void outmap1() {
		for (int iPos = 0; iPos < 200; iPos++) {
			Iterator it = map1[iPos].keySet().iterator();
			for (; it.hasNext();) {
				String key = (String) it.next();
				Vector<Integer> conditionPos = this.map1[iPos].get(key);
				System.out.println(iPos + ":" + key + ":" + conditionPos.size());
			}
		}
	}

	boolean f_INT(int field, Integer[] value, boolean IN) {
		for (int i = 0; i < value.length; i++) {
			if (value[i] == field) {
				return IN;
			}
		}
		return !IN;
	}

	boolean f_LONG(Long field, Long[] value, boolean IN) {
		if (field == null) {
			return false;
		}
		for (int i = 0; i < value.length; i++) {
			Long temp = value[i];
			if (temp != null && temp.equals(field)) {
				return IN;
			}
		}
		return !IN;
	}

	boolean f_LONG(long field, String value, boolean IN) {
		String[] vv = value.split(",");
		for (int i = 0; i < vv.length; i++) {
			if (vv[i].length() < 1)
				continue;
			if (Long.parseLong(vv[i]) == field) {
				return IN;
			}
		}
		return !IN;
	}

	private String toString(String[] vv) {
		if (vv == null) {
			return "";
		}
		if (vv.length == 0) {
			return "";
		}
		String str = "";
		for (int i = 0; i < vv.length; i++) {
			if (str == null || str.length() == 0) {
				str = vv[i];
			} else {
				str += "," + vv[i];
			}

		}
		return str;
	}

	boolean f_STRING(String field, String[] vv, boolean IN) {
		String valueStr = toString(vv);
		logger.error("**************f_STRING ************object_v" + valueStr + ",field=" + field + ",in=" + IN);

		for (int i = 0; i < vv.length; i++) {
			if (field.equals(vv[i])) {
				return IN;
			}
		}
		return !IN;
	}

	private Object convert_old(int id, String v) {
		if (id == 5 || id == 98 || id == 100) { // 濮濓絽鍨悰銊ㄦ彧瀵骏绱潀閸掑棗澹�
			String[] vv = getValuesReg(v, "\\|");
			Object a = vv;
			return a;
		}
		if (id == 13 || id == 31 || id == 101015 || id == 36 || id == 33 || id == 101108 || id == 37 || id == 101103
				|| id == 101104 || id == 101016 || id == 24 || id == 52 || id == 101109 || id == 93
				|| id == 101102) { // 鐎涙顑佹稉鏌ユ肠閸氬牞绱�
									// ,閸掑棗澹�
			String[] vv = getValues(v, ",");
			Object a = vv;
			return a;
		}
		Integer[] vv = getValuesInt(v, ",");
		Object a = vv;
		return a;
	}

	// private void addmap(int id_def, String s) {
	// if (!keysleng.contains(s.length())) {
	// keysleng.add(s.length());
	// }
	// if (keys.containsKey(s)) {
	// return;
	// }
	//
	// keys.put(s, curri);
	// curri++;
	//
	// keyss[curri] = s.toCharArray();
	// if (s.indexOf(".*") > -1) {
	// keyss_patch[curri_patch] = curri;
	// curri_patch++;
	// keyss_patch[0] = curri_patch;
	// }
	// int firtchar = s.charAt(0);
	// b[firtchar] = false;
	// bb[firtchar][0] = bb[firtchar][0] + 1;
	// bb[firtchar][(int) bb[firtchar][0]] = curri;
	// }

	// private void addmap_98(int id_def, String s) {
	// try {
	// if (!keysleng_98.contains(s.length())) {
	// keysleng_98.add(s.length());
	// }
	// if (keys_98.containsKey(s)) {
	// return;
	// }
	//
	// keys_98.put(s, curri_98);
	// curri_98++;
	//
	// keyss_98[curri_98] = s.toCharArray();
	// if (s.indexOf(".*") > -1) {
	// keyss_98_patch[curri_98_patch] = curri_98;
	// curri_98_patch++;
	// keyss_98_patch[0] = curri_98_patch;
	// }
	// int firtchar = s.charAt(0);
	// b_98[firtchar] = false;
	// bb_98[firtchar][0] = bb_98[firtchar][0] + 1;
	// bb_98[firtchar][(int) bb_98[firtchar][0]] = curri_98;
	// } catch (Exception e) {
	// logger.error("addmap_98 Exception.id_def:" + id_def + ",s:" + s);
	// }
	// }

	public static Object convert(int id, String targetname, int def_type, String v) {
		// 8007,"sheet_security_level",7,"10-"
		// 2013.12.25 大小写不敏感比较

		v = v.toLowerCase();
		String[] valueArray = null;
		if (def_type == 3) {
			valueArray = v.split("\\|");
		} else {
			valueArray = v.split(",");
		}
		// if(def_type == 2) {
		// if(valueArray.length>=2) {
		// String temp =valueArray[0]+"-"+valueArray[1];
		// valueArray = temp.split(",");
		// }
		// }

		int valueArrayLength = 0;
		if (valueArray != null) {
			valueArrayLength = valueArray.length;
		}

		List<String> valueList = new ArrayList<String>();
		String tempValue = "";
		String[] tempValueArray;
		String temp_temp_value = "";
		for (int i = 0; i < valueArrayLength; i++) {

			tempValue = valueArray[i];
			if (tempValue == null) {
				continue;
			}
			tempValue = tempValue.trim();
			if (tempValue.isEmpty()) {
				continue;
			}
			if (def_type == 3) {
				tempValueArray = tempValue.split("\\|");
				int tempValueArrayLength = 0;
				if (tempValueArray != null) {
					tempValueArrayLength = tempValueArray.length;
				}
				for (int tempIndex = 0; tempIndex < tempValueArrayLength; tempIndex++) {
					temp_temp_value = tempValueArray[tempIndex];
					if (temp_temp_value == null) {
						continue;
					}
					temp_temp_value = temp_temp_value.trim();
					if (temp_temp_value.isEmpty()) {
						continue;
					}

					valueList.add(temp_temp_value);
				}
			} else {
				valueList.add(tempValue);
			}

		}
		int valueListSize = valueList.size();

		String[] returnValueArray = new String[valueListSize];

		returnValueArray = valueList.toArray(returnValueArray);

		// for (int i = 0; i < returnValueArray.length; i++) {
		// if (targetname.equals("alarm_text")) {// id==5){
		// addmap(id, returnValueArray[i]);
		// } else {
		// if (targetname.equals("standard_alarm_id"))// id==98)
		// addmap_98(id, returnValueArray[i]);
		// }
		// }
		//
		// System.out.println(returnValueArray);
		if ("int_id".equals(targetname)) {
			StringBuilder sb = new StringBuilder();
			if (returnValueArray != null) {
				for (String str : returnValueArray) {
					sb.append(str).append(",");
				}
			}

		}
		return returnValueArray;
		// }
		/*
		 * Integer[] vv = getValuesInt(v,","); Object a = vv; return a;
		 */
	}

	public static void main(String[] args) {
		convert(3, "event_time", 2, "633,960");
	}

	private String[] getValuesReg(String value, String string) {
		// TODO Auto-generated method stub
		if (value.indexOf("|") == 0) {
			value = value.substring(1);
		}
		/*
		 * if(value.lastIndexOf(regex)==(value.length() - regex.length())){
		 * value = value.substring(0,value.length() - regex.length()); }
		 * split閼冲�鍤滈崝銊ョ殺閺堬拷鎮楅惃鍕敄鐎涙顑佹稉鎻掑箵閹猴拷
		 */
		String[] vv = value.split("\\|");
		return vv;
	}

	String[] getValues(String value, String regex) {

		if (value.indexOf(regex) == 0) {
			value = value.substring(regex.length());
		}
		/*
		 * if(value.lastIndexOf(regex)==(value.length() - regex.length())){
		 * value = value.substring(0,value.length() - regex.length()); }
		 * split閼冲�鍤滈崝銊ョ殺閺堬拷鎮楅惃鍕敄鐎涙顑佹稉鎻掑箵閹猴拷
		 */
		String[] vv = value.split(regex);
		return vv;
	}

	Integer[] getValuesInt(String value, String regex) {
		if (value.indexOf(regex) == 0) {
			value = value.substring(regex.length());
		}
		if (value.lastIndexOf(regex) == (value.length() - regex.length())) {
			value = value.substring(0, value.length() - regex.length());
		}

		String[] vv = value.split(regex);
		Integer[] vvi = new Integer[vv.length];
		for (int i = 0; i < vvi.length; i++) {
			vvi[i] = Integer.parseInt(vv[i]);
		}
		return vvi;
	}

	/*
	 * public void set_f(int idef_id, Integer ivalue) { String value =
	 * String.valueOf(ivalue); set_f(idef_id, value); }
	 * 
	 * public void set_f(int idef_id, Long ivalue) { String value =
	 * String.valueOf(ivalue); set_f(idef_id, value); }
	 * 
	 * public void set_f(int idef_id, String value) { if (value == null) { value
	 * = ""; } String key = value; int iPos = op8.getPos(idef_id);
	 * 
	 * if (idef_id == 8) { Iterator it = map1[iPos].keySet().iterator(); for (;
	 * it.hasNext();) { key = (String) it.next(); boolean flag =
	 * f_8(Long.parseLong(key), Long.parseLong(value), true); if (flag) {
	 * Vector<Integer> conditionPos = this.map1[iPos].get(key); for (int i = 0;
	 * i < conditionPos.size(); i++) { c[conditionPos.get(i)] = true; } } } }
	 * else if (idef_id == 5 || idef_id == 100 || idef_id == 98) { Iterator it =
	 * map1[iPos].keySet().iterator(); for (; it.hasNext();) { key = (String)
	 * it.next(); if (value.indexOf(key) > -1) { Vector<Integer> conditionPos =
	 * this.map1[iPos].get(key); for (int i = 0; i < conditionPos.size(); i++) {
	 * c[conditionPos.get(i)] = true; } } } } else { if
	 * (map1[iPos].containsKey(key)) { Vector<Integer> conditionPos =
	 * this.map1[iPos].get(key); for (int i = 0; i < conditionPos.size(); i++) {
	 * int iSUBC = conditionPos.get(i); c[iSUBC] = true; } } } }
	 */
	boolean f_Vector(long field, HashMap<Long, Integer> value, boolean IN) {
		// for(int i=0;i<value.size();i++){
		if (value.containsKey(field)) {
			return IN;
		}
		// }
		return !IN;
	}

	boolean f_Vector(long field, HashSet<Long> value, boolean IN) {
		// for(int i=0;i<value.size();i++){
		if (value.contains(field)) {
			return IN;
		}
		// }
		return !IN;
	}

	// 闂囷拷顪呯粔锟藉煃閹存劒绮爐ic_negrp_list鐞涖劏顕伴崣鏍祲鎼存梻娈戦崐纭风礉娴犲氦锟介崑姝焫its閹垮秳缍�
	public int ne_group;

	boolean f_8_OLD(Integer[] value, boolean IN) {
		return f_INT(ne_group, value, IN);
	}

	boolean f_8(Long negroupid, Long value, boolean IN) {
		HashSet<Long> values = TIC_NEGRP_LISTMgr.getIntidSetByNeGroupId(negroupid);
		if (values == null) {
			return !IN;
		}
		boolean flag = f_Vector(value, values, IN);
		if (flag == IN)
			return IN;
		return !IN;
	}

	boolean f_8(Integer[] value, boolean IN) {
		for (int i = 0; i < value.length; i++) {
			Integer negroupid = value[i];
			HashSet<Long> values = TIC_NEGRP_LISTMgr.getIntidSetByNeGroupId(negroupid);
			boolean flag = f_Vector(ne_group, values, IN);
			if (flag == IN)
				return IN;
		}
		return !IN;
	}

	// 濮濓絽鍨悰銊ㄦ彧瀵拷
	boolean f3_regula_expression(String field, String[] value, boolean IN) {

		for (int i = 0; i < value.length; i++) {
			if (value[i].length() < 1)
				continue;
			if (field.indexOf(value[i]) > -1) {
				// if(field.equals(value[i])){
				return IN;
			}
		}
		return !IN;
	}

	// 闁褰跨�妤冾儊娑擄拷
	boolean f4_STRING(String field, String[] value, boolean IN) {
		return f_STRING(field, value, IN);
	}

	// 閹碘晛鐫嶇�妯哄亶缁鐎�
	boolean f5_STRING(String field, String[] value, boolean IN) {
		return this.f_STRING(field, value, IN);
	}

	// 閹碘晛鐫嶇�妯哄亶缁鐎�
	boolean f6_INT(int field, Integer[] value, boolean IN) {
		if (value[0] == field) {
			return IN;
		}
		return !IN;
	}

	// 鐢箒瀵栭崶瀵告畱闁褰挎稉锟�
	boolean f7_INT(int field, Integer[] value, boolean IN) {
		return this.f_INT(field, value, IN);
	}

	// 閺傚洦婀伴惃鍕拷閸欒渹瑕�
	boolean f8_STRING(String field, String[] value, boolean IN) {
		return f_STRING(field, value, IN);
	}

	HashMap<String, Boolean> map13 = new HashMap<String, Boolean>();

	public E e;

	public AlgorithmBase<E> algorithmBase = null;

	public void setAlgorithm(AlgorithmBase<E> algorithmBase) {
		// TODO Auto-generated method stub
		this.algorithmBase = algorithmBase;

	}

	public void init(E e) {
		this.e = e;
		// this.op8 = op;
		// this.op8.TIC_NEGRP_LISTMgr = TIC_NEGRP_LISTMgr;
		// initcache();
	}

	public void initvalue(String alarmId, E e) {
		for (int i = 0; i < c.length; i++) {
			c[i] = false;
		}
		algorithmBase.rb = this;
		// op8.map1 = map1;
		algorithmBase.initalarmvalue(alarmId, e);
		// c = op8.c;
		// map1 = op8.map1;
	}

	// public abstract void pre4();
	public void pre3() {
		/*
		 * initdatalen(); int iTEXTSEARCHKEYMAXCOUNT = 10000;//keys.size(); int
		 * iTEXTSEARCHKEYMAXSAMECOUNT = 200;keys.put(key, value) initdatalen(int
		 * iFieldCount,int iConditionCount,int iFilterCount,int
		 * iTEXTSEARCHKEYMAXCOUNT,int iTEXTSEARCHKEYMAXSAMECOUNT,int
		 * iALARMIDSEARCHKEYMAXSAMECOUN,int iALARMIDSEARCHKEYMAXCOUNT);
		 */
		/*
		 * pre3_1(5,op8.getPos(5)); pre3_1(100,op8.getPos(100));
		 * pre3_1(98,op8.getPos(98));
		 */
		algorithmBase.rb = this;
		algorithmBase.pre4();
		map12map0();
	}

	private void map12map0() {
		// TODO Auto-generated method stub
		for (int i = 0; i < map1.length; i++) {
			if (map1[i] != null) {
				Iterator<String> keys = map1[i].keySet().iterator();
				while (keys.hasNext()) {
					String key = keys.next();
					Vector<Integer> vv = map1[i].get(key);
					Integer[] cc = vv.toArray(new Integer[0]);
					map0[i].put(key, cc);
				}
			}
		}
	}

	public void pre3_1(int idef_id, int iPos) {
		// int iPos = AlgorithmBase.getPos(idef_id);
		String[] aa = map1[iPos].keySet().toArray(new String[0]);// .toArray(<String>[]
																	// aa);
		map2[iPos] = aa;
	}

	// abstract public void initalarmvalue();

	public void add(String filterids) {
		String[] ids = filterids.split(",");
		for (int i = 0; i < ids.length; i++) {
			resultlistadd(Integer.parseInt(ids[i]));
		}
	}

	public void add(int filterid) {
		resultlistadd(filterid);
	}

	public void add(int filterid1, int filterid2) {
		resultlistadd(filterid1);
		resultlistadd(filterid2);
	}

	public void add(int filterid1, int filterid2, int filterid3) {
		resultlistadd(filterid1);
		resultlistadd(filterid2);
		resultlistadd(filterid3);
	}

	public void add(int filterid1, int filterid2, int filterid3, int filterid4) {
		resultlistadd(filterid1);
		resultlistadd(filterid2);
		resultlistadd(filterid3);
		resultlistadd(filterid4);
	}

	public void add(int filterid1, int filterid2, int filterid3, int filterid4, int filterid5) {
		resultlistadd(filterid1);
		resultlistadd(filterid2);
		resultlistadd(filterid3);
		resultlistadd(filterid4);
		resultlistadd(filterid5);
	}

	public void add(int filterid1, int filterid2, int filterid3, int filterid4, int filterid5, int filterid6) {
		resultlistadd(filterid1);
		resultlistadd(filterid2);
		resultlistadd(filterid3);
		resultlistadd(filterid4);
		resultlistadd(filterid5);
		resultlistadd(filterid6);
	}

	public void add(int filterid1, int filterid2, int filterid3, int filterid4, int filterid5, int filterid6,
			int filterid7) {
		resultlistadd(filterid1);
		resultlistadd(filterid2);
		resultlistadd(filterid3);
		resultlistadd(filterid4);
		resultlistadd(filterid5);
		resultlistadd(filterid6);
		resultlistadd(filterid7);
	}

	public void add(int filterid1, int filterid2, int filterid3, int filterid4, int filterid5, int filterid6,
			int filterid7, int filterid8) {
		resultlistadd(filterid1);
		resultlistadd(filterid2);
		resultlistadd(filterid3);
		resultlistadd(filterid4);
		resultlistadd(filterid5);
		resultlistadd(filterid6);
		resultlistadd(filterid7);
		resultlistadd(filterid8);
	}

	public void add(int filterid1, int filterid2, int filterid3, int filterid4, int filterid5, int filterid6,
			int filterid7, int filterid8, int filterid9) {
		resultlistadd(filterid1);
		resultlistadd(filterid2);
		resultlistadd(filterid3);
		resultlistadd(filterid4);
		resultlistadd(filterid5);
		resultlistadd(filterid6);
		resultlistadd(filterid7);
		resultlistadd(filterid8);
		resultlistadd(filterid9);
	}

	public void add(int filterid1, int filterid2, int filterid3, int filterid4, int filterid5, int filterid6,
			int filterid7, int filterid8, int filterid9, int filterid10) {
		resultlistadd(filterid1);
		resultlistadd(filterid2);
		resultlistadd(filterid3);
		resultlistadd(filterid4);
		resultlistadd(filterid5);
		resultlistadd(filterid6);
		resultlistadd(filterid7);
		resultlistadd(filterid8);
		resultlistadd(filterid9);
		resultlistadd(filterid10);
	}

	public void add(int filterid1, int filterid2, int filterid3, int filterid4, int filterid5, int filterid6,
			int filterid7, int filterid8, int filterid9, int filterid10, int filterid11) {
		resultlistadd(filterid1);
		resultlistadd(filterid2);
		resultlistadd(filterid3);
		resultlistadd(filterid4);
		resultlistadd(filterid5);
		resultlistadd(filterid6);
		resultlistadd(filterid7);
		resultlistadd(filterid8);
		resultlistadd(filterid9);
		resultlistadd(filterid10);
		resultlistadd(filterid11);
	}

	public void add(int filterid1, int filterid2, int filterid3, int filterid4, int filterid5, int filterid6,
			int filterid7, int filterid8, int filterid9, int filterid10, int filterid11,
			int filterid12) {
		resultlistadd(filterid1);
		resultlistadd(filterid2);
		resultlistadd(filterid3);
		resultlistadd(filterid4);
		resultlistadd(filterid5);
		resultlistadd(filterid6);
		resultlistadd(filterid7);
		resultlistadd(filterid8);
		resultlistadd(filterid9);
		resultlistadd(filterid10);
		resultlistadd(filterid11);
		resultlistadd(filterid12);
	}

	public void add(int filterid1, int filterid2, int filterid3, int filterid4, int filterid5, int filterid6,
			int filterid7, int filterid8, int filterid9, int filterid10, int filterid11,
			int filterid12, int filterid13) {
		resultlistadd(filterid1);
		resultlistadd(filterid2);
		resultlistadd(filterid3);
		resultlistadd(filterid4);
		resultlistadd(filterid5);
		resultlistadd(filterid6);
		resultlistadd(filterid7);
		resultlistadd(filterid8);
		resultlistadd(filterid9);
		resultlistadd(filterid10);
		resultlistadd(filterid11);
		resultlistadd(filterid12);
		resultlistadd(filterid13);
	}

	public void add(int filterid1, int filterid2, int filterid3, int filterid4, int filterid5, int filterid6,
			int filterid7, int filterid8, int filterid9, int filterid10, int filterid11,
			int filterid12, int filterid13, int filterid14) {
		resultlistadd(filterid1);
		resultlistadd(filterid2);
		resultlistadd(filterid3);
		resultlistadd(filterid4);
		resultlistadd(filterid5);
		resultlistadd(filterid6);
		resultlistadd(filterid7);
		resultlistadd(filterid8);
		resultlistadd(filterid9);
		resultlistadd(filterid10);
		resultlistadd(filterid11);
		resultlistadd(filterid12);
		resultlistadd(filterid13);
		resultlistadd(filterid14);
	}

	public void add(int filterid1, int filterid2, int filterid3, int filterid4, int filterid5, int filterid6,
			int filterid7, int filterid8, int filterid9, int filterid10, int filterid11,
			int filterid12, int filterid13, int filterid14, int filterid15) {
		resultlistadd(filterid1);
		resultlistadd(filterid2);
		resultlistadd(filterid3);
		resultlistadd(filterid4);
		resultlistadd(filterid5);
		resultlistadd(filterid6);
		resultlistadd(filterid7);
		resultlistadd(filterid8);
		resultlistadd(filterid9);
		resultlistadd(filterid10);
		resultlistadd(filterid11);
		resultlistadd(filterid12);
		resultlistadd(filterid13);
		resultlistadd(filterid14);
		resultlistadd(filterid15);
	}

	public void add(int filterid1, int filterid2, int filterid3, int filterid4, int filterid5, int filterid6,
			int filterid7, int filterid8, int filterid9, int filterid10, int filterid11,
			int filterid12, int filterid13, int filterid14, int filterid15, int filterid16) {
		resultlistadd(filterid1);
		resultlistadd(filterid2);
		resultlistadd(filterid3);
		resultlistadd(filterid4);
		resultlistadd(filterid5);
		resultlistadd(filterid6);
		resultlistadd(filterid7);
		resultlistadd(filterid8);
		resultlistadd(filterid9);
		resultlistadd(filterid10);
		resultlistadd(filterid11);
		resultlistadd(filterid12);
		resultlistadd(filterid13);
		resultlistadd(filterid14);
		resultlistadd(filterid15);
		resultlistadd(filterid16);
	}

	public void add(int filterid1, int filterid2, int filterid3, int filterid4, int filterid5, int filterid6,
			int filterid7, int filterid8, int filterid9, int filterid10, int filterid11,
			int filterid12, int filterid13, int filterid14, int filterid15, int filterid16, int filterid17) {
		resultlistadd(filterid1);
		resultlistadd(filterid2);
		resultlistadd(filterid3);
		resultlistadd(filterid4);
		resultlistadd(filterid5);
		resultlistadd(filterid6);
		resultlistadd(filterid7);
		resultlistadd(filterid8);
		resultlistadd(filterid9);
		resultlistadd(filterid10);
		resultlistadd(filterid11);
		resultlistadd(filterid12);
		resultlistadd(filterid13);
		resultlistadd(filterid14);
		resultlistadd(filterid15);
		resultlistadd(filterid16);
		resultlistadd(filterid17);
	}

	public void add(int filterid1, int filterid2, int filterid3, int filterid4, int filterid5, int filterid6,
			int filterid7, int filterid8, int filterid9, int filterid10, int filterid11,
			int filterid12, int filterid13, int filterid14, int filterid15, int filterid16, int filterid17,
			int filterid18) {
		resultlistadd(filterid1);
		resultlistadd(filterid2);
		resultlistadd(filterid3);
		resultlistadd(filterid4);
		resultlistadd(filterid5);
		resultlistadd(filterid6);
		resultlistadd(filterid7);
		resultlistadd(filterid8);
		resultlistadd(filterid9);
		resultlistadd(filterid10);
		resultlistadd(filterid11);
		resultlistadd(filterid12);
		resultlistadd(filterid13);
		resultlistadd(filterid14);
		resultlistadd(filterid15);
		resultlistadd(filterid16);
		resultlistadd(filterid17);
		resultlistadd(filterid18);
	}

	public void add(int filterid1, int filterid2, int filterid3, int filterid4, int filterid5, int filterid6,
			int filterid7, int filterid8, int filterid9, int filterid10, int filterid11,
			int filterid12, int filterid13, int filterid14, int filterid15, int filterid16, int filterid17,
			int filterid18, int filterid19) {
		resultlistadd(filterid1);
		resultlistadd(filterid2);
		resultlistadd(filterid3);
		resultlistadd(filterid4);
		resultlistadd(filterid5);
		resultlistadd(filterid6);
		resultlistadd(filterid7);
		resultlistadd(filterid8);
		resultlistadd(filterid9);
		resultlistadd(filterid10);
		resultlistadd(filterid11);
		resultlistadd(filterid12);
		resultlistadd(filterid13);
		resultlistadd(filterid14);
		resultlistadd(filterid15);
		resultlistadd(filterid16);
		resultlistadd(filterid17);
		resultlistadd(filterid18);
		resultlistadd(filterid19);
	}

	public void add(int filterid1, int filterid2, int filterid3, int filterid4, int filterid5, int filterid6,
			int filterid7, int filterid8, int filterid9, int filterid10, int filterid11,
			int filterid12, int filterid13, int filterid14, int filterid15, int filterid16, int filterid17,
			int filterid18, int filterid19, int filterid20) {
		resultlistadd(filterid1);
		resultlistadd(filterid2);
		resultlistadd(filterid3);
		resultlistadd(filterid4);
		resultlistadd(filterid5);
		resultlistadd(filterid6);
		resultlistadd(filterid7);
		resultlistadd(filterid8);
		resultlistadd(filterid9);
		resultlistadd(filterid10);
		resultlistadd(filterid11);
		resultlistadd(filterid12);
		resultlistadd(filterid13);
		resultlistadd(filterid14);
		resultlistadd(filterid15);
		resultlistadd(filterid16);
		resultlistadd(filterid17);
		resultlistadd(filterid18);
		resultlistadd(filterid19);
		resultlistadd(filterid20);
	}

	public void add(int filterid1, int filterid2, int filterid3, int filterid4, int filterid5, int filterid6,
			int filterid7, int filterid8, int filterid9, int filterid10, int filterid11,
			int filterid12, int filterid13, int filterid14, int filterid15, int filterid16, int filterid17,
			int filterid18, int filterid19, int filterid20, int filterid21) {
		resultlistadd(filterid1);
		resultlistadd(filterid2);
		resultlistadd(filterid3);
		resultlistadd(filterid4);
		resultlistadd(filterid5);
		resultlistadd(filterid6);
		resultlistadd(filterid7);
		resultlistadd(filterid8);
		resultlistadd(filterid9);
		resultlistadd(filterid10);
		resultlistadd(filterid11);
		resultlistadd(filterid12);
		resultlistadd(filterid13);
		resultlistadd(filterid14);
		resultlistadd(filterid15);
		resultlistadd(filterid16);
		resultlistadd(filterid17);
		resultlistadd(filterid18);
		resultlistadd(filterid19);
		resultlistadd(filterid20);
		resultlistadd(filterid21);
	}

	public void add(int filterid1, int filterid2, int filterid3, int filterid4, int filterid5, int filterid6,
			int filterid7, int filterid8, int filterid9, int filterid10, int filterid11,
			int filterid12, int filterid13, int filterid14, int filterid15, int filterid16, int filterid17,
			int filterid18, int filterid19, int filterid20, int filterid21, int filterid22) {
		resultlistadd(filterid1);
		resultlistadd(filterid2);
		resultlistadd(filterid3);
		resultlistadd(filterid4);
		resultlistadd(filterid5);
		resultlistadd(filterid6);
		resultlistadd(filterid7);
		resultlistadd(filterid8);
		resultlistadd(filterid9);
		resultlistadd(filterid10);
		resultlistadd(filterid11);
		resultlistadd(filterid12);
		resultlistadd(filterid13);
		resultlistadd(filterid14);
		resultlistadd(filterid15);
		resultlistadd(filterid16);
		resultlistadd(filterid17);
		resultlistadd(filterid18);
		resultlistadd(filterid19);
		resultlistadd(filterid20);
		resultlistadd(filterid21);
		resultlistadd(filterid22);
	}

	public void add(int filterid1, int filterid2, int filterid3, int filterid4, int filterid5, int filterid6,
			int filterid7, int filterid8, int filterid9, int filterid10, int filterid11,
			int filterid12, int filterid13, int filterid14, int filterid15, int filterid16, int filterid17,
			int filterid18, int filterid19, int filterid20, int filterid21, int filterid22,
			int filterid23) {
		resultlistadd(filterid1);
		resultlistadd(filterid2);
		resultlistadd(filterid3);
		resultlistadd(filterid4);
		resultlistadd(filterid5);
		resultlistadd(filterid6);
		resultlistadd(filterid7);
		resultlistadd(filterid8);
		resultlistadd(filterid9);
		resultlistadd(filterid10);
		resultlistadd(filterid11);
		resultlistadd(filterid12);
		resultlistadd(filterid13);
		resultlistadd(filterid14);
		resultlistadd(filterid15);
		resultlistadd(filterid16);
		resultlistadd(filterid17);
		resultlistadd(filterid18);
		resultlistadd(filterid19);
		resultlistadd(filterid20);
		resultlistadd(filterid21);
		resultlistadd(filterid22);
		resultlistadd(filterid23);
	}

	int curr = 0;

	private void resultlistadd(int filterid1) {
		// TODO Auto-generated method stub
		resultlist1[curr] = filterid1;
		curr++;
		resultlist.add(filterid1);
	}

	public void resultlistremoveAllElements() {
		// TODO Auto-generated method stub
		curr = 0;
		resultlist.removeAllElements();
	}

	public boolean doFilterByFilterOwner(Map<String, Object> args, Integer user_level, Integer regionId,
			Integer user_id, String user_name) {
		if (user_level == null) {
			return true;
		}

		if (user_level < 9) {// 超级用户
			return true;
		} else if (user_level == 9) {
			// 集团用户
			return true;
		} else if (user_level == 10) {
			// 省级用户

			Object province_id = args.get("province_id");
			if (province_id == null) {
				return false;
			}

			Integer int_province_id = null;
			if (province_id instanceof Integer) {
				int_province_id = (Integer) province_id;
			}
			if (int_province_id == null) {
				return false;
			}

			if (int_province_id.equals(regionId)) {

				return true;
			} else {
				return false;
			}
		} else if (user_level == 11) {
			// 地市用户
			Object city_id = args.get("city_id");
			if (city_id == null) {
				return false;
			}

			Integer int_city_id = null;
			if (city_id instanceof Integer) {
				int_city_id = (Integer) city_id;
			}
			if (int_city_id == null) {
				return false;
			}

			if (int_city_id.equals(regionId)) {

				return true;
			} else {
				return false;
			}

		} else if (user_level == 12) {
			// 区县用户
			Object region_id = args.get("region_id");
			if (region_id == null) {
				return false;
			}

			Integer int_region_id = null;
			if (region_id instanceof Integer) {
				int_region_id = (Integer) region_id;
			}
			if (int_region_id == null) {
				return false;
			}

			if (int_region_id.equals(regionId)) {

				return true;
			} else {
				return false;
			}
		} else {
			logger.error("doFilterByFilterOwner ,unknow userLevel.user_level:" + user_level + ",user_id:" + user_id
					+ ",user_name:" + user_name + ",regionId:" + regionId);
		}

		return false;

	}

}
