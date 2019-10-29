package com.chaos.TestJava;

import java.io.IOException;

public class DockerShell {
	public static void main(String args[]) {
		String cmd = "/bin/bash";
		String arg = "/home/chaos/dockerrun.sh";

		String[] cmds = new String[] { cmd, "-c", arg };
		try {
			Process process = Runtime.getRuntime().exec(cmds);
			process.waitFor();
			process.destroy();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
