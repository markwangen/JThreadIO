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

public class MultiThreadServer {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		String encoding = System.getProperty("file.encoding");
		final Charset charset = Charset.forName(encoding);
		final ByteBuffer bb = ByteBuffer.allocate(256);

		ServerSocketChannel ssc = ServerSocketChannel.open();
		ssc.socket().bind(new InetSocketAddress(8081));
		ssc.configureBlocking(false);

		final Selector sel = Selector.open();
		ssc.register(sel, SelectionKey.OP_ACCEPT);

		while (true) {
			sel.select();
			Iterator<SelectionKey> ite = sel.selectedKeys().iterator();
			while (ite.hasNext()) {
				final SelectionKey key = ite.next();
				ite.remove();
				if ((key.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {
					final SocketChannel ch = ssc.accept();
					final Selector sel2 = Selector.open();
					ch.configureBlocking(false);
					ch.register(sel2, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
					System.out.println("A connection is comming");
					
					new Thread() {

						public void run() {
							while(true) {
								try {
									sel2.select();
								} catch (IOException e1) {
									e1.printStackTrace();
									return;
								}
								Iterator<SelectionKey> ite = sel2.selectedKeys().iterator();
								while (ite.hasNext()) {
									SelectionKey key = ite.next();
									ite.remove();
									if(key.isReadable()) {
										try {
											ch.read(bb);
										} catch (IOException e) {
											e.printStackTrace();
										}
										System.out.print(charset.decode((ByteBuffer)bb.flip()));
										bb.clear();
										bb.put((Thread.currentThread().toString() + " xiexie\n").getBytes());
										try {
											ch.write((ByteBuffer) bb.flip());
										} catch (IOException e) {
											e.printStackTrace();
										}
										bb.clear();
										try {
											Thread.sleep(1000);
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
									}
								}
							}
						}
						
					}.start();
				}
			}
		}
	}

}
