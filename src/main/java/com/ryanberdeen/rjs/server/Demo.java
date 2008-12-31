package com.ryanberdeen.rjs.server;

import java.io.IOException;
import java.util.HashSet;

/**
 * Demo {@link RjsHandler} that broadcasts all messages to all connected
 * clients.
 */
public class Demo extends RjsHandlerAdapter  {
	private HashSet<RjsClient> clients = new HashSet<RjsClient>();

	@Override
	public void clientConnected(RjsClient client) throws Exception {
		clients.add(client);
	}

	@Override
	public void clientDisconnected(RjsClient client) throws Exception {
		clients.remove(client);
	}

	public void messageReceived(RjsClient client, String message) throws Exception {
		for (RjsClient c : clients) {
			c.send(message);
		}
	}

	public static void main(String[] args) throws IOException {
		RjsServer server = new RjsServer();
		server.setPort(1843);
		server.setHandler(new Demo());
		server.start();
	}
}
