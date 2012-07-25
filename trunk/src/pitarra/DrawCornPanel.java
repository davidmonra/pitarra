package pitarra;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DrawCornPanel extends JPanel {
	private Vertex v;

	public DrawCornPanel() {
		super();
		this.v = new Vertex(false);
	}

	public void paint(Graphics page) {
		super.paintComponent(page);
		setBackground(Color.cyan);

		v.setLocation(getWidth() / 2, getHeight() / 2);
		v.setPlayer(1);
		// v.drawSquare(page, getWidth()/2);
		v.drawCornKernal(page, getWidth());
	}
}