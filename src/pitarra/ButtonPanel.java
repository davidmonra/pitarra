package pitarra;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ButtonPanel extends JPanel {
	private GamePanel gPanel;
	private MouseListener listener;
	private JButton startNewGame;

	public ButtonPanel(GamePanel game) {
		this.gPanel = game;
		this.listener = new ButtonListener();
		this.startNewGame = new JButton(PitCons.newGame);
		setBackground(PitCons.genericBackColor);

		startNewGame.addMouseListener(listener);
		add(startNewGame);
	}

	private class ButtonListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			Object source = e.getSource();

			if (source == startNewGame) {
				gPanel.getPyramid().resetPyramid();
			}
		}
	}
}
