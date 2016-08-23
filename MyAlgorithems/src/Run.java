import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.mazeGenerators.RandomItemChooser;
import algorithms.search.BFS;
import algorithms.search.DFS;
import algorithms.search.MazeSearchable;
import algorithms.search.Searchable;
import algorithms.search.Searcher;

public class Run {

	public static void main(String[] args) {
		testSearcher(new BFS<Position>(),
			new MazeSearchable<Position>(new GrowingTreeGenerator(new RandomItemChooser<>())
					.generate(2, 2, 2)));
		
		testSearcher(new DFS<Position>(),
				new MazeSearchable<Position>(new GrowingTreeGenerator(new RandomItemChooser<>())
						.generate(2, 2, 2)));
	}

	private static void testSearcher(Searcher<Position> searcher, Searchable<Position> searchable) {

		searcher.search(searchable); //Here we play the search algorithm
		System.out.println("Number of evaluated nodes: " + searcher.getNumberOfNodesEvaluated());
	}
}
