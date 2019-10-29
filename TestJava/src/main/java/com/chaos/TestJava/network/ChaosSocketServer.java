package com.chaos.TestJava.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChaosSocketServer {
	private static BufferedReader br = null;
	private static PrintWriter pw = null;
	private static ServerSocket ss;
	private static Socket s;
	static Scanner scanner = new Scanner(System.in);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ss = new ServerSocket(5500);
			s = ss.accept();// 阻塞方法
			System.out.println("connect success " + s.getRemoteSocketAddress());
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			// pw = new PrintWriter(new
			// OutputStreamWriter(s.getOutputStream()));
			while (true) {
				String string = br.readLine();
				System.out.println("Server ：" + string);
				Thread.sleep(1000);
				// System.out.println("Server端请输入：");
				// String str = scanner.next();
				// pw.println(str);
				// pw.flush();
				// if (str.equals("exit")) {
				// break;
				// }
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			pw.close();
			br.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
