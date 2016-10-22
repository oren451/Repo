package gui.windows;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import gui.widgets.MazeBoardWidget;
import presenter.CommandsList;
import view.MyView;

/**
 * 
 * The main window of the application
 * 
 * 
 * @author orenk
 */
public class MainGameWindow extends CommonWindow {
	private final String WINNER_STR = "Winner! Winner! Winner!";

	private Maze3d mMaze;
	private String mMazeName;
	private MazeBoardWidget mMazeBoard;
	private List<Point> mMoveUpList;
	private List<Point> mMoveDownList;
	private int[][] mCurrCrossSection;
	private int[][] mUpperCrossSection;
	private int[][] mLowerCrossSection;

	public MainGameWindow(final MyView view) {
		mView = view;
	}

	@Override
	public void announcedWinner() {
		mCurrCrossSection = mMaze.getCrossSectionByX(mMaze.getGoalPosition().getX());
		mMazeBoard.setWhichFloorAmI(mMaze.getGoalPosition().getX());
		setIfCanGoUpOrDown(mMaze.getGoalPosition().getX());
		mMazeBoard.setCrossSection(mCurrCrossSection, mMoveUpList, mMoveDownList);
		mMazeBoard.setWinner(true);
		printToConsole(WINNER_STR);

		mMazeBoard.setEnabled(false);
		mMazeBoard.setWinner(false);
	}

	@Override
	public void dbValues(final String databaseValues) {
	}

	@Override
	public void dirListReady(final String[] dirList) {
		final LoadFileWindow winLoad = new LoadFileWindow(mView, dirList[0]);
		winLoad.start();
	}

	@Override
	public void displaySolution(final Solution<Position> solution) {

		mTimerTask = new TimerTask() {
			int index = 0;

			@Override
			public void run() {
				if (index < solution.getStates().size()) {
					move(solution.getStates().get(index++).getValue());
				} else {
					Display.getDefault().syncExec(new Runnable() {
						@Override
						public void run() {
							announcedWinner();
						}

					});
					cancel();
				}
			}
		};
		mTimer = new Timer();
		mTimer.scheduleAtFixedRate(mTimerTask, 0, 500);
	}

	@Override
	public void executeCommand(final String commandLine) {
		try {
			mView.executeCommand(commandLine);
		} catch (final IllegalArgumentException e) {
			printToConsole(e.getMessage());
		}
	}

	public String getMazeName() {
		return mMazeName;
	}

	@Override
	public void initWidgets() {
		final GridLayout grid = new GridLayout(2, false);
		mShell.setLayout(grid);
		mShell.setSize(1000, 600);
		mShell.setText("Maze Game In Java");
		mShell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		mShell.setBackgroundImage(new Image(null, getClass().getClassLoader().getResourceAsStream("res/images/firstBG.png")));

		// Open in center of screen
		final Rectangle bounds = Display.getDefault().getPrimaryMonitor().getBounds();
		final Rectangle rect = mShell.getBounds();
		final int x = bounds.x + (bounds.width - rect.width) / 2;
		final int y = bounds.y + (bounds.height - rect.height) / 2;
		mShell.setLocation(x, y);

		// handle with X button
		mShell.addListener(SWT.Close, new Listener() {
			@Override
			public void handleEvent(final Event arg0) {
				executeCommand("exit");
			}
		});

		final Composite buttons = new Composite(mShell, SWT.NONE);
		buttons.setLayout(new GridLayout(1, false));

		final Composite cmpGenerateHint = new Composite(buttons, SWT.NONE);
		cmpGenerateHint.setLayout(new GridLayout(1, false));
		cmpGenerateHint.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 13));

		final Button btnGenerateMaze = new Button(cmpGenerateHint, SWT.PUSH);
		mShell.setDefaultButton(btnGenerateMaze);
		btnGenerateMaze.setText("Generate new maze");
		btnGenerateMaze.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnGenerateMaze.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(final SelectionEvent arg0) {
			}

			@Override
			public void widgetSelected(final SelectionEvent arg0) {
				final GenerateWindow genWin = new GenerateWindow(mView);
				genWin.start();
			}
		});

		Button btnSolve = null;

		final Composite cmpSolve = new Composite(buttons, SWT.NONE);
		cmpSolve.setLayout(new GridLayout(1, false));
		cmpSolve.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 13));

		final Label lblSolve = new Label(cmpSolve, SWT.NONE);
		lblSolve.setText("Choose how to solve:");

		final FontData fontData = lblSolve.getFont().getFontData()[0];
		final Font font = new Font(Display.getDefault(),
				new FontData(fontData.getName(), fontData.getHeight() + 4, SWT.BOLD));
		lblSolve.setFont(font);

		lblSolve.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1));

		final Combo cmbSolveAlgo = new Combo(cmpSolve, SWT.READ_ONLY | SWT.FILL);
		final String items[] = { "BFS", "DFS" };
		cmbSolveAlgo.setItems(items);
		cmbSolveAlgo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		btnSolve = new Button(cmpSolve, SWT.PUSH | SWT.FILL);
		mShell.setDefaultButton(btnSolve);
		btnSolve.setText("Solve");
		btnSolve.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnSolve.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(final SelectionEvent arg0) {
			}

			@Override
			public void widgetSelected(final SelectionEvent arg0) {
				if (mMazeName == null)
					mView.printMessage("Please generate a maze first");
				else
					executeCommand(CommandsList.SOLVE_CMD + " " + mMazeName + " " + cmbSolveAlgo.getText());
			}
		});

		final Composite cmpSaveLoadFromFile = new Composite(buttons, SWT.NONE);
		cmpSaveLoadFromFile.setLayout(new GridLayout(1, false));
		cmpSaveLoadFromFile.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 13));

		final Button btnSave = new Button(cmpSaveLoadFromFile, SWT.PUSH | SWT.FILL);
		mShell.setDefaultButton(btnSave);
		btnSave.setText("Save to file");
		btnSave.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnSave.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(final SelectionEvent arg0) {
			}

			@Override
			public void widgetSelected(final SelectionEvent arg0) {
				executeCommand(CommandsList.SAVE_CMD + " " + mMazeName + " " + mMazeName + ".maz");
			}
		});

		final Button btnLoad = new Button(cmpSaveLoadFromFile, SWT.PUSH | SWT.FILL);
		mShell.setDefaultButton(btnLoad);
		btnLoad.setText("Load from file");
		btnLoad.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnLoad.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(final SelectionEvent arg0) {
			}

			@Override
			public void widgetSelected(final SelectionEvent arg0) {
				executeCommand(CommandsList.DISPLAY_DIR_CMD + " ./res/saved_mazes");
			}
		});

		final Composite cmpExit = new Composite(buttons, SWT.NONE);
		cmpExit.setLayout(new GridLayout(1, false));
		cmpExit.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 10));

		final Button btnExit = new Button(cmpExit, SWT.PUSH | SWT.FILL);
		mShell.setDefaultButton(btnExit);
		btnExit.setText("Exit");
		btnExit.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnExit.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(final SelectionEvent arg0) {
			}

			@Override
			public void widgetSelected(final SelectionEvent arg0) {
				executeCommand("exit");
			}
		});

		mMazeBoard = new MazeBoardWidget(mShell, SWT.BORDER, mView);
		mMazeBoard.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	}

	private void isMoveDownPossible(final int y, final int x) {
		if (mLowerCrossSection[y][x] == mCurrCrossSection[y][x] && mCurrCrossSection[y][x] == 0) {
			mMoveDownList.add(new Point(y, x));
		}
	}

	private void isMoveUpPossible(final int y, final int x) {
		if (mUpperCrossSection[y][x] == mCurrCrossSection[y][x] && mCurrCrossSection[y][x] == 0) {
			mMoveUpList.add(new Point(y, x));
		}
	}

	@Override
	public void mazeIsReady(final Maze3d maze, final String mazeName) {

		mMazeName = mazeName;
		mMaze = maze;
		mCurrCrossSection = mMaze.getCrossSectionByX(mMaze.getStartPosition().getX());
		setIfCanGoUpOrDown(mMaze.getStartPosition().getX());
		mMazeBoard.setCrossSection(mCurrCrossSection, mMoveUpList, mMoveDownList);
		mMazeBoard.setCharacterPosition(mMaze.getStartPosition());
		mMazeBoard.setGoalPosition(mMaze.getGoalPosition());
		mMazeBoard.setMazeName(mMazeName);
	}

	@Override
	public void move(final Position pos) {
		mCurrCrossSection = mMaze.getCrossSectionByX(pos.getX());
		setIfCanGoUpOrDown(pos.getX());
		mMazeBoard.setCrossSection(mCurrCrossSection, mMoveUpList, mMoveDownList);
		mMazeBoard.setWhichFloorAmI(pos.getX());
		mMazeBoard.moveTheCharacter(pos);
	}

	@Override
	public void printToConsole(final String msg) {
		final MessageBox msgBox = new MessageBox(mShell, SWT.ICON_INFORMATION);
		msgBox.setMessage(msg);
		msgBox.open();
		if (msg.equals(WINNER_STR)) {
			mMazeBoard.setWinner(false);
		}
	}

	private void setIfCanGoUpOrDown(final int floor) {
		boolean isUpPossible = false;
		boolean isDownPossible = false;

		mMoveUpList = new ArrayList<Point>();
		mMoveDownList = new ArrayList<Point>();

		if (floor < mMaze.getMaze().length - 1) {
			mUpperCrossSection = mMaze.getCrossSectionByX(floor + 1);
			isUpPossible = true;
		}
		if (floor > 0) {
			mLowerCrossSection = mMaze.getCrossSectionByX(floor - 1);
			isDownPossible = true;
		}

		for (int i = 0; i < mCurrCrossSection.length; i++) {
			for (int j = 0; j < mCurrCrossSection[0].length; j++) {
				if (isUpPossible)
					isMoveUpPossible(i, j);
				if (isDownPossible)
					isMoveDownPossible(i, j);
			}
		}
	}
}