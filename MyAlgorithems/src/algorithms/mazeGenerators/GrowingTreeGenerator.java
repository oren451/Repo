package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Specific Implementation "GrowingTreeGenerator"
 * 	of our "problem" world "Maze3dGenerator"
 * @author orenk
 *
 */
public class GrowingTreeGenerator extends CommonMaze3dGenerator {

	ItemChooser<Position> ic;

	public GrowingTreeGenerator(ItemChooser<Position> ic) {
		super();
		this.ic = ic;
	}

	@Override
	public Maze3d generate(int z, int y, int x) {
		try {

			List<Position> c = new ArrayList<Position>();
			maze = new Maze3d(z,y,x);
			Random rand = new Random();

			Position p = new Position(rand.nextInt(z), rand.nextInt(y), rand.nextInt(x));
			maze.setValue(p, Maze3d.ROUTE_SIGN);
			c.add(p);

			while(! c.isEmpty())
			{
				Position pFromc = ic.chooseItem(c);

				ArrayList<Position> validNeighbors = getAllValidNeighborsForPosition(pFromc);
				if(! validNeighbors.isEmpty())
				{
					Position n = validNeighbors.get(new Random().nextInt(validNeighbors.size()));
					maze.setValue(n, Maze3d.ROUTE_SIGN);
					maze.createPassageBetweenNeighbors(pFromc,n);
					c.add(n);
				}
				else
				{
					c.remove(pFromc);
				}
			}

			Position pStart = maze.getRandomBoundaryPoint();
			Position pEnd = maze.getRandomBoundaryPoint();

			while (pStart == pEnd)
			{
				pEnd = maze.getRandomBoundaryPoint();
			}
			
			maze.setStartPosition(pStart);
			maze.setEndPosition(pEnd);
			
			return maze;

		} catch (Exception e) {
			System.out.println("Failed generating maze");
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * Return all the neighbors with -1 value, means not visited.
	 * @param p - the point you search valid neighbors for.
	 * @return ArrayList of all the valid neighbors.
	 * @throws Exception
	 */
	protected ArrayList<Position> getAllValidNeighborsForPosition(Position p) throws Exception {

		ArrayList<Position> neighbors = maze.getNeighborsForPosition(p);
		ArrayList<Position> unvisitedNeighborsList = new ArrayList<>();
		for (Position neighbor : neighbors) {
			if (maze.getCellValue(neighbor) == Maze3d.UNINITIALIZED) {
				unvisitedNeighborsList.add(neighbor);
			}
		}
		return unvisitedNeighborsList;
	}
}