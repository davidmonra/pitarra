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
	// Fonts
	protected final static int fontSize = 20;
	protected final static Font boldFont = new Font("Ariel", Font.BOLD,
			fontSize);
	protected final static Font normalFont = new Font("Sans Serif", Font.PLAIN,
			fontSize);
	// window constants
	protected final static double windowSizeScale = .6; // % of screen width
	protected final static double pyramidSizeScale = .5; // % of window width
	protected final static double squareSizeScale = .06; // % of pyramid width
	// color constants
	protected final static Color pyramidLineColor = Color.black;
	protected final static Color player1Color = Color.red;
	protected final static Color player2Color = Color.yellow;
	protected final static Color genericBackColor = Color.orange;

	// border thickness constants
	protected final static Color genericBorderColor = Color.black;
	protected final static int borderThickness = 3;
	protected final static Border genericBorder = BorderFactory
			.createLineBorder(PitCons.genericBorderColor,
					PitCons.borderThickness);

	// image icons
	// Change the .jpg image files in the icons folder to change the backdrops.
	// Make sure the file names are the same.
	protected final static ImageIcon gameBackdrop = new ImageIcon(
			"icons/GameBackdrop.jpg");
	protected final static ImageIcon pyramidBackdrop = new ImageIcon(
			"icons/PyramidBackdrop.jpg");
	protected final static ImageIcon Player1Backdrop = new ImageIcon(
			"icons/Player1Backdrop.jpg");
	protected final static ImageIcon Player2Backdrop = new ImageIcon(
			"icons/Player2Backdrop.jpg");

	// File text constants
	protected final static String advancedInstructions = FileUtilities
			.readFile("Text Files/Advanced Instructions.txt");
	protected final static String basicInstructions = FileUtilities
			.readFile("Text Files/Basic Instructions.txt");

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
