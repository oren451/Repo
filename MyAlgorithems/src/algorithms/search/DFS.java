package algorithms.search;

import java.util.HashMap;
import java.util.LinkedList;

import algorithms.mazeGenerators.Position;

public class DFS<T> extends CommonSearcher<T> {

	private LinkedList<State<T>> visitedStates = new LinkedList<State<T>>();
	
	@Override
	public Solution<T> search(Searchable<T> s) {

		dfs(s, s.getStartState());
		return backTrace(s.getGoalState(), s.getStartState()); 
	}

	private void dfs(Searchable<T> s, State<T> currState)
	{
		if (currState.equals(s.getGoalState()))
		{
			return;
		}

		visitedStates.add(currState);

		//What is action? Try to match it to the exist infra 
		HashMap<Action,State<T>> actions = s.getAllPossibleStates(currState);

		for(State<T> neighbor: actions.values())
		{
			if (! visitedStates.contains(neighbor)) 
			{
				neighbor.setCameFrom(currState);
				//add this function where it belongs 
				increaseEvaluatedNodes();
				dfs(s, neighbor);
			}
		}
	}	
}	