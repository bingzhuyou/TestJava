package com.chaos.TestJava.AlarmRuleEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Cineady
 *
 */
public class Util {

	static public String getFieldName(String fname, String defname) {
		if (fname == null || fname.equals("")) {
			fname = defname;
		}
		fname = fname.toLowerCase();
		fname = (String.valueOf(fname.charAt(0))).toLowerCase() + fname.substring(1);
		int index = -1;
		for (index = fname.indexOf('_'); index != -1; index = fname.indexOf('_')) {
			fname = fname.substring(0, index) + (String.valueOf(fname.charAt(index + 1))).toUpperCase()
					+ fname.substring(index + 2);
		}

		int fieldNameLength = fname.length();
		List<Integer> indexList = new ArrayList<Integer>();
		for (int i = 0; i < fieldNameLength; i++) {
			char temp = fname.charAt(i);
			if (temp >= '0' && temp <= '9') {
				indexList.add(i);
			}
		}

		int indexListSize = indexList.size();
		if (indexListSize == 0) {
			return fname;
		}

		for (Integer numberIndex : indexList) {

			String temp = fname.substring(0, numberIndex + 1);
			if (numberIndex + 2 <= fname.length()) {

				temp += fname.substring(numberIndex + 1, numberIndex + 2).toUpperCase()
						+ fname.substring(numberIndex + 2);
			}
			fname = temp;
		}

		return fname;
	}

	static public String getFieldName1(String fieldname, String defname) {
		String fieldname1 = getFieldName(fieldname, defname);
		byte[] cc = fieldname1.getBytes();
		cc[0] = (byte) (cc[0] - 0x20); // 妫ｆ牕鐡уВ宥呫亣閸愶拷
		return new String(cc);
	}

	static public String trim(String v) {
		if (v == null) {
			v = "";
		}
		if (v.length() > 0 && v.charAt(0) == ' ') {
			v = trim(v.substring(1));
		}
		if (v.length() > 0 && v.charAt(0) == '[') {
			v = trim(v.substring(1));
		}
		if (v.length() > 0 && v.charAt(v.length() - 1) == ']') {
			v = trim(v.substring(0, v.length() - 1));
		}
		return v;
	}

}