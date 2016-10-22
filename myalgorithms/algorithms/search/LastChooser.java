package algorithms.search;

import java.util.ArrayList;

import algorithms.mazeGenerators.Position;

public class LastChooser implements IChooser {

	@Override
	public Position choose(ArrayList<Position> c) {
		return c.get(c.size() - 1);
	}

}
