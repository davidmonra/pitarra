package pitarra;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

@SuppressWarnings("serial")
public class Vertex extends Point {
	private static final int cornKernalX[] = { 5, 8, 19, 21, 13 };
	private static final int cornKernalY[] = { 0, 10, 10, 0, -2 };

	private int xPoints[];
	private int yPoints[];
	private boolean isAvailable;
	private boolean isCornerVertex;
	private int player;
	private Color squareColor;
	private Vertex left, right, above, below; // adjacent vertices

	public Vertex(boolean isCornerVertex) {
		super();
		setLocation(0, 0);
		this.isAvailable = true;
		this.isCornerVertex = isCornerVertex;
		this.player = 0;
		this.squareColor = PitCons.squareClearColor;
		this.left = null;
		this.right = null;
		this.above = null;
		this.below = null;

		this.xPoints = new int[cornKernalX.length];
		this.yPoints = new int[cornKernalY.length];
	}

	public void drawCornKernal(Graphics page, int width) {
		int x = (int) getX();
		int y = (int) getY();

		for (int i = 0; i < cornKernalX.length; i++) {
			xPoints[i] = x - width / 2 + cornKernalX[i]
					* (width / PitCons.squareSize);
			yPoints[i] = y - width / 4 + cornKernalY[i]
					* (width / PitCons.squareSize);
		}

		page.setColor(squareColor);

		page.fillPolygon(xPoints, yPoints, xPoints.length);
		page.setColor(PitCons.genericBorderColor);
		page.drawPolygon(xPoints, yPoints, xPoints.length);
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean available) {
		this.isAvailable = available;
	}

	public boolean isCornerVertex() {
		return isCornerVertex;
	}

	public Vertex getLeft() {
		return left;
	}

	public void setLeft(Vertex left) {
		this.left = left;
	}

	public Vertex getRight() {
		return right;
	}

	public void setRight(Vertex right) {
		this.right = right;
	}

	public Vertex getAbove() {
		return above;
	}

	public void setAbove(Vertex above) {
		this.above = above;
	}

	public Vertex getBelow() {
		return below;
	}

	public void setBelow(Vertex below) {
		this.below = below;
	}

	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
		setColor();
	}

	public void setColor() {
		switch (player) {
		case 1:
			squareColor = PitCons.player1Color;
			setAvailable(false);
			break;
		case 2:
			squareColor = PitCons.player2Color;
			setAvailable(false);
			break;
		default:
			squareColor = PitCons.squareClearColor;
			setAvailable(true);
		}
	}

	public void drawConnectingLines(Graphics page, Color color) {
		page.setColor(color);
		if (left != null)
			page.drawLine(x, y, left.x, left.y);
		if (right != null)
			page.drawLine(x, y, right.x, right.y);
		if (above != null)
			page.drawLine(x, y, above.x, above.y);
	}

	public void drawSquare(Graphics page, int width) {
		page.setColor(squareColor);
		page.fillRect(x - width / 2, y - width / 2, width, width);
	}

	public void highlightSquare(Graphics page, int width) {
		int playerNum = player;
		clearSquare(page, width);
		squareColor = PitCons.squareHighlightColor;
		drawSquare(page, width + 10);
		setPlayer(playerNum);
		drawSquare(page, width);
	}

	public void clearSquare(Graphics page, int width) {
		setPlayer(0);
		drawSquare(page, width);
	}

}
