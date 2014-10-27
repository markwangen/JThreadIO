package org.mark.synchronize;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestBlockingQueue {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final BlockingQueue<String> bq = new ArrayBlockingQueue<String>(2);
		
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new Runnable() {

			@Override
			public void run() {
				bq.poll();
			}
		});

		exec.execute(new Runnable() {

			@Override
			public void run() {
				try {
					bq.put("hello");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}

}
