package org.mark.reactor;

import java.nio.charset.Charset;


public class EncodingHandler implements Handler {
	Charset charset = Charset.forName(System.getProperty("file.encoding"));
	
	@Override
	public void accept(ServerContext sc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void read(ServerContext sc) {
		System.out.println("Read : " + new String(sc.getMessage().getBytes(), charset));
	}

	@Override
	public void write(ServerContext sc) {
		sc.addResponse("Send : xiexie\n");
	}
	
}
