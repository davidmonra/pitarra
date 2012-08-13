package pitarra;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Pitarra {
	private static JFrame frame = new JFrame(PitCons.title);
	private static GamePanel gPanel = new GamePanel(PitCons.gameBackdrop);
	private static Menu menu = new Menu(gPanel);

	public static void main(String[] args) {
		// Add to Event Dispatch Thread (EDT) to prevent
		// potential race conditions
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowPitarra();
			}
		});
	}

	private static void createAndShowPitarra() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(PitCons.initialWindowSize);

		frame.getContentPane().add(gPanel);
		frame.pack();
		frame.setLocation(PitCons.initialWindowPosition);
		frame.setJMenuBar(menu.getMenuBar());

		frame.setResizable(false);
		frame.setVisible(true);
	}
}
