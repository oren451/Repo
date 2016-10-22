package com.client;

import java.io.Serializable;

import model.SerializabledMaze;

public class Problem implements Serializable {
	private static final long serialVersionUID = 1L;

	private String mMazeName;
	private String mTask;
	private SerializabledMaze mMaze;
	private String mSolveType;

	public SerializabledMaze getMaze() {
		return mMaze;
	}

	public String getMazeName() {
		return mMazeName;
	}

	public String getSolveType() {
		return mSolveType;
	}

	public String getTask() {
		return mTask;
	}

	public void setMazeName(String mazeName) {
		this.mMazeName = mazeName;
	}

	public void setMazeSearchable(SerializabledMaze maze) {
		this.mMaze = maze;
	}

	public void setSolveType(String solveType) {
		this.mSolveType = solveType;
	}

	public void setTask(String task) {
		this.mTask = task;
	}
}
