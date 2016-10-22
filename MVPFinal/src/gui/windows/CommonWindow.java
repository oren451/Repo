package gui.windows;

import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import view.CommonUI;

public abstract class CommonWindow extends CommonUI implements ISwtWindow {
	protected Shell mShell;
	protected Timer mTimer;
	protected TimerTask mTimerTask;

	@Override
	public void exitApplication() {
		if (mTimer != null) {
			mTimer.cancel();
		}
		if (mTimerTask != null) {
			mTimerTask.cancel();
		}

		Display.getDefault().dispose();
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
				exitApplication();
			}
		});
	}

	@Override
	public void startView() {
		run();
	}
}