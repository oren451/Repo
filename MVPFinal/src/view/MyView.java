package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Observable;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import gui.windows.MainGameWindow;
import properties.PropertiesIO;

/**
 * Implements View Interface.
 * 
 * @author orenk
 */
public class MyView extends Observable implements IView {
	private final BufferedReader mIn;
	private final PrintWriter mOut;
	private final CommonUI mUi;

	public MyView(final BufferedReader in, final PrintWriter out) {
		mUi = chooseUIFromProperties();
		mIn = in;
		mOut = out;
	}

	private CommonUI chooseUIFromProperties() {
		switch (PropertiesIO.getProperties().getUserInterface()) {
		case "CLI":
		case "cli":
			return new CLI(this);
		case "GUI":
		case "gui":
			return new MainGameWindow(this);
		}
		return null;
	}

	@Override
	public void dbValues(final String databaseValues) {
		mUi.dbValues(databaseValues);
	}

	@Override
	public void dirReady(final String[] dirList) {
		if (dirList.length != 0)
			mUi.dirListReady(dirList);
		else
			printMessage("This path is empty!");

	}

	@Override
	public void displaySolution(final Solution<Position> solution) {
		mUi.displaySolution(solution);
	}

	public void executeCommand(final String commandLine) {
		setChanged();
		notifyObservers(commandLine);
	}

	@Override
	public void exit() {
		try {
			mIn.close();
		} catch (final IOException e) {
		}
		mOut.close();
		mUi.exitApplication();
	}

	@Override
	public void generatedMaze(final Maze3d maze, final String mazeName) {
		mUi.mazeIsReady(maze, mazeName);
	}

	public BufferedReader getIn() {
		return mIn;
	}

	@Override
	public String getInputFromUser() {

		String userInput = null;
		try {
			userInput = mIn.readLine();
		} catch (final IOException e) {
			printMessage("IO Exception");
		}
		return userInput;
	}

	public PrintWriter getOut() {
		return mOut;
	}

	@Override
	public void move(final Position pos) {
		mUi.move(pos);
	}

	@Override
	public void printMessage(final String msg) {
		mUi.printToConsole(msg);
	}

	public void printToOutputStream(final String msg) {
		mOut.println(msg);
	}

	@Override
	public void start() {
		mUi.startView();
	}

	@Override
	public void winner() {
		mUi.announcedWinner();
	}
}