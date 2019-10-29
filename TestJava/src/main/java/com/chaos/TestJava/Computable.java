package com.chaos.TestJava;

public interface Computable<A, V> {
	V compute(A arg) throws InterruptedException;
}