package com.chaos.alg.exercises;

public class SortExercises {

	public static void printArray(int[] input) {
		StringBuffer strb = new StringBuffer();
		boolean isSorted = true;
		for (int i = 0; i < input.length; i++) {
			if (i < input.length - 1) {
				if (input[i] < input[i + 1]) {
					isSorted = false;
				}
			}
			strb.append(" " + input[i]);
		}
		if (isSorted) {
			System.out.println("Sorted!");
		} else {

			System.out.println("Not sorted!");
		}
		System.out.println(strb.toString());
	}

	public static void bubbleSort(int[] input) {
		int len = input.length;
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len - 1 - i; j++) {
				if (input[j] < input[j + 1]) {
					int tmp = input[j];
					input[j] = input[j + 1];
					input[j + 1] = tmp;
				}
			}
		}
	}

	public static void insertSortbadperf(int[] input) {
		int len = input.length;
		int[] output = new int[len];
		output[0] = input[0];
		for (int i = 1; i < len; i++) {
			// printArray(output);
			for (int j = 0; j <= i; j++) {
				if (output[j] < input[i]) {
					for (int k = i; k > j; k--) {
						output[k] = output[k - 1];
					}
					output[j] = input[i];
					break;
				}
				if (j == i - 1) {
					output[i] = input[i];
				}
			}
		}
		// printArray(output);

		for (int l = 0; l < len; l++) {
			input[l] = output[l];
		}
	}

	public static void insertSort(int[] input) {
		int temp = 0;
		int len = input.length;
		for (int i = 1; i < len; i++) {
			temp = input[i];
			int j = i - 1;
			for (; j >= 0 && input[j] > temp; j--) {
				input[j + 1] = input[j];
			}
			input[j + 1] = temp;
		}
	}

	public static void insertSortU(int[] nums) {
		int temp;
		int uniPos = 0;
		for (int i = 1; i < nums.length; i++) {
			temp = nums[i];
			int j = uniPos;
			for (; j >= 0 && nums[j] > temp; j--) {
				nums[j + 1] = nums[j];
			}
			if (j < 0 || nums[j] != temp) {
				nums[j + 1] = temp;
				uniPos++;
			} else {
				j++;
				for (; j <= uniPos; j++) {
					nums[j] = nums[j + 1];
				}
			}
		}
		System.out.println("U pos : " + uniPos);
	}

	public static void shellSort(int[] input) {
		int temp = 0;
		int len = input.length;
		int d = len / 2;
		while (d >= 1) {
			for (int l = 0; l < d; l++) {
				for (int i = d + l; i < len; i += d) {
					// printArray(output);
					temp = input[i];
					int j = i - d;
					for (; j >= 0 && input[j] < temp; j -= d) {
						input[j + d] = input[j];
					}
					input[j + d] = temp;
					// System.out.print("###" + i + " " + j + ", " + temp + "
					// ###");
					// printArray(input);
				}
			}
			d = d / 2;
		}
	}

	public static void selectSort(int[] input) {
		int max = 0;
		int len = input.length;
		int ind = 0;
		for (int i = 0; i < len; i++) {
			ind = i;
			for (int j = i + 1; j < len; j++) {
				if (input[ind] < input[j]) {
					ind = j;
				}
			}
			max = input[ind];
			input[ind] = input[i];
			input[i] = max;
		}
	}

	public static void quickSort(int[] input) {
		// quickSortHoare(input, 0, input.length - 1);
		quickSortLomuto(input, 0, input.length - 1);
	}

	public static int quickSortPartitionLomuto(int[] input, int beg, int end) {
		int p = beg - 1;
		int pivot = input[end];
		int temp;
		for (int i = beg; i < end; i++) {
			if (input[i] > pivot) {
				p += 1;
				temp = input[i];
				input[i] = input[p];
				input[p] = temp;
			}
		}
		if (pivot > input[p + 1]) {
			temp = input[p + 1];
			input[p + 1] = input[end];
			input[end] = temp;
		}
		return p + 1;
	}

	public static int quickSortPartitionHoare(int[] input, int beg, int end) {
		int povit = input[beg];
		int i = beg - 1;
		int j = end + 1;
		int temp;
		while (true) {
			do {
				i = i + 1;
			} while (input[i] > povit);
			do {
				j = j - 1;
			} while (input[j] < povit);

			if (i >= j) {
				return j;
			}

			temp = input[i];
			input[i] = input[j];
			input[j] = temp;

		}
	}

	public static void quickSortHoare(int[] input, int beg, int end) {

		if (beg >= end) {
			return;
		}
		int p = quickSortPartitionHoare(input, beg, end);
		quickSortHoare(input, beg, p);
		quickSortHoare(input, p + 1, end);
	}

	public static void quickSortLomuto(int[] input, int beg, int end) {

		if (beg >= end) {
			return;
		}
		int p = quickSortPartitionLomuto(input, beg, end);
		// System.out.println("" + p + " " + input[p] + " , " + beg + " " +
		// end);
		// printArray(input);
		quickSortLomuto(input, beg, p - 1);
		quickSortLomuto(input, p + 1, end);
	}

	public static void maxHeapify(int[] arr, int index, int len, int rNum) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < rNum; i++) {
			sb.append("\t");
		}
		System.out.println(sb.toString() + "maxHeapify : " + index + ", " + len);
		int li = (index << 1) + 1;
		int ri = li + 1;
		int cMin = li;
		if (li > len) {
			return;
		}
		if (ri <= len && arr[ri] < arr[li]) {
			cMin = ri;
		}
		if (arr[cMin] < arr[index]) {
			int temp = arr[cMin];
			arr[cMin] = arr[index];
			arr[index] = temp;

			maxHeapify(arr, cMin, len, rNum + 1);
		}
	}

	public static void heapSort(int[] arr) {
		int len = arr.length - 1;
		int beginIndex = (len - 1) >> 1;
		System.out.println(len + " : " + beginIndex);
		for (int i = beginIndex; i >= 0; i--) {
			maxHeapify(arr, i, len, 1);

		}
		System.out.println("========================  sorting =====================");
		for (int i = len; i > 0; i--) {
			int temp = arr[0];
			arr[0] = arr[i];
			arr[i] = temp;
			maxHeapify(arr, 0, i - 1, 1);
		}
	}
}
