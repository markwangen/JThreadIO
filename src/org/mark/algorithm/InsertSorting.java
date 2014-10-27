package org.mark.algorithm;

import java.util.Arrays;

/**
 * O(N^2)
 * @author wangmark
 *
 */
public class InsertSorting {
	
	public static void sort(int[] arrays) {
		for(int i = 0; i < arrays.length; i++) {
			for(int j = i; j > 0; j --) {
				if(arrays[j] < arrays[j - 1]) {
					int temp = arrays[j - 1];
					arrays[j - 1] = arrays[j];
					arrays[j] = temp;
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
