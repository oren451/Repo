package model;

import java.io.Serializable;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

/**
 * The class SerializabledMaze implements Serializable interface
 * 
 * @author orenk
 */
@SuppressWarnings("serial")
public class SerializabledMaze implements Serializable {

	private Maze3d mMaze;
	private Position mCurrPosition;
	private Solution<Position> mSolution;

	public SerializabledMaze() {
		mMaze = null;
		mCurrPosition = null;
		mSolution = null;
	}

	public SerializabledMaze(final Maze3d maze) {
		mMaze = maze;
		mCurrPosition = maze.getStartPosition();
		mSolution = null;
	}

	public Position getCurrPosition() {
		return mCurrPosition;
	}

	public Maze3d getMaze() {
		return mMaze;
	}

	public Solution<Position> getSolution() {
		return mSolution;
	}

	public void setCurrPosition(final Position currPosition) {
		mCurrPosition = currPosition;
	}

	public void setMaze(final Maze3d maze) {
		mMaze = maze;
	}

	public void setSolution(final Solution<Position> solution) {
		mSolution = solution;
	}
}