package org.mark.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class Client {

	public static void main(String[] args) throws IOException {
		final String encoding = System.getProperty("file.encoding");
		final Charset charset = Charset.forName(encoding);
		for (int i = 0; i < 1; i++) {
			new Thread() {

				@Override
				public void run() {
					SocketChannel channel = null;
					try {
						channel = SocketChannel.open();
						channel.connect(new InetSocketAddress("127.0.0.1", 8081));

						ByteBuffer buf = ByteBuffer.allocate(256);
						while (true) {
							buf.put(Thread.currentThread().toString()
									.getBytes());
							buf.put("\n".getBytes());
							buf.flip();
							channel.write(buf);
							buf.clear();
							channel.read(buf);
							System.out.print(charset.decode((ByteBuffer) buf
									.flip()));
							buf.clear();
							Thread.sleep(1000);
						}

					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						try {
							channel.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}

			}.start();
		}

	}

}
