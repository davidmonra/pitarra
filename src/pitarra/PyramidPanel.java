package pitarra;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
	// private Color lineColor;
	private Vertex[][] grid;
	private GamePanel gPanel;

	public PyramidPanel(ImageIcon backdrop, int x, int y, int width,
			Color lineColor, GamePanel panel) {
		setBackdrop(backdrop);
		this.panelWidth = width;
		// this.lineColor = lineColor;
		this.gPanel = panel;
		// the faces of the pyramid are unfolded onto a 3 x 8 2D grid
		this.grid = new Vertex[gridRows][gridCols];
		this.setDoubleBuffered(true);
		for (int row = 0; row < gridRows; row++) {
			for (int col = 0; col < gridCols; col++) {
				// Vertices at the corners of the pyramid have even indices
				boolean isCornerVertex = false;
				if (col % 2 == 0)
					isCornerVertex = true;

				grid[row][col] = new Vertex(isCornerVertex);
			}
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

		addMouseListener(new PyramidListener());
	}

	public void paintComponent(Graphics page) {
		setGridCoords();
		Utilities.drawBackdrop(page, backdrop, panelWidth, panelWidth, this);
		drawPyramid(page);
	}

	public void setBackdrop(ImageIcon backdrop) {
		this.backdrop = backdrop;
	}

	public int getSquareWidth() {
		return squareWidth;
	}

	public int getGridCols() {
		return gridCols;
	}

	public Vertex[][] getGrid() {
		return grid;
	}

	public void resetPyramid() {
		repaint();
		for (Vertex[] row : grid)
			for (Vertex v : row)
				v.clearSquare(getGraphics(), squareWidth);
	}

	public void highlightPlayerSquares(int playerNumber) {
		for (Vertex[] row : grid)
			for (Vertex v : row)
				if (v.getPlayer() == playerNumber)
					v.highlight(getGraphics(), squareWidth);
	}

	public boolean playerCanMove(int playerNumber) {
		for (Vertex[] row : grid)
			for (Vertex v : row)
				if (v.getPlayer() == playerNumber
						&& ((v.getAbove() != null && v.getAbove().isAvailable())
								|| (v.getBelow() != null && v.getBelow()
										.isAvailable())
								|| (v.getLeft() != null && v.getLeft()
										.isAvailable()) || (v.getRight() != null && v
								.getRight().isAvailable())))
					return true;

		return false;
	}

	// private classes
	private void setGridCoords() {
		this.panelWidth = Math.min(getWidth(), getHeight());
		this.squareWidth = (int) (panelWidth * PitCons.squareSizeScale);
		this.gridOffset = panelWidth / 10;
		this.gridWidth = panelWidth - 2 * gridOffset;

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
				v.drawConnectingLines(page);
		for (Vertex[] row : grid)
			for (Vertex v : row)
				v.drawPieces(page, this, squareWidth);
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

	private class PyramidListener implements MouseListener {
		Vertex startVertex, endVertex;

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
			startVertex = checkIfClicked(e.getPoint());
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			endVertex = checkIfClicked(e.getPoint());
			if (startVertex == null || endVertex == null) // nothing to do
				return;
			gPanel.playGame(startVertex, endVertex);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
	}
}