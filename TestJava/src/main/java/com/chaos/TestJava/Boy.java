package com.chaos.TestJava;

import java.math.BigDecimal;

class Boy implements Cloneable {

	String name;
	int age = 10;
	String gender;
	// 构造代码块,给所有对象进行初始化。
	// {
	// System.out.println("哭。。。");
	// }

	Boy() {
		System.out.println("无参构造 " + age);
	}

	Boy(String n, int a, String g) {
		System.out.println("有参构造  1  " + age);
		name = n;
		age = a;
		gender = g;
		System.out.println("有参构造");
	}

	void run() {
		System.out.println("跑...");
	}

	public static void main(String[] args) {
		double d = 0.3;
		int k = 8;
		long l = 2;

		BigDecimal bd = new BigDecimal("0.88");
		BigDecimal bd2 = new BigDecimal("0.88");

		System.out.println(bd);
		System.out.println(bd.add(bd2));

		System.out.println();
		Boy b = new Boy();

		Boy b2 = new Boy("jack", 1, "男");

	}

	public Boy clone() {
		return this;
	}
}