package gui.windows;

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
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import presenter.CommandsList;
import view.MyView;

/**
 * Generate new maze window
 * 
 * @author orenk
 */
public class GenerateWindow extends CommonDialogWindow {
	public GenerateWindow(final MyView view) {
		mView = view;
	}

	@Override
	public void initWidgets() {

		mShell = new Shell(Display.getDefault(), SWT.TITLE | SWT.CLOSE | SWT.MIN);
		mShell.setText("Generate maze");
		mShell.setSize(250, 250);
		mShell.setLayout(new GridLayout(2, false));
		mShell.setBackground(new Color(Display.getDefault(), 125, 125, 240, 1));

		setOpenWindowAtCenterOfDisplay();

		final Label labelHead = new Label(mShell, SWT.BOLD);
		final FontData fontData = labelHead.getFont().getFontData()[0];
		final Font font = new Font(Display.getDefault(),
				new FontData(fontData.getName(), fontData.getHeight() + 1, SWT.BOLD));
		labelHead.setFont(font);
		labelHead.setText("Enter maze dimensions:");
		labelHead.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 2, 1));

		final Label labelFloors = new Label(mShell, SWT.NONE);
		labelFloors.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false));
		labelFloors.setText("Floors: ");
		final Text txtFloors = new Text(mShell, SWT.BORDER);
		txtFloors.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));

		final Label labelRows = new Label(mShell, SWT.NONE);
		labelRows.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false));
		labelRows.setText("Rows: ");
		final Text txtRows = new Text(mShell, SWT.BORDER);
		txtRows.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));

		final Label lblColumns = new Label(mShell, SWT.NONE);
		lblColumns.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false));
		lblColumns.setText("Columns: ");
		final Text txtcolumns = new Text(mShell, SWT.BORDER);
		txtcolumns.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));

		final Label labelHeadName = new Label(mShell, SWT.BOLD);
		final FontData fontDataName = labelHeadName.getFont().getFontData()[0];
		final Font fontName = new Font(Display.getDefault(),
				new FontData(fontDataName.getName(), fontData.getHeight() + 1, SWT.BOLD));
		labelHeadName.setFont(fontName);
		labelHeadName.setText("Maze Name:");
		labelHeadName.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 2, 1));

		final Label lblName = new Label(mShell, SWT.NONE);
		lblName.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false));
		lblName.setText("Name: ");
		final Text txtName = new Text(mShell, SWT.BORDER);
		txtName.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));

		final Button btnStartGame = new Button(mShell, SWT.PUSH);
		btnStartGame.setText("Generate Maze");
		btnStartGame.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 2, 1));

		btnStartGame.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetDefaultSelected(final SelectionEvent arg0) {
			}

			@Override
			public void widgetSelected(final SelectionEvent arg0) {
				int floors = 0;
				int rows = 0;
				int columns = 0;
				String mazeName = null;
				try {
					floors = Integer.parseInt(txtFloors.getText());
					rows = Integer.parseInt(txtRows.getText());
					columns = Integer.parseInt(txtcolumns.getText());
					mazeName = txtName.getText();
				} catch (NullPointerException | NumberFormatException e) {
					mView.printMessage("Invalid Arguments!");
				}
				mView.executeCommand(
						CommandsList.GENERATE_CMD + " " + mazeName + " " + floors + " " + rows + " " + columns);
				mShell.dispose();

			}
		});
	}
}