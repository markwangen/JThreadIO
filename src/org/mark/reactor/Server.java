package org.mark.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	private int port;
	private ArrayList<Handler> serverHandlers;
	private ArrayList<Handler> threadHandlers;
	
	public Server(int port) {
		this.port = port;
		this.serverHandlers = new ArrayList<Handler>();
		this.threadHandlers = new ArrayList<Handler>();
	}
	
	public void registServerHandler(Handler handler) {
		serverHandlers.add(handler);
	}
	
	public void removeServerHandler(Handler handler) {
		serverHandlers.remove(handler);
	}
	
	public void registThreadHandler(Handler handler) {
		threadHandlers.add(handler);
	}
	
	public void removeThreadHandler(Handler handler) {
		threadHandlers.remove(handler);
	}
	
	public void startServer() throws IOException, ClosedChannelException {
		ExecutorService exec = Executors.newCachedThreadPool();

		ServerSocketChannel ssc = ServerSocketChannel.open();
		ssc.bind(new InetSocketAddress(port));

		Selector sel = Selector.open();
		ssc.configureBlocking(false);
		ssc.register(sel, SelectionKey.OP_ACCEPT);

		while (true) {
			sel.select();
			Iterator<SelectionKey> ite = sel.selectedKeys().iterator();
			while (ite.hasNext()) {
				final SelectionKey key = ite.next();
				ite.remove();
				if ((key.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {
					ServerContext sc = new ServerContext();
					for(Handler handler : serverHandlers) {
						handler.accept(sc);
					}
					
					exec.execute(new Worker(ssc));
				}
			}
		}
	}

	private class Worker implements Runnable {
		private ByteBuffer bb;
		private SocketChannel ch;
		private Selector sel2;

		public Worker(ServerSocketChannel ssc) throws IOException {
			ch = ssc.accept();
			sel2 = Selector.open();
			ch.configureBlocking(false);
			ch.register(sel2, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
			this.bb = ByteBuffer.allocate(128);
		}

		public void run() {
			while (true) {
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
					if (key.isReadable()) {
						int len = 0;
						try {
							len = ch.read(bb);
						} catch (IOException e) {
							e.printStackTrace();
						}
						
						bb.flip();
						
						ServerContext sc = new ServerContext();
						byte[] bytes = new byte[len];
						bb.get(bytes);
						sc.addMessage(new String(bytes));
//						System.out.print(Charset.forName(System.getProperty("file.encoding")).decode(bb));
						for(Handler handler : threadHandlers) {
							handler.read(sc);
							handler.write(sc);
						}
						bb.clear();
						
						bb.put((Thread.currentThread().toString() + sc.getResponse()).getBytes());
						
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

	}
}
