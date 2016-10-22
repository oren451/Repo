package algorithms.search;

import java.util.List;
import java.util.Stack;

public class DFS<T> extends CommonSearcher<T>
{
	Stack<State<T>> stack = new Stack<State<T>>();  
	Stack<State<T>> blackStack = new Stack<State<T>>();
	
	int flag;
	
	@Override
	public Solution<T> search(Searchable<T> s) 
	{
		State<T> startState = s.getStartState();
		State<T> goalState = s.getGoalState();
		
		stack.add(startState);
		
		  while (!stack.isEmpty())  
		  {
			  flag=0;
				State<T> currState = stack.pop();
				stack.add(currState);
				
				if (currState.equals(goalState))
					{
						return backTrace(currState);
					}
				
			   List<State<T>> neighbors = s.getAllPossibleStates(currState);
				if (! blackStack .contains(currState))
				{
					for (State<T> neighbor : neighbors)
					{  	
						if(!stack.contains(neighbor) && !blackStack .contains(neighbor))  
							{
							flag=1;
								stack.add(neighbor);  
								neighbor.setCameFrom(currState);
							}
					}
					if (flag == 0)
					{
						blackStack .add(currState);
					stack.remove(currState);
					}
				}	
				else
				{
					stack.remove(currState);
				}
		  }

		return null;
	}
}