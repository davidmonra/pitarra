package pitarra;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SoundClipTester {
	private static JButton play, playForever, stop;
	private static SoundClipPlayer player;

	public static void main(String[] args) {
		JFrame frame = new JFrame("Sound Clip Tester");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		player = new SoundClipPlayer("Sound Files/chimes.wav");
		JPanel panel = new JPanel();

		play = new JButton("Play");
		playForever = new JButton("Play Forever");
		stop = new JButton("Stop");

		ButtonListener listener = new ButtonListener();
		play.addMouseListener(listener);
		playForever.addMouseListener(listener);
		stop.addMouseListener(listener);

		panel.add(play);
		panel.add(playForever);
		panel.add(stop);

		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
	}

	private static class ButtonListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == play) {
				player.play();
			}
			if (e.getSource() == playForever) {
				player.playItForever();
			}
			if (e.getSource() == stop) {
				player.stop();
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
