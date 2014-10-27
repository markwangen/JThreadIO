package org.mark.algorithm;


public class BinaryHeap {
	private static final int DEFAULT_CAPACITY = 10;
	private int currentSize;
	private int[] array;

	public BinaryHeap(int[] items) {
		currentSize = items.length;
		array = new int[(currentSize + 2) * 11 / 10];

		int i = 1;
		for (int item : items) {
			array[i++] = item;
		}

		buildHeap();
	}

	public void insert(int x) {
		if (currentSize == array.length - 1) {
			enlargeArray(array.length * 2 + 1);
		}

		int hole = ++currentSize;
		for (; hole > 1 && x < array[hole / 2]; hole /= 2) {
			array[hole] = array[hole / 2];
		}

		array[hole] = x;
	}

	public int findMin() {
		return array[0];
	}

	public int deleteMin() throws Exception {
		if (isEmpty()) {
			throw new Exception();
		}

		int minItem = findMin();
		array[1] = array[currentSize--];
		percolateDown(1);

		return minItem;
	}

	public boolean isEmpty() {
		if (currentSize <= 0) {
			return false;
		}

		return true;
	}

	public void makeEmpty() {
		currentSize = 0;
		for (int i = 0; i < array.length; i++) {
			array[i] = 0;
		}
	}

	private void percolateDown(int hole) {
		int child;

		int tmp = array[hole];
		for (; hole * 2 <= currentSize; hole = child) {
			child = hole * 2;
			if (child != currentSize && array[child + 1] < array[child]) {
				child++;
			}

			if (array[child] < tmp) {
				array[hole] = array[child];
			} else {
				break;
			}
		}

		array[hole] = tmp;
	}

	private void buildHeap() {
		for (int i = currentSize / 2; i > 0; i--) {
			percolateDown(i);
		}
	}

	private void enlargeArray(int newSize) {
		int[] arraytemp = new int[newSize];
		System.arraycopy(array, 0, arraytemp, 0, array.length);
		array = arraytemp;
	}
}
