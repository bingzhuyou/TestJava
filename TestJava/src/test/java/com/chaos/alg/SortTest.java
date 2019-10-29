package com.chaos.alg;

import java.util.Random;

import org.junit.Test;

import com.chaos.alg.exercises.Exercise1;
import com.chaos.alg.exercises.SortExercises;
import com.chaos.alg.sort.SortUtils;

public class SortTest {

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
		SortUtils.printArray(input1);
		SortUtils.bubbleSort(input1);
		SortUtils.printArray(input1);

		int[] input2 = genInput(20);
		SortUtils.printArray(input2);
		SortUtils.bubbleSort(input2);
		SortUtils.printArray(input2);

		int[] input3 = genInput(50);
		SortUtils.printArray(input3);
		SortUtils.bubbleSort(input3);
		SortUtils.printArray(input3);

		int[] input4 = genInput(100);
		SortUtils.printArray(input4);
		SortUtils.bubbleSort(input4);
		SortUtils.printArray(input4);

		int[] input5 = genInput(101);
		SortUtils.printArray(input5);
		SortUtils.bubbleSort(input5);
		SortUtils.printArray(input5);
	}

	@Test
	public void testInsertSort() {

		// int[] input = { 86, 40, 0, 53, 0, 46, 15, 15, 40, 46, 22, 13, 44, 98,
		// 44, 42, 80, 88, 48, 76 };
		// SortExercises.printArray(input);
		// SortExercises.insertSortU(input);
		// SortExercises.printArray(input);

		int[] input1 = genInput(10);
		SortUtils.printArray(input1);
		SortUtils.insertSort(input1);
		SortUtils.printArray(input1);

		int[] input2 = genInput(20);
		SortUtils.printArray(input2);
		SortUtils.insertSort(input2);
		SortUtils.printArray(input2);

		int[] input3 = genInput(50);
		SortUtils.printArray(input3);
		SortUtils.insertSort(input3);
		SortUtils.printArray(input3);

		int[] input4 = genInput(100);
		SortUtils.printArray(input4);
		SortUtils.insertSort(input4);
		SortUtils.printArray(input4);

		int[] input5 = genInput(101);
		SortUtils.printArray(input5);
		SortUtils.insertSort(input5);
		SortUtils.printArray(input5);
	}

	@Test
	public void testSelectSort() {
		int[] input = { 86, 40, 0, 53, 74, 46, 15, 20, 40, 46, 22, 13, 38, 98, 44, 42, 80, 88, 48, 76 };
		SortUtils.printArray(input);
		SortUtils.selectSort(input);
		SortUtils.printArray(input);
		int[] input1 = genInput(10);
		SortUtils.printArray(input1);
		SortUtils.selectSort(input1);
		SortUtils.printArray(input1);

		int[] input2 = genInput(20);
		SortUtils.printArray(input2);
		SortUtils.selectSort(input2);
		SortUtils.printArray(input2);

		int[] input3 = genInput(50);
		SortUtils.printArray(input3);
		SortUtils.selectSort(input3);
		SortUtils.printArray(input3);

		int[] input4 = genInput(100);
		SortUtils.printArray(input4);
		SortUtils.selectSort(input4);
		SortUtils.printArray(input4);

		int[] input5 = genInput(101);
		SortUtils.printArray(input5);
		SortUtils.selectSort(input5);
		SortUtils.printArray(input5);
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

}
