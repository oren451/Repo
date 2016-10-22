package gui.widgets;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;

import algorithms.mazeGenerators.Position;

/**
 *
 * @author orenk
 */
public class PlayerWidget {
	private Position mPlayerPos;
	private final Image mPlayerImg;

	public PlayerWidget() {
		mPlayerImg = new Image(null, getClass().getClassLoader().getResourceAsStream("res/images/trump.png"));
	}

	public void draw(final int cellWidth, final int cellHeight, final GC gc) {
		gc.drawImage(mPlayerImg, 0, 0, mPlayerImg.getBounds().width, mPlayerImg.getBounds().height,
				cellWidth * mPlayerPos.getZ(), cellHeight * mPlayerPos.getY(), cellWidth, cellHeight);
	}

	public Position getPos() {
		return mPlayerPos;
	}

	public void setPos(final Position pos) {
		mPlayerPos = pos;
	}
}