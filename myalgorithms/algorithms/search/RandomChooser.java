package algorithms.search;

import java.util.ArrayList;
import java.util.Random;

import algorithms.mazeGenerators.Position;

public class RandomChooser implements IChooser {

	@Override
	public Position choose(ArrayList<Position> C) {
		
        Random random = new Random();
        int randomNum = random.nextInt(C.size());
        
        return C.get(randomNum);
	}
}