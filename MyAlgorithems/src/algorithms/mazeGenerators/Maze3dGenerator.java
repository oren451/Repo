package algorithms.mazeGenerators;

public interface Maze3dGenerator {

	Maze3d generate(int z, int y, int x);
	String measureAlgorithmTime(int z, int y, int x);
}
