package com.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.client.Problem;

import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

public class ClientHandler
{
	private Socket socket;
	private ServerModel model;
	public ClientHandler(Socket socket) 
	{
		this.socket = socket;
		model = new ServerModel();
	}
	
	private Solution<Position> solveProblem(Problem problem) 
	{
			return model.solve(problem.getMazeName(), problem.getMaze().getMaze(), problem.getSolveType());
	}
	
	public void start() 
	{
		try 
		{
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			Problem prob = (Problem)ois.readObject();
			System.out.println("Request Recieved");
			System.out.println("maze recieved: " + prob.getMazeName());
			Solution<Position> sol = solveProblem(prob);
			
			ObjectOutputStream ous = new ObjectOutputStream(socket.getOutputStream());
			ous.writeObject(sol);
			ous.close();
			socket.close();
			
		} 
		catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
