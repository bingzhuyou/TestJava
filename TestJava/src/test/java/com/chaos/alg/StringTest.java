package com.chaos.alg;

import org.junit.Assert;
import org.junit.Test;

import com.chaos.alg.exercises.Exercise1;

public class StringTest {

	@Test
	public void isStrDupChar() {
		Assert.assertTrue(Exercise1.isStrDupChar("bvshsdfgsdfg"));
		Assert.assertTrue(Exercise1.isStrDupChar("1adhjl;nm,1"));
		Assert.assertTrue(Exercise1.isStrDupChar("1adhjl2;1nm,"));
		Assert.assertTrue(Exercise1.isStrDupChar("adhjl;nm,11"));
		Assert.assertTrue(Exercise1.isStrDupChar("11adhjl;nm,"));

		Assert.assertFalse(Exercise1.isStrDupChar(""));
		Assert.assertFalse(Exercise1.isStrDupChar("123"));
		Assert.assertFalse(Exercise1.isStrDupChar("adsf"));
		// Assert.assertFalse(Exercise1.isStrDupChar("bvshsdfgsdfg"));
	}

	@Test
	public void isStrDup() {
		Assert.assertTrue(Exercise1.isDupStr("bvshsdfgsdfg", "bvshsdfgsdfg"));
		Assert.assertTrue(Exercise1.isDupStr("1adhjlnm,1;", "1adhjl;nm,1"));
		Assert.assertTrue(Exercise1.isDupStr("1adhjl2;1nm,", "1adhjl2;1nm,"));
		Assert.assertTrue(Exercise1.isDupStr("adhjl;2nm,11", "adhjl;nm,112"));
		Assert.assertTrue(Exercise1.isDupStr("1adhjl;2nm,11", "adhjl;n1m,112"));
		Assert.assertTrue(Exercise1.isDupStr("", ""));

		Assert.assertFalse(Exercise1.isDupStr("bvshsdfgsdfg2", "bvshsdfgsdfg1"));
		Assert.assertFalse(Exercise1.isDupStr("bvssdfgsdfg", "dfgbvshssdfg"));
		// Assert.assertFalse(Exercise1.isStrDupChar("bvshsdfgsdfg"));
	}

	@Test
	public void testReplaceSpace() {
		Assert.assertEquals(Exercise1.replaceSpace("Mr John Simth"), "Mr%20John%20Simth");
		Assert.assertEquals(Exercise1.replaceSpace(" 1adhjl;nm,1"), "%201adhjl;nm,1");
		Assert.assertEquals(Exercise1.replaceSpace("1adhjl2;1nm, "), "1adhjl2;1nm,%20");

		// Assert.(message, expected, actual);(Exercise1.replaceSpace(""));
		// Assert.assertFalse(Exercise1.replaceSpace("123"));
		// Assert.assertFalse(Exercise1.replaceSpace("adsf"));
	}

	@Test
	public void testCompressStr() {
		Assert.assertEquals(Exercise1.compressStr("aaabbbcccbbbd"), "a3b3c3b3d1");
		Assert.assertEquals(Exercise1.compressStr("aaabbbcccbbbddddddddd"), "a3b3c3b3d9");
		Assert.assertEquals(Exercise1.compressStr("aaabbbcccccccccbbbddd"), "a3b3c9b3d3");
		Assert.assertEquals(Exercise1.compressStr("abcccbbbddd"), "a1b1c3b3d3");
		Assert.assertEquals(Exercise1.compressStr("abcbbbddd"), "abcbbbddd");

		// Assert.(message, expected, actual);(Exercise1.replaceSpace(""));
		// Assert.assertFalse(Exercise1.replaceSpace("123"));
		// Assert.assertFalse(Exercise1.replaceSpace("adsf"));
	}

	@Test
	public void testReverseInt() {
		Assert.assertEquals(Exercise1.reverseInt(123), 321);
		Assert.assertEquals(Exercise1.reverseInt(-123), -321);
		Assert.assertEquals(Exercise1.reverseInt(-2147483648), 0);
		Assert.assertEquals(Exercise1.reverseInt(2147483647), 0);

		// Assert.(message, expected, actual);(Exercise1.replaceSpace(""));
		// Assert.assertFalse(Exercise1.replaceSpace("123"));
		// Assert.assertFalse(Exercise1.replaceSpace("adsf"));
	}
}
