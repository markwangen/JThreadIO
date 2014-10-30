package org.mark.thread;


public class Customer implements Runnable {
	private Restaurant res;

	public Customer(Restaurant res) {
		this.res = res;
	}

	@Override
	public void run() {
		while(!Thread.interrupted()) {
			synchronized(this) {
				while(res.meal == null) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
			res.meal = null;
			System.out.println("消费者消费完成");
			
			synchronized(res.producer) {
				res.producer.notifyAll();
			}
			
			synchronized(this) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
