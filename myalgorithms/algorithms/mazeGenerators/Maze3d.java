package algorithms.mazeGenerators;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Maze3d  implements Serializable 
{
	
	private static final long serialVersionUID = 1L;
	
	private int x, y, z;
	private int[][][] mMaze;
	private Position mStartPosition;
	private Position mGoalPosition;

	public Maze3d(byte[] b) {

		mStartPosition = new Position();
		mGoalPosition = new Position();
		mStartPosition.setX(b[0]);
		mStartPosition.setY(b[1]);
		mStartPosition.setZ(b[2]);

		mGoalPosition.setX(b[3]);
		mGoalPosition.setY(b[4]);
		mGoalPosition.setZ(b[5]);

		x = b[6];
		y = b[7];
		z = b[8];

		mMaze = new int[x][y][z];


		int i=9;

		for (int floor=0; floor<x; floor++)
			for(int row=0; row<y; row++)
				for(int column=0; column<z; column++)
				{
					mMaze[floor][row][column] = b[i];
					i++;
				}
	}



	public Maze3d(int x, int y, int z) {

		this.x = x;
		this.y = y;
		this.z = z;
		mMaze = new int[x][y][z];

	}


	boolean column_is_first_or_middle(Position cell)
	{
		return (cell.getZ()+1<getZ() && getMaze()[cell.getX()][cell.getY()][cell.getZ()+1] == 0);
	}



	boolean column_is_last_or_middle(Position cell)
	{
		return (cell.getZ()>0 && getMaze()[cell.getX()][cell.getY()][cell.getZ()-1] == 0);
	}


	@Override
	public boolean equals(Object obj) {
		Maze3d m=(Maze3d)obj;
		if (m.getX() == x && m.getY() == y && m.getZ() == z)
			if (m.getStartPosition() == mStartPosition && m.getGoalPosition() == mGoalPosition)
				for (int floor=0; floor<x; floor++)
					for(int row=0; row<y; row++)
						for(int column=0; column<z; column++)
							if(mMaze[floor][row][column] != m.getMaze()[floor][row][column])
								return false;
		return true;


	}


	boolean floor_is_first_or_middle(Position cell)
	{
		return (cell.getX()+1<getX() && getMaze()[cell.getX()+1][cell.getY()][cell.getZ()] == 0);
	}


	boolean floor_is_last_or_middle(Position cell)
	{
		return (cell.getX()>0 && getMaze()[cell.getX()-1][cell.getY()][cell.getZ()] == 0);
	}



	public int[][] getCrossSectionByX(int index)
	{
		if (index < x)
		{		


			int[][]maze2d = new int[y][z];
			for (int row=0; row<y; row++)
				for(int column=0; column<z; column++)
					maze2d[row][column]=mMaze[index][row][column];


			return maze2d;
		}

		else
		{
			IndexOutOfBoundsException exception=new IndexOutOfBoundsException();
			throw exception;
		}

	}

	public int[][] getCrossSectionByY(int index)
	{
		if (index < y)
		{
			int[][]maze2d = new int[x][z];

			for (int floor=0; floor<x; floor++)
				for(int column=0; column<z; column++)
					maze2d[floor][column]=mMaze[floor][index][column];

			return maze2d;
		}

		else
		{
			IndexOutOfBoundsException exception=new IndexOutOfBoundsException();
			throw exception;
		}

	}



	public int[][] getCrossSectionByZ(int index)
	{
		if (index < z)
		{
			int[][]maze2d = new int[x][y];

			for (int floor=0; floor<x; floor++)
				for(int row=0; row<y; row++)
					maze2d[floor][row]=mMaze[floor][row][index];


			return maze2d; 
		}
		else
		{
			IndexOutOfBoundsException exception=new IndexOutOfBoundsException();
			throw exception;
		}


	}



	public Position getGoalPosition() {
		return mGoalPosition;
	}

	public int[][][] getMaze() {
		return mMaze;
	}

	public ArrayList<Position> getPossibleMoves(Position cell)
	{
		ArrayList<Position> pMoves = new ArrayList<Position>();

		if (floor_is_first_or_middle(cell))
			pMoves.add(new Position(cell.getX()+1,cell.getY(),cell.getZ()));
		if (floor_is_last_or_middle(cell))
			pMoves.add(new Position(cell.getX()-1,cell.getY(),cell.getZ()));

		if (row_is_first_or_middle(cell))
			pMoves.add(new Position(cell.getX(),cell.getY()+1,cell.getZ()));
		if (row_is_last_or_middle(cell))
			pMoves.add(new Position(cell.getX(),cell.getY()-1,cell.getZ()));

		if (column_is_first_or_middle(cell))
			pMoves.add(new Position(cell.getX(),cell.getY(),cell.getZ()+1));
		if (column_is_last_or_middle(cell))
			pMoves.add(new Position(cell.getX(),cell.getY(),cell.getZ()-1));

		if (pMoves.isEmpty() == false)
			return pMoves;
		else
			return null;
	}

	public Position getStartPosition() {
		return mStartPosition;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	public boolean isWall(Position pos)
	{
		if (mMaze[pos.getX()][pos.getY()][pos.getZ()] == 1)
			return true;
		else return false;
	}


	public String[] print_moves(ArrayList<Position> pMoves)
	{
		if (pMoves.isEmpty() == false)
		{
			String[] moves = new String[pMoves.size()];
			for (int i=0; i<pMoves.size(); i++)
				moves[i]=pMoves.get(i).toString();
			return moves;
		}
		else return null;
	}



	public void print2dMaze(int[][] maze,int a,int b)
	{
		for (int i=0; i<a; i++)
		{
			for (int j=0; j<b; j++)
				System.out.print(maze[i][j]+" ");
			System.out.println();
		}
	}

	public int Randomize(int num)
	{
		Random random = new Random();
		int randomNum = random.nextInt(num);
		return randomNum;
	}

	boolean row_is_first_or_middle(Position cell)
	{
		return (cell.getY()+1<getY() && getMaze()[cell.getX()][cell.getY()+1][cell.getZ()] == 0);
	}

	boolean row_is_last_or_middle(Position cell)
	{
		return (cell.getY()>0 && getMaze()[cell.getX()][cell.getY()-1][cell.getZ()] == 0);
	}

	public void setGoalPosition(Position goalPosition) {
		mGoalPosition = goalPosition;
	}

	public void setMaze(int[][][] maze) {
		this.mMaze = maze;
	}


	public void setStartPosition(Position startPosition) {
		mStartPosition = startPosition;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public byte[] toByteArray()
	{
		/*
		 * 6=start(x,y,z)+goal(x,y,z) position.
		 * 3=size of maze(x,y,z).
		 * x*y*z=content of the maze.
		 * */
		int size = 6 + 3 + (x*y*z);
		byte[] MazeInfo = new byte[size];

		MazeInfo[0] = (byte)mStartPosition.getX();
		MazeInfo[1] = (byte)mStartPosition.getY();
		MazeInfo[2] = (byte)mStartPosition.getZ();


		MazeInfo[3] = (byte)mGoalPosition.getX();
		MazeInfo[4] = (byte)mGoalPosition.getY();
		MazeInfo[5] = (byte)mGoalPosition.getZ();


		MazeInfo[6] = (byte)x;
		MazeInfo[7] = (byte)y;
		MazeInfo[8] = (byte)z;

		int i=9;

		for (int floor=0; floor<x; floor++)
			for(int row=0; row<y; row++)
				for(int column=0; column<z; column++)
				{
					MazeInfo[i] = (byte)mMaze[floor][row][column];
					i++;
				}

		return MazeInfo;
	}

	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		for (int floor=0; floor<x; floor++){
			for(int row=0; row<y; row++){
				for(int column=0; column<z; column++){
					if (mStartPosition.getX() == floor && mStartPosition.getY() == row && mStartPosition.getZ() == column)
						sb.append("A ");
					else if (mGoalPosition.getX() == floor && mGoalPosition.getY() == row && mGoalPosition.getZ() == column)
						sb.append("Z ");
					else
						sb.append(mMaze[floor][row][column]+" ");
				}
				sb.append("\n");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public boolean validPos(Position pos) {
		if (pos.getX() < this.x && pos.getX() >= 0)
			if (pos.getY() < this.y && pos.getY() >= 0)
				if (pos.getZ() < this.z && pos.getZ() >= 0)
					return true;
		return false;
	}
}