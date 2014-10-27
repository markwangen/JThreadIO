package org.mark.algorithm;

import junit.framework.TestCase;

public class BinarySearch extends TestCase {

	public static int binarySeach(int[] arrays, int target) {
		int leftIndex = 0;
		int rightIndex = arrays.length - 1;

		while (leftIndex <= rightIndex) {
			int index = (rightIndex + leftIndex) / 2;

			if (arrays[index] == target) {
				return index;
			} else if (arrays[index] > target) {
				rightIndex = index - 1;
			} else if (arrays[index] < target) {
				leftIndex = index + 1;
			}
		}

		return -1;
	}

	public void test() throws Exception {
		int[] arrays = { -3, -1, 5, 7, 11, 15, 19, 23, 34, 51, 59, 87, 93, 99,
				100 };
		assertEquals(binarySeach(arrays, 0), -1);
		assertEquals(binarySeach(arrays, 10), -1);
		assertEquals(binarySeach(arrays, 20), -1);
		assertEquals(binarySeach(arrays, 30), -1);
		assertEquals(binarySeach(arrays, 40), -1);
		assertEquals(binarySeach(arrays, 50), -1);
		assertEquals(binarySeach(arrays, -3), 0);
		assertEquals(binarySeach(arrays, -1), 1);
		assertEquals(binarySeach(arrays, 5), 2);
		assertEquals(binarySeach(arrays, 7), 3);
		assertEquals(binarySeach(arrays, 11), 4);
		assertEquals(binarySeach(arrays, 15), 5);
		assertEquals(binarySeach(arrays, 19), 6);
		assertEquals(binarySeach(arrays, 23), 7);
		assertEquals(binarySeach(arrays, 34), 8);
		assertEquals(binarySeach(arrays, 51), 9);
		assertEquals(binarySeach(arrays, 59), 10);
		assertEquals(binarySeach(arrays, 87), 11);
		assertEquals(binarySeach(arrays, 93), 12);
		assertEquals(binarySeach(arrays, 99), 13);
		assertEquals(binarySeach(arrays, 100), 14);
	}

}
