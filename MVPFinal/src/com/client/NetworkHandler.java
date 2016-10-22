package com.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

public class NetworkHandler {
	@SuppressWarnings("unchecked")
	public Solution<Position> sendProblem(Object obj) throws Exception {
		Socket socket = null;
		BufferedReader reader = null;
		try {

			socket = new Socket("localhost", 8000);
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(obj);
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			Solution<Position> result = (Solution<Position>) ois.readObject();
			return result;

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			reader.close();
			socket.close();
		}

		throw new Exception("Something went wrong");
	}
}