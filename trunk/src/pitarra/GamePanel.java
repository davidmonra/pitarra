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
	private ButtonPanel buttons;
	private boolean isPlayer1Turn;

	public GamePanel(ImageIcon backdrop) {
		super();
		this.backdrop = backdrop;
		this.isPlayer1Turn = true;
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(
				(int) PitCons.initialWindowSize.getWidth(),
				(int) PitCons.initialWindowSize.getHeight()));
		setDoubleBuffered(true);

		pyramid = new PyramidPanel(PitCons.pyramidBackdrop,
				PitCons.initialPyramidPosition.x,
				PitCons.initialPyramidPosition.y, PitCons.initialPyramidSize,
				PitCons.pyramidLineColor, this);
		add(pyramid, BorderLayout.CENTER);

		buttons = new ButtonPanel(this);
		add(buttons, BorderLayout.NORTH);
	}

	public boolean isPlayer1Turn() {
		return isPlayer1Turn;
	}

	public void switchPlayer() {
		this.isPlayer1Turn = !isPlayer1Turn;
	}

	public PyramidPanel getPyramid() {
		return pyramid;
	}

	public void showMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}

	public void paintComponent(Graphics page) {
		super.paintComponent(page);
		// draw backdrop
		if (backdrop != null) {
			page.drawImage(backdrop.getImage(), 0, 0, getWidth(), getHeight(),
					0, 0, backdrop.getIconWidth(), backdrop.getIconHeight(),
					this);
		}
	}

}
