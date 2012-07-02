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
			for (int col = 0; col < gridCols; col++)
				grid[row][col] = new Vertex(gPanel);

		// set adjacent vertices
		Vertex left, right, above;
		for (int row = 0; row < gridRows; row++) {
			for (int col = 0; col < gridCols; col++) {
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
				grid[row][col].setLeft(left);
				grid[row][col].setRight(right);
			}
		}
		setGridCoords();
		addMouseListener(new PyramidListener());
	}

	public void paintComponent(Graphics page) {
		super.paintComponent(page);
		setGridCoords();
		drawBackdrop(page);
		drawPyramid(page);
	}

	public void setBackdrop(ImageIcon backdrop) {
		this.backdrop = backdrop;
	}

	public void resetPyramid() {
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

	private class PyramidListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			// check the grid
			Vertex v = checkIfClicked(e.getPoint());
			if (v != null) {
				v.setPlayer();
				v.drawSquare(getGraphics(), squareWidth);
			}
		}
	}

}
