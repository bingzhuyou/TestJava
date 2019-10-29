package com.chaos.concurrency;

import java.util.LinkedList;

public class WaitNotifyExample {
	LinkedList<Integer> list;

	public WaitNotifyExample() {
		list = new LinkedList<Integer>();
	}

	public synchronized void add(Integer val) {
		list.add(val);
		notifyAll();
	}

	public synchronized Integer get() throws InterruptedException {
		if (list.isEmpty()) {
			System.out.println("no date sleeping. ");
			wait();
			System.out.println("no date wake up. ");
		}
		return list.removeFirst();
	}

	public static void main(String args[]) {
		WaitNotifyExample wne = new WaitNotifyExample();
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
