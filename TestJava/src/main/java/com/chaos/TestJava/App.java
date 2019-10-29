package com.chaos.TestJava;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class App {
	public static Logger logger = LoggerFactory.getLogger(App.class);
	private static int KEY_COUNT = 100;
	private static int FIELD_COUNT = 10;

	private final int myid;
	private final List<String> names;

	public App() {
		myid = 1000;
		names = new ArrayList<String>();

		names.add("Asian");
		names.add("Europe");
		names.add("America");
		names.add("Oceaniron");
		names.add("Afilica");
	}

	public void setNames() {
		names.add("lakdsjfkl");
		names.remove(2);
	}

	public static void parseArgs(String[] args) {
		Options options = new Options();
		Option opt = new Option("k", "key", true, "test key number");
		opt.setRequired(false);
		options.addOption(opt);

		CommandLineParser parser = new PosixParser();

		// Parse the program arguments
		CommandLine commandLine;
		try {
			commandLine = parser.parse(options, args, true);

			// if (commandLine.hasOption('k')) {
			String keynbr = commandLine.getOptionValue('k');
			KEY_COUNT = Integer.parseInt(keynbr);
			// } else {
			// KEY_COUNT = 100;
			// }

		} catch (ParseException e2) {
			e2.printStackTrace();
		}
		System.out.println("KEY_COUNT:" + KEY_COUNT);
	}

	public static void parseArgs2(String[] args) {
		Options options = new Options();
		Option opt = new Option("f", "field", true, "test field number");
		opt.setRequired(false);
		options.addOption(opt);

		CommandLineParser parser = new PosixParser();

		// Parse the program arguments
		CommandLine commandLine;
		try {
			commandLine = parser.parse(options, args, true);

			// if (commandLine.hasOption('f')) {
			String fieldnbr = commandLine.getOptionValue('f');
			FIELD_COUNT = Integer.parseInt(fieldnbr);
			// } else {
			// FIELD_COUNT = 10;
			// }

		} catch (ParseException e2) {
			e2.printStackTrace();
		}
		System.out.println("FIELD_COUNT:" + FIELD_COUNT);
	}

	public static void main(String[] args) {

		// JianDan jiandan = new JianDan();
		// jiandan.startDownload();

		App kapp = new App();

		// double yyxt = 5.95;
		//
		// for (int i = 0; i < 100; i++) {
		// yyxt *= 0.95;
		// System.out.println(i + ", " + yyxt);
		// }

		// GitLabApi gitLabApi = new GitLabApi("https://182.18.57.7:6443",
		// "VjXyvix88M-e_vmvzTyy");
		// ApiVersion version = gitLabApi.getApiVersion();
		// System.out.println("GitLab-ApiVersion " + version);
		// // Create a GitLabApi instance to communicate with your GitLab server
		//
		// // Get the list of projects your account has access to
		// try {
		// // List<User> find_user = gitLabApi.getUserApi().getUsers();
		// List<Project> projects = gitLabApi.getProjectApi().getProjects();
		// } catch (GitLabApiException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// parseArgs(args);
		// parseArgs2(args);

		// while (true) {
		// logger.info("Hello World!");
		// try {
		// Thread.sleep(1000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// }
	}

	protected int k;
}
