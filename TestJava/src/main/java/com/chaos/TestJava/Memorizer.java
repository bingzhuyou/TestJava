package com.chaos.TestJava;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class Memorizer<A, V> implements Computable<A, V> {
	private final ConcurrentMap<A, Future<V>> cache = new ConcurrentHashMap<A, Future<V>>();
	private Computable<A, V> c = null;

	public Memorizer(Computable<A, V> c) {
		this.c = c;
	}

	public V compute(final A arg0) throws InterruptedException {
		while (true) {
			Future<V> f = cache.get(arg0);
			if (f == null) {
				Callable<V> eval = new Callable<V>() {
					public V call() throws InterruptedException {
						return c.compute(arg0);

					}
				};
				FutureTask<V> ft = new FutureTask<V>(eval);
				f = cache.putIfAbsent(arg0, ft);
				if (f == null) {
					f = ft;
					ft.run();
				}
			}

			try {
				return f.get();
			} catch (CancellationException e) {
				cache.remove(arg0, f);
			} catch (ExecutionException e) {
				throw new InterruptedException();
			}
		}
	}

}
