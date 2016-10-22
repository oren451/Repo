package gui.windows;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import view.MyView;

/**
 * 
 * Common class for all the dialogs in the applications
 * 
 * @author orenk
 */
public abstract class CommonDialogWindow implements ISwtWindow {
	protected Shell mShell;
	protected MyView mView;

	protected void setOpenWindowAtCenterOfDisplay() {
		// Open in CENTER of screen
		final Rectangle dispBounds = Display.getDefault().getPrimaryMonitor().getBounds();
		final Rectangle shellBounds = mShell.getBounds();
		final int x = dispBounds.x + (dispBounds.width - shellBounds.width) / 2;
		final int y = dispBounds.y + (dispBounds.height - shellBounds.height) / 2;

		mShell.setLocation(x, y);
	}

	public void start() {
		mShell = new Shell(Display.getDefault());
		initWidgets();
		mShell.open();
	}
}