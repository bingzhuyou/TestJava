package com.chaos.TestJava;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestFileOp {
	public static void main(String args[]) {

		String filePath = "/home/chaos/hs_err_pid14417.log";

		File file = new File(filePath);
		if (!file.exists() || !file.isFile()) {
			System.out.println("Can't open file " + filePath);
			System.exit(-1);
		}

		FileInputStream fileInputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader reader = null;

		FileInputStream fileInputStream2 = null;
		InputStreamReader inputStreamReader2 = null;
		BufferedReader reader2 = null;

		FileInputStream fileInputStream3 = null;
		InputStreamReader inputStreamReader3 = null;
		BufferedReader reader3 = null;
		try {
			fileInputStream = new FileInputStream(file);
			inputStreamReader = new InputStreamReader(fileInputStream, "GBK");
			reader = new BufferedReader(inputStreamReader);
			String lineContent = "";
			while ((lineContent = reader.readLine()) != null) {
				System.out.println(lineContent);
			}

			System.out.println(
					"##################################    1    ##############################################");
			fileInputStream2 = new FileInputStream(file);
			inputStreamReader2 = new InputStreamReader(fileInputStream2, "GBK");
			reader2 = new BufferedReader(inputStreamReader2);
			String lineContent2 = "";
			while ((lineContent2 = reader2.readLine()) != null) {
				System.out.println(lineContent2);
			}
			System.out.println(
					"##################################    1 2   ##############################################");
			fileInputStream3 = new FileInputStream(file);
			inputStreamReader3 = new InputStreamReader(fileInputStream3, "GBK");
			reader3 = new BufferedReader(inputStreamReader3);
			String lineContent3 = "";
			while ((lineContent3 = reader3.readLine()) != null) {
				System.out.println(lineContent3);
			}
			System.out.println(
					"##################################    1  3  ##############################################");
			Thread.sleep(1000 * 20);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (fileInputStream != null) {
					fileInputStream.close();
				}
				if (inputStreamReader != null) {
					inputStreamReader.close();
				}
				if (reader != null) {
					reader.close();
				}

				if (fileInputStream2 != null) {
					fileInputStream2.close();
				}
				if (inputStreamReader2 != null) {
					inputStreamReader2.close();
				}
				if (reader2 != null) {
					reader2.close();
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println(
				"##########################################    2      ##################################################");
		try {
			Thread.sleep(1000 * 20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(
				"#######################################    3     ################################################");
	}
}
