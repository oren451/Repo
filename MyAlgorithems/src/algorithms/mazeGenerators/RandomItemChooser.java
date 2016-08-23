package algorithms.mazeGenerators;

import java.util.List;
import java.util.Random;

/**
 * Choose a random any T item from any implementation of List interface.
 * @author orenk
 *
 * @param <T>
 */
public class RandomItemChooser<T> implements ItemChooser<T> {

	@Override
	public T chooseItem(List<T> list) {

		if(list.isEmpty())
		{
			return null;
		}
		else
		{
			Random rand = new Random();
			return list.get(rand.nextInt(list.size()));
		}
	}
}
