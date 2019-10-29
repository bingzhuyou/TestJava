package com.chaos.TestJava;

import org.apache.commons.lang3.RandomUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jersey.repackaged.com.google.common.cache.Cache;

//import com.boco.xdpp.data.model.AlarmID;

import jersey.repackaged.com.google.common.cache.CacheBuilder;
import jersey.repackaged.com.google.common.cache.RemovalListener;
import jersey.repackaged.com.google.common.cache.RemovalNotification;

public class GuavaCacheTest {
	private class AlarmID {
		private long cfp0;
		private long cfp1;
		private long cfp2;
		private long cfp3;

		AlarmID(long cfp0, long cfp1, long cfp2, long cfp3) {
			this.cfp0 = cfp0;
			this.cfp1 = cfp1;
			this.cfp2 = cfp2;
			this.cfp3 = cfp3;
		}

		public String toString() {
			StringBuffer sb = new StringBuffer();
			sb.append(cfp0);
			sb.append("_");
			sb.append(cfp1);
			sb.append("_");
			sb.append(cfp2);
			sb.append("_");
			sb.append(cfp3);
			return sb.toString();
		}
	}

	private static final Logger logger = LoggerFactory.getLogger(GuavaCacheTest.class);

	int maxSize = 2000000;
	Object defaultObject = new Object();
	Cache<AlarmID, Object> fpCache;

	@Before
	public void setUp() throws Exception {
		fpCache = CacheBuilder	.newBuilder().maximumSize(maxSize)
								// .refreshAfterWrite(2, TimeUnit.SECONDS)
								// .expireAfterAccess(5, TimeUnit.SECONDS)
								.removalListener(new MyRemoveListener()).build();
		for (int i = 0; i < maxSize; i++) {
			AlarmID id = new AlarmID(i, 0, 0, 0);
			fpCache.put(id, defaultObject);
		}
		System.out.println("put " + maxSize + " alarm id in cache");
	}

	class MyRemoveListener implements RemovalListener<AlarmID, Object> {
		@Override
		public void onRemoval(RemovalNotification<AlarmID, Object> notification) {
			logger.info("remove " + notification.getKey());
		}
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void memoryTest() throws Exception {
		Thread.sleep(100000);
	}

	@Test
	public void speetTest() throws Exception {
		// speed-->2668801.7080330933/s
		int testTimes = maxSize * 100;
		long start = System.currentTimeMillis();
		for (int i = 0; i < testTimes; i++) {
			int nextInt = RandomUtils.nextInt(0, maxSize);
			AlarmID id = new AlarmID(nextInt, 0, 0, 0);
			fpCache.getIfPresent(id);
		}
		long spend = System.currentTimeMillis() - start;
		double speed = testTimes * 1000.0 / spend;
		System.out.println("speed-->" + speed);
	}

}