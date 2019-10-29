package com.chaos.TestJava.classLoader;

public class TestMyClassLoader {
	public static void main(String[] args) throws Exception {
		MyClassLoader mcl = new MyClassLoader();
		// Class<?> c1 = Class.forName("com.chaos.TestJava.classLoader.Person",
		// true, mcl);
		Class<?> c1 = Class.forName("com.chaos.TestJava.classLoader.Person");
		Object obj = c1.newInstance();
		System.out.println(obj);
		System.out.println(obj.getClass().getClassLoader());
	}
}