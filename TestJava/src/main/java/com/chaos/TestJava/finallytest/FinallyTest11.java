package com.chaos.TestJava.finallytest;

//finally语句在return语句执行之后return返回之前执行的。
public class FinallyTest11 {

	public static void main(String[] args) {

		System.out.println(test11());
	}

	public static String test11() {
		try {
			System.out.println("try block");

			return test12();
		} finally {
			System.out.println("finally block");
		}
	}

	public static String test12() {
		System.out.println("return statement");

		return "after return";
	}

}