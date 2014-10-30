package org.mark.thread;

public class Restaurant {
	class Meal {}
	public Meal meal;
	public Customer customer;
	public Producer producer;
	
	public static void main(String[] args) {
		Restaurant res = new Restaurant();
		res.customer = new Customer(res);
		res.producer = new Producer(res);
		
		new Thread(res.customer).start();
		new Thread(res.producer).start();
	}

}
