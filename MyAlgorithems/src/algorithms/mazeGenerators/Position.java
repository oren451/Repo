package algorithms.mazeGenerators;

/**
 * Wrap of our domain items - Maze3D containing Postions
 * @author orenk
 *
 */
public class Position {

	private int z;
	private int y;
	private int x;

	protected Position(int z, int y, int x) {
		this.z = z;
		this.y = y;
		this.x = x;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	@Override
	public String toString() {
		return "Position [z=" + z + ", y=" + y + ", x=" + x + "]";
	}
	
}
