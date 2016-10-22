package gui.windows;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import properties.PropertiesIO;

/**
 * 
 * Let the user choose if he want to start with a GUI view or with CLI.
 * 
 * @author orenk
 *
 */
public class EntranceWindow implements ISwtWindow {
	private Shell mShell;

	@Override
	public void initWidgets() {
		// maze title
		mShell = new Shell(Display.getDefault(), SWT.TITLE | SWT.CLOSE);
		mShell.setText("Maze Game");

		// set size to maze and load background
		mShell.setLayout(new GridLayout(2, false));
		mShell.setSize(585, 460);
		mShell.setBackgroundImage(new Image(null, getClass().getClassLoader().getResourceAsStream("res/images/usaflag.png")));
		mShell.setBackgroundMode(SWT.INHERIT_DEFAULT);

		// Open in center of screen
		final Rectangle bounds = Display.getCurrent().getPrimaryMonitor().getBounds();
		final Rectangle rect = mShell.getBounds();
		final int x = bounds.x + (bounds.width - rect.width) / 2;
		final int y = bounds.y + (bounds.height - rect.height) / 2;
		mShell.setLocation(x, y);

		// handle with X
		mShell.addListener(SWT.Close, new Listener() {

			@Override
			public void handleEvent(final Event arg0) {
				System.exit(0);
			}
		});

		// Empty spaces
		for (int i = 0; i < 13; i++)
			new Label(mShell, SWT.NONE);

		// import GUI AND CLI buttons
		final Image imgGUI = new Image(Display.getDefault(), getClass().getClassLoader().getResourceAsStream("res/images/Gui.png"));
		final Image imgCLI = new Image(Display.getDefault(), getClass().getClassLoader().getResourceAsStream("res/images/CLI.png"));

		// locate GUI
		final Label lblGUI = new Label(mShell, SWT.NONE);
		lblGUI.setLayoutData(new GridData(SWT.LEFT, SWT.LEFT, true, false, 2, 1));
		lblGUI.setImage(imgGUI);

		// locate CLI
		final Label lblCLI = new Label(mShell, SWT.PUSH);
		lblCLI.setLayoutData(new GridData(SWT.LEFT, SWT.LEFT, true, false, 2, 1));
		lblCLI.setImage(imgCLI);

		// add mouse listener to CLI
		lblCLI.addMouseListener(new MouseListener() {

			// do nothing
			@Override
			public void mouseDoubleClick(final MouseEvent arg0) {
			}

			// if clicked make CLI in "xml" file as true
			@Override
			public void mouseDown(final MouseEvent arg0) {
				PropertiesIO.writeToPropertiesFile("CLI", "true");
				mShell.dispose();
			}

			// do nothing
			@Override
			public void mouseUp(final MouseEvent arg0) {
			}
		});

		// add mouse listener to GUI
		lblGUI.addMouseListener(new MouseListener() {

			// do nothing
			@Override
			public void mouseDoubleClick(final MouseEvent arg0) {
			}

			// if clicked make GUI in "xml" file as true
			@Override
			public void mouseDown(final MouseEvent arg0) {
				PropertiesIO.writeToPropertiesFile("GUI", "true");
				mShell.dispose();
			}

			// do nothing
			@Override
			public void mouseUp(final MouseEvent arg0) {
			}
		});
	}

	public void run() {
		Display.getDefault().syncExec(new Runnable() {
			@Override
			public void run() {
				mShell = new Shell(Display.getDefault());

				initWidgets();
				mShell.open();

				// main event loop
				while (!mShell.isDisposed())
					// windows isn't closed
					if (!Display.getDefault().readAndDispatch())
						Display.getDefault().sleep();
				Display.getDefault().close();
			}
		});
	}
}