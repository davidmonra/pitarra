package pitarra;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class DrawCornTester {
	private static final int frameWidth = 300;
	private static final Dimension screenSize = Toolkit.getDefaultToolkit()
			.getScreenSize();

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		DrawCornPanel corn = new DrawCornPanel();

		corn.setPreferredSize(new Dimension(frameWidth, frameWidth));

		frame.getContentPane().add(corn);
		frame.pack();

		int startX = (int) ((screenSize.getWidth() - frameWidth) / 2);
		int startY = (int) ((screenSize.getHeight() - frameWidth) / 2);

		frame.setLocation(startX, startY);
		frame.setVisible(true);
	}
}
