package algorithms.search;

public interface Searcher<T> {

	public int getNumberOfNodesEvaluated();

	public Solution<T> search(Searchable<T> s);
}



