package algorithms.search;

import java.util.ArrayList;

import algorithms.mazeGenerators.Position;

public interface IChooser
{
	Position choose(ArrayList <Position> c);
}
