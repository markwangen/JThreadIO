package org.mark.reactor;

import java.io.IOException;
import java.util.concurrent.Executors;

public class ServerTest {

	public static void main(String[] args) throws IOException {
		Executors.newCachedThreadPool();
		Server server = new Server(8080);
		server.registServerHandler(new LoggingHandler());
		server.registThreadHandler(new EncodingHandler());
		server.startServer();
	}

}
