package pitarra;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Vertex extends Point {
	private boolean isAvailable;
	private boolean isCornerVertex;
	private int player;
	private Color squareColor, lineColor;
	private Vertex left, right, above, below; // adjacent vertices
	private ImageIcon cornKernal;

	public Vertex(boolean isCornerVertex) {
		super();
		setLocation(0, 0);
		this.isAvailable = true;
		this.isCornerVertex = isCornerVertex;
		this.player = 0;
		this.squareColor = PitCons.squareClearColor;
		this.lineColor = PitCons.pyramidLineColor;
		this.left = null;
		this.right = null;
		this.above = null;
		this.below = null;
		this.cornKernal = new ImageIcon();
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

	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}

	public void setPlayer(int player) {
		this.player = player;
		switch (player) {
		case 1:
			squareColor = PitCons.player1Color;
			cornKernal = PitCons.player1CornKernal;
			setAvailable(false);
			break;
		case 2:
			squareColor = PitCons.player2Color;
			cornKernal = PitCons.player2CornKernal;
			setAvailable(false);
			break;
		default:
			squareColor = PitCons.squareClearColor;
			setAvailable(true);
		}
	}

	public void drawConnectingLines(Graphics page) {
		page.setColor(lineColor);
		if (left != null)
			page.drawLine(x, y, left.x, left.y);
		if (right != null)
			page.drawLine(x, y, right.x, right.y);
		if (above != null)
			page.drawLine(x, y, above.x, above.y);
	}

	public void drawPieces(Graphics page, JPanel panel, int squareWidth) {
		drawSquare(page, squareWidth);
		if (player != 0)
			drawCornKernal(page, panel, squareWidth);
	}

	private void drawCornKernal(Graphics page, JPanel panel, int squareWidth) {
		int cornX = x - squareWidth / 2;
		int cornY = y - squareWidth / 2;
		page.drawImage(cornKernal.getImage(), cornX, cornY,
				cornX + squareWidth, cornY + squareWidth, 0, 0,
				cornKernal.getIconWidth(), cornKernal.getIconHeight(), panel);
	}

	private void drawSquare(Graphics page, int squareWidth) {
		page.setColor(squareColor);
		page.fillRect(x - squareWidth / 2, y - squareWidth / 2, squareWidth,
				squareWidth);
	}

	public void highlight(Graphics page, int squareWidth) {
		int playerNum = player;
		clearSquare(page, squareWidth);
		squareColor = PitCons.squareHighlightColor;
		drawSquare(page, squareWidth + 10);
		setPlayer(playerNum);
		drawPieces(page, null, squareWidth);
		// drawSquare(page, width);
	}

	public void clearSquare(Graphics page, int width) {
		setPlayer(0);
		drawSquare(page, width);
	}

}
