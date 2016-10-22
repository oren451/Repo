package presenter;

import java.io.IOException;

/**
 * 
 * Interface for Command design pattern
 * 
 * @author orenk
 */
public interface ICommand {
	void doCommand(String[] args) throws IOException;
}