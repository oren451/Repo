package model;

import java.io.IOException;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

/**
 * 
 * The model interface of the application as part of MVP arch.
 * 
 * @author orenk
 */
public interface IModel {
	public void displayCross(String[] args);

	public void displayFilesInPath(String[] args);

	public void displayMaze(String[] args);

	public void displaySolution(String[] args);

	public void exit();

	public void generateMaze(String[] args);

	public Position getCurrPosition();

	public void getCurrPosition(String[] args);

	public void getDbValues(String[] args);

	public Maze3d getMaze(String mazeName);

	public Solution<Position> getSolution(String mazeName);

	public void goBackward(String[] args);

	public void goDown(String[] args);

	public void goForward(String[] args);

	public void goLeft(String[] args);

	public void goLevelUp(String[] args);

	public void goRight(String[] args);

	public void loadMaze(String[] args) throws IOException;

	public void printToOutputStream(String out);

	public void saveMaze(String[] args) throws IOException;

	public void solve(String[] args);
}