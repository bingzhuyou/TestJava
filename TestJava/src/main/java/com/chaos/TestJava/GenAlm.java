package com.chaos.TestJava;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GenAlm {

	public static void main(String argv[]) {
		Path file = Paths.get("/home/chaos/downloads/", "tfa_alarm.csv");
		Charset charset = Charset.forName("gbk");

		// Pattern p = Pattern.compile("(\\w+)");
		try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
			String line = null;
			String[] segs = null;
			String[] elms = null;
			String xmlHead = "<xml><message client_q_name='HUAWEI803.Q' type='1406' msg_serial=''><OneRecord  ";
			String xmlTail = "/></message></xml>";
			String xmlBody = "";
			int linecnt = 1;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				if (linecnt == 1) {
					segs = line.split("\",\"");
					for (int i = 0; i < segs.length; i++) {
						segs[i].toLowerCase();
					}
				} else {
					elms = line.split("\",\"");
					System.out.println(segs.length + "  " + elms.length);
					if (elms.length < segs.length) {
						continue;
					}
					for (int i = 0; i < segs.length; i++) {
						xmlBody += segs[i];
						xmlBody += "='";
						xmlBody += elms[i];
						xmlBody += "' ";
					}
				}
				linecnt++;
			}
			System.out.println(xmlHead + xmlBody + xmlTail);
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}
	}
}
