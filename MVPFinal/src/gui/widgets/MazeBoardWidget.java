package gui.widgets;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerators.Position;
import presenter.CommandsList;
import properties.PropertiesIO;
import view.MyView;

/**
 * Here we actually play
 * 
 * @author orenk
 */
public class MazeBoardWidget extends Canvas {
	private String mMazeName;
	private int mWhichFloorAmI;
	private final PlayerWidget mCharacter;
	private final Image mImgTarget;
	private final Image imgWinner;
	private final Image imgUp;
	private final Image imgDown;
	private final Image imgUpDown;
	private final Image imgWall;
	private boolean mWinner;
	private Position mTarget;
	private List<Point> mDownHint;
	private List<Point> mUpHint;
	private int[][] mCrossSection = { { 0 }, { 0 } };

	public MazeBoardWidget(final Composite parent, final int style, final MyView view) {
		super(parent, style);

		mWhichFloorAmI = 0;
		mCharacter = new PlayerWidget();
		mCharacter.setPos(new Position(-1, -1, -1));
		mImgTarget = new Image(null, getClass().getClassLoader().getResourceAsStream("res/images/hilary.png"));
		imgWinner = new Image(null, getClass().getClassLoader().getResourceAsStream("res/images/win.png"));
		imgUp = new Image(null, getClass().getClassLoader().getResourceAsStream("res/images/uparrow.png"));
		imgDown = new Image(null, getClass().getClassLoader().getResourceAsStream("res/images/downarrow.png"));
		imgUpDown = new Image(null, getClass().getClassLoader().getResourceAsStream("res/images/upanddown.png"));
		imgWall = new Image(null, getClass().getClassLoader().getResourceAsStream("res/images/wall.png"));
		mWinner = false;
		mTarget = new Position(-1, -1, -1);
		mUpHint = new ArrayList<Point>();
		mDownHint = new ArrayList<Point>();

		addPaintListener(new PaintListener() {
			@Override
			public void paintControl(final PaintEvent e) {
				int x, y;
				final int canvasWidth = getSize().x;
				final int canvasHeight = getSize().y;
				final int cellWidth = canvasWidth / mCrossSection[0].length;
				final int cellHeight = canvasHeight / mCrossSection.length;

				for (int i = 0; i < mCrossSection.length; i++) {
					for (int j = 0; j < mCrossSection[i].length; j++) {
						x = j * cellWidth;
						y = i * cellHeight;
						if (mCrossSection[i][j] != 0) 
							e.gc.drawImage(imgWall, 0, 0, imgWall.getBounds().width, imgWall.getBounds().height, x, y,
									cellWidth, cellHeight);
						if (PropertiesIO.getProperties().getGUIUpDownHints().equals("true"))
							paintUpDownHints(e, i, j, cellWidth, cellHeight);
					}
				}

				if (!mWinner) {
					mCharacter.draw(cellWidth, cellHeight, e.gc);
					if (mWhichFloorAmI == mTarget.getX())
						e.gc.drawImage(mImgTarget, 0, 0, mImgTarget.getBounds().width, mImgTarget.getBounds().height,
								cellWidth * mTarget.getZ(), cellHeight * mTarget.getY(), cellWidth, cellHeight);
				} else
					e.gc.drawImage(imgWinner, 0, 0, imgWinner.getBounds().width, imgWinner.getBounds().height,
							cellWidth * mTarget.getZ(), cellHeight * mTarget.getY(), cellWidth, cellHeight);
			}

			private void paintUpDownHints(final PaintEvent e, final int i, final int j, final int cellWidth,
					final int cellHeight) {
				final Point upDownHint = new Point(i, j);

				if (mUpHint.contains(upDownHint) && mDownHint.contains(upDownHint))
					e.gc.drawImage(imgUpDown, 0, 0, imgUpDown.getBounds().width, imgUpDown.getBounds().height,
							cellWidth * j, cellHeight * i, cellWidth, cellHeight);
				else {
					if (mUpHint.contains(upDownHint))
						e.gc.drawImage(imgUp, 0, 0, imgUp.getBounds().width, imgUp.getBounds().height, cellWidth * j,
								cellHeight * i, cellWidth, cellHeight);
					else if (mDownHint.contains(upDownHint))
						e.gc.drawImage(imgDown, 0, 0, imgDown.getBounds().width, imgDown.getBounds().height,
								cellWidth * j, cellHeight * i, cellWidth, cellHeight);
				}
			}
		});

		addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(final KeyEvent e) {
				String input = null;

				switch (e.keyCode) {
				case SWT.ARROW_RIGHT:
					input = CommandsList.MOVE_RIGHT_CMD + " " + mMazeName;
					break;
				case SWT.ARROW_LEFT:
					input = CommandsList.MOVE_LEFT_CMD + " " + mMazeName;
					break;
				case SWT.ARROW_UP:
					input = CommandsList.MOVE_BACK_CMD + " " + mMazeName;
					break;
				case SWT.ARROW_DOWN:
					input = CommandsList.MOVE_FORWARD_CMD + " " + mMazeName;
					break;
				case SWT.PAGE_DOWN:
					input = CommandsList.MOVE_DOWN_CMD + " " + mMazeName;
					break;
				case SWT.PAGE_UP:
					input = CommandsList.MOVE_UP_CMD + " " + mMazeName;
					break;
				default:
					break;
				}
				if (input != null) {
					view.executeCommand(input);
					redraw();
				}
			}

			@Override
			public void keyReleased(final KeyEvent arg0) {
			}
		});
	}

	public void moveTheCharacter(final Position pos) {
		mCharacter.setPos(pos);
		redrawMe();
	}

	private void redrawMe() {
		getDisplay().syncExec(new Runnable() {
			@Override
			public void run() {
				setEnabled(true);
				redraw();
			}
		});
	}

	/**
	 * Setters
	 */

	public void setCharacterPosition(final Position pos) {
		mCharacter.setPos(pos);
		redrawMe();
	}

	public void setCrossSection(final int[][] crossSection, final List<Point> upHint, final List<Point> downHint) {
		mCrossSection = crossSection;
		mUpHint = upHint;
		mDownHint = downHint;
		redrawMe();
	}

	public void setGoalPosition(final Position goalPosition) {
		mTarget = goalPosition;
	}

	public void setMazeName(final String mazeName) {
		mMazeName = mazeName;
	}

	public void setWhichFloorAmI(final int whichFloorAmI) {
		mWhichFloorAmI = whichFloorAmI;
	}

	public void setWinner(final boolean winner) {
		mWinner = winner;
	}
}