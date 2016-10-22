package algorithms.search;

import java.util.List;

public abstract class CommonSearcher<T> implements Searcher<T>
{
	protected int evaluatedNodes;

	protected Solution<T> backTrace(State<T> goalState)
	{
		Solution<T> sol = new Solution<T>();
		
		State<T> currState = goalState;
		List<State<T>> states = sol.getStates();
		while (currState != null)
		{	
			states.add(0, currState);
			currState = currState.getCameFrom();
		}
		sol.setStates(states);
		return sol;
	}
	
	@Override
	public int getNumberOfNodesEvaluated()
	{		
		return evaluatedNodes;
	}
}