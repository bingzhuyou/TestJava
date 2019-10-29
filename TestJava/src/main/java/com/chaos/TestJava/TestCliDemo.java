package com.chaos.TestJava;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

public class TestCliDemo {

	/**
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
		Options options = new Options();
		options.addOption("t", true, "display current time");
		options.addOption("c", true, "country code");

		CommandLineParser parser = new PosixParser();
		CommandLine cmd = parser.parse(options, args, true);

		if (cmd.hasOption("c")) {
			String countryCode = cmd.getOptionValue("c");
			System.out.println(countryCode);
		}

		if (cmd.hasOption("t")) {
			String countryCode = cmd.getOptionValue("t");
			System.out.println(countryCode);
		}
	}

}