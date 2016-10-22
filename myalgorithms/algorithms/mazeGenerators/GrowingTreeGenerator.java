package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Random;

import algorithms.search.IChooser;
import algorithms.search.LastChooser;

public class GrowingTreeGenerator extends CommonMaze3dGenerator {
	
	IChooser choise = new LastChooser();
	Position cell;
	Position neighbor;
	

	public Position chooseNeighbor(Maze3d maze, Position cell)
	{
		ArrayList<Position> neighbors = new ArrayList<Position>();
		
		if (floor_is_first_or_middle(maze, cell))
			neighbors.add(new Position(cell.getX()+1,cell.getY(),cell.getZ()));
		if (floor_is_last_or_middle(maze, cell))
			neighbors.add(new Position(cell.getX()-1,cell.getY(),cell.getZ()));
		
		if (row_is_first_or_middle(maze, cell))
			neighbors.add(new Position(cell.getX(),cell.getY()+2,cell.getZ()));
		if (row_is_last_or_middle(maze, cell))
			neighbors.add(new Position(cell.getX(),cell.getY()-2,cell.getZ()));
		
		if (column_is_first_or_middle(maze, cell))
			neighbors.add(new Position(cell.getX(),cell.getY(),cell.getZ()+2));
		if (column_is_last_or_middle(maze, cell))
			neighbors.add(new Position(cell.getX(),cell.getY(),cell.getZ()-2));
		
		
		if (neighbors.isEmpty() == false)
		{
		 int randomize_free_neighbor_index;
		 randomize_free_neighbor_index = randomize(neighbors.size());
		 return neighbors.get(randomize_free_neighbor_index);
		}
		else
			return null;
		
	}

	
	boolean column_is_first_or_middle(Maze3d maze,Position cell)
	{
		return (cell.getZ()+1<maze.getZ() && maze.getMaze()[cell.getX()][cell.getY()][cell.getZ()+2] == 1);
	}

	
	boolean column_is_last_or_middle(Maze3d maze,Position cell)
	{
		return (cell.getZ()>0 && maze.getMaze()[cell.getX()][cell.getY()][cell.getZ()-2] == 1);
	}
	
	void fillWithWalls(Maze3d maze)
	{
		
		for (int x=0; x<maze.getX(); x++)
			for (int y=0; y<maze.getY(); y++)
				for (int z=0; z<maze.getZ(); z++)
				{
					maze.getMaze()[x][y][z]=1;
				}
	}
	
	boolean floor_is_first_or_middle(Maze3d maze,Position cell)
	{
		return (cell.getX()+1<maze.getX() && maze.getMaze()[cell.getX()+1][cell.getY()][cell.getZ()] == 1);
	}
	
	
	boolean floor_is_last_or_middle(Maze3d maze,Position cell)
	{
		return (cell.getX()>0 && maze.getMaze()[cell.getX()-1][cell.getY()][cell.getZ()] == 1);
	}
	
	@Override
	public Maze3d generate(int x, int y, int z) {

		Maze3d maze = new Maze3d(x,y,z);
		ArrayList<Position> C = new ArrayList<Position>();
		
		fillWithWalls(maze);
		
		C.add(getRandomPosition(x,y,z));
		
		maze.setStartPosition(C.get(0));
		

		while(C.isEmpty()==false)
		{
			cell = choise.choose(C);
			
			maze.getMaze()[cell.getX()][cell.getY()][cell.getZ()]=0;
												
			neighbor = chooseNeighbor(maze,cell);
			
			if (neighbor != null)
			{
				make_transition_to_neighbor(maze,cell,neighbor);
				C.add(neighbor);
			}
			else
			{
					if(maze.getStartPosition().getX() != cell.getX() && maze.getStartPosition().getY() != cell.getY() && maze.getStartPosition().getZ() != cell.getZ())
						maze.setGoalPosition(new Position(cell.getX(),cell.getY(),cell.getZ()));	
					
				C.remove(cell);	
			}
							
		}
		
		return maze;
	}
	
	public Position getRandomPosition(int x,int y,int z)
	{
		
		Position p=new Position(x,y,z);
		p.setX(randomize(x));
		p.setY(1);
		do{p.setY(randomize(y));}
		while (p.getY()%2 == 1);
		p.setZ(1);
		do{p.setZ(randomize(z));}
		while (p.getZ()%2 == 1);
		
		return p;
	}
	
	//make a transition from a cell to a neighbor
	void make_transition_to_neighbor(Maze3d maze,Position cell,Position neighbor)
	{
		//if neighbor is located up
		if (cell.getX()-neighbor.getX() > 0)
		{
			//make the neighbor accessible 
			maze.getMaze()[cell.getX()-1][cell.getY()][cell.getZ()]=0;
		}
		
		//if neighbor is located down
		if (cell.getX()-neighbor.getX() < 0)
		{
			//make the neighbor accessible 
			maze.getMaze()[cell.getX()+1][cell.getY()][cell.getZ()]=0;
		}
		
		
		//if neighbor is located backward
		if (cell.getY()-neighbor.getY() > 0)
		{
			//remove wall
			maze.getMaze()[cell.getX()][cell.getY()-1][cell.getZ()]=0;
			//make the neighbor accessible 
			maze.getMaze()[cell.getX()][cell.getY()-2][cell.getZ()]=0;
		}
		
		//if neighbor is located forward
		if (cell.getY()-neighbor.getY() < 0)
		{
			//remove wall
			maze.getMaze()[cell.getX()][cell.getY()+1][cell.getZ()]=0;
			//make the neighbor accessible 
			maze.getMaze()[cell.getX()][cell.getY()+2][cell.getZ()]=0;
		}
		
		
		
		//if neighbor is located on the left
		if (cell.getZ()-neighbor.getZ() > 0)
		{
			//remove wall
			maze.getMaze()[cell.getX()][cell.getY()][cell.getZ()-1]=0;
			//make the neighbor accessible 
			maze.getMaze()[cell.getX()][cell.getY()][cell.getZ()-2]=0;
		}
		
		//if neighbor is located right
		if (cell.getZ()-neighbor.getZ() < 0)
		{
			//remove wall
			maze.getMaze()[cell.getX()][cell.getY()][cell.getZ()+1]=0;
			//make the neighbor accessible 
			maze.getMaze()[cell.getX()][cell.getY()][cell.getZ()+2]=0;
		}
		

		
	}
	
	public int randomize(int num)
	{
        Random random = new Random();
        int randomNum = random.nextInt(num);
        return randomNum;
	}
	
	boolean row_is_first_or_middle(Maze3d maze,Position cell)
	{
		return (cell.getY()+1<maze.getY() && maze.getMaze()[cell.getX()][cell.getY()+2][cell.getZ()] == 1);
	}
	
	
	boolean row_is_last_or_middle(Maze3d maze,Position cell)
	{
		return (cell.getY()>0 && maze.getMaze()[cell.getX()][cell.getY()-2][cell.getZ()] == 1);
	}
}