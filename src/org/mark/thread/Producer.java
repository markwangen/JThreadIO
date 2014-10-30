package org.mark.thread;

public class Producer implements Runnable {
	private Restaurant res;

	public Producer(Restaurant res) {
		this.res = res;
	}

	@Override
	public void run() {
		while(!Thread.interrupted()) {
			while(res.meal != null) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
			res.meal = res.new Meal();
			System.out.println("�������������");
			synchronized(res.customer) {
				res.customer.notifyAll();
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
