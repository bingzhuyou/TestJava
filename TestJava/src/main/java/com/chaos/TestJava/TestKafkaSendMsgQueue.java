package com.chaos.TestJava;

public class TestKafkaSendMsgQueue {

	public static void main(String args[]) {
		KafkaSendMessageQueue ksmQ = new KafkaSendMessageQueue();

		String sendMessageWarpper = "Test snd ";
		ksmQ.start();
		long k = 0;
		while (true) {
			k++;
			ksmQ.addSendMessage(sendMessageWarpper + k);
			if (k % 1000 == 0) {
				try {
					Thread.sleep(2000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
