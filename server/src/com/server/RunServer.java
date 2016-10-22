package com.server;

public class RunServer {

	public static void main(String[] args) {

		final int PORT = 8000;
		
		MyTcpIpServer server = new MyTcpIpServer(PORT);
		server.startServer(50);
	}
}
