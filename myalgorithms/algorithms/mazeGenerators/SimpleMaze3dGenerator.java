package algorithms.mazeGenerators;
import java.util.Random;


public class SimpleMaze3dGenerator extends CommonMaze3dGenerator {
	
	public void fillMaze(Maze3d maze)
	{
		Random random = new Random();
		int randomNum;
		for (int x = 0; x<maze.getX(); x++)
			for (int y = 0; y<maze.getY(); y++)
				for (int z = 0; z<maze.getZ(); z++)
				{
					randomNum = random.nextInt(2);
					maze.getMaze()[x][y][z] = randomNum;
				}
	}
	
	@Override
	public Maze3d generate(int x,int y,int z) {
	
	Maze3d simpleMaze = new Maze3d(x,y,z);
	
	fillMaze(simpleMaze);
	
	simpleMaze.setStartPosition(randomizeCoordinates(x, y, z));
	simpleMaze.setGoalPosition(randomizeCoordinates(x, y, z));
	
	while(simpleMaze.getStartPosition().getX() == simpleMaze.getGoalPosition().getX() && simpleMaze.getStartPosition().getY() == simpleMaze.getStartPosition().getY() && simpleMaze.getStartPosition().getZ() == simpleMaze.getStartPosition().getZ())
	{
		simpleMaze.setStartPosition(randomizeCoordinates(x, y, z));
		simpleMaze.setGoalPosition(randomizeCoordinates(x, y, z));
	}	
	
	make_track_to_goal(simpleMaze);
	

	return simpleMaze;
	}
	
	public void make_track_to_goal(Maze3d simpleMaze)
	{
		Position startTemp = new Position(simpleMaze.getStartPosition().getX(),simpleMaze.getStartPosition().getY(),simpleMaze.getStartPosition().getZ());

		while (startTemp.equals(simpleMaze.getGoalPosition()) == false)
		{
			
			if (startTemp.getX()<simpleMaze.getGoalPosition().getX())
			{
				startTemp.setX(startTemp.getX()+1);
				simpleMaze.getMaze()[startTemp.getX()][startTemp.getY()][startTemp.getZ()]=0;
			}
			
			if (startTemp.getY()<simpleMaze.getGoalPosition().getY())
			{
				startTemp.setY(startTemp.getY()+1);
				simpleMaze.getMaze()[startTemp.getX()][startTemp.getY()][startTemp.getZ()]=0;
			}
			
			if (startTemp.getZ()<simpleMaze.getGoalPosition().getZ())
			{
				startTemp.setZ(startTemp.getZ()+1);
				simpleMaze.getMaze()[startTemp.getX()][startTemp.getY()][startTemp.getZ()]=0;
			}
			
			if (startTemp.getX()>simpleMaze.getGoalPosition().getX())
			{
				startTemp.setX(startTemp.getX()-1);
				simpleMaze.getMaze()[startTemp.getX()][startTemp.getY()][startTemp.getZ()]=0;
			}
			
			if (startTemp.getY()>simpleMaze.getGoalPosition().getY())
			{
				startTemp.setY(startTemp.getY()-1);
				simpleMaze.getMaze()[startTemp.getX()][startTemp.getY()][startTemp.getZ()]=0;
			}
			
			if (startTemp.getZ()>simpleMaze.getGoalPosition().getZ())
			{
				startTemp.setZ(startTemp.getZ()-1);
				simpleMaze.getMaze()[startTemp.getX()][startTemp.getY()][startTemp.getZ()]=0;
			}
		}
	}
	
	public int randomize(int num)
	{
        Random random = new Random();
        int randomNum = random.nextInt(num);
        return randomNum;
	}

	public Position randomizeCoordinates(int x,int y,int z)
	{
		Position p = new Position(x,y,z);
		p.setX(randomize(x));
		p.setY(randomize(y));
		p.setZ(randomize(z));
		
		return p;
	}
}