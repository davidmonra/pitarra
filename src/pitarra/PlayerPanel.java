package pitarra;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

@SuppressWarnings("serial")
public class PlayerPanel extends JPanel {
	private static final String nameFieldPrompt = " Name? ";
	private GamePanel gPanel;
	private ImageIcon backdrop;
	private String player;
	private String playerName;
	private JLabel titleLabel, nameLabel;
	private JPanel namePanel;

	public PlayerPanel(GamePanel panel, ImageIcon backdrop, int playerNumber) {
		this.gPanel = panel;
		this.backdrop = backdrop;
		this.player = "Player " + playerNumber;
		this.playerName = nameFieldPrompt;
		this.titleLabel = new JLabel("PLAYER " + playerNumber, JLabel.CENTER);
		this.nameLabel = new JLabel(nameFieldPrompt, JLabel.CENTER);
		this.namePanel = new JPanel();
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		// label appearance
		titleLabel.setFont(PitCons.boldFont);
		titleLabel.setOpaque(true);
		switch (playerNumber) {
		case 1:
			titleLabel.setBackground(PitCons.player1SquareColor);
			nameLabel.setBackground(PitCons.player1SquareColor);
			break;
		case 2:
			titleLabel.setBackground(PitCons.player2SquareColor);
			nameLabel.setBackground(PitCons.player2SquareColor);
			break;
		default:
			titleLabel.setBackground(PitCons.genericBackColor);
			nameLabel.setBackground(PitCons.genericBackColor);
		}
		titleLabel.setForeground(PitCons.genericBorderColor);
		titleLabel.setBorder(BorderFactory
				.createLineBorder(PitCons.genericBorderColor));
		titleLabel.setIcon(backdrop);

		// nameLabel appearance
		nameLabel.setFont(PitCons.normalFont);
		nameLabel.setOpaque(true);
		nameLabel.setForeground(Color.gray);
		nameLabel.setBorder(BorderFactory
				.createBevelBorder(BevelBorder.LOWERED));
		nameLabel.addMouseListener(new PlayerListener());

		// namePanel appearance
		namePanel.setBorder(BorderFactory.createEtchedBorder());
		namePanel.setLayout(new GridLayout(2, 1));
		// add labels to namePanel
		namePanel.add(titleLabel);
		namePanel.add(nameLabel);

		add(namePanel);
		setPreferredSize(new Dimension(PitCons.initialPyramidSize / 2,
				PitCons.initialPyramidSize / 8));

	}

	public void paintComponent(Graphics page) {
		super.paintComponent(page);
		namePanel.repaint();
	}

	private class PlayerListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == nameLabel) {
				String userName = JOptionPane.showInputDialog(nameLabel,
						nameFieldPrompt, player, JOptionPane.QUESTION_MESSAGE);

				if (userName != null && !userName.isEmpty()) {
					playerName = userName;
					nameLabel.setForeground(Color.black);
					nameLabel.setText(" " + playerName + " ");
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
