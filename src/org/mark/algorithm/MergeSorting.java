package org.mark.algorithm;

import java.util.Arrays;

/**
 * O(NlogN)
 * @author wangmark
 *
 */
public class MergeSorting {

	public static void sort(int[] arrays) {
		int[] tempArray = new int[arrays.length];
		mergeSort(arrays, tempArray, 0, arrays.length - 1);
		
		System.out.println(Arrays.toString(arrays));
	} 
	
	public static void mergeSort(int[] arrays, int[] tempArray, int left, int right) {
		if(left < right) {
			int center = (left + right) / 2;
			mergeSort(arrays, tempArray, left, center);
			mergeSort(arrays, tempArray, center + 1, right);
			merge(arrays, tempArray, left, center + 1, right);
		}
	}
	
	public static void merge(int[] arrays, int[] tempArray, int leftPos, int rightPos, int rightEnd) {
		int tmpPos = leftPos;
		int tmpPos2 = leftPos;
		int leftEnd = rightPos - 1;
		while(leftPos <= leftEnd && rightPos <= rightEnd) {
			if(arrays[leftPos] < arrays[rightPos]) {
				tempArray[tmpPos ++] = arrays[leftPos ++];
			} else {
				tempArray[tmpPos ++] = arrays[rightPos ++];
			}
		}
		
		while(leftPos <= leftEnd) {
			tempArray[tmpPos ++] = arrays[leftPos++];
		}
		
		while(rightPos <= rightEnd) {
			tempArray[tmpPos ++] = arrays[rightPos++];
		}
		
		while(tmpPos2 <= rightEnd) {
			arrays[tmpPos2] = tempArray[tmpPos2 ++];
		} 
	}
	
	public static void main(String[] args) {
		int[] arrays = new int[] {81, 94, 11, 96, 12, 35, 17, 95, 28, 58, 41, 75, 15};
		sort(arrays);
	}

}
