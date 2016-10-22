package algorithms.mazeGenerators;

import java.io.Serializable;

public class Position implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int x,y,z;

	public Position(int x,int y,int z) {
		this.x=x;
		this.y=y;
		this.z=z;

	}

	public Position() {}

	//override equals method to compare positions
	@Override
	public boolean equals(Object object) {
		Position position=(Position)object;
		return(position.x==x && position.y==y&&position.z==z);
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

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public Position toPosition(String position)
	{
		int coordinate[]=new int[3];
		String string=position.substring(1, position.length()-1);
		String[]retval=string.split(",");
		for(int i=0;i<retval.length;i++)
		{
			coordinate[i]=Integer.parseInt(retval[i]);
		}
		Position pos=new Position(coordinate[0],coordinate[1],coordinate[2]);
		return pos;
	}

	@Override
	public String toString()
	{
		return "{" + x + "," + y + "," + z + "}";
	}
}