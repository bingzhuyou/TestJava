package com.chaos.TestJava;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

import org.junit.Test;

public class GCRefsTest {

	@Test
	public void test_gc1() {
		// 在heap中创建内容为"wohenhao"的对象，并建立a到该对象的强引用，此时该对象时强可及
		String a = new String("wohenhao");
		// 对heap中的对象建立软引用，此时heap中的对象仍然是强可及
		SoftReference<?> softReference = new SoftReference<String>(a);
		// 对heap中的对象建立弱引用，此时heap中的对象仍然是强可及
		WeakReference<?> weakReference = new WeakReference<String>(a);
		System.out.println("强引用：" + a + "\n软引用" + softReference.get() + "\n弱引用" + weakReference.get() + "\n");
		// heap中的对象从强可及到软可及
		a = null;
		System.out.println("强引用：" + a + "\n软引用" + softReference.get() + "\n弱引用" + weakReference.get() + "\n");
		softReference.clear();// heap中对象从软可及变成弱可及,此时调用System.gc()，
		System.gc();
		System.out.println("强引用：" + a + "\n软引用" + softReference.get() + "\n弱引用" + weakReference.get() + "\n");
	}

	@Test
	public void test_gc2() {
		// 在heap中创建内容为"wohenhao"的对象，并建立a到该对象的强引用，此时该对象时强可及
		String a = new String("wohenhao");
		// 对heap中的对象建立软引用，此时heap中的对象仍然是强可及
		SoftReference<?> softReference = new SoftReference<String>(a);
		// 对heap中的对象建立弱引用，此时heap中的对象仍然是强可及
		WeakReference<?> weakReference = new WeakReference<String>(a);
		System.out.println("强引用：" + a + "\n软引用" + softReference.get() + "\n弱引用" + weakReference.get() + "\n");
		a = null;// heap中的对象从强可及到软可及
		System.out.println("强引用：" + a + "\n软引用" + softReference.get() + "\n弱引用" + weakReference.get() + "\n");
		System.gc();
		System.out.println("强引用：" + a + "\n软引用" + softReference.get() + "\n弱引用" + weakReference.get() + "\n");
	}

	@Test
	public void test_gc3() {
		// 在heap中创建内容为"wohenhao"的对象，并建立a到该对象的强引用，此时该对象时强可及
		String a = new String("wohenhao");
		// 对heap中的对象建立软引用，此时heap中的对象仍然是强可及
		String softReference = a;
		// 对heap中的对象建立弱引用，此时heap中的对象仍然是强可及
		String weakReference = new String(a);
		System.out.println("强引用：" + a + "\n软引用" + softReference + "\n弱引用" + weakReference + "\n");
		// heap中的对象从强可及到软可及
		a = null;
		System.out.println("强引用：" + a + "\n软引用" + softReference + "\n弱引用" + weakReference + "\n");

		System.gc();
		System.out.println("强引用：" + a + "\n软引用" + softReference + "\n弱引用" + weakReference + "\n");
	}
}
