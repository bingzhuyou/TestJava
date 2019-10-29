package com.chaos.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestThreadPool {
	public static void main(String args[]) {
		ExecutorService es = Executors.newFixedThreadPool(2);

		for (int i = 0; i < 4; i++) {
			es.execute(new Runnable() {
				public void run() {
					System.out.println("thread begin " + Thread.currentThread().getName());
					try {
						Thread.sleep(4000);
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
					System.out.println("thread end " + Thread.currentThread().getName());
				}
			});
		}

		es.shutdown();

		ExecutorService es2 = new ThreadPoolExecutor(2, 2,
				0L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>());

		for (int i = 0; i < 4; i++) {
			es2.execute(new Runnable() {
				public void run() {
					System.out.println("thread2 begin " + Thread.currentThread().getName());
					try {
						Thread.sleep(4000);
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
					System.out.println("thread2 end " + Thread.currentThread().getName());
				}
			});
		}

		es2.shutdown();
	}
}
