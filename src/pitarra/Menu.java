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
	private JMenu menuMain, menuGame, menuHelp, menuLang, menuSound;
	private JMenuItem Exit, Instruction, ResetGame, About;
	// action listener for traditional game option, beginner game option, exit,
	// sound, and instructions
	private ActionListener alTrad, alBeg, alEx, alSound, alInstruction,
			alAbout, alReset, alEng, alSpa, alMusic;
	private JCheckBoxMenuItem sound, NewTrad, NewBeg, langEng, langSpa, music;
	private ButtonGroup menuGroupGame, menuGroupLang;

	public Menu(GamePanel game) {
		this.gPanel = game;

		// menu bar
		this.menuBar = new JMenuBar();

		// menu bar options
		this.menuMain = new JMenu(Language.menuFileEng);
		this.menuGame = new JMenu(Language.menuGameEng);
		this.menuHelp = new JMenu(Language.menuHelpEng);
		this.menuLang = new JMenu(Language.menuLanguageEng);
		this.menuSound = new JMenu(Language.menuSoundEng);

		// menu items
		this.NewBeg = new JCheckBoxMenuItem(Language.menuNewBegEng);
		this.NewTrad = new JCheckBoxMenuItem(Language.menuNewTradEng);
		this.sound = new JCheckBoxMenuItem(Language.menuSoundEng);
		this.Exit = new JMenuItem(Language.menuExitEng);
		this.Instruction = new JMenuItem(Language.menuInstructionEng);
		this.ResetGame = new JMenuItem(Language.menuResetGameEng);
		this.About = new JMenuItem(Language.menuAboutEng);
		this.langEng = new JCheckBoxMenuItem(Language.menuEnglishEng);
		this.langSpa = new JCheckBoxMenuItem(Language.menuSpanishEng);
		this.music = new JCheckBoxMenuItem(Language.menuMusicEng);

		// menu grouping to make game mode mutually exclusive
		this.menuGroupGame = new ButtonGroup();
		this.menuGroupLang = new ButtonGroup();

		this.alTrad = new ButtonActionNewGameT();
		this.alBeg = new ButtonActionNewGameB();
		this.alEx = new ButtonActionExit();
		this.alSound = new ButtonActionSound();
		this.alInstruction = new ButtonActionInstruction();
		this.alAbout = new ButtonActionAbout();
		this.alReset = new ButtonActionResetGame();
		this.alEng = new ButtonActionEnglish();
		this.alSpa = new ButtonActionSpanish();
		this.alMusic = new ButtonActionMusic();

		this.NewTrad.addActionListener(alTrad);
		this.NewBeg.addActionListener(alBeg);
		this.Exit.addActionListener(alEx);
		this.sound.addActionListener(alSound);
		this.Instruction.addActionListener(alInstruction);
		this.ResetGame.addActionListener(alReset);
		this.About.addActionListener(alAbout);
		this.langEng.addActionListener(alEng);
		this.langSpa.addActionListener(alSpa);
		this.music.addActionListener(alMusic);

		setMenu();
	}

	public JMenuBar getMenuBar() {
		return menuBar;
	}

	private void setMenu() {
		menuBar.add(menuMain);
		menuBar.add(menuGame);
		menuBar.add(menuSound);
		menuBar.add(menuLang);
		menuBar.add(menuHelp);

		menuGroupGame.add(NewTrad);
		menuGroupGame.add(NewBeg);
		menuGroupGame.setSelected(NewBeg.getModel(), true);
		menuGroupLang.add(langEng);
		menuGroupLang.add(langSpa);
		menuGroupLang.setSelected(langEng.getModel(), true);

		menuGame.add(NewBeg);
		menuGame.add(NewTrad);
		menuGame.addSeparator();
		menuGame.add(Instruction);

		menuSound.add(sound);
		menuSound.add(music);
		sound.setSelected(true);
		music.setSelected(true);

		menuLang.add(langEng);
		menuLang.add(langSpa);

		menuHelp.add(About);

		menuMain.add(ResetGame);
		menuMain.addSeparator();
		menuMain.add(Exit);
	}

	private class ButtonActionAbout implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// PitCons.aboutText
			gPanel.showMessage(PitCons.aboutText, "About Pitarra");
		}

	}

	private class ButtonActionResetGame implements ActionListener {

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
				gPanel.playBackgroundSound(false);
			} else {
				gPanel.setSoundOn(true);
				gPanel.playBackgroundSound(true);
			}
		}
	}

	private class ButtonActionMusic implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (gPanel.isMusicOn()) {
				gPanel.setMusicOn(false);
				gPanel.playBackgroundSound(false);
			} else {
				gPanel.setMusicOn(true);
				gPanel.playBackgroundSound(true);
			}
		}

	}

	private class ButtonActionInstruction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			gPanel.showInstructions();
		}
	}

	private class ButtonActionEnglish implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			menuMain.setText(Language.menuFileEng);
			menuGame.setText(Language.menuGameEng);
			menuHelp.setText(Language.menuHelpEng);
			menuLang.setText(Language.menuLanguageEng);
			menuSound.setText(Language.menuSoundEng);

			NewBeg.setText(Language.menuNewBegEng);
			NewTrad.setText(Language.menuNewTradEng);
			sound.setText(Language.menuSoundEng);
			Exit.setText(Language.menuExitEng);
			Instruction.setText(Language.menuInstructionEng);
			ResetGame.setText(Language.menuResetGameEng);
			About.setText(Language.menuAboutEng);
			langEng.setText(Language.menuEnglishEng);
			langSpa.setText(Language.menuSpanishEng);
			music.setText(Language.menuMusicEng);

			/*
			 * ignore for now, language implementation for later
			 * Language.playerString = Language.playerStringEng;
			 * Language.playerStringAC = Language.playerStringACEng;
			 * Language.pieceCountText = Language.pieceCountTextEng;
			 * Language.pieceLostText = Language.pieceLostTextEng;
			 * gPanel.getPlayer1().updateLabels();
			 * gPanel.getPlayer2().updateLabels();
			 * //System.out.print(Language.playerString);
			 */

		}

	}

	private class ButtonActionSpanish implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			menuMain.setText(Language.menuFileSpa);
			menuGame.setText(Language.menuGameSpa);
			menuHelp.setText(Language.menuHelpSpa);
			menuLang.setText(Language.menuLanguageSpa);
			menuSound.setText(Language.menuSoundSpa);

			NewBeg.setText(Language.menuNewBegSpa);
			NewTrad.setText(Language.menuNewTradSpa);
			sound.setText(Language.menuSoundSpa);
			Exit.setText(Language.menuExitSpa);
			Instruction.setText(Language.menuInstructionSpa);
			ResetGame.setText(Language.menuResetGameSpa);
			About.setText(Language.menuAboutSpa);
			langEng.setText(Language.menuEnglishSpa);
			langSpa.setText(Language.menuSpanishSpa);
			music.setText(Language.menuMusicSpa);

			/*
			 * ignore for now, language implementation for later
			 * Language.playerString = Language.playerStringSpa;
			 * Language.playerStringAC = Language.playerStringACSpa;
			 * Language.pieceCountText = Language.pieceCountTextSpa;
			 * Language.pieceLostText = Language.pieceLostTextSpa;
			 * gPanel.getPlayer1().updateLabels();
			 * gPanel.getPlayer2().updateLabels();
			 * //System.out.print(Language.playerString);
			 */
		}

	}

}