package pitarra;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu extends JMenuBar{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9120600991573125349L;
	private JMenuBar menuBar;
	private GamePanel gPanel;
	private JMenu menu;
	private JMenuItem NewTrad, NewBeg, Exit;
	private ActionListener trad, beg, ex;
    
    public Menu(GamePanel game){
    	this.menuBar = new JMenuBar();
    	this.gPanel = game;
    	this.menu = new JMenu("Menu");
    	this.NewBeg = new JMenuItem("New Beginner Game");
    	this.NewTrad = new JMenuItem("New Traditional Game");
    	this.Exit = new JMenuItem("Exit");
    	
    	this.trad = new ButtonActionNewGameT();
    	this.beg = new ButtonActionNewGameB();
    	this.ex = new ButtonActionExit();
    	
    	this.NewTrad.addActionListener(trad);
    	this.NewBeg.addActionListener(beg);
    	this.Exit.addActionListener(ex);
    	setMenu();
    }
    
    public JMenuBar getMenuBar(){
    	return menuBar;
    }
    
    private void setMenu(){
    	menuBar.add(menu);
    	menu.add(NewTrad);
    	menu.add(NewBeg);
    	menu.add(Exit);
    }
    
    private class ButtonActionNewGameT implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			gPanel.getPyramid().resetPyramid();
			gPanel.setPlayBasicGame(false);
		}
    }
    
    private class ButtonActionNewGameB implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			gPanel.getPyramid().resetPyramid();
			gPanel.setPlayBasicGame(true);
		}
    	
    }
    
    private class ButtonActionExit implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
    	
    }
}
