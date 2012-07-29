//constants class for Pitarra
package pitarra;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.Border;

public final class PitCons {
	// text strings
	protected final static String title = "PITARRA";
	protected final static String newGame = "New Game";

	// window constants
	protected final static double windowSizeScale = 0.7; // % of screen width
	protected final static double pyramidSizeScale = .5; // % of window width
	protected final static double squareSizeScale = .06; // % of pyramid width

	// Fonts
	protected final static int fontSize = (int) (35 * windowSizeScale);
	protected final static String fontName = "Ariel";
	protected final static Font normalFont = new Font(fontName, Font.PLAIN,
			fontSize);
	protected final static Font boldFont = new Font(fontName, Font.BOLD,
			fontSize);
	protected final static Font italicFont = new Font(fontName, Font.ITALIC,
			fontSize);
	protected final static Font italicBoldFont = new Font(fontName, Font.ITALIC
			+ Font.BOLD, fontSize);
	protected final static Font bigBoldFont = new Font(fontName, Font.ITALIC
			+ Font.BOLD, fontSize * 3 / 2);

	// number of pieces
	protected final static int initialNumberOfPieces = 12;
	// lose more than maxPiecesYouCanLose and you lose the game
	// (i.e. have to have at least 3 pieces to keep playing)
	protected final static int maxPiecesYouCanLose = initialNumberOfPieces - 3;

	// color constants
	protected final static Color pyramidLineColor = Color.black;
	protected final static Color custonFontColor = new Color(255, 150, 75);
	protected final static Color notifyTextColor = Color.cyan;
	// protected final static Color player1Color = custonFontColor;
	protected final static Color player1Color = Color.yellow;
	protected final static Color player2Color = Color.red;
	protected final static Color genericBackColor = Color.orange;
	protected final static Color squareClearColor = Color.gray;
	protected final static Color squareHighlightColor = Color.cyan;

	// border thickness constants
	protected final static Color genericBorderColor = Color.black;
	protected final static int borderThickness = 3;
	protected final static Border genericBorder = BorderFactory
			.createLineBorder(PitCons.genericBorderColor,
					PitCons.borderThickness);

	// player panel constants
	protected final static String nameFieldPrompt = "Enter your name";
	protected final static String pieceCountText = " left";
	protected final static String pieceLostText = " lost";

	// menu strings
	protected final static String menuNewBeg = "New Beginner Game";
	protected final static String menuNewTrad = "New Traditional Game";
	protected final static String menuSound = "Sound";
	protected final static String menuExit = "Exit";
	protected final static String menuInstruction = "Instruction";
	protected final static String menuResetGame = "Reset Game";
	protected final static String menuAbout = "About";

	// sound constants
	protected final static String dropSound = "Sound Files/BD.wav";
	protected final static String moveSound = "Sound Files/Windows XP Minimize.wav";
	protected final static String takeSound = "Sound Files/recycle.wav";
	protected final static String winSound = "Sound Files/tada.wav";
	protected final static String highlightSound = "Sound Files/windows xp pop-up blocked.wav";
	protected final static String backgroundMusic = "Sound Files/music-pitarra-1.wav";

	// image icons
	// Change the .jpg image files in the icons folder to change the backdrops.
	// Make sure the file names are the same.
	protected final static ImageIcon gameBackdrop = new ImageIcon(
			"icons/GameBackdrop.jpg");
	// protected final static ImageIcon pyramidBackdrop = new ImageIcon(
	// "icons/PyramidBackdrop.jpg");
	protected final static ImageIcon pyramidBackdrop = new ImageIcon(
			"icons/PyramidBackdrop.jpg");
	protected final static ImageIcon player1Backdrop = new ImageIcon(
			"icons/Player1Backdrop.jpg");
	protected final static ImageIcon player2Backdrop = new ImageIcon(
			"icons/Player2Backdrop.jpg");
	protected final static ImageIcon calendarBackdrop = new ImageIcon(
			"icons/AztecCalendarBackdrop.JPG");
	protected final static ImageIcon sacrificeBackdrop = new ImageIcon(
			"icons/AztecSacrifice.jpg.JPG");
	protected final static ImageIcon player1CornKernal = new ImageIcon(
			"icons/player1corn.JPG");
	protected final static ImageIcon player2CornKernal = new ImageIcon(
			"icons/player2corn.JPG");

	// instruction constants
	protected final static String basicInstructionsTitle = "Basic Game Instructions";
	protected final static String advancedInstructionsTitle = "Advanced Game Instructions";

	// File text constants
	protected final static String advancedInstructions = Utilities
			.readFile("Text Files/Advanced Instructions.txt");
	protected final static String aboutText = Utilities
			.readFile("Text Files/About Text.txt");
	protected final static String basicInstructions = Utilities
			.readFile("Text Files/Basic Instructions.txt");

	// facts from file constant
	protected final static String[] facts = Utilities
			.readFilePerLine("Text Files/Facts.txt");

	// Calculated size and position constants
	// Don't change these. Change the "Scale" values above to change the sizes.
	protected final static Dimension screenSize = Toolkit.getDefaultToolkit()
			.getScreenSize();
	protected final static Dimension initialWindowSize = new Dimension(
			(int) (screenSize.getWidth() * windowSizeScale),
			(int) (screenSize.getHeight() * windowSizeScale));
	protected final static Point initialWindowPosition = new Point(
			(int) (screenSize.getWidth() - initialWindowSize.getWidth()) / 2,
			(int) (screenSize.getHeight() - initialWindowSize.getHeight()) / 2);

	// pyramid constants
	protected final static int initialPyramidSize = (int) (initialWindowSize
			.getWidth() * pyramidSizeScale);
	protected final static Point initialPyramidPosition = new Point(
			(int) initialWindowSize.getWidth() / 2,
			(int) initialWindowSize.getHeight() / 2);
	protected final static int squareSize = (int) (initialPyramidSize * squareSizeScale);

	// Panel Size constants
	protected final static int initialPlayerPanelWidth = (int) ((initialWindowSize.width - initialPyramidSize) / 2.5);

	// constructor: nothing to do for static constants class
	private PitCons() {
	};
}