package com.chaos.leetcode;

import java.util.Stack;

public class MinStack {
	int currentMin;
	Stack<Integer> iStack = new Stack<Integer>();
	Stack<Integer> minStack = new Stack<Integer>();

	/** initialize your data structure here. */
	public MinStack() {
		currentMin = Integer.MAX_VALUE;
	}

	public void push(int x) {
		if (x < currentMin) {
			minStack.push(x);
			currentMin = x;
		} else {
			minStack.push(currentMin);
		}
		iStack.push(x);
	}

	public void pop() {
		minStack.pop();
		iStack.pop();
		if (minStack.empty()) {
			currentMin = Integer.MAX_VALUE;
		} else {
			currentMin = minStack.peek();
		}
	}

	public int top() {
		return iStack.peek();
	}

	public int getMin() {
		return minStack.peek();
	}

	public static void main(String[] args) {
		MinStack minStack = new MinStack();
		minStack.push(-2);
		minStack.push(0);
		minStack.push(-3);
		System.out.println(minStack.getMin());
		minStack.pop();
		System.out.println(minStack.top());
		System.out.println(minStack.getMin());
	}
}