package com.chaos.TestJava.ClassLoadTest;

import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

// orig bad version 

public class SubProcessJarLoader {

	public static void main(String[] args) throws Exception {
		String jarPath = "C:\\Users\\LIYONG\\Desktop\\subProcess\\dom4j-1.6.1.jar";
		loadSubProcessJars(jarPath, "xxxxx", null, false);
	}

	public static Class<?> loadSubProcessJars(String subProcessJars, String mainClassName) throws Exception {
		return loadSubProcessJars(subProcessJars, mainClassName, ":", true);
	}

	public static Class<?> loadSubProcessJars(String subProcessJars, String mainClassName, String separator,
			boolean isSplit) throws Exception {
		Class<?> mainClass = null;
		ClassLoader parentLoader = Thread.currentThread().getContextClassLoader();
		GFrameClassLoader loader = new GFrameClassLoader(parentLoader);
		if (isSplit) {
			String[] subProcessJarss = subProcessJars.split(separator);
			for (String jarPath : subProcessJarss) {
				loader.addJar(jarPath);
			}
			for (String jarPath : subProcessJarss) {

				Class<?> mainClass_ = null;
				System.out.println("**********[" + jarPath + "]");
				try {
					mainClass_ = loadClass(loader, jarPath, mainClassName);
				} catch (Exception e) {
					System.out.println("load jar[" + jarPath + "]fail");
					// throw new Exception("load jar[" + jarPath + "]fail",e);
				}
				if (null != mainClass_) {
					mainClass = mainClass_;
				}
			}
		} else {
			loader.addJar(subProcessJars);
			Class<?> mainClass_ = loadClass(loader, subProcessJars, mainClassName);
			if (null != mainClass_) {
				mainClass = mainClass_;
			}
		}

		return mainClass;
	}

	private static Class<?> loadClass(GFrameClassLoader localClassLoader,
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
			Class<?> tmpClazz = localClassLoader.loadClass(name);
			if (mainClassName != null && name.equals(mainClassName)) {
				rtClazz = tmpClazz;
			}
		}
		return rtClazz;
	}
}