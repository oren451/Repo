package com.server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class MyTcpIpServer
{
	private ServerSocket server;
	private ExecutorService executor;

	public MyTcpIpServer(int port)
	{	

		try 
		{
			server = new ServerSocket(port);
		}
		catch (IOException e) {
			System.out.println("Cannot listen on port " + port);
		}
	}

	public void startServer(int maxClientsNum) {
		executor = Executors.newFixedThreadPool(maxClientsNum);
		System.out.println("Server started");
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run()
			{
				while (true)
				{
					try {
						Socket socket = server.accept();
						System.out.println("Client connected");

						final ClientHandler handler = new ClientHandler(socket);
						executor.submit(new Runnable() {

							@Override
							public void run() {
								handler.start();								
							}							
						});

					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}			
		});

		thread.start();
	}
}