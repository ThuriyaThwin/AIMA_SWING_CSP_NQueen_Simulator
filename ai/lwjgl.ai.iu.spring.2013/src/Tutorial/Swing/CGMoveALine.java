package Tutorial.Swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Custom Graphics Example: Using key/button to move a line left or right.
 */
@SuppressWarnings("serial")
public class CGMoveALine extends JFrame {
	// Name-constants for the various dimensions
	public static final int		CANVAS_WIDTH		= 400;
	public static final int		CANVAS_HEIGHT		= 140;
	public static final Color	LINE_COLOR			= Color.BLACK;
	public static final Color	CANVAS_BACKGROUND	= Color.CYAN;

	// The line from (x1, y1) to (x2, y2), initially position at the center
	private int					x1					= CANVAS_WIDTH / 2;
	private final int			y1					= CANVAS_HEIGHT / 8;
	private int					x2					= x1;
	private final int			y2					= (CANVAS_HEIGHT / 8) * 7;

	private final DrawCanvas	canvas;										// the custom drawing canvas (extends JPanel)

	/** Constructor to set up the GUI */
	public CGMoveALine() {
		// Set up a panel for the buttons
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new FlowLayout());
		JButton btnLeft = new JButton("Move Left ");
		btnPanel.add(btnLeft);
		btnLeft.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				x1 -= 10;
				x2 -= 10;
				canvas.repaint();
				requestFocus(); // change the focus to JFrame to receive KeyEvent
			}
		});
		JButton btnRight = new JButton("Move Right");
		btnPanel.add(btnRight);
		btnRight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				x1 += 10;
				x2 += 10;
				canvas.repaint();
				requestFocus(); // change the focus to JFrame to receive KeyEvent
			}
		});

		// Set up a custom drawing JPanel
		canvas = new DrawCanvas();
		canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

		// Add both panels to this JFrame
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		cp.add(canvas, BorderLayout.CENTER);
		cp.add(btnPanel, BorderLayout.SOUTH);

		// "this" JFrame fires KeyEvent
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				switch (evt.getKeyCode()) {
					case KeyEvent.VK_LEFT:
						x1 -= 10;
						x2 -= 10;
						repaint();
						break;
					case KeyEvent.VK_RIGHT:
						x1 += 10;
						x2 += 10;
						repaint();
						break;
				}
			}
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Move a Line");
		pack(); // pack all the components in the JFrame
		setVisible(true); // show it
		requestFocus(); // set the focus to JFrame to receive KeyEvent
	}

	/**
	 * DrawCanvas (inner class) is a JPanel used for custom drawing
	 */
	class DrawCanvas extends JPanel {
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(LINE_COLOR);
			g.drawLine(x1, y1, x2, y2); // draw the line
		}
	}

	/** The entry main() method */
	public static void main(String[] args) {
		// Run GUI codes on the Event-Dispatcher Thread for thread safety
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new CGMoveALine(); // Let the constructor do the job
			}
		});
	}
}