package algorithms.mazeGenerators;

/**
 * Abstract class which define all the members and methods which are common to 
 * Maze3dGenerator
 * @author orenk
 *
 */
public abstract class CommonMaze3dGenerator implements Maze3dGenerator {

	protected Maze3d maze;

	@Override
	public abstract Maze3d generate(int z, int y, int x); 

	@Override
	public String measureAlgorithmTime(int z, int y, int x) {
		long before = System.currentTimeMillis();
		generate(z, y, x);
		return String.format("Algorithm time is: %s", 
				String.valueOf(System.currentTimeMillis() - before));
	}
}