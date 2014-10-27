package org.mark.exception;

public class TestException {

	/**
	 * Test wether exception will shutdown the jvm.
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new Thread("HelloThread") {
			public void run() {
				try {
					Thread.sleep(50000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
		
		Thread.currentThread().setName("MarkWang");
		test();
	}
	
	public static void test() {
		throw new RuntimeException();
	}

}
