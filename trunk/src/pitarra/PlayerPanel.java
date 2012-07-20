package pitarra;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PlayerPanel extends JPanel {
	private static final String nameFieldPrompt = "Enter your name";
	private GamePanel gPanel;
	private JLabel titleLabel, nameLabel;
	private JPanel namePanel;
	private String player;
	private String playerName;
	private Color playerColor;

	public PlayerPanel(GamePanel panel, int playerNumber) {
		this.gPanel = panel;
		this.titleLabel = new JLabel("PLAYER " + playerNumber, JLabel.CENTER);
		this.nameLabel = new JLabel(nameFieldPrompt, JLabel.CENTER);
		this.namePanel = new JPanel();
		this.player = "Player " + playerNumber;
		this.playerName = nameFieldPrompt;
		this.setOpaque(false);

		// set the player color
		switch (playerNumber) {
		case 1:
			this.playerColor = PitCons.player1Color;
			break;
		case 2:
			this.playerColor = PitCons.player2Color;
			break;
		default:
			this.playerColor = PitCons.genericBackColor;
		}

		// label appearance
		titleLabel.setOpaque(true);
		titleLabel.setFont(PitCons.boldFont);
		titleLabel.setForeground(Color.black);
		titleLabel.setBackground(playerColor);

		// nameLabel appearance
		nameLabel.setOpaque(false);
		nameLabel.setFont(PitCons.boldFont);
		nameLabel.setForeground(Color.white);
		nameLabel.addMouseListener(new PlayerListener());

		// namePanel appearance
		namePanel.setOpaque(false);
		namePanel.setPreferredSize(new Dimension(
				PitCons.initialPlayerPanelWidth,
				PitCons.initialPlayerPanelWidth / 2));
		namePanel.setLayout(new GridLayout(2, 1));
		namePanel.setBorder(PitCons.genericBorder);

		// add labels to namePanel
		namePanel.add(titleLabel);
		namePanel.add(nameLabel);

		add(namePanel);
	}

	public String getPlayerName() {
		return playerName;
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
