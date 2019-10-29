package com.chaos.TestJava.finallytest;

import java.util.HashMap;
import java.util.Map;
//如果finally语句中没有return语句覆盖返回值，那么原来的返回值可能因为finally里的修改而改变也可能不变。

public class FinallyTest6 {
	public static void main(String[] args) {
		System.out.println(getMap().get("KEY").toString());
	}

	public static Map<String, String> getMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("KEY", "INIT");

		try {
			map.put("KEY", "TRY");
			return map;
		} catch (Exception e) {
			map.put("KEY", "CATCH");
		} finally {
			map.put("KEY", "FINALLY");
			map = null;
		}

		return map;
	}
}