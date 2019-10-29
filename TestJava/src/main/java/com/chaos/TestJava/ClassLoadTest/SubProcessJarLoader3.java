package com.chaos.TestJava.ClassLoadTest;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

// GFrameClassLoader test is ok 
public class SubProcessJarLoader3 {

	public static void main(String[] args) throws Exception {
		String jarPath1 = "/home/chaos/downloads/dom4j-1.6.1.jar";
		String jarPath2 = "/home/chaos/downloads/jaxen-1.1.6.jar";
		String jarPath3 = "/home/chaos/downloads/pull-parser-2.1.10.jar";
		String jarPath4 = "/home/chaos/downloads/xsdlib-2013.2.jar";
		String jarPath5 = "/home/chaos/downloads/relaxngDatatype-1.0.jar";
		String jarPath6 = "/home/chaos/downloads/isorelax-20030108.jar";
		String jarPath7 = "/home/chaos/downloads/jaxb-libs-1.0.5.jar";
		String jarPath = jarPath7 + ":" + jarPath6 + ":" + jarPath5 + ":" + jarPath4 + ":" + jarPath3 + ":" + jarPath2
				+ ":" + jarPath1;

		loadSubProcessJars(jarPath, "xxxxx", ":", true);
	}

	public static Class<?> loadSubProcessJars(String subProcessJars, String mainClassName) throws Exception {
		return loadSubProcessJars(subProcessJars, mainClassName, ":", true);
	}

	public static Class<?> loadSubProcessJars(String subProcessJars, String mainClassName, String separator,
			boolean isSplit) throws Exception {
		Class<?> mainClass = null;
		ClassLoader parentLoader = SubProcessJarLoader3.class.getClassLoader();// Thread.currentThread().getContextClassLoader();
		GFrameClassLoader loader = null;
		if (isSplit) {
			String[] subProcessJarss = subProcessJars.split(separator);
			URL urls[] = new URL[subProcessJarss.length];
			for (int i = 0; i < subProcessJarss.length; i++) {
				try {
					File file = new File(subProcessJarss[i]);
					urls[i] = file.toURI().toURL();

					System.out.println(subProcessJarss[i]);
				} catch (MalformedURLException e) {
					e.printStackTrace();
					System.out.println("加载lib目录下jar文件出错！");
					throw new RuntimeException("加载lib目录下jar文件出错！", e);
				}
			}
			loader = new GFrameClassLoader(urls, parentLoader);
		}

		try {
			Class<?> tmpClazz = loader.loadClass("org.dom4j.xpp.ProxyXmlStartTag");
			mainClass = tmpClazz;
		} catch (java.lang.NoClassDefFoundError ke) {
			ke.printStackTrace();
			System.out.println("NOT FOUND : ");
		} catch (java.lang.ClassNotFoundException ke) {
			ke.printStackTrace();
			System.out.println("NOT FOUND2 : ");
		}

		System.out.println("FOUND3");
		return mainClass;
	}

	private static Class<?> loadClass(URLClassLoader localClassLoader,
			String jarPath, String mainClassName) throws IOException,
			ClassNotFoundException {
		Class<?> rtClazz = null;
		JarFile jarFile = new JarFile(jarPath);
		Enumeration<JarEntry> en = jarFile.entries();
		while (en.hasMoreElements()) {
			JarEntry je = en.nextElement();
			String name = je.getName();
			if (name.contains("/")) {
				name = name.replace("/", ".");
			}
			if (name.endsWith(".class")) {
				name = name.substring(0, name.lastIndexOf("."));
			} else {
				continue;
			}
			System.out.println(name);
			try {
				Class<?> tmpClazz = localClassLoader.loadClass(name);
				if (mainClassName != null && name.equals(mainClassName)) {
					rtClazz = tmpClazz;
				}
			} catch (java.lang.NoClassDefFoundError ke) {
				ke.printStackTrace();
				System.out.println("NOT FOUND : " + name);
			} catch (java.lang.ClassNotFoundException ke) {
				ke.printStackTrace();
				System.out.println("NOT FOUND2 : " + name);
			}
		}
		// jarFile.close();
		return rtClazz;
	}
}