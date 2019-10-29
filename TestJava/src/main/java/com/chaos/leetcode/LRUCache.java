package com.chaos.leetcode;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

class LRUCache {

	Queue<Integer> lruQueue = new LinkedBlockingQueue<Integer>();

	public LRUCache(int capacity) {

	}

	public int get(int key) {
		return -1;
	}

	public void put(int key, int value) {

	}
}

/**
 * Your LRUCache object will be instantiated and called as such: LRUCache obj =
 * new LRUCache(capacity); int param_1 = obj.get(key); obj.put(key,value);
 */