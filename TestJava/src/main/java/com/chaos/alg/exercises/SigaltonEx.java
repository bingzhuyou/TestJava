package com.chaos.alg.exercises;

public class SigaltonEx {

	private static SigaltonEx sigaltonExObj;

	private String label;

	private SigaltonEx() {
		label = "chaos";
	}

	public static synchronized SigaltonEx newInstance() {
		if (sigaltonExObj == null) {
			sigaltonExObj = new SigaltonEx();
		}
		return sigaltonExObj;
	}

	public void printObj() {
		System.out.println("sigalton label : " + label);
	}

}
