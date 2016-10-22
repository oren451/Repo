package boot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import gui.windows.EntranceWindow;
import model.MyModel;
import presenter.MyPresenter;
import properties.PropertiesIO;
import view.MyView;

/**
 * Starting point of the application
 * 
 * @authors orenk & Yossi K
 */
public class Run {

	public static void main(final String[] args) {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				runGame();
			}
		});

		t.start();

		runGame();
	}

	private static void runGame() {
		final EntranceWindow mainWindow = new EntranceWindow();
		mainWindow.run();

		PropertiesIO.readPropertiesFile();

		final MyModel model = new MyModel();
		final MyView view = new MyView(new BufferedReader(new InputStreamReader(System.in)),
				new PrintWriter(System.out, true));
		final MyPresenter presenter = new MyPresenter(view, model);

		view.addObserver(presenter);
		model.addObserver(presenter);

		view.start();
	}
}