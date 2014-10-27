package org.mark.reactor;

public class LoggingHandler implements Handler {

	@Override
	public void accept(ServerContext sc) {
		// TODO Auto-generated method stub
		System.out.println("A connection is comming");
	}

	@Override
	public void read(ServerContext sc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void write(ServerContext sc) {
		// TODO Auto-generated method stub
		
	}
}
