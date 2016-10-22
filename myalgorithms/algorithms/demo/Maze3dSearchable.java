package algorithms.demo;

import java.util.ArrayList;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Searchable;
import algorithms.search.State;

public class Maze3dSearchable implements Searchable<Position>
{
	private Maze3d mMaze;
	
	public Maze3dSearchable(Maze3d maze) {
		this.mMaze = maze;
	}
	
	@Override
	public ArrayList<State<Position>> getAllPossibleStates(State<Position> s) {
		
		ArrayList<Position> positionsList = new ArrayList<Position>();
		ArrayList<State<Position>> stateList = new ArrayList<State<Position>>(); 

		positionsList = mMaze.getPossibleMoves(s.getValue());
		
		if (positionsList.isEmpty() == false)
			for (int i=0; i<positionsList.size(); i++)
				stateList.add(new State<Position>(positionsList.get(i)));			

		if (stateList.isEmpty() == false)
			return stateList;
		else
			return null;
	}

	@Override
	public State<Position> getGoalState() {
		return new State<Position>(mMaze.getGoalPosition());
	}

	@Override
	public double getMoveCost(State<Position> currState, State<Position> neighbor) {
		return 10;
	}

	@Override
	public State<Position> getStartState() {
		return new State<Position>(mMaze.getStartPosition());
	}
}