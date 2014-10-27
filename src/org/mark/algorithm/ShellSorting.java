package org.mark.algorithm;

import java.util.Arrays;

/**
 * 此情况的O(N^2),采用Hibbard增量的希尔排序的时间复杂度为O(N^3/2)
 * @author wangmark
 *
 */
public class ShellSorting {
	
	public static void sort(int[] arrays) {
		for(int gap = arrays.length / 2; gap > 0; gap /= 2) {
			for(int i = gap; i < arrays.length; i ++) {
				for(int j = i; j >= gap; j -= gap) {
					if(arrays[j] < arrays[j - gap]) {
						int temp = arrays[j - gap];
						arrays[j - gap] = arrays[j];
						arrays[j] = temp;
					}
				}
			}
		}
		
		System.out.println(Arrays.toString(arrays));
	}
	
	public static void main(String[] args) {
		int[] arrays = new int[] {81, 94, 11, 96, 12, 35, 17, 95, 28, 58, 41, 75, 15};
		sort(arrays);
	}

}
