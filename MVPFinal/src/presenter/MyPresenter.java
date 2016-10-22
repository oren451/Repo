package presenter;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import model.MyModel;
import view.MyView;

/**
 * The presenter class as part of MVP arch.
 * 
 * @author orenk
 */
public class MyPresenter implements Observer {
	private final CommandsManager mCommandsManager;

	public MyPresenter(final MyView view, final MyModel model) {
		mCommandsManager = new CommandsManager(model, view);
	}

	@Override
	public void update(final Observable o, final Object arg) {

		final String commandLine = (String) arg;
		try {
			mCommandsManager.executeCommand(commandLine);
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
}