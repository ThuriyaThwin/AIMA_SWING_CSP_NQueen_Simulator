package Tutorial.Swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/** Test drawImage() thru ImageIcon */
@SuppressWarnings("serial")
public class CGDrawImageDemo extends JFrame {
	// Define named-constants for the various dimensions
	public static final int		ROWS				= 3;
	public static final int		COLS				= 3;
	public static final int		IMAGE_SIZE			= 50;
	public static final int		PADDING				= 20;							// padding from the border
	public static final int		CELL_SIZE			= IMAGE_SIZE + (2 * PADDING);
	public static final int		CANVAS_SIZE			= CELL_SIZE * ROWS;

	private final DrawCanvas	canvas;											// The drawing canvas
	private final Random		random				= new Random();				// for picking images in random

	// Images
	private final String		imgCrossFilename	= "res/bird.png";
	private final String		imgNoughtFilename	= "//res//fail.png";
	private final Image			imgCross;											// drawImage() uses an Image object
	private final Image			imgNought;

	/** Constructor to set up the GUI components */
	public CGDrawImageDemo() {
		// Prepare the ImageIcon and Image objects for drawImage()
		ImageIcon iconCross = null;
		ImageIcon iconNought = null;
		URL imgURL = getClass().getClassLoader().getResource(imgCrossFilename);

		if (imgURL != null) {
			iconCross = new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + imgCrossFilename);
			System.exit(0);
		}
		imgCross = iconCross.getImage();

		imgURL = getClass().getClassLoader().getResource(imgNoughtFilename);
		if (imgURL != null) {
			iconNought = new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + imgNoughtFilename);
			System.exit(0);
		}
		imgNought = iconNought.getImage();

		canvas = new DrawCanvas();
		canvas.setPreferredSize(new Dimension(CANVAS_SIZE, CANVAS_SIZE));
		setContentPane(canvas); // use JPanel as content-pane
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack(); // pack the components of this JFrame
		setTitle("Test drawImage()");
		setVisible(true);
	}

	/** DrawCanvas (inner class) is a JPanel used for custom drawing */
	private class DrawCanvas extends JPanel {
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			setBackground(Color.WHITE); // Set background color for this JPanel
			// Drawing Images (picked in random)
			for (int row = 0; row < ROWS; row++) {
				for (int col = 0; col < COLS; col++) {
					boolean useCross = random.nextBoolean();
					Image img = useCross ? imgCross : imgNought;
					g.drawImage(img, (CELL_SIZE * col) + PADDING, (CELL_SIZE * row) + PADDING, IMAGE_SIZE, IMAGE_SIZE, null);
				}
			}
			// Draw Borders
			g.fill3DRect(CELL_SIZE - 2, 0, 4, CELL_SIZE * 3, true);
			g.fill3DRect((CELL_SIZE * 2) - 2, 0, 4, CELL_SIZE * 3, true);
			g.fill3DRect(0, CELL_SIZE - 2, CELL_SIZE * 3, 4, true);
			g.fill3DRect(0, (CELL_SIZE * 2) - 2, CELL_SIZE * 3, 4, true);
		}
	}

	/** The entry main method */
	public static void main(String[] args) {
		// Run the GUI codes on the Event-Dispatching thread for thread-safety
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new CGDrawImageDemo(); // Let the constructor do the job
			}
		});
	}
}