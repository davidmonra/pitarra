package pitarra;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PyramidPanel extends JPanel {
	private final int gridRows = 3;
	private final int gridCols = 4 * (gridRows - 1);
	private int panelWidth;
	private int gridOffset;
	private int gridWidth;
	private int squareWidth;
	private ImageIcon backdrop;
	private Color lineColor;
	private Vertex[][] grid;
	private GamePanel gPanel;

	public PyramidPanel(ImageIcon backdrop, int x, int y, int width,
			Color lineColor, GamePanel panel) {
		setBackdrop(backdrop);
		setBounds(x - width / 2, y - width / 2, width, width);
		setDoubleBuffered(true);
		this.panelWidth = width;
		this.lineColor = lineColor;
		this.gPanel = panel;
		// the faces of the pyramid are unfolded onto a 3 x 8 2D grid
		this.grid = new Vertex[gridRows][gridCols];

		for (int row = 0; row < gridRows; row++)
			for (int col = 0; col < gridCols; col++) {
				// Vertices at the corners of the pyramid have even indices
				boolean isCornerVertex = false;
				if (col % 2 == 0)
					isCornerVertex = true;

				grid[row][col] = new Vertex(isCornerVertex);
			}

		// set adjacent vertices
		Vertex left, right, above, below;
		for (int row = 0; row < gridRows; row++) {
			for (int col = 0; col < gridCols; col++) {
				if (row > 0) {
					below = grid[row - 1][col];
				} else {
					below = null;
				}
				if (row < gridRows - 1) {
					above = grid[row + 1][col];
				} else {
					above = null;
				}
				if (col > 0) {
					left = grid[row][col - 1];
				} else { // wraps around to the last column
					left = grid[row][gridCols - 1];
				}
				if (col < gridCols - 1) {
					right = grid[row][col + 1];
				} else { // wraps around to the first column
					right = grid[row][0];
				}

				grid[row][col].setAbove(above);
				grid[row][col].setBelow(below);
				grid[row][col].setLeft(left);
				grid[row][col].setRight(right);
			}
		}
		setGridCoords();
		addMouseListener(new PyramidListener());
	}

	public void paintComponent(Graphics page) {
		setGridCoords();
		drawBackdrop(page);
		drawPyramid(page);
	}

	public void setBackdrop(ImageIcon backdrop) {
		this.backdrop = backdrop;
	}

	public void resetPyramid() {
		gPanel.setPlayer1Turn(true); // player 1 starts the game
		for (Vertex[] row : grid)
			for (Vertex v : row) {
				v.setAvailable(true);
				v.drawSquare(getGraphics(), squareWidth);
			}
	}

	// private classes
	private void setGridCoords() {
		this.panelWidth = (int) (gPanel.getWidth() * PitCons.pyramidSizeScale);
		this.squareWidth = (int) (panelWidth * PitCons.squareSizeScale);
		this.gridOffset = panelWidth / 10;
		this.gridWidth = panelWidth - 2 * gridOffset;

		setBounds(gPanel.getWidth() / 2 - panelWidth / 2, gPanel.getHeight()
				/ 2 - panelWidth / 2, panelWidth, panelWidth);

		int centerX = panelWidth / 2;
		int centerY = panelWidth / 2;

		int topLevelSpacing = gridWidth / 6;
		int midLevelSpacing = topLevelSpacing * 2;
		int botLevelSpacing = topLevelSpacing * 3;

		// bottom level
		grid[0][0].setLocation(centerX - botLevelSpacing, centerY
				- botLevelSpacing);
		grid[0][1].setLocation(centerX - botLevelSpacing, centerY);
		grid[0][2].setLocation(centerX - botLevelSpacing, centerY
				+ botLevelSpacing);
		grid[0][3].setLocation(centerX, centerY + botLevelSpacing);
		grid[0][4].setLocation(centerX + botLevelSpacing, centerY
				+ botLevelSpacing);
		grid[0][5].setLocation(centerX + botLevelSpacing, centerY);
		grid[0][6].setLocation(centerX + botLevelSpacing, centerY
				- botLevelSpacing);
		grid[0][7].setLocation(centerX, centerY - botLevelSpacing);

		// middle level
		grid[1][0].setLocation(centerX - midLevelSpacing, centerY
				- midLevelSpacing);
		grid[1][1].setLocation(centerX - midLevelSpacing, centerY);
		grid[1][2].setLocation(centerX - midLevelSpacing, centerY
				+ midLevelSpacing);
		grid[1][3].setLocation(centerX, centerY + midLevelSpacing);
		grid[1][4].setLocation(centerX + midLevelSpacing, centerY
				+ midLevelSpacing);
		grid[1][5].setLocation(centerX + midLevelSpacing, centerY);
		grid[1][6].setLocation(centerX + midLevelSpacing, centerY
				- midLevelSpacing);
		grid[1][7].setLocation(centerX, centerY - midLevelSpacing);

		// top level
		grid[2][0].setLocation(centerX - topLevelSpacing, centerY
				- topLevelSpacing);
		grid[2][1].setLocation(centerX - topLevelSpacing, centerY);
		grid[2][2].setLocation(centerX - topLevelSpacing, centerY
				+ topLevelSpacing);
		grid[2][3].setLocation(centerX, centerY + topLevelSpacing);
		grid[2][4].setLocation(centerX + topLevelSpacing, centerY
				+ topLevelSpacing);
		grid[2][5].setLocation(centerX + topLevelSpacing, centerY);
		grid[2][6].setLocation(centerX + topLevelSpacing, centerY
				- topLevelSpacing);
		grid[2][7].setLocation(centerX, centerY - topLevelSpacing);
	}

	private void drawPyramid(Graphics page) {
		for (Vertex[] row : grid)
			for (Vertex v : row)
				v.drawConnectingLines(page, lineColor);
		for (Vertex[] row : grid)
			for (Vertex v : row)
				v.drawSquare(page, squareWidth);
	}

	private void drawBackdrop(Graphics page) {
		// draw backdrop
		if (backdrop != null) {
			page.drawImage(backdrop.getImage(), 0, 0, panelWidth, panelWidth,
					0, 0, backdrop.getIconWidth(), backdrop.getIconHeight(),
					this);
		}
	}

	private Vertex checkIfClicked(Point p) {
		for (Vertex[] row : grid)
			for (Vertex v : row) {
				int distance = (int) v.distance(p);
				if (distance <= squareWidth / 2) {
					return v;
				}
			}
		return null;
	}

	private void playBasicGame(Vertex v) {
		if (!v.isAvailable()) // can't change a square once it's been set
			return;
		v.setPlayer(gPanel.isPlayer1Turn() ? 1 : 2);
		v.drawSquare(getGraphics(), squareWidth);
		gPanel.switchPlayer(); // next player's turn
		int winner = checkWin();
		if (winner != 0) {
			gPanel.weGotWinner(winner);
			resetPyramid();
		}
	}

	private int checkLeft(Vertex mid) {
		int win = 0;
		if (mid.getPlayer() != 0) {
			if (mid.getPlayer() == mid.getLeft().getPlayer()
					&& mid.getPlayer() == mid.getLeft().getLeft().getPlayer()) {
				return mid.getPlayer();
			}
		}
		return win;
	}

	private int checkWin() {
		int win = 0;
		for (int x = 0; x < gridCols; x++) {
			if (grid[0][x].getPlayer() == 1) {
				if (grid[0][x].getPlayer() == grid[0][x].getAbove().getPlayer()
						&& grid[0][x].getPlayer() == grid[0][x].getAbove()
								.getAbove().getPlayer()) {
					return grid[0][x].getPlayer();
				}
			} else if (grid[0][x].getPlayer() == 2) {
				if (grid[0][x].getPlayer() == grid[0][x].getAbove().getPlayer()
						&& grid[0][x].getPlayer() == grid[0][x].getAbove()
								.getAbove().getPlayer()) {
					return grid[0][x].getPlayer();
				}
			}
		}
		for (int x = 0; x < gridCols; x++) {
			if (x % 2 == 0) {
				int win1 = checkLeft(grid[0][x]);
				int win2 = checkLeft(grid[1][x]);
				int win3 = checkLeft(grid[2][x]);
				if (win1 != 0) {
					return win1;
				} else if (win2 != 0) {
					return win2;
				} else if (win3 != 0) {
					return win3;
				}
			}
		}

		return win;
	}

	private void playTraditionalGame(Vertex v) {

		System.out.println("\n");
		int currentPlayer = gPanel.isPlayer1Turn() ? 1 : 2;
		// remove the piece that was clicked
		if (gPanel.isRemoveNextPiece()) {
			int vertexPlayerNumber = v.getPlayer();
			if (!v.isAvailable() && vertexPlayerNumber != currentPlayer) {
				v.setAvailable(true); // clear the square
				v.drawSquare(getGraphics(), squareWidth);
				gPanel.incrPiecesLost(vertexPlayerNumber);
				gPanel.setRemoveNextPiece(false);
				gPanel.switchPlayer();

				System.out.println("Piece removed");
				// clear the remove piece flag

			} else {
				System.out
						.println("Can't remove your own piece.\nSelect a different vertex.");
			}
		} else if (v.isAvailable()) {
			v.setPlayer(currentPlayer);
			v.drawSquare(getGraphics(), squareWidth);
			// set the game piece counts
			gPanel.decrPiecesLeft(currentPlayer);
			// check if v is part of a 3-in-a-row
			if (currentPlayerGot3inArow(v)) {
				System.out.println("Player " + v.getPlayer()
						+ " gets to remove one of player "
						+ (v.getPlayer() % 2 + 1) + "'s pieces.");
				// highlight pieces that can be removed

				// set remove next piece flag in game panel
				gPanel.setRemoveNextPiece(true);
			} else {
				gPanel.switchPlayer(); // next player's turn
			}
		}
		// ===================================
		System.out.println("Corner Vertex =\t" + v.isCornerVertex());
		System.out.println("Player1: " + gPanel.getPiecesLeft(1) + "\tLost: "
				+ gPanel.getPiecesLost(1));
		System.out.println("Player2: " + gPanel.getPiecesLeft(2) + "\tLost: "
				+ gPanel.getPiecesLost(2));
		System.out.println("3-in-a-row =\t" + currentPlayerGot3inArow(v));

		// ===================================

	}

	// checks if Vertex v is part of a 3-in-a-row for the current player
	private boolean currentPlayerGot3inArow(Vertex v) {
		// check for vertical 3-in-a-row
		Vertex bottom = v;
		// go to the bottom vertex in the current column
		while (bottom.getBelow() != null) {
			bottom = bottom.getBelow();
		}
		if (v.getPlayer() == bottom.getPlayer()
				&& bottom.getPlayer() == bottom.getAbove().getPlayer()
				&& bottom.getPlayer() == bottom.getAbove().getAbove()
						.getPlayer()) {
			return true;
		}
		// check for horizontal 3-in-a-row
		// have to check 2 sides of the pyramid if v is a corner
		if (v.isCornerVertex()) {
			// check left face of pyramid
			if (v.getPlayer() == v.getLeft().getPlayer()
					&& v.getPlayer() == v.getLeft().getLeft().getPlayer()) {
				return true;
			}
			// check right face of pyramid
			if (v.getPlayer() == v.getRight().getPlayer()
					&& v.getPlayer() == v.getRight().getRight().getPlayer()) {
				return true;
			}
		} else { // v is a middle vertex
			// just check vertices to the left and right of v
			if (v.getPlayer() == v.getLeft().getPlayer()
					&& v.getPlayer() == v.getRight().getPlayer()) {
				return true;
			}
		}

		return false;
	}

	private class PyramidListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			Vertex v = checkIfClicked(e.getPoint()); // check the grid
			if (v != null) {
				if (gPanel.isBasicGame()) { // basic game
					playBasicGame(v);
				} else { // traditional game
					playTraditionalGame(v);
				}
			}
		}
	}

}