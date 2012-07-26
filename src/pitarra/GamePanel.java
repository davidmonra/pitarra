package pitarra;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	private static final String takePieceMessage = ", take a highlighted square.";
	private static final String yourTurnMessage = "'s turn.";
	private static final String movePieceMessage = ", drag your piece on a line.";
	private ImageIcon backdrop;
	private PyramidPanel pyramid;
	private boolean isPlayer1Turn;
	private boolean basicGame;
	private boolean soundOn;
	private boolean takeNextPiece; // if true, next piece clicked is removed
	private int playerToRemove;
	private int p1PiecesLeft, p2PiecesLeft, p1PiecesLost, p2PiecesLost;
	private JPanel eastPanel, westPanel, centerPanel, northPanel;
	private PlayerPanel player1, player2;
	private JLabel notifyText;
	private Dimension windowSize;

	public GamePanel(ImageIcon backdrop) {
		super();
		this.backdrop = backdrop;
		this.pyramid = new PyramidPanel(PitCons.pyramidBackdrop,
				PitCons.initialPyramidPosition.x,
				PitCons.initialPyramidPosition.y, PitCons.initialPyramidSize,
				PitCons.pyramidLineColor, this);
		this.basicGame = true;
		this.soundOn = true;
		this.takeNextPiece = false;
		this.playerToRemove = 0;
		this.eastPanel = new JPanel();
		this.westPanel = new JPanel();
		this.centerPanel = new JPanel();
		this.northPanel = new JPanel();
		this.player1 = new PlayerPanel(PitCons.player1Backdrop, 1,
				PitCons.player1Color, this);
		this.player2 = new PlayerPanel(PitCons.player2Backdrop, 2,
				PitCons.player2Color, this);
		this.player1.reset(basicGame);
		this.player1.showColorText(true);
		this.player2.reset(basicGame);
		this.player2.showColorText(true);
		this.p1PiecesLeft = PitCons.initialNumberOfPieces;
		this.p2PiecesLeft = PitCons.initialNumberOfPieces;
		this.p1PiecesLost = 0;
		this.p2PiecesLost = 0;
		this.isPlayer1Turn = true;
		this.notifyText = new JLabel();
		this.windowSize = new Dimension(
				(int) PitCons.initialWindowSize.getWidth(),
				(int) PitCons.initialWindowSize.getHeight());
		clearTakeNextPiece();

		setLayout(new BorderLayout());
		setPreferredSize(windowSize);
		setDoubleBuffered(true);

		eastPanel.setOpaque(false);
		eastPanel.add(player2);

		centerPanel.setOpaque(false);
		centerPanel.setLayout(new GridLayout());
		centerPanel.add(pyramid);

		westPanel.setOpaque(false);
		westPanel.add(player1);

		notifyText.setOpaque(false);
		notifyText.setFont(PitCons.bigBoldFont);
		notifyText.setForeground(PitCons.notifyTextColor);
		notifyText.setText(PitCons.title);

		northPanel.setOpaque(false);
		northPanel.add(notifyText);

		add(eastPanel, BorderLayout.EAST);
		add(centerPanel, BorderLayout.CENTER);
		add(westPanel, BorderLayout.WEST);
		add(northPanel, BorderLayout.NORTH);

		notifyPlayer(getCurrentPlayerNumber(), yourTurnMessage);
	}

	public void resetGame() {
		this.player1.reset(basicGame);
		this.player2.reset(basicGame);
		this.p1PiecesLeft = PitCons.initialNumberOfPieces;
		this.p2PiecesLeft = PitCons.initialNumberOfPieces;
		this.p1PiecesLost = 0;
		this.p2PiecesLost = 0;
		this.isPlayer1Turn = true;
		this.pyramid.resetPyramid();
		clearTakeNextPiece();
		notifyPlayer(getCurrentPlayerNumber(), yourTurnMessage);
	}

	public void paintComponent(Graphics page) {
		Utilities.drawBackdrop(page, backdrop, getWidth(), getHeight(), this);
		this.getSize();
	}

	public void playGame(Vertex startVertex, Vertex endVertex) {
		if (startVertex == null || endVertex == null)
			throw new IllegalArgumentException("Vertices cannot be null.");

		int currentPlayer = getCurrentPlayerNumber();
		int otherPlayer = getOtherPlayerNumber();

		boolean dragAndDrop = (startVertex != endVertex);
		boolean takePiece = takeNextPiece && !dragAndDrop;
		boolean putPiece = !dragAndDrop && !takeNextPiece
				&& getPiecesLeft(currentPlayer) > 0;
		boolean movePiece = !takeNextPiece && dragAndDrop;

		if (basicGame) {// basic game
			if (!dragAndDrop)
				playBasicGame(startVertex);
		} else { // traditional game
			if (takePiece)
				removePieceFromBoard(startVertex);
			else if (putPiece)
				putPiecesOnBoard(startVertex, endVertex, currentPlayer,
						otherPlayer);
			else if (movePiece)
				movePiecesOnBoard(startVertex, endVertex, currentPlayer,
						otherPlayer);
		}
	}

	private void removePieceFromBoard(Vertex v) {
		int vertexPlayerNumber = v.getPlayer();
		if (vertexPlayerNumber == playerToRemove) {
			takePiece(v);
			incrPiecesLost(vertexPlayerNumber);
			notifyPlayer(getCurrentPlayerNumber(), yourTurnMessage);
		}
	}

	private void putPiecesOnBoard(Vertex v, Vertex newLocation,
			int currentPlayer, int otherPlayer) {
		if (v.isAvailable()) {// put down more pieces
			v.setPlayer(currentPlayer);
			v.drawPieces(pyramid.getGraphics(), this, pyramid.getSquareWidth());
			decrPiecesLeft(currentPlayer); // set the game piece counts
			if (playerGot3inArow(currentPlayer, v)) { // is v in 3-in-a-row?
				highlightPieces(otherPlayer);
				notifyPlayer(otherPlayer, takePieceMessage);
			} else {
				notifyPlayer(otherPlayer, yourTurnMessage);
			}
			switchPlayer();
		}
	}

	private void movePiecesOnBoard(Vertex v, Vertex newLocation,
			int currentPlayer, int otherPlayer) {
		boolean isAdjacentVertex = newLocation == v.getLeft()
				|| newLocation == v.getRight() || newLocation == v.getAbove()
				|| newLocation == v.getBelow();

		if (v.getPlayer() == currentPlayer && newLocation.isAvailable()
				&& isAdjacentVertex) {
			v.clearSquare(pyramid.getGraphics(), pyramid.getSquareWidth());
			newLocation.setPlayer(currentPlayer);
			newLocation.drawPieces(pyramid.getGraphics(), this,
					pyramid.getSquareWidth());
			if (playerGot3inArow(currentPlayer, newLocation)) {
				highlightPieces(otherPlayer);
				notifyPlayer(otherPlayer, takePieceMessage);
			} else {
				notifyPlayer(otherPlayer, movePieceMessage);
			}
			switchPlayer();
		}
	}

	private void playBasicGame(Vertex v) {
		if (!v.isAvailable()) // can't change a square once it's been set
			return;
		int playerNumber = getCurrentPlayerNumber();
		v.setPlayer(playerNumber);
		v.drawPieces(pyramid.getGraphics(), this, pyramid.getSquareWidth());
		decrPiecesLeft(playerNumber);
		switchPlayer(); // next player's turn
		notifyPlayer(getCurrentPlayerNumber(), yourTurnMessage);
		int winner = checkWin();
		if (winner != 0) {
			weGotWinner(winner);
			resetGame();
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
		Vertex[][] grid = pyramid.getGrid();
		int gridCols = pyramid.getGridCols();

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

	private void highlightPieces(int player) {
		// highlight pieces that can be removed
		pyramid.highlightPlayerSquares(player);
		// set remove piece flag
		setTakeNextPiece(true, player);
	}

	private void notifyPlayer(int player, String message) {
		if (player == 1)
			setNotifyText(player1.getPlayerName() + message,
					PitCons.player1Color);
		else
			setNotifyText(player2.getPlayerName() + message,
					PitCons.player2Color);
	}

	// checks if Vertex v is part of a 3-in-a-row for the current player
	private boolean playerGot3inArow(int player, Vertex v) {
		if (v.getPlayer() != player)
			return false;
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

	private void switchPlayer() {
		this.isPlayer1Turn = !isPlayer1Turn;
	}

	public int getPiecesLeft(int playerNumber) {
		switch (playerNumber) {
		case 1:
			return p1PiecesLeft;
		case 2:
			return p2PiecesLeft;
		default:
			throw new IllegalArgumentException("Player number must be 1 or 2.");
		}
	}

	// decrements the number of pieces left by 1
	public void decrPiecesLeft(int playerNumber) {
		switch (playerNumber) {
		case 1:
			p1PiecesLeft--;
			player1.setPiecesLeft(p1PiecesLeft);
			break;
		case 2:
			p2PiecesLeft--;
			player2.setPiecesLeft(p2PiecesLeft);
			break;
		default:
			throw new IllegalArgumentException("Player number must be 1 or 2.");
		}
	}

	public int getPiecesLost(int playerNumber) {
		switch (playerNumber) {
		case 1:
			return p1PiecesLost;
		case 2:
			return p2PiecesLost;
		default:
			throw new IllegalArgumentException("Player number must be 1 or 2.");
		}
	}

	// increments the number of pieces lost by 1
	public void incrPiecesLost(int playerNumber) {
		switch (playerNumber) {
		case 1:
			p1PiecesLost++;
			player1.setPiecesLost(p1PiecesLost);
			break;
		case 2:
			p2PiecesLost++;
			player2.setPiecesLost(p2PiecesLost);
			break;
		default:
			throw new IllegalArgumentException("Player number must be 1 or 2.");
		}
	}

	public void setBasicGame(boolean basicGame) {
		this.basicGame = basicGame;
	}

	public boolean isSoundOn() {
		return soundOn;
	}

	public void setSoundOn(boolean soundOn) {
		this.soundOn = soundOn;
	}

	public void setNotifyText(String message, Color color) {
		this.notifyText.setForeground(color);
		this.notifyText.setText(message);
	}

	public void clearTakeNextPiece() {
		setTakeNextPiece(false, 0);
	}

	public void setTakeNextPiece(boolean takeNextPiece, int player) {
		this.takeNextPiece = takeNextPiece;
		this.playerToRemove = player;
	}

	public void takePiece(Vertex v) {
		v.clearSquare(pyramid.getGraphics(), pyramid.getSquareWidth());
		pyramid.paintComponent(pyramid.getGraphics());
		clearTakeNextPiece();
	}

	public int getCurrentPlayerNumber() {
		return isPlayer1Turn ? 1 : 2;
	}

	public int getOtherPlayerNumber() {
		return getCurrentPlayerNumber() % 2 + 1;
	}

	public void showMessage(String message, String title) {
		JOptionPane.showMessageDialog(this, message, title,
				JOptionPane.PLAIN_MESSAGE);
	}

	public void showInstructions() {
		if (basicGame) {
			showMessage(PitCons.basicInstructions,
					PitCons.basicInstructionsTitle);
		} else {
			showMessage(PitCons.advancedInstructions,
					PitCons.advancedInstructionsTitle);
		}
	}

	public void weGotWinner(int playerNumber) {
		String message;
		Color color;
		switch (playerNumber) { // get the player's name
		case 1:
			message = player1.getPlayerName();
			color = PitCons.player1Color;
			break;
		case 2:
			message = player2.getPlayerName();
			color = PitCons.player2Color;
			break;
		default:
			throw new IllegalArgumentException("Player number must be 1 or 2.");
		}

		message += " is the winner!";
		setNotifyText(message, color);
		showMessage(message, "Player " + playerNumber + " wins!");

	}
}
