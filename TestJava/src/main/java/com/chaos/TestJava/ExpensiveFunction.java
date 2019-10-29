package com.chaos.TestJava;

import java.math.BigInteger;

public class ExpensiveFunction implements Computable<String, BigInteger> {
	public BigInteger compute(String arg) throws InterruptedException {
		return new BigInteger(arg);
	}
}