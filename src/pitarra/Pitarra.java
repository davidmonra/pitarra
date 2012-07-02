package pitarra;

import javax.swing.JFrame;

public class Pitarra {
	public static void main(String[] args) {
		JFrame frame = new JFrame(PitCons.title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(PitCons.initialWindowSize);

		frame.getContentPane().add(new GamePanel(PitCons.gameBackdrop));
		frame.pack();
		frame.setLocation(PitCons.initialWindowPosition);

		frame.setResizable(true);
		frame.setVisible(true);
	}

}