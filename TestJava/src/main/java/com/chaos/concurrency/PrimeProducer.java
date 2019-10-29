package com.chaos.concurrency;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

public class PrimeProducer extends Thread {

	private final BlockingQueue<BigInteger> queue;

	PrimeProducer(BlockingQueue<BigInteger> queue) {
		this.queue = queue;
	}

	public void run() {
		try {
			BigInteger p = BigInteger.ONE;
			while (!Thread.currentThread().isInterrupted())
				queue.put(p = p.nextProbablePrime());
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("do some clear job 1 " + Thread.currentThread().isInterrupted());
			Thread.interrupted();
			System.out.println("do some clear job 2 " + Thread.currentThread().isInterrupted());

		}
	}

	public void cancel() {
		interrupt();
	}
}
