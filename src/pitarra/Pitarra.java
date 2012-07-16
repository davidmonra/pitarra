package pitarra;

import javax.swing.JFrame;

public class Pitarra {
        public static void main(String[] args) {
        		Menu menu;
        		GamePanel gPanel;
        		gPanel = new GamePanel(PitCons.gameBackdrop);
        		menu = new Menu(gPanel);
                JFrame frame = new JFrame(PitCons.title);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setPreferredSize(PitCons.initialWindowSize);

                frame.getContentPane().add(gPanel);
                frame.pack();
                frame.setLocation(PitCons.initialWindowPosition);
                frame.setJMenuBar(menu.getMenuBar());

                frame.setResizable(true);
                frame.setVisible(true);
        }

}