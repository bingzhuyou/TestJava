package com.chaos.concurrency;

import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQExample {
	LinkedBlockingQueue<Integer> list;

	public BlockingQExample() {
		list = new LinkedBlockingQueue<Integer>();
	}

	public void add(Integer val) {
		list.add(val);
	}

	public Integer get() throws InterruptedException {
		if (list.isEmpty()) {
			return null;
		}
		return list.remove();
	}

	public static void main(String args[]) {
		BlockingQExample wne = new BlockingQExample();
		Thread writer = new Thread() {
			public void run() {
				int k = 1;
				while (!currentThread().isInterrupted()) {
					System.out.println("wrote : " + k);
					wne.add(k);
					k++;
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
						currentThread().interrupt();
					}
				}
			}
		};

		Thread reader = new Thread() {
			public void run() {
				while (!currentThread().isInterrupted()) {
					try {
						System.out.println("readed : " + wne.get());
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
						currentThread().interrupt();
					}
				}
			}
		};

		reader.start();
		writer.start();

	}
}
