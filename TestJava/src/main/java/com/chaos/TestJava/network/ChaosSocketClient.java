package com.chaos.TestJava.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ChaosSocketClient {
	private static PrintWriter pw = null;
	private static BufferedReader br = null;
	private static Socket s;
	static Scanner scanner = new Scanner(System.in);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Socket s = new Socket(InetAddress.getLocalHost(), 5500);
			pw = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
			long i = 0;
			while (true) {
				String str = "hello world " + i;
				pw.println(str);
				pw.flush();
				if (i % 100 == 0) {
					System.out.println("write 100 package " + i);
				}
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			br.close();
			pw.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}