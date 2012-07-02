//constants class for Pitarra
package pitarra;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

public final class PitCons {
	// text strings
	protected final static String title = "PITARRA";
	protected final static String newGame = "New Game";
	// window constants
	protected final static double windowSizeScale = .6; // % of screen width
	protected final static double pyramidSizeScale = .5; // % of window width
	protected final static double squareSizeScale = .06; // % of pyramid width
	// color constants
	protected final static Color pyramidLineColor = Color.black;
	protected final static Color player1SquareColor = Color.red;
	protected final static Color player2SquareColor = Color.yellow;
	protected final static Color genericBackColor = Color.orange;
	// image icons
	// Change the .jpg image files in the icons folder to change the backdrops.
	// Make sure the file names are the same.
	protected final static ImageIcon gameBackdrop = new ImageIcon(
			"icons/GameBackdrop.jpg");
	protected final static ImageIcon pyramidBackdrop = new ImageIcon(
			"icons/PyramidBackdrop.jpg");
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

	// constructor: nothing to do for static constants class
	private PitCons() {
	};
}
