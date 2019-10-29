package com.chaos.TestJava;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import com.chaos.alg.exercises.Exercise1;
import com.chaos.alg.exercises.SortExercises;

public class AppTest {

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

	public int[] genInput(int len) {

		int[] input = new int[len];

		Random r = new Random();

		for (int i = 0; i < len; i++) {
			input[i] = r.nextInt(100);
		}

		return input;
	}

	@Test
	public void testBubbleSort() {
		int[] input1 = genInput(10);
		SortExercises.printArray(input1);
		SortExercises.bubbleSort(input1);
		SortExercises.printArray(input1);

		int[] input2 = genInput(20);
		SortExercises.printArray(input2);
		SortExercises.bubbleSort(input2);
		SortExercises.printArray(input2);

		int[] input3 = genInput(50);
		SortExercises.printArray(input3);
		SortExercises.bubbleSort(input3);
		SortExercises.printArray(input3);

		int[] input4 = genInput(100);
		SortExercises.printArray(input4);
		SortExercises.bubbleSort(input4);
		SortExercises.printArray(input4);

		int[] input5 = genInput(101);
		SortExercises.printArray(input5);
		SortExercises.bubbleSort(input5);
		SortExercises.printArray(input5);
	}

	@Test
	public void testInsertSort() {

		int[] input = { 86, 40, 0, 53, 0, 46, 15, 15, 40, 46, 22, 13, 44, 98, 44, 42, 80, 88, 48, 76 };
		SortExercises.printArray(input);
		SortExercises.insertSortU(input);
		SortExercises.printArray(input);

		int[] input1 = genInput(10);
		SortExercises.printArray(input1);
		SortExercises.insertSort(input1);
		SortExercises.printArray(input1);

		int[] input2 = genInput(20);
		SortExercises.printArray(input2);
		SortExercises.insertSort(input2);
		SortExercises.printArray(input2);

		int[] input3 = genInput(50);
		SortExercises.printArray(input3);
		SortExercises.insertSort(input3);
		SortExercises.printArray(input3);

		int[] input4 = genInput(100);
		SortExercises.printArray(input4);
		SortExercises.insertSort(input4);
		SortExercises.printArray(input4);

		int[] input5 = genInput(101);
		SortExercises.printArray(input5);
		SortExercises.insertSort(input5);
		SortExercises.printArray(input5);
	}

	@Test
	public void testSelectSort() {
		int[] input = { 86, 40, 0, 53, 74, 46, 15, 20, 40, 46, 22, 13, 38, 98, 44, 42, 80, 88, 48, 76 };
		SortExercises.printArray(input);
		SortExercises.selectSort(input);
		SortExercises.printArray(input);
		int[] input1 = genInput(10);
		SortExercises.printArray(input1);
		SortExercises.selectSort(input1);
		SortExercises.printArray(input1);

		int[] input2 = genInput(20);
		SortExercises.printArray(input2);
		SortExercises.selectSort(input2);
		SortExercises.printArray(input2);

		int[] input3 = genInput(50);
		SortExercises.printArray(input3);
		SortExercises.selectSort(input3);
		SortExercises.printArray(input3);

		int[] input4 = genInput(100);
		SortExercises.printArray(input4);
		SortExercises.selectSort(input4);
		SortExercises.printArray(input4);

		int[] input5 = genInput(101);
		SortExercises.printArray(input5);
		SortExercises.selectSort(input5);
		SortExercises.printArray(input5);
	}

	@Test
	public void testShellSort() {

		int[] input = { 86, 40, 0, 53, 74, 46, 15, 20, 40, 46, 22, 13, 38, 98, 44, 42, 80, 88, 48, 76 };
		SortExercises.printArray(input);
		SortExercises.shellSort(input);
		SortExercises.printArray(input);

		int[] input1 = genInput(10);
		SortExercises.printArray(input1);
		SortExercises.shellSort(input1);
		SortExercises.printArray(input1);

		int[] input2 = genInput(20);
		SortExercises.printArray(input2);
		SortExercises.shellSort(input2);
		SortExercises.printArray(input2);

		int[] input3 = genInput(50);
		SortExercises.printArray(input3);
		SortExercises.shellSort(input3);
		SortExercises.printArray(input3);

		int[] input4 = genInput(100);
		SortExercises.printArray(input4);
		SortExercises.shellSort(input4);
		SortExercises.printArray(input4);

		int[] input5 = genInput(101);
		SortExercises.printArray(input5);
		SortExercises.shellSort(input5);
		SortExercises.printArray(input5);
	}

	@Test
	public void testQuickSort() {

		int[] input = { 86, 40, 0, 53, 74, 46, 15, 20, 40, 46, 22, 13, 38, 98, 44, 42, 80, 88, 48, 76 };
		SortExercises.printArray(input);
		SortExercises.quickSort(input);
		SortExercises.printArray(input);

		int[] input1 = genInput(10);
		SortExercises.printArray(input1);
		SortExercises.quickSort(input1);
		SortExercises.printArray(input1);

		int[] input2 = genInput(20);
		SortExercises.printArray(input2);
		SortExercises.quickSort(input2);
		SortExercises.printArray(input2);

		int[] input3 = genInput(50);
		SortExercises.printArray(input3);
		SortExercises.quickSort(input3);
		SortExercises.printArray(input3);

		int[] input4 = genInput(100);
		SortExercises.printArray(input4);
		SortExercises.quickSort(input4);
		SortExercises.printArray(input4);

		int[] input5 = genInput(101);
		SortExercises.printArray(input5);
		SortExercises.quickSort(input5);
		SortExercises.printArray(input5);
	}

	@Test
	public void testHeapSort() {

		int[] input = { 86, 40, 0, 53, 74, 46, 15, 20, 40, 46, 22, 13, 38, 98, 44, 42, 80, 88, 48, 76 };
		SortExercises.printArray(input);
		SortExercises.heapSort(input);
		SortExercises.printArray(input);

		int[] input1 = genInput(10);
		SortExercises.printArray(input1);
		SortExercises.heapSort(input1);
		SortExercises.printArray(input1);

		int[] input2 = genInput(20);
		SortExercises.printArray(input2);
		SortExercises.heapSort(input2);
		SortExercises.printArray(input2);

		int[] input3 = genInput(50);
		SortExercises.printArray(input3);
		SortExercises.heapSort(input3);
		SortExercises.printArray(input3);

		int[] input4 = genInput(100);
		SortExercises.printArray(input4);
		SortExercises.heapSort(input4);
		SortExercises.printArray(input4);

		int[] input5 = genInput(101);
		SortExercises.printArray(input5);
		SortExercises.heapSort(input5);
		SortExercises.printArray(input5);
	}

	@Test
	public void testSortColors() {
		int[] input = { 1, 0 };
		SortExercises.printArray(input);
		Exercise1.sortColors(input);
		SortExercises.printArray(input);
	}

	@Test
	public void testfindMedianSortedArrays() {
		int[] input1 = { 1, 2, 5, 6, 7, 10 };
		int[] input2 = { 1, 1, 1, 2, 3, 4 };

		// int[] input1 = { 1, 3 };
		// int[] input2 = { 2 };
		SortExercises.printArray(input1);
		SortExercises.printArray(input2);
		Exercise1.findMedianSortedArrays(input1, input2);
	}

	@Test
	public void testMergeTwoSortedList() {
		ListNode l1 = new ListNode(1);
		ListNode l2 = new ListNode(1);
		ListNode l3 = new ListNode(2);
		ListNode l4 = new ListNode(3);
		ListNode l5 = new ListNode(4);
		ListNode l6 = new ListNode(4);
		// ListNode l7 = new ListNode(5);
		// ListNode l8 = new ListNode(17);
		// ListNode l9 = new ListNode(8);
		// ListNode l10 = new ListNode(19);

		l1.next = l3;
		l3.next = l5;
		l5.next = null;
		// l5.next = l7;
		// l7.next = l9;
		// l9.next = null;
		l2.next = l4;
		l4.next = l6;
		l6.next = null;
		// l6.next = l8;
		// l8.next = l10;
		// l10.next = null;

		// int[] input1 = { 1, 3 };
		// int[] input2 = { 2 };
		ListNode lk = Exercise1.mergeTwoLists(l1, l2);

		while (lk != null) {
			System.out.print(lk.val + " ");
			lk = lk.next;
		}
		System.out.println("\n");

	}

}
