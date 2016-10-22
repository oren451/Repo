package com.server;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import algorithms.demo.Maze3dSearchable;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.BFS;
import algorithms.search.CommonSearcher;
import algorithms.search.DFS;
import algorithms.search.Solution;
public class ServerModel
{
	private final String cachePath = "cache.maz";

	private Map<String, Solution<Position>> mazesSolution = new ConcurrentHashMap<String, Solution<Position>>();

	//private ExecutorService threadService;

	public ServerModel()
	{
		readCache();
		//threadService =  Executors.newFixedThreadPool(50);
	}

	public void exit()
	{
		try
		{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(this.cachePath));			
			out.writeObject(this.mazesSolution);
			out.close();
		}
		catch(Exception e){}

	}

	@SuppressWarnings("unchecked")
	public void readCache()
	{
		try
		{
			ObjectInputStream ois =
					new ObjectInputStream(new FileInputStream(this.cachePath));			 
			this.mazesSolution = (Map<String,Solution<Position>>)ois.readObject();
			ois.close();
		}
		catch(Exception e){}		
	}

	public Solution<Position> solve(String name, Maze3d currMaze, String searcherName)
	{
		CommonSearcher<Position>  searcher;
		if (searcherName.contains("BFS") ||  searcherName.contains("bfs"))
		{
			searcher = new BFS<Position>();
		}
		else
		{
			searcher = new DFS<Position>();
		}
		
		
		System.out.println("Start Search algorithms" + searcherName);
		
		Solution<Position> solution = null;
		try
		{
			Maze3dSearchable mazeAdaptor = new Maze3dSearchable(currMaze);
			solution = searcher.search(mazeAdaptor);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		if (mazesSolution.containsKey(name)){
			mazesSolution.remove(name);
		}

		mazesSolution.put(name, solution);			
		System.out.println("Finish Search algorithms" + searcherName);
		return solution;
	}
}