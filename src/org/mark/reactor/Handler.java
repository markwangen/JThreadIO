package org.mark.reactor;

public interface Handler {
	public void accept(ServerContext sc);
	public void read(ServerContext sc);
	public void write(ServerContext sc);
}
