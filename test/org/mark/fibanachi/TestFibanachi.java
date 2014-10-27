package org.mark.fibanachi;

import org.junit.Test;

import junit.framework.TestCase;

public class TestFibanachi extends TestCase {
	
	@Test
	public void testFibanachi() {
		Fibanachi fib = new Fibanachi();
		assertEquals(fib.fibanachi(1), 1);
		assertEquals(fib.fibanachi(2), 2);
		assertEquals(fib.fibanachi(3), 3);
		assertEquals(fib.fibanachi(4), 5);
		assertEquals(fib.fibanachi(5), 8);
		assertEquals(fib.fibanachi(6), 13);
	}
}
