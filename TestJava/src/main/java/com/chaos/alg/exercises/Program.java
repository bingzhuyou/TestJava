package com.chaos.alg.exercises;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Program {
	public static void swap(int x, int y) {
		int temp = x;
		x = y;
		y = temp;
		System.out.println("ax = " + x + ", bx = " + y);
	}

	public static void swapI(Integer x, Integer y) {
		Integer temp = x;
		x = y;
		y = temp;
		System.out.println("ax = " + x + ", bx = " + y);
	}

	public static int testFinally() {
		int b = 20;

		try {
			System.out.println("try block");

			return b += 80;
		} catch (Exception e) {

			System.out.println("catch block");
		} finally {

			System.out.println("finally block");

			if (b > 25) {
				System.out.println("b>25, b = " + b);
			}
		}

		return b;
	}

	public static int testFinallyEn() {
		try {
			System.out.println("testFinallyEn try block");

			return testFinally();
		} finally {
			System.out.println("testFinallyEn finally block");
		}
	}

	public static void testConcurrentModMT() {
		List<String> myList = new ArrayList<String>();

		myList.add("1");
		myList.add("2");
		myList.add("3");
		myList.add("4");
		myList.add("5");

		new Thread(new Runnable() {

			@Override
			public void run() {
				synchronized (myList) {
					for (String string : myList) {
						System.out.println("遍历集合 value = " + string);

						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				synchronized (myList) {
					for (Iterator<String> it = myList.iterator(); it.hasNext();) {
						String value = it.next();

						System.out.println("删除元素 value = " + value);

						if (value.equals("3")) {
							it.remove();
						}

						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}).start();
	}

	public static void testRefArg() {
		int a = 5;
		int b = 10;
		System.out.println("a = " + a + ", b = " + b);
		swap(a, b);
		System.out.println("a = " + a + ", b = " + b);

		Integer aI = 5;
		Integer bI = 10;
		System.out.println("a = " + aI + ", b = " + bI);
		swapI(aI, bI);
		System.out.println("a = " + aI + ", b = " + bI);
	}

	public static void testStrEq() {
		String s1 = "Programming";
		String s2 = new String("Programming");
		String s3 = "Program";
		String s4 = "ming";
		String s5 = "Program" + "ming";
		String s6 = s3 + s4;
		System.out.println(s1 == s2);
		System.out.println(s1 == s5);
		System.out.println(s1 == s6);
		System.out.println(s1 == s6.intern());
		System.out.println(s2 == s2.intern());
	}

	public static void testConcurrentMod() {
		List<String> myList = new ArrayList<String>();

		myList.add("1");
		myList.add("2");
		myList.add("3");
		myList.add("4");
		myList.add("5");

		Iterator<String> it = myList.iterator();
		while (it.hasNext()) {
			String value = it.next();
			if (value.equals("3")) {
				it.remove(); // ok
				// myList.remove(value); // error
			}
		}

		for (it = myList.iterator(); it.hasNext();) {
			String value = it.next();
			if (value.equals("4")) {
				// myList.remove(value); // error
				it.remove(); // ok
			}
		}

		for (String value : myList) {
			System.out.println("List Value:" + value);
			if (value.equals("5")) {
				myList.remove(value); // error
			}
			System.out.println("List Value222:" + value);
		}
	}

	public static void testFinallyFunc() {
		testFinallyEn();
		try {
			Thread.sleep(1000000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SigaltonEx sig = SigaltonEx.newInstance();
		sig.printObj();

		try {
			SyncThread sThread = SyncThread.class.newInstance();
			System.out.println(sThread.getName());

			SyncThread sT = (SyncThread) (Class.forName("com.chaos.TestJava.exercises.SyncThread").newInstance());
			System.out.println(sT.getName());
			SigaltonEx sig2 = SigaltonEx.class.newInstance();
			sig2.printObj();
			Method m = SigaltonEx.class.getMethod("getName");
			m.invoke(sig2);

		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
