package view;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

/**
 * The abstract class UI implements Runnable
 * 
 * @author orenk
 *
 */
public abstract class CommonUI {
	protected MyView mView;

	public abstract void announcedWinner();

	public abstract void dbValues(String dbValues);

	public abstract void dirListReady(String[] dirList);

	public abstract void displaySolution(Solution<Position> solution);

	public abstract void executeCommand(String commandLine);

	public abstract void exitApplication();

	public abstract void mazeIsReady(Maze3d maze, String mazeName);

	public abstract void move(Position pos);

	public abstract void printToConsole(String msg);

	public abstract void startView();

}