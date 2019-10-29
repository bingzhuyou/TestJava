package com.chaos.TestJava.ClassLoadTest;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import sun.misc.URLClassPath;

public class GFrameClassLoader extends URLClassLoader {
	URLClassPath ucp;

	public GFrameClassLoader() {
		super(new URL[] {});
	}

	public GFrameClassLoader(ClassLoader parent) {
		super(new URL[] {}, parent);
	}

	public GFrameClassLoader(URL[] urls, ClassLoader parent) {
		super(urls, parent);
	}

	public void addJar(String jarPath) throws MalformedURLException {
		File file = new File(jarPath);
		URL urls = file.toURI().toURL();
		super.addURL(urls);
	}

	@Override
	protected Class<?> loadClass(String name, boolean resolve)
			throws ClassNotFoundException {
		// respect the java.* packages.
		if (name.startsWith("java.")) {
			return super.loadClass(name, resolve);
		} else {
			// see if we have already loaded the class.

			Class<?> tmpClazz = findLoadedClass(name);
			if (tmpClazz != null) {
				System.out.println("findLoadedClass " + name);
				return tmpClazz;
			} else {
				// System.out.println("NOT findLoadedClass " + name);
			}

			// the class is not loaded yet. Since the parent class loader has
			// all of the
			// definitions that we need, we can use it as our source for
			// classes.
			try {
				tmpClazz = super.loadClass(name, resolve);
			} catch (Exception e) {
				tmpClazz = findClass(name);
				System.out.println("NOT Found the class222222 " + name);
			}

			if (resolve) {
				resolveClass(tmpClazz);
			}
			if (findLoadedClass(name) != null) {
				System.out.println(name + " is loaded");
			}
			return tmpClazz;
		}
	}

}