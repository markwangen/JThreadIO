package org.mark.synchronize;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class TestLock {
	public static class A {
		private Lock lock = new ReentrantLock();
		
		public void lock1() {
			try {
				lock.lock();
				System.out.println("method lock1 called");
				Thread.sleep(3000);
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				lock.unlock();
			}
		}
		
		public void lock2() {
			try {
				lock.lock();
				System.out.println("method lock2 called");
				Thread.sleep(3000);
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				lock.unlock();
			}
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final A a = new A();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new Runnable() {

			@Override
			public void run() {
				a.lock1();
			}
		});

		exec.execute(new Runnable() {

			@Override
			public void run() {
				a.lock2();
			}
		});
	}

}
