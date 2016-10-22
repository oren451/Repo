package algorithms.mazeGenerators;

public abstract class CommonMaze3dGenerator implements Maze3dGenerator {

	@Override
	public abstract Maze3d generate(int x,int y,int z); 

	@Override
	public String measureAlgorithmTime(int x,int y,int z) {
	
		long start = System.currentTimeMillis();				
	
		generate(x,y,z);
		
		return "Generate time running:" + (System.currentTimeMillis() - start)+" ms";
	}
}