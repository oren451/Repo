package view;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

/**
 *
 * Command-Line Interface
 * 
 * @author orenk
 */
public class CLI extends CommonUI implements Runnable {
	private final MyView mView;
	private Thread mIOThread;

	public CLI(final MyView view) {
		mView = view;
	}

	@Override
	public void announcedWinner() {
		printToConsole("Winner! Winner! Winner!");
	}

	@Override
	public void dbValues(final String databaseValues) {
		printToConsole(databaseValues);
	}

	@Override
	public void dirListReady(final String[] dirList) {
		if (dirList.length != 0)
			printToConsole(dirList[0]);
		else
			printToConsole("Wrong path!!!");
	}

	@Override
	public void displaySolution(final Solution<Position> solution) {
		printToConsole(solution.toString());
	}

	@Override
	public void executeCommand(final String commandLine) {
		mView.executeCommand(commandLine);
	}

	@Override
	public void exitApplication() {
		mIOThread.interrupt();
	}

	public String getInputFromUser() {
		return mView.getInputFromUser();
	}

	@Override
	public void mazeIsReady(final Maze3d maze, final String mazeName) {
		printToConsole(maze.toString());
	}

	@Override
	public void move(final Position pos) {
		printToConsole(pos.toString());
	}

	@Override
	public void printToConsole(final String msg) {
		mView.printToOutputStream(msg);
	}

	@Override
	public void run() {
		mIOThread = new Thread(new Runnable() {

			@Override
			public void run() {
				String commandLine = null;
				do {
					commandLine = getInputFromUser();
					try {
						executeCommand(commandLine);
					} catch (final IllegalArgumentException e) {
						printToConsole(e.getMessage());
					} catch (final NullPointerException e) {
						printToConsole(e.getMessage());
					}
				} while (!(commandLine.equals("exit")));
			}
		});
		mIOThread.start();
	}

	@Override
	public void startView() {
		run();
	}
}