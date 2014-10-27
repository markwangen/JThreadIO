package org.mark.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

public class Server {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		String encoding = System.getProperty("file.encoding");
		final Charset charset = Charset.forName(encoding);
		SocketChannel ch;
		final ByteBuffer bb = ByteBuffer.allocate(256);

		ServerSocketChannel ssc = ServerSocketChannel.open();
		ssc.socket().bind(new InetSocketAddress(8081));
		ssc.configureBlocking(false);

		Selector sel = Selector.open();
		ssc.register(sel, SelectionKey.OP_ACCEPT);

		while (true) {
			sel.select();
			Iterator<SelectionKey> ite = sel.selectedKeys().iterator();
			while (ite.hasNext()) {
				final SelectionKey key = ite.next();
				ite.remove();
				if ((key.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {
					ch = ssc.accept();
					ch.configureBlocking(false);
					ch.register(sel, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
					System.out.println("A connection is comming");
				} else if ((key.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ) {
					ch = (SocketChannel) key.channel();
					try {
						ch.read(bb);
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.out.print(charset.decode((ByteBuffer)bb.flip()));
					bb.clear();
					bb.put((System.currentTimeMillis() + " xiexie\n").getBytes());
					try {
						ch.write((ByteBuffer) bb.flip());
					} catch (IOException e) {
						e.printStackTrace();
					}
					bb.clear();
					
				}
			}
		}
	}

}
