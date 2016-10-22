package algorithms.search;

import java.util.ArrayList;

public interface Searchable<T> {

	ArrayList<State<T>> getAllPossibleStates(State<T> s);

	State<T> getGoalState();

	double getMoveCost(State<T> currState, State<T> neighbor);

	State<T> getStartState();
}
