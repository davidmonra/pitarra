package pitarra;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.ButtonGroup;

public class Menu extends JMenuBar {
        /**
         * 
         */
        private static final long serialVersionUID = -9120600991573125349L;
        private JMenuBar menuBar;
        private GamePanel gPanel;
        private JMenu menuMain, menuGame, menuHelp;
        private JMenuItem Exit, Instruction, ResetGame, About;
        // action listener for traditional game option, beginner game option, exit,
        // sound, and instructions
        private ActionListener alTrad, alBeg, alEx, alSound, alInstruction, alAbout, alReset;
        private JCheckBoxMenuItem sound, NewTrad, NewBeg;
        private ButtonGroup menuGroupGame;

        public Menu(GamePanel game) {
                this.gPanel = game;

                // menu bar
                this.menuBar = new JMenuBar();

                // menu bar options
                this.menuMain = new JMenu("File");
                this.menuGame = new JMenu("Game");
                this.menuHelp = new JMenu("Help");

                // menu items
                this.NewBeg = new JCheckBoxMenuItem(PitCons.menuNewBeg);
                this.NewTrad = new JCheckBoxMenuItem(PitCons.menuNewTrad);
                this.sound = new JCheckBoxMenuItem(PitCons.menuSound);
                this.Exit = new JMenuItem(PitCons.menuExit);
                this.Instruction = new JMenuItem(PitCons.menuInstruction);
                this.ResetGame = new JMenuItem(PitCons.menuResetGame);
                this.About = new JMenuItem(PitCons.menuAbout);

                // menu grouping to make game mode mutually exclusive
                this.menuGroupGame = new ButtonGroup();

                this.alTrad = new ButtonActionNewGameT();
                this.alBeg = new ButtonActionNewGameB();
                this.alEx = new ButtonActionExit();
                this.alSound = new ButtonActionSound();
                this.alInstruction = new ButtonActionInstruction();
                this.alAbout = new ButtonActionAbout();
                this.alReset = new ButtonActionResetGame();

                this.NewTrad.addActionListener(alTrad);
                this.NewBeg.addActionListener(alBeg);
                this.Exit.addActionListener(alEx);
                this.sound.addActionListener(alSound);
                this.Instruction.addActionListener(alInstruction);
                this.ResetGame.addActionListener(alReset);
                this.About.addActionListener(alAbout);
                
                setMenu();
        }

        public JMenuBar getMenuBar() {
                return menuBar;
        }

        private void setMenu() {
                menuBar.add(menuMain);
                menuBar.add(menuGame);
                menuBar.add(menuHelp);

                menuGroupGame.add(NewTrad);
                menuGroupGame.add(NewBeg);
                menuGroupGame.setSelected(NewBeg.getModel(), true);

                menuGame.add(NewBeg);
                menuGame.add(NewTrad);
                menuGame.addSeparator();
                menuGame.add(sound);
                menuGame.addSeparator();
                menuGame.add(Instruction);
                sound.setSelected(true);
                
                menuHelp.add(About);

                menuMain.add(ResetGame);
                menuMain.addSeparator();
                menuMain.add(Exit);
        }

        private class ButtonActionAbout implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//PitCons.aboutText
				gPanel.showMessage(PitCons.aboutText, "About Pitarra");
			}
        	
        }
        
        private class ButtonActionResetGame implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				gPanel.resetGame();
			}
        	
        }
        
        private class ButtonActionNewGameT implements ActionListener {

                @Override
                public void actionPerformed(ActionEvent arg0) {
                        gPanel.setBasicGame(false);
                        gPanel.resetGame();
                }
        }

        private class ButtonActionNewGameB implements ActionListener {

                @Override
                public void actionPerformed(ActionEvent e) {
                        gPanel.setBasicGame(true);
                        gPanel.resetGame();
                }
        }

        private class ButtonActionExit implements ActionListener {

                @Override
                public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                }
        }

        private class ButtonActionSound implements ActionListener {

                @Override
                public void actionPerformed(ActionEvent arg0) {
                        if (gPanel.isSoundOn()) {
                                gPanel.setSoundOn(false);
                        } else {
                                gPanel.setSoundOn(true);
                        }
                }
        }

        private class ButtonActionInstruction implements ActionListener {

                @Override
                public void actionPerformed(ActionEvent e) {
                        gPanel.showInstructions();
                }
        }

}