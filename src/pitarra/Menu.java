package pitarra;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.ButtonGroup;

public class Menu extends JMenuBar{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9120600991573125349L;
	private JMenuBar menuBar;
	private GamePanel gPanel;
	private JMenu menuMain, menuGame;
	private JMenuItem Exit;
	private ActionListener alTrad, alBeg, alEx, alSound;
	private JCheckBoxMenuItem sound, NewTrad, NewBeg;
	private ButtonGroup menuGroupGame;
    
    public Menu(GamePanel game){
    	this.gPanel = game;
    	
    	//menu bar
    	this.menuBar = new JMenuBar();
    	
    	//menu bar options
    	this.menuMain = new JMenu("Menu");
    	this.menuGame = new JMenu("Game");
    	
    	//menu items
    	this.NewBeg = new JCheckBoxMenuItem("New Beginner Game");
    	this.NewTrad = new JCheckBoxMenuItem("New Traditional Game");
    	this.sound = new JCheckBoxMenuItem("Sound");
    	this.Exit = new JMenuItem("Exit");
    	
    	//menu grouping to make game mode mutually exclusive
    	this.menuGroupGame = new ButtonGroup();
    	
    	this.alTrad = new ButtonActionNewGameT();
    	this.alBeg = new ButtonActionNewGameB();
    	this.alEx = new ButtonActionExit();
    	this.alSound = new ButtonActionSound();
    	
    	this.NewTrad.addActionListener(alTrad);
    	this.NewBeg.addActionListener(alBeg);
    	this.Exit.addActionListener(alEx);
    	this.sound.addActionListener(alSound);
    	setMenu();
    }
    
    public JMenuBar getMenuBar(){
    	return menuBar;
    }
    
    private void setMenu(){
    	menuBar.add(menuMain);
    	menuBar.add(menuGame);
    	
    	menuGroupGame.add(NewTrad);
    	menuGroupGame.add(NewBeg);
    	
    	menuGame.add(NewTrad);
    	menuGame.add(NewBeg);
    	menuGame.addSeparator();
    	menuGame.add(sound);
    	
    	menuMain.add(Exit);
    }
    
    private class ButtonActionNewGameT implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			gPanel.setBasicGame(false);
			gPanel.getPyramid().resetPyramid();
		}
    }
    
    private class ButtonActionNewGameB implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			gPanel.setBasicGame(true);
			gPanel.getPyramid().resetPyramid();
		}
    }
    
    private class ButtonActionExit implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
    }
    
    private class ButtonActionSound implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
		}
    }
}
