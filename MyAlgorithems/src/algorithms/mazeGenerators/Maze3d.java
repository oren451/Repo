package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Random;

import algorithms.search.CommonSearchDomain;

/**
 * Specific Domain of our "Problem" describing a maze build by 3D array of integers.
 * @author orenk
 *
 */
public class Maze3d extends CommonSearchDomain {

	public final static int ROUTE_SIGN = 0;
	public final static int NONROUTE_SIGN = 1;
	public final static int UNINITIALIZED = -1;

	private int zDimension;
	private int yDimension;
	private int xDimension;
	private Position pStart;
	private Position pEnd;

	/**
	 * The first coordinate describing the Z axis
	 * The second coordinate describing the Y axis
	 * The third coordinate describing the X axis
	 */
	private int[][][] mMazeArray;

	/**
	 *  Constructor
	 */
	public Maze3d(int z, int y, int x)
	{
		zDimension = z;
		yDimension = y;
		xDimension = x;

		mMazeArray = new int[2*zDimension+1][2*yDimension+1][2*xDimension+1];

		//Fill the maze with NONROUTE_SIGN (1)
		for (int i = 0; i < 2*z+1; i++) {
			for (int j = 0; j < 2*y+1; j++) {
				for (int k = 0; k < 2*x+1; k++) {
					mMazeArray[i][j][k] = NONROUTE_SIGN;
				}
			}
		}

		//Fill the 2*index + 1 with UNINITIALIZED (-)
		for (int i = 0; i < zDimension; i++) {
			for (int j = 0; j < yDimension; j++) {
				for (int k = 0; k < xDimension; k++) {
					mMazeArray[2*i+1][2*j+1][2*k+1] = UNINITIALIZED;
				}
			}
		}
	}

	/**
	 *  Getters 
	 */
	public int getzDimension() {
		return zDimension;
	}

	public int getyDimension() {
		return yDimension;
	}

	public int getxDimension() {
		return xDimension;
	}

	public Position getEndPosition() {
		return pEnd;
	}

	public int getCellValue(Position p)
	{
		try
		{
			return mMazeArray[2*p.getZ() + 1][2*p.getY() + 1][2*p.getX() + 1];
		}
		catch(IndexOutOfBoundsException e)
		{
			throw new IndexOutOfBoundsException(String.format("Index out of bounds at: %d,%d,%d  Dimensions are: %d,%d,%d",
					p.getZ(),p.getY(),p.getX(), zDimension, yDimension, xDimension));
		}
	}

	private int getCoordsValue(Position p)
	{
		try
		{
			return mMazeArray[p.getZ()][p.getY()][p.getX()];
		}
		catch(IndexOutOfBoundsException e)
		{
			throw new IndexOutOfBoundsException(String.format("Index out of bounds at: %d,%d,%d  Dimensions are: %d,%d,%d",
					p.getZ(),p.getY(),p.getX(), zDimension, yDimension, xDimension));
		}
	}

	public int[][][] getMazeArray()
	{
		return mMazeArray;
	}

	public Position getStartPosition() {
		return pStart;
	}

	/**
	 *  Setters
	 */
	public void setStartPosition(Position p) {
		pStart = p;
	}

	public void setEndPosition(Position p) {
		pEnd = p;
	}

	public void setValue(Position p, int value)
	{
		Position actualP = convertPositionToActualCoords(p);
		try
		{
			mMazeArray[actualP.getZ()][actualP.getY()][actualP.getX()] = value;
		}
		catch(IndexOutOfBoundsException e)
		{
			System.out.println(String.format("Index out of bounds at: %d,%d,%d  Dimensions are: %d,%d,%d",
					p.getZ(),p.getY(),p.getX(), zDimension, yDimension, xDimension));
		}
	}

	/**
	 *  Methods & Enums
	 */
	private enum AXIS {Z, Y, X};

	/**
	 * Return the neighbors for given position
	 * @param p - the given position.
	 * @return
	 */
	public ArrayList<Position> getNeighborsForPosition(Position p)
	{
		ArrayList<Position> list = new ArrayList<Position>();
		int x = p.getX();
		int y = p.getY();
		int z = p.getZ();

		if(isInBoundaries((z + 1),y,x))
		{
			list.add(new Position((z + 1),y,x));
		}
		if(isInBoundaries((z - 1),y,x))
		{
			list.add(new Position((z - 1), y, x));
		}
		if(isInBoundaries(z, (y + 1), x))
		{
			list.add(new Position(z, (y + 1), x));
		}
		if(isInBoundaries(z, (y - 1), x))
		{
			list.add(new Position(z, (y - 1), x));
		}
		if(isInBoundaries(z, y, (x + 1)))
		{
			list.add(new Position(z, y, (x + 1)));
		}
		if(isInBoundaries(z, y, (x - 1)))
		{
			list.add(new Position(z, y, (x - 1)));
		}

		return list;
	}

	public boolean isInBoundaries(int z, int y, int x)
	{
		if(z >= zDimension || y >= yDimension || x >= xDimension)
			return false;

		if(z < 0 || y < 0 || x < 0)
			return false;

		return true;
	}

	public void setAllDefaultPostionToNonRoute()
	{
		for (int i = 0; i < 2*zDimension +1; i++) {
			for (int j = 0; j < 2*yDimension+1; j++) {
				for (int k = 0; k < 2*xDimension+1; k++) {
					if(mMazeArray[i][j][k] == Maze3d.UNINITIALIZED)
						mMazeArray[i][j][k] = Maze3d.NONROUTE_SIGN;
				}
			}
		}
	}

	public Position getRandomBoundaryPoint()
	{
		Position p = null;
		Position actualP;
		Random rand = new Random();
		int i = rand.nextInt(2);

		switch(rand.nextInt(3)) 
		{
		case 0: 
			p = new Position(i == 0 ? 0 : zDimension-1, rand.nextInt(yDimension), rand.nextInt(xDimension));
			actualP = convertPositionToActualCoords(p);

			if(i == 0)
			{
				mMazeArray[actualP.getZ()-1][actualP.getY()][actualP.getX()] = ROUTE_SIGN;
			}
			else mMazeArray[actualP.getZ()+1][actualP.getY()][actualP.getX()] = ROUTE_SIGN;
			break;

		case 1:
			p = new Position(rand.nextInt(zDimension), i == 0 ? 0 : yDimension-1, rand.nextInt(xDimension));
			actualP = convertPositionToActualCoords(p);
			if(i == 0)
			{
				mMazeArray[actualP.getZ()][actualP.getY()-1][actualP.getX()] = ROUTE_SIGN;
			}
			else mMazeArray[actualP.getZ()][actualP.getY()+1][actualP.getX()] = ROUTE_SIGN;
			break;

		case 2: 
			p = new Position(rand.nextInt(zDimension), rand.nextInt(yDimension), i == 0 ? 0 : xDimension-1);
			actualP = convertPositionToActualCoords(p);
			if(i == 0)
			{
				mMazeArray[actualP.getZ()][actualP.getY()][actualP.getX()-1] = ROUTE_SIGN;
			}
			else mMazeArray[actualP.getZ()][actualP.getY()][actualP.getX()+1] = ROUTE_SIGN;
			break;
		}

		return p;
	}

	public void printMaze()
	{
		System.out.println("----------------------------------------------------------");
		for (int i = 0; i < zDimension; i++) {
			for (int j = 0; j < yDimension; j++) {
				for (int k = 0; k < xDimension; k++) {
					System.out.print(mMazeArray[i][j][k] + ",");
				}
				System.out.println();
			}
			System.out.println();
		}

		System.out.println("----------------------------------------------------------");
	}

	public void printImplMaze()
	{
		System.out.println("----------------------------------------------------------");
		for (int i = 0; i < 2*zDimension +1 ; i++) {
			for (int j = 0; j < 2*yDimension+1; j++) {
				for (int k = 0; k < 2*xDimension+1; k++) {
					if(k == 2*xDimension)
					{
						System.out.print(mMazeArray[i][j][k]);
					}
					else System.out.print(mMazeArray[i][j][k] + ",");
				}
				System.out.println();
			}
			System.out.println();
		}

		System.out.println("----------------------------------------------------------");
	}

	public void createPassageBetweenNeighbors(Position p1, Position p2) throws Exception {

		if (p1.equals(p2)) {
			return;
		}

		Position wall = findWallBetweenNeighbors(p1,p2);
		mMazeArray[wall.getZ()][wall.getY()][wall.getX()] = ROUTE_SIGN;
	}

	/**
	 * Make a passage by equals each AXIS of the given positions
	 * @param p1
	 * @param p2
	 */
	public void createSimplePassageBetweenPositions(Position p1, Position p2)
	{
		Position actualP2 = convertPositionToActualCoords(p2);
		Position actualP1 = convertPositionToActualCoords(p1);

		String P1First = null;
		int minX = -1, minY = -1, maxY = -1, minZ = -1, maxZ = -1;
		
		//Define who is the promoting forward position actualP1 or actualP2. 
		if(actualP1.getX() != actualP2.getX())
		{
			if(actualP1.getX() < actualP2.getX())
			{
				minX = actualP1.getX();
				P1First = "true";
			}
			else
			{
				minX = actualP2.getX();
				P1First = "false";
			}
		}

		if(actualP1.getY() != actualP2.getY())
		{
			if(P1First == null)
			{
				if(actualP1.getY() < actualP2.getY())
				{
					minY = actualP1.getY();
					P1First = "true";
				}
				else
				{
					minY = actualP2.getY();
					P1First = "false";
				}
			}

			else
			{
				if(P1First.equals("true"))
				{
					if(actualP1.getY() < actualP2.getY())
						minY = actualP1.getY();
					else maxY = actualP1.getY(); 
				}
				else
				{
					if(actualP2.getY() < actualP1.getY())
						minY = actualP2.getY();
					else maxY = actualP2.getY();
				}
			}
		}

		if(actualP1.getZ() != actualP2.getZ())
		{
			if(P1First == null)
			{
				if(actualP1.getZ() < actualP2.getZ())
				{
					minZ = actualP1.getZ();
					P1First = "true";
				}
				else
				{
					minZ = actualP2.getZ();
					P1First = "false";
				}
			}

			else
			{
				if(P1First.equals("true"))
				{
					if(actualP1.getZ() < actualP2.getZ())
						minZ = actualP1.getZ();
					else maxZ = actualP1.getZ(); 
				}
				else
				{
					if(actualP2.getZ() < actualP1.getZ())
						minZ = actualP2.getZ();
					else maxZ = actualP2.getZ();
				}
			}
		}

		if(minX != -1)
		{
			if(P1First.equals("true"))
			{
				int distance = minX + Math.abs(actualP1.getX() - actualP2.getX());
				for (int i = minX; i <= distance; i++) {
					mMazeArray[actualP1.getZ()][actualP1.getY()][i] = ROUTE_SIGN;
					actualP1 = new Position(actualP1.getZ(), actualP1.getY(), i);
				}
			}
			else
			{
				int distance = minX + Math.abs(actualP1.getX() - actualP2.getX());
				for (int i = minX; i <= distance; i++) {
					mMazeArray[actualP2.getZ()][actualP2.getY()][i] = ROUTE_SIGN;
					actualP2 = new Position(actualP2.getZ(), actualP2.getY(), i);
				}
			}
			System.out.println("End");
		}

		if(minY != -1)
		{
			if(P1First.equals("true"))
			{
				int distance = minY + Math.abs(actualP1.getY() - actualP2.getY());
				for (int i = minY; i <= distance; i++) {
					mMazeArray[actualP1.getZ()][i][actualP1.getX()] = ROUTE_SIGN;
					actualP1 = new Position(actualP1.getZ(), i, actualP1.getX());
				}
			}
			else
			{
				int distance = minY + Math.abs(actualP1.getY() - actualP2.getY());
				for (int i = minY; i <= distance; i++) {
					mMazeArray[actualP2.getZ()][i][actualP2.getX()] = ROUTE_SIGN;
					actualP2 = new Position(actualP2.getZ(), i, actualP2.getX());
				}
			}
		}

		if(maxY != -1)
		{
			if(P1First.equals("true"))
			{
				int distance = maxY - Math.abs(actualP1.getY() - actualP2.getY());
				for (int i = maxY; i >= distance; i--) {
					mMazeArray[actualP1.getZ()][i][actualP1.getX()] = ROUTE_SIGN;
					actualP1 = new Position(actualP1.getZ(), i, actualP1.getX());
				}
			}
			else
			{
				int distance = maxY - Math.abs(actualP1.getY() - actualP2.getY());
				for (int i = maxY; i >= distance; i--) {
					mMazeArray[actualP2.getZ()][i][actualP2.getX()] = ROUTE_SIGN;
					actualP2 = new Position(actualP2.getZ(), i, actualP2.getX());
					System.out.println(actualP2);
				}
			}
		}
		
		if(minZ != -1)
		{
			if(P1First.equals("true"))
			{
				int distance = minZ + Math.abs(actualP1.getZ() - actualP2.getZ());
				for (int i = minZ; i <= distance; i++) {
					mMazeArray[i][actualP1.getY()][actualP1.getX()] = ROUTE_SIGN;
					actualP1 = new Position(i, actualP1.getY(), actualP1.getX());
					System.out.println(actualP1);
				}
			}
			else
			{
				int distance = minZ + Math.abs(actualP1.getZ() - actualP2.getZ());
				for (int i = minZ; i <= distance; i++) {
					mMazeArray[i][actualP2.getY()][actualP2.getX()] = ROUTE_SIGN;
					actualP2 = new Position(i, actualP2.getY(), actualP2.getX());
					System.out.println(actualP2);
				}
			}
		}

		if(maxZ != -1)
		{
			if(P1First.equals("true"))
			{
				int distance = maxZ - Math.abs(actualP1.getZ() - actualP2.getZ());
				for (int i = maxZ; i >= distance; i--) {
					mMazeArray[i][actualP1.getY()][actualP1.getX()] = ROUTE_SIGN;
					actualP1 = new Position(i, actualP1.getY(), actualP1.getX());
				}
			}
			else
			{
				int distance = maxZ - Math.abs(actualP1.getZ() - actualP2.getZ());
				for (int i = maxZ; i >= distance; i--) {
					mMazeArray[i][actualP2.getY()][actualP2.getX()] = ROUTE_SIGN;
					actualP2 = new Position(i, actualP2.getY(), actualP2.getX());
				}
			}
		}
	}

	private Position findWallBetweenNeighbors(Position p1, Position p2) throws Exception {

		AXIS axis = getDifferentAxis(p1,p2);
		Position actualP1 = convertPositionToActualCoords(p1);
		Position actualP2 = convertPositionToActualCoords(p2);

		switch (axis) {
		case X:
			int xWall = (actualP1.getX() + actualP2.getX()) / 2;
			{
				return new Position(actualP1.getZ(),actualP1.getY(),xWall);
			}

		case Y:
			int yWall = (actualP1.getY() + actualP2.getY()) / 2;
			{
				return new Position(actualP1.getZ(),yWall,actualP1.getX());
			}
		case Z:
			int zWall = (actualP1.getZ() + actualP2.getZ()) / 2;
			{
				return new Position(zWall,actualP1.getY(),actualP1.getX());
			}

		default:
			return null;
		}
	}

	private Position convertPositionToActualCoords(Position p)
	{
		return new Position(2*p.getZ()+1, 2*p.getY()+1, 2*p.getX()+1);
	}

	private AXIS getDifferentAxis(Position p1, Position p2) throws Exception {

		if((p1.getX() - p2.getX()) != 0)
		{
			if((Math.abs(p1.getY() - p2.getY()) != 0) || (Math.abs(p1.getZ() - p2.getZ()) != 0))
			{
				throw new Exception(String.format("p1 and p2 are not neighbors %s %s"
						,p1, p2));
			}
			else return AXIS.X;
		}

		if((p1.getY() - p2.getY()) != 0)
		{
			if((Math.abs(p1.getZ() - p2.getZ()) != 0) || (Math.abs(p1.getX() - p2.getX()) != 0))
			{
				throw new Exception(String.format("p1 and p2 are not neighbors %s %s"
						,p1, p2));
			}
			else return AXIS.Y;

		}
		else 
		{
			if((Math.abs(p1.getY() - p2.getY()) != 0) || (Math.abs(p1.getX() - p2.getX()) != 0))
			{
				throw new Exception(String.format("p1 and p2 are not neighbors %s %s"
						,p1, p2));
			}
			else return AXIS.Z;
		}
	}

	public boolean hasWallBetweenNeighbors(Position p, Position neighbor) throws Exception {
		Position wall = findWallBetweenNeighbors(p, neighbor);
		return getCoordsValue(wall) == NONROUTE_SIGN;
	}

	public int[][] getCrossSectionByZ(int i) {

		if(i >= zDimension || i < 0)
		{
			throw new IndexOutOfBoundsException();
		}

		int[][] maze2D = new int[2*yDimension+1][2*xDimension+1];
		int zCoords = 2 * i + 1;
		for (int j = 0; j < 2*yDimension+1; j++) {
			for (int k = 0; k < 2*xDimension+1; k++) {
				maze2D[j][k] = mMazeArray[zCoords][j][k];
			}
		}

		return maze2D;
	}

	public int[][] getCrossSectionByY(int i) {

		if(i >= yDimension || i < 0)
		{
			throw new IndexOutOfBoundsException();
		}

		int[][] maze2D = new int[2*zDimension+1][2*xDimension+1];
		int yCoords = 2 * i + 1;
		for (int j = 0; j < 2*zDimension+1; j++) {
			for (int k = 0; k < 2*xDimension+1; k++) {
				maze2D[j][k] = mMazeArray[j][yCoords][k];
			}
		}

		return maze2D;
	}

	public int[][] getCrossSectionByX(int i) {

		if(i >= xDimension || i < 0)
		{
			throw new IndexOutOfBoundsException();
		}

		int[][] maze2D = new int[2*zDimension+1][2*yDimension+1];
		int xCoords = 2 * i + 1;
		for (int j = 0; j < 2*zDimension+1; j++) {
			for (int k = 0; k < 2*yDimension+1; k++) {
				maze2D[j][k] = mMazeArray[j][k][xCoords];
			}
		}

		return maze2D;
	}

	public String[] getPossibleMoves(Position p) throws Exception
	{
		ArrayList<Position> neighbors = getNeighborsForPosition(p);
		String[] movesList = new String[neighbors.size()];
		int i = 0;
		for(Position neighbor : neighbors)
		{
			if(! hasWallBetweenNeighbors(p, neighbor))
			{
				movesList[i] = neighbor.toString();
				i++;
			}
		}

		return movesList;
	}
	
	public ArrayList<Position> getPossibleMovesAsPositionsArray(Position p) throws Exception
	{
		ArrayList<Position> neighbors = getNeighborsForPosition(p);
		ArrayList<Position> movesList = new ArrayList<Position>(neighbors.size());
		
		for(Position neighbor : neighbors)
		{
			if(! hasWallBetweenNeighbors(p, neighbor))
			{
				movesList.add(neighbor);
			}
		}

		return movesList;
	}
}