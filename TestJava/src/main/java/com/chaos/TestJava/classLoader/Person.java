package com.chaos.TestJava.classLoader;

public class Person {
	private String name;

	public Person() {
		name = "facai";
	}

	public Person(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return "I am a person, my name is " + name;
	}
}