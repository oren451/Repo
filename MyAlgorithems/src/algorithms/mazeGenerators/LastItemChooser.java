package algorithms.mazeGenerators;

import java.util.List;

/**
 * Choose the last any T item from any implementation of List interface.
 * @author orenk
 *
 * @param <T>
 */
public class LastItemChooser<T> implements ItemChooser<T> {

	@Override
	public T chooseItem(List<T> list) {
		
		if(list.isEmpty())
		{
			return null;
		}
		else return list.get(list.size() - 1);
	}
}
