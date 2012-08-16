package pitarra;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Vertex extends Point {
	private boolean available;
	private boolean cornerVertex;
	private boolean showSquares, showCornKernals, showGridLines;
	private boolean highlighted;
	private int playerNumber;
	private int squareWidth;
	private Color shapeColor, lineColor, shapeClearColor, shapeHighlightColor;
	private Color player1Color, player2Color;
	private Vertex left, right, above, below; // adjacent vertices
	private Image player1CornImage, player2CornImage, player1CornImageHL,
			player2CornImageHL;
	private int player1CornWidth, player1CornHeight, player2CornWidth,
			player2CornHeight, player1CornHLWidth, player1CornHLHeight,
			player2CornHLWidth, player2CornHLHeight;

	public Vertex(boolean isCornerVertex, int squareWidth) {
		super();
		this.available = true;
		this.cornerVertex = isCornerVertex;
		this.showSquares = PitCons.showSquares;
		this.showCornKernals = PitCons.showCornKernals;
		this.showGridLines = PitCons.showGridLines;
		this.highlighted = false;
		this.playerNumber = 0;
		this.squareWidth = squareWidth;
		this.lineColor = PitCons.pyramidLineColor;
		this.shapeClearColor = PitCons.squareClearColor;
		this.shapeHighlightColor = PitCons.squareHighlightColor;
		this.shapeColor = PitCons.squareClearColor;
		this.player1Color = PitCons.player1Color;
		this.player2Color = PitCons.player2Color;
		this.left = null;
		this.right = null;
		this.above = null;
		this.below = null;
		// regular corn images
		this.player1CornImage = PitCons.player1CornKernal.getImage();
		this.player2CornImage = PitCons.player2CornKernal.getImage();
		// regular corn dimensions
		this.player1CornWidth = PitCons.player1CornKernal.getIconWidth();
		this.player1CornHeight = PitCons.player1CornKernal.getIconHeight();
		this.player2CornWidth = PitCons.player2CornKernal.getIconWidth();
		this.player2CornHeight = PitCons.player2CornKernal.getIconHeight();
		// highlighted corn images
		this.player1CornImageHL = PitCons.player1CornKernalHL.getImage();
		this.player2CornImageHL = PitCons.player2CornKernalHL.getImage();
		// highlighted corn dimensions
		this.player1CornHLWidth = PitCons.player1CornKernalHL.getIconWidth();
		this.player1CornHLHeight = PitCons.player1CornKernalHL.getIconHeight();
		this.player2CornHLWidth = PitCons.player2CornKernalHL.getIconWidth();
		this.player2CornHLHeight = PitCons.player2CornKernalHL.getIconHeight();
		setLocation(0, 0);
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public boolean isCornerVertex() {
		return cornerVertex;
	}

	public boolean isHighlighted() {
		return highlighted;
	}

	public void setHighlighted(boolean highlighted) {
		this.highlighted = highlighted;
	}

	public int getPlayerNumber() {
		return playerNumber;
	}

	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}

	public int getSquareWidth() {
		return squareWidth;
	}

	public void setSquareWidth(int squareWidth) {
		this.squareWidth = squareWidth;
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

	public void setPlayer(int player) {
		this.playerNumber = player;
		switch (player) {
		case 1:
			shapeColor = player1Color;
			setAvailable(false);
			break;
		case 2:
			shapeColor = player2Color;
			setAvailable(false);
			break;
		default:
			shapeColor = shapeClearColor;
			setAvailable(true);
		}
	}

	public void drawConnectingLines(Graphics page) {
		if (showGridLines) {
			page.setColor(lineColor);
			if (left != null)
				page.drawLine(x, y, left.x, left.y);
			if (right != null)
				page.drawLine(x, y, right.x, right.y);
			if (above != null)
				page.drawLine(x, y, above.x, above.y);
		}
	}

	public void drawPieces(Graphics page, JPanel panel) {
		if (highlighted) {
			Color sqColor = shapeColor;
			int sqWidth = squareWidth;
			if (showSquares) { // draw a small player color square
				shapeColor = shapeHighlightColor;
				drawSquare(page);
				squareWidth = squareWidth * 7 / 10;
				shapeColor = sqColor;
				drawSquare(page);
				squareWidth = sqWidth;
			}
			if (showCornKernals) { // show corn
				drawCornKernal(page, panel);
				shapeColor = sqColor;
			}
		} else {
			if (showSquares)
				drawSquare(page);
			if (showCornKernals && playerNumber != 0)
				drawCornKernal(page, panel);
		}
	}

	public void clear() {
		this.highlighted = false;
		setPlayer(0);
	}

	private void drawCornKernal(Graphics page, JPanel panel) {
		Image cornImage;
		int width, height;

		switch (playerNumber) {
		case 1:
			cornImage = highlighted ? player1CornImageHL : player1CornImage;
			width = highlighted ? player1CornHLWidth : player1CornWidth;
			height = highlighted ? player1CornHLHeight : player1CornHeight;
			break;
		case 2:
			cornImage = highlighted ? player2CornImageHL : player2CornImage;
			width = highlighted ? player2CornHLWidth : player2CornWidth;
			height = highlighted ? player2CornHLHeight : player2CornHeight;
			break;
		default:
			return;
		}

		int cornX = x - squareWidth / 2;
		int cornY = y - squareWidth / 2;

		page.drawImage(cornImage, cornX, cornY, cornX + squareWidth, cornY
				+ squareWidth, 0, 0, width, height, panel);
	}

	private void drawSquare(Graphics page) {
		page.setColor(shapeColor);
		page.fillRect(x - squareWidth / 2, y - squareWidth / 2, squareWidth,
				squareWidth);
	}
}
