package com.chaos.concurrency;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PrimeProducerTest {
	public static void main(String args[]) {
		BlockingQueue<BigInteger> pq = new LinkedBlockingQueue<BigInteger>();
		PrimeProducer pp = new PrimeProducer(pq);

		pp.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		pp.cancel();

		for (BigInteger bi : pq) {
			System.out.println("prime number is " + bi);
		}
	}
}
