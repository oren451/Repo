package algorithms.mazeGenerators;

import java.util.List;

/**
 * Interface for choosing any T items in different ways 
 * 	from any implementation of List interface.
 * @author orenk
 *
 * @param <T>
 */
public interface ItemChooser<T> {

	T chooseItem(List<T> list);
}
