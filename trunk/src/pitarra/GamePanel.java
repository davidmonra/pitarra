package pitarra;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	private ImageIcon backdrop;
	private PyramidPanel pyramid;
	private boolean isPlayer1Turn;
	private boolean basicGame;
	private boolean soundOn;
	private boolean takeNextPiece; // if true, next piece clicked is removed
	private int p1PiecesLeft, p2PiecesLeft, p1PiecesLost, p2PiecesLost;

	private PlayerPanel player1, player2;

	public GamePanel(ImageIcon backdrop) {
		super();
		this.backdrop = backdrop;
		this.pyramid = new PyramidPanel(PitCons.pyramidBackdrop,
				PitCons.initialPyramidPosition.x,
				PitCons.initialPyramidPosition.y, PitCons.initialPyramidSize,
				PitCons.pyramidLineColor, this);
		this.isPlayer1Turn = true;
		this.basicGame = true;
		this.soundOn = true;
		this.takeNextPiece = false;
		this.p1PiecesLeft = 12;
		this.p2PiecesLeft = 12;
		this.p1PiecesLost = 0;
		this.p2PiecesLost = 0;

		this.player1 = new PlayerPanel(this, 1);
		this.player2 = new PlayerPanel(this, 2);

		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(
				(int) PitCons.initialWindowSize.getWidth(),
				(int) PitCons.initialWindowSize.getHeight()));
		setDoubleBuffered(true);

		add(pyramid, BorderLayout.CENTER);
		add(player1, BorderLayout.WEST);
		add(player2, BorderLayout.EAST);
	}

	public void resetGame() {
		this.p1PiecesLeft = 12;
		this.p2PiecesLeft = 12;
		this.p1PiecesLost = 0;
		this.p2PiecesLost = 0;
		this.pyramid.resetPyramid();
		setPlayer1Turn(true);
		setRemoveNextPiece(false);
	}

	public boolean isPlayer1Turn() {
		return isPlayer1Turn;
	}

	public void setPlayer1Turn(boolean isPlayer1Turn) {
		this.isPlayer1Turn = isPlayer1Turn;
	}

	public void switchPlayer() {
		this.isPlayer1Turn = !isPlayer1Turn;
	}

	public PyramidPanel getPyramid() {
		return pyramid;
	}

	public boolean isBasicGame() {
		return basicGame;
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
			break;
		case 2:
			p2PiecesLeft--;
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
			break;
		case 2:
			p2PiecesLost++;
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

	public boolean isRemoveNextPiece() {
		return takeNextPiece;
	}

	public void setRemoveNextPiece(boolean removeNextPiece) {
		this.takeNextPiece = removeNextPiece;
	}

	public void showMessage(String message, String title) {
		JOptionPane.showMessageDialog(this, message, title,
				JOptionPane.PLAIN_MESSAGE);
	}

	public void showInstructions() {
		if (isBasicGame()) {
			showMessage(PitCons.basicInstructions, "Basic Game Instructions");
		} else {
			showMessage(PitCons.advancedInstructions,
					"Advanced Game Instructions");
		}
	}

	public void weGotWinner(int playerNumber) {
		String message;
		switch (playerNumber) { // get the player's name
		case 1:
			message = player1.getPlayerName();
			break;
		case 2:
			message = player2.getPlayerName();
			break;
		default:
			throw new IllegalArgumentException("Player number must be 1 or 2.");
		}

		message += " is the winner!";
		showMessage(message, "Player " + playerNumber + " wins!");
	}

	public void paintComponent(Graphics page) {
		// draw backdrop
		if (backdrop != null) {
			page.drawImage(backdrop.getImage(), 0, 0, getWidth(), getHeight(),
					0, 0, backdrop.getIconWidth(), backdrop.getIconHeight(),
					this);
		}
	}

}
