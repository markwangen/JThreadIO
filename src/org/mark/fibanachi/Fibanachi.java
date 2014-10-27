package org.mark.fibanachi;

public class Fibanachi {
	public int fibanachi(int i) {
		if(i == 1 || i == 2) {
			return i;
		}
		
		return fibanachi(i - 2) + fibanachi(i - 1);
	}
}
