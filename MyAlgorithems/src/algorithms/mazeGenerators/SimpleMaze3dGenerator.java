package algorithms.mazeGenerators;

/**
 * Specific Implementation "SimpleMazeGenerator"
 * 	of our "problem" world "Maze3dGenerator"
 * @author orenk
 *
 */
public class SimpleMaze3dGenerator extends CommonMaze3dGenerator {

	@Override
	public Maze3d generate(int zDimension, int yDimension, int xDimension) {

		maze = new Maze3d(zDimension, yDimension, xDimension);
		
		Position pStart = maze.getRandomBoundaryPoint();
		Position pEnd = maze.getRandomBoundaryPoint();
		
		while (pStart == pEnd){
			pEnd = maze.getRandomBoundaryPoint();
		}
		
		maze.setStartPosition(pStart);
		maze.setEndPosition(pEnd);
		
		try {
			maze.createSimplePassageBetweenPositions(pStart, pEnd);
		} catch (Exception e) {
			System.out.println("Failed generate maze");
			e.printStackTrace();
		}
		
		maze.setAllDefaultPostionToNonRoute();
		maze.printImplMaze();

		return maze;
	}
}