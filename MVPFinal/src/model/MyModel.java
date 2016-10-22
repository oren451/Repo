package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.client.NetworkHandler;
import com.client.Problem;

import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.mazeGenerators.SimpleMaze3dGenerator;
import algorithms.search.Solution;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;
import presenter.CommandsList;
import properties.PropertiesIO;

/**
 * Implements Model Interface. manages the data, logic and rules of the maze
 * 
 * @author orenk
 */
public class MyModel extends Observable implements IModel {
	private NetworkHandler mNetworkHandler;
	private Position mDestPos;
	private Maze3d mCurrMaze;
	private Position mCurrPos;

	private Solution<Position> mCurrSolution;
	private Map<String, SerializabledMaze> mMazeMap;
	private final ExecutorService mThreadPool;

	public MyModel() {
		mMazeMap = Collections.synchronizedMap(new HashMap<String, SerializabledMaze>());
		mThreadPool = Executors.newFixedThreadPool(PropertiesIO.getProperties().getThreadPoolNumber());
		mNetworkHandler = new NetworkHandler();
	}

	@Override
	public void displayCross(final String[] args) {
		int[][] crossSection = null;
		int index;
		if (args.length != 3)
			throw new IllegalArgumentException("Invalid Arguments!");
		try {
			index = Integer.parseInt(args[0]);
		} catch (final NumberFormatException e) {
			throw new IllegalArgumentException("Illegal Arguments!");
		}
		getMazeFromDatabase(args[2]);
		try {
			switch (args[1]) {
			case "X":
			case "x":
				crossSection = mCurrMaze.getCrossSectionByX(index);
				break;
			case "Y":
			case "y":
				crossSection = mCurrMaze.getCrossSectionByY(index);
				break;
			case "Z":
			case "z":
				crossSection = mCurrMaze.getCrossSectionByZ(index);
				break;
			default:
				throw new IllegalArgumentException("Invalid Arguments!");
			}
		} catch (final IndexOutOfBoundsException e) {
			throw new IllegalArgumentException("Invalid Arguments!");
		}
		setChanged();
		notifyObservers(CommandsList.DISPLAY_MESSAGE_CMD + " " + printMatrix(crossSection));
	}

	@Override
	public void displayFilesInPath(final String[] args) {
		final StringBuilder sb = new StringBuilder();
		if (args.length != 1)
			throw new IllegalArgumentException("Invalid Arguments!");
		final File folder = new File(args[0]);
		final File[] listOfFiles = folder.listFiles();
		if (listOfFiles == null)
			throw new NullPointerException("There is no such path!");
		for (int i = 0; i < listOfFiles.length; i++)
			sb.append(listOfFiles[i].getName() + "\n");
		setChanged();
		notifyObservers(CommandsList.DIR_READY_CMD + " " + sb.toString());
	}

	@Override
	public void displayMaze(final String[] args) {
		getMazeFromDatabase(args[0]);
		setChanged();
		notifyObservers(CommandsList.MAZE_IS_READY_CMD + " " + args[0]);
	}

	@Override
	public void displaySolution(final String[] args) {
		String out;
		if (args.length != 1)
			throw new IllegalArgumentException("Illegal Arguments!");
		getMazeFromDatabase(args[0]);
		if (mMazeMap.get(args[0]).getSolution() != null)
			out = mMazeMap.get(args[0]).getSolution().toString();
		else
			out = "no solution available";
		setChanged();
		notifyObservers(CommandsList.DISPLAY_MESSAGE_CMD + " " + out);
	}

	@Override
	public void exit() {
		// zipSave();
		try {
			mThreadPool.shutdownNow();
		} catch (final Exception e) {
		}
	}

	@Override
	public void generateMaze(final String[] args) {
		if (args.length != 4)
			throw new IllegalArgumentException("Illegal Arguments!");
		mThreadPool.submit(new Callable<Maze3d>() {

			@Override
			public Maze3d call() throws Exception {
				final int[] mazeDimensions = getMazeDimension(Arrays.copyOfRange(args, 1, args.length));
				final Maze3dGenerator mg = getAlgorithmFromXML();
				final SerializabledMaze maze = new SerializabledMaze(
						mg.generate(mazeDimensions[0], mazeDimensions[1], mazeDimensions[2]));
				putInDatabase(args[0], maze);
				setChanged();
				notifyObservers(CommandsList.MAZE_IS_READY_CMD + " " + args[0]);
				return maze.getMaze();
			}
		});
	}

	private Maze3dGenerator getAlgorithmFromXML() {
		switch (PropertiesIO.getProperties().getGenerateAlgorithm()) {
		case "GrowingTree":
		case "Growing Tree":
			return new GrowingTreeGenerator();
		case "Simple":
			return new SimpleMaze3dGenerator();
		default:
			return new SimpleMaze3dGenerator();
		}
	}

	@Override
	public Position getCurrPosition() {
		return mCurrPos;
	}

	@Override
	public void getCurrPosition(final String[] args) {
		getMazeFromDatabase(args[0]);
		setChanged();
		notifyObservers("MyPositionInitialized");
	}

	@Override
	public void getDbValues(final String[] args) {
		if (args.length != 0)
			throw new IllegalArgumentException("Illegal Arguments!");
		final StringBuilder sb = new StringBuilder();
		for (final Entry<String, SerializabledMaze> entry : mMazeMap.entrySet()) {
			sb.append(entry.getKey() + ",");
		}
		setChanged();
		notifyObservers(CommandsList.SET_DB_VALUES_CMD + " " + sb.toString());
	}

	@Override
	public Maze3d getMaze(final String mazeName) {
		getMazeFromDatabase(mazeName);
		return mCurrMaze;
	}

	public Map<String, SerializabledMaze> getMazeDatabase() {
		return mMazeMap;
	}

	private int[] getMazeDimension(final String[] args) {
		int floors, rows, cols;
		try {
			floors = Integer.parseInt(args[0]);
			rows = Integer.parseInt(args[1]);
			cols = Integer.parseInt(args[2]);
		} catch (final NumberFormatException e) {
			throw new IllegalArgumentException("Illegal Arguments!");
		}
		final int[] mazeDimensions = { floors, rows, cols };
		return mazeDimensions;
	}

	private void getMazeFromDatabase(final String name) {
		final SerializabledMaze maze = mMazeMap.get(name);
		if (maze == null)
			throw new IllegalArgumentException("can't find the maze: '" + name + "'");
		mCurrMaze = maze.getMaze();
		mCurrPos = maze.getCurrPosition();
		mCurrSolution = maze.getSolution();
	}

	@Override
	public Solution<Position> getSolution(final String mazeName) {
		getMazeFromDatabase(mazeName);
		return mCurrSolution;
	}

	@Override
	public void goBackward(final String[] args) {
		if (args.length != 1)
			throw new IllegalArgumentException("Illegal Arguments!");
		getMazeFromDatabase(args[0]);
		mDestPos = new Position(mCurrPos.getX(), mCurrPos.getY() - 1, mCurrPos.getZ());
		moveToDestPos();
	}

	@Override
	public void goDown(final String[] args) {
		if (args.length != 1)
			throw new IllegalArgumentException("Illegal Arguments!");
		getMazeFromDatabase(args[0]);
		mDestPos = new Position(mCurrPos.getX() - 1, mCurrPos.getY(), mCurrPos.getZ());
		moveToDestPos();
	}

	@Override
	public void goForward(final String[] args) {
		if (args.length != 1)
			throw new IllegalArgumentException("Illegal Arguments!");
		getMazeFromDatabase(args[0]);
		mDestPos = new Position(mCurrPos.getX(), mCurrPos.getY() + 1, mCurrPos.getZ());
		moveToDestPos();
	}

	@Override
	public void goLeft(final String[] args) {
		if (args.length != 1)
			throw new IllegalArgumentException("Illegal Arguments!");
		getMazeFromDatabase(args[0]);
		mDestPos = new Position(mCurrPos.getX(), mCurrPos.getY(), mCurrPos.getZ() - 1);
		moveToDestPos();
	}

	@Override
	public void goLevelUp(final String[] args) {
		if (args.length != 1)
			throw new IllegalArgumentException("Illegal Arguments!");
		getMazeFromDatabase(args[0]);
		mDestPos = new Position(mCurrPos.getX() + 1, mCurrPos.getY(), mCurrPos.getZ());
		moveToDestPos();
	}

	@Override
	public void goRight(final String[] args) {
		if (args.length != 1)
			throw new IllegalArgumentException("Illegal Arguments!");
		getMazeFromDatabase(args[0]);
		mDestPos = new Position(mCurrPos.getX(), mCurrPos.getY(), mCurrPos.getZ() + 1);
		moveToDestPos();
	}

	@Override
	public void loadMaze(final String[] args) throws IOException {

		if (args.length != 2)
			throw new IllegalArgumentException("Illegal Arguments!");

		Maze3d loadedMaze;
		File myFile = null;
		MyDecompressorInputStream input = null;
		myFile = new File("res/saved_mazes/" + args[0]);
		input = new MyDecompressorInputStream(new FileInputStream(myFile));
		final byte bytearray[] = new byte[(int) myFile.length() * 2];

		try {
			input.read(bytearray);
			input.close();
		} catch (final FileNotFoundException e) {
			throw new NullPointerException("File not found");
		}
		loadedMaze = new Maze3d(bytearray);
		if (loadedMaze != null) {
			mMazeMap.put(args[1], new SerializabledMaze(loadedMaze));
			setChanged();
			notifyObservers(CommandsList.MAZE_IS_READY_CMD + " " + args[1]);
		}
	}

	private void moveToDestPos() {
		String state;

		if (mCurrMaze.validPos(mDestPos) && !mCurrMaze.isWall(mDestPos)) {
			setCurrentToWanted();
			if (mCurrPos.equals(mCurrMaze.getGoalPosition()))
				state = CommandsList.WIN_CMD;
			else
				state = CommandsList.MOVE_CMD;
		} else {
			state = CommandsList.DISPLAY_MESSAGE_CMD + " THIS IS A WALL !!! ";
		}
		setChanged();
		notifyObservers(state);
	}

	private String printMatrix(final int[][] crossSection) {
		final StringBuilder sb = new StringBuilder();

		for (int i = 0; i < crossSection.length; i++) {
			for (int j = 0; j < crossSection[0].length; j++)
				sb.append(crossSection[i][j]);
			sb.append("\n");
		}
		return sb.toString();
	}

	@Override
	public void printToOutputStream(final String out) {
		setChanged();
		notifyObservers(out);
	}

	private void putInDatabase(final String key, final SerializabledMaze value) {
		mMazeMap.put(key, value);
	}

	@Override
	public void saveMaze(final String[] args) throws IOException {

		if (args.length != 2)
			throw new IllegalArgumentException("Illegal Arguments!");
		getMazeFromDatabase(args[0]);
		File myFile = null;
		MyCompressorOutputStream fileOutput = null;
		final byte[] bytearray = mCurrMaze.toByteArray();
		try {

			myFile = new File("res/saved_mazes/" + args[1]);
			fileOutput = new MyCompressorOutputStream(new FileOutputStream(myFile));
			fileOutput.write(bytearray);
			fileOutput.close();

		} catch (final FileNotFoundException e) {
			new IllegalArgumentException("File not found");
		}
		setChanged();
		notifyObservers(CommandsList.DISPLAY_MESSAGE_CMD + " Maze " + args[0] + " saved!");
	}

	private void setCurrentToWanted() {
		mCurrPos.setX(mDestPos.getX());
		mCurrPos.setY(mDestPos.getY());
		mCurrPos.setZ(mDestPos.getZ());

	}

	@Override
	public void solve(final String[] args) {
		if (args.length != 2)
			throw new IllegalArgumentException("Illegal Arguments!");
		getMazeFromDatabase(args[0]);
		if (mCurrSolution != null && mCurrPos.equals(mCurrSolution.getStates().get(0).getValue())) {
			setChanged();
			notifyObservers(CommandsList.SOLUTION_IS_READY_CMD + " " + args[0]);
		} else
			solveInThread(args);

	}

	private void solveInThread(final String[] args) {
		mThreadPool.submit(new Callable<Solution<Position>>() {

			@Override
			public Solution<Position> call() throws Exception {
				getMazeFromDatabase(args[0]);
				SerializabledMaze mazeRecord = new SerializabledMaze();
				Solution<Position> solution = null;
				mazeRecord = getMazeDatabase().get(args[0]);
				Problem problem = new Problem();
				problem.setMazeSearchable(mazeRecord);
				problem.setMazeName(args[0]);
				problem.setTask("solve");
				problem.setSolveType(args[1]);

				System.out.println("Start sending maze to server....");
				solution = mNetworkHandler.sendProblem(problem);
				System.out.println("Recieve maze from server....");
				mazeRecord.setSolution(solution);
				setChanged();
				notifyObservers(CommandsList.SOLUTION_IS_READY_CMD + " " + args[0]);
				return solution;
			}
		});
	}
}