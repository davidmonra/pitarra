package pitarra;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PlayerPanel extends JPanel {
	private static final String nameFieldPrompt = "Enter your name";
	private static final String pieceCountText = "Left: ";
	private static final String pieceLostText = "Lost: ";
	private GamePanel gPanel;
	private JLabel titleLabel, nameLabel, piecesLeft, piecesLost;
	private JPanel namePanel;
	private String player;
	private String playerName;
	private ImageIcon backdrop;
	private Color playerColor;

	public PlayerPanel(ImageIcon backdrop, int playerNumber, Color playerColor,
			GamePanel panel) {
		this.gPanel = panel;
		this.titleLabel = new JLabel("PLAYER " + playerNumber, JLabel.CENTER);
		this.nameLabel = new JLabel(nameFieldPrompt, JLabel.CENTER);
		this.piecesLeft = new JLabel(pieceCountText
				+ gPanel.getPiecesLeft(playerNumber), JLabel.CENTER);
		this.piecesLost = new JLabel(pieceLostText
				+ gPanel.getPiecesLost(playerNumber), JLabel.CENTER);
		this.namePanel = new JPanel();
		this.player = "Player " + playerNumber;
		this.playerName = nameFieldPrompt;
		this.setOpaque(false);
		this.backdrop = backdrop;
		this.playerColor = playerColor;

		// titleLabel appearance
		titleLabel.setOpaque(false);
		titleLabel.setFont(PitCons.italicBoldFont);

		// nameLabel appearance
		nameLabel.setOpaque(false);
		nameLabel.setFont(PitCons.boldFont);
		nameLabel.setForeground(playerColor);
		nameLabel.addMouseListener(new PlayerListener());

		// pieceCount appearance
		piecesLeft.setOpaque(false);
		piecesLeft.setFont(PitCons.italicBoldFont);

		// pieceCount appearance
		piecesLost.setOpaque(false);
		piecesLost.setFont(PitCons.italicBoldFont);

		// namePanel appearance
		namePanel.setOpaque(false);
		namePanel.setPreferredSize(new Dimension(
				PitCons.initialPlayerPanelWidth,
				PitCons.initialPlayerPanelWidth * 2 / 3));

		setPiecesLeft(PitCons.initialNumberOfPieces);
		namePanel.setLayout(new GridLayout(4, 1));

		// add labels to namePanel
		namePanel.add(titleLabel);
		namePanel.add(nameLabel);
		namePanel.add(piecesLeft);
		namePanel.add(piecesLost);

		add(namePanel);
	}

	public void paintComponent(Graphics page) {
		Utilities.drawBackdrop(page, backdrop, getWidth(), getHeight(), this);
	}

	public void reset(boolean isBasicGame) {
		setPiecesLeft(PitCons.initialNumberOfPieces);
		setPiecesLost(0);
		piecesLost.setVisible(!isBasicGame);
		// repaint();
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPiecesLeft(int pieceCount) {
		this.piecesLeft.setText(pieceCountText + pieceCount);
	}

	public void setPiecesLost(int piecesLost) {
		this.piecesLost.setText(pieceLostText + piecesLost);
	}

	private class PlayerListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == nameLabel) {
				String userName = JOptionPane.showInputDialog(nameLabel,
						nameFieldPrompt, player, JOptionPane.QUESTION_MESSAGE);

				if (userName != null && !userName.isEmpty()) {
					playerName = userName;
					nameLabel.setForeground(playerColor);
					nameLabel.setText(playerName);
				}
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}
}
