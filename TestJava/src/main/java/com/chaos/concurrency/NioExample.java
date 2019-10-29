package com.chaos.concurrency;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class NioExample extends Thread {
	private Selector selector;
	private List<String> sendBuffer;
	private Map<String, SocketChannel> cliChannels;

	private boolean init(int port) {
		sendBuffer = new ArrayList<String>();
		cliChannels = new HashMap<String, SocketChannel>();
		try {
			this.selector = Selector.open();
			ServerSocketChannel serverChannel = ServerSocketChannel.open();
			serverChannel.configureBlocking(false);
			serverChannel.socket().bind(new InetSocketAddress("0.0.0.0", port));
			serverChannel.register(selector, SelectionKey.OP_ACCEPT);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public void wakeup() {
		selector.wakeup();
	}

	public void run() {
		init(10000);
		System.out.println("started");
		System.out.println("run begin");
		try {
			listen();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("run end");
		}
	}

	public void listen() throws Exception {
		System.out.println("start server");
		while (true) {
			selector.select();
			System.out.println("selecting ");

			for (String tStr : sendBuffer) {
				for (Entry<String, SocketChannel> en : cliChannels.entrySet()) {
					en.getValue().write(ByteBuffer.wrap(tStr.getBytes()));
				}
			}
			sendBuffer.clear();

			Iterator<SelectionKey> ite = this.selector.selectedKeys().iterator();
			while (ite.hasNext()) {
				SelectionKey key = (SelectionKey) ite.next();
				ite.remove();
				if (key.isAcceptable()) {
					ServerSocketChannel server = (ServerSocketChannel) key.channel();
					SocketChannel channel = server.accept();
					channel.configureBlocking(false);
					channel.write(ByteBuffer.wrap(new String("hello client").getBytes()));
					channel.register(this.selector, SelectionKey.OP_READ);
					System.out.println(channel.toString() + "  " + channel.getRemoteAddress() + " "
							+ channel.getLocalAddress());
					cliChannels.put(channel.toString(), channel);

				} else if (key.isReadable()) {
					read(key);
				} else {
					System.out.println("Enter into no loop");
				}
			}
		}
	}

	private void read(SelectionKey key) throws Exception {
		SocketChannel channel = (SocketChannel) key.channel();
		ByteBuffer buffer = ByteBuffer.allocate(1048);
		int len = channel.read(buffer);
		if (len == -1) {
			System.out.println("client " + channel.toString() + " is close");
			channel.close();
			cliChannels.remove(channel.toString());
			// socketChannelList.remove(channel);
		} else {
			byte[] data = buffer.array();
			String msg = new String(data).trim();
			System.out.println("server receive from client: " + msg);
			ByteBuffer outBuffer = ByteBuffer.wrap(msg.getBytes());
			channel.write(outBuffer);
		}
	}

	public void send(String str) {
		sendBuffer.add(str);
	}

	public static void main(String[] args) throws Throwable {
		NioExample server = new NioExample();
		server.start();
		int k = 0;
		while (true) {
			System.out.println("Main run ");
			Thread.sleep(10000);
			k++;
			server.send("send to client " + k);
			server.wakeup();
		}
	}

}
