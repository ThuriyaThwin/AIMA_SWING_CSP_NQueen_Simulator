package Tutorial.Swing;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class DrawingColor {
	public static void main(String[] args) {
		DrawingColor d = new DrawingColor();
	}

	public DrawingColor() {
		JFrame frame = new JFrame("Drawing with Alpha");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new MyComponent());
		frame.setSize(400, 600);
		frame.setVisible(true);
	}

	public class MyComponent extends JComponent {

		public MyComponent() {

		}

		@Override
		public void paint(Graphics g) {
			int height = 200;
			int width = 120;
			g.setColor(Color.red);
			g.drawRect(10, 10, height, width);
			g.setColor(Color.gray);
			g.fillRect(11, 11, height, width);
			g.setColor(Color.red);
			g.drawOval(250, 20, height, width);
			g.setColor(Color.magenta);
			g.fillOval(249, 19, height, width);
		}
	}
}