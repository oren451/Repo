package gui.windows;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

import presenter.CommandsList;
import view.MyView;

/**
 * The class LoadFileWindow extends the abstract class PopUpCommonWindow The
 * window let the user to load a maze as a file
 * 
 * @author orenk
 */
public class LoadFileWindow extends CommonDialogWindow {
	private final ArrayList<String> mFileList;

	public LoadFileWindow(final MyView view, final String filesList) {
		mView = view;

		final String[] strArray = filesList.split("\n");

		mFileList = new ArrayList<String>();
		for (int i = 0; i < strArray.length; i++) {
			if (strArray[i].contains(".maz")) {
				mFileList.add(strArray[i]);
			}
		}
	}

	@Override
	public void initWidgets() {

		mShell = new Shell(Display.getDefault(), SWT.TITLE | SWT.CLOSE | SWT.MIN);
		mShell.setText("Load maze from a file");
		mShell.setLayout(new GridLayout(1, false));
		mShell.setSize(400, 400);
		mShell.setBackground(new Color(Display.getDefault(), 125, 125, 240));
		mShell.setBackgroundMode(SWT.INHERIT_DEFAULT);

		setOpenWindowAtCenterOfDisplay();

		final Label lblHead = new Label(mShell, SWT.NONE);
		final FontData fontData = lblHead.getFont().getFontData()[0];
		final Font font = new Font(Display.getDefault(),
				new FontData(fontData.getName(), fontData.getHeight() + 4, SWT.BOLD));
		lblHead.setFont(font);
		lblHead.setText("Choose a maze from the list:");
		lblHead.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 2, 1));

		final List lstFiles = new List(mShell, SWT.BORDER | SWT.SINGLE | SWT.V_SCROLL);
		for (int i = 0; i < mFileList.size(); i++) {
			lstFiles.add(mFileList.get(i));
		}

		lstFiles.setLayoutData(new GridData(GridData.FILL_BOTH));

		final Button btnLoad = new Button(mShell, SWT.PUSH);
		btnLoad.setText("Load maze");
		btnLoad.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnLoad.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(final SelectionEvent arg0) {
			}

			@Override
			public void widgetSelected(final SelectionEvent arg0) {
				final int index = lstFiles.getSelectionIndex();
				final String[] mazeName = mFileList.get(index).split(".maz");
				try {
					mView.executeCommand(CommandsList.LOAD_CMD + " " + mazeName[0] + ".maz " + mazeName[0]);
				} catch (IllegalArgumentException | IndexOutOfBoundsException e) {
					mView.printMessage(e.getMessage());
				}
				mShell.dispose();
			}
		});
	}
}