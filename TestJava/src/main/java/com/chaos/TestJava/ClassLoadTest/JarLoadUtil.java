package com.chaos.TestJava.ClassLoadTest;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class JarLoadUtil {

	private static URL lib_url = ClassLoader.getSystemClassLoader().getResource("/home/chaos/downloads/tmplib");
	private static URLClassLoader loader = null;

	/*
	 * 加载lib目录下所有jar文件，并返回相应的的URLClassLoader
	 */
	public static URLClassLoader getURLClassLoader() {

		if (loader == null) {
			File file_directory = new File("/home/chaos/downloads/tmplib");
			String fileNames[] = file_directory.list();
			if (fileNames != null && fileNames.length > 0) {
				URL urls[] = new URL[fileNames.length];
				for (int i = 0; i < fileNames.length; i++) {
					try {
						File file = new File("/home/chaos/downloads/tmplib" + "/" + fileNames[i]);
						urls[i] = file.toURI().toURL();
					} catch (MalformedURLException e) {
						e.printStackTrace();
						System.out.println("加载lib目录下jar文件出错！");
						throw new RuntimeException("加载lib目录下jar文件出错！", e);
					}
				}
				loader = new URLClassLoader(urls, Thread.currentThread().getContextClassLoader());
			}
		}
		return loader;
	}

	public static void main(String args[]) {
		URLClassLoader loader = JarLoadUtil.getURLClassLoader();
		try {
			Class<?> clazz = loader.loadClass("org.dom4j.xpp.ProxyXmlStartTag");
			// Class<?> clazz =
			// loader.loadClass("org.dom4j.xpp.ProxyXmlStartTag");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("OK");
	}
}