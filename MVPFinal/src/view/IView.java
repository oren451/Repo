package view;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

/**
 * Interface for the view part in MVP arch.
 * 
 * @author orenk
 */
public interface IView {

	public void dbValues(String databaseValues);

	public void dirReady(String[] dirList);

	public void displaySolution(Solution<Position> solution);

	public void exit();

	public void generatedMaze(Maze3d maze, String mazeName);

	public String getInputFromUser();

	public void move(Position pos);

	public void printMessage(String msg);

	public void start();

	public void winner();

}