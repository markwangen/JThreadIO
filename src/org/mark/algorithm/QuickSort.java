package org.mark.algorithm;

import java.util.Arrays;

public class QuickSort {
	public static int[] quickSort(int[] array) {
		quickSort(array, 0, array.length - 1);
		return array;
	}

	private static void quickSort(int[] array, int low, int high) {
		if (low < high) {
			int pivot = partition(array, low, high);
			quickSort(array, low, pivot - 1);
			quickSort(array, pivot + 1, high);
		}
	}

	private static int partition(int[] array, int low, int high) {
		int m = low + (high - low) / 2;
		if(array[low] > array[high]) {
			swap(array, low, high);
		}
		
		if(array[m] > array[high]) {
			swap(array, high, m);
		}
		
		if(array[m] > array[low]) {
			swap(array, m, low);
		}
		
		int temp = array[low];
		
		while (low < high) {
			while (low < high && array[high] >= temp) {
				high--;
			}

			swap(array, low, high);

			while (low < high && array[low] <= temp) {
				low++;
			}

			swap(array, low, high);
		}

		return low;
	}

	private static void swap(int[] array, int low, int high) {
		int temp = array[low];
		array[low] = array[high];
		array[high] = temp;
	}

	public static void main(String[] args) {
		int[] array = { 5, 3, 4, 2, 1, 2 };
		System.out.println(Arrays.toString(quickSort(array)));
	}

}
