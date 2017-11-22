package AISim;

import java.awt.Font;
import java.io.File;

import lab5.Csp.Sudoku.ISodokuListener;
import lab5.Csp.Sudoku.SodokuBoard;
import lab5.Csp.Sudoku.SodokuVariable;
import lab5.Csp.Sudoku.SodokuVariableCollection;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Rectangle;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import aima.core.search.csp.Assignment;
import aima.core.search.csp.ImprovedBacktrackingStrategy;
import aima.core.search.csp.ImprovedBacktrackingStrategy.Inference;
import aima.core.search.csp.ImprovedBacktrackingStrategy.Selection;

public class SodokuOpenGL {
	private static final int		height		= 360;
	private static final int		width		= 800;

	public static final int			WIDTH		= 30;
	public static final int			HEIGHT		= 30;
	public static final int			WARP		= 3;

	public SodokuBoard				csp;
	public SodokuVariableCollection	var;
	private int						counter		= 0;
	private boolean					isRunning	= false;
	String							drawStatus	= "";

	public SodokuOpenGL(String filename) {
		var = new SodokuVariableCollection(filename);
		csp = new SodokuBoard(var);
	}

	public void solver() {
		isRunning = true;
		start_time = System.currentTimeMillis();
		ImprovedBacktrackingStrategy solver2 = new ImprovedBacktrackingStrategy();

		solver2.setVariableSelection(Selection.MRV);
		solver2.setInference(Inference.FORWARD_CHECKING);

		System.out.println("Apply Backtracking Strategy: ImprovedBacktrackingStrategy");

		ISodokuListener listener = new ISodokuListener() {

			@Override
			public void setVariable(SodokuVariable v, int assign) {
				counter++;
				v.value = assign;
				try {
					Thread.sleep(speed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};

		csp.setSodokuListener(listener);

		// Solve the Problem, and Get the result
		long start = System.currentTimeMillis();
		Assignment results = solver2.solve(csp);
		long finish = System.currentTimeMillis();
		System.out.println("counter = " + counter);
		long diff = finish - start;

		System.out.println("execute time " + (diff / 1000.0f) + " second");
		drawStatus = "execute time " + (diff / 1000.0f) + " second";
		isRunning = false;
		// Print the output
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				SodokuVariable v = var.get(i, j);
				v.value = (Integer) results.getAssignment(v);
			}
		}

		System.out.println("\n------->>  result  <<-----------");
		System.out.println(var.getString());

	}

	long	start_time	= 0;

	public void start() {

		initGL();
		initFont();

		new Thread(new Runnable() {

			@Override
			public void run() {
				solver();
				System.out.println("solve finish");
			}
		}).start();

		while (!Display.isCloseRequested()) {
			// Clear the screen and depth buffer

			int delta = getDelta();
			update(delta);
			pollInput();
			drawGL();

			Display.update();
			Display.sync(60);
		}

		Display.destroy();

		//System.exit(0);
	}

	private void initGL() {
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		Display.setTitle("Sodoku Simulator");
		GL11.glShadeModel(GL11.GL_SMOOTH);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, width, 0, height, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);

	}

	private TrueTypeFont	font;
	private int				speed;

	private void initFont() {
		Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
		font = new TrueTypeFont(awtFont, true);
	}

	private void drawGL() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(0.4f, 0.29f, 0.76f, 1);

		GL11.glPushMatrix();
		{
			GL11.glTranslatef(0, 360, 0);
			GL11.glRotatef(-90, 0, 0, 1);

			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {

					drawColor(i + 1, j + 1);
					GL11.glBegin(GL11.GL_QUADS);
					{
						int x = (i + 1) * (WIDTH + WARP);
						int y = (j + 1) * (HEIGHT + WARP);

						int X1 = x, Y1 = y;
						int X2 = x + WIDTH, Y2 = y;
						int X3 = x + WIDTH, Y3 = y + HEIGHT;
						int X4 = x, Y4 = y + HEIGHT;

						GL11.glVertex2f(X1, Y1);
						GL11.glVertex2f(X2, Y2);
						GL11.glVertex2f(X3, Y3);
						GL11.glVertex2f(X4, Y4);

					}
					GL11.glEnd();
				}
			}

			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					SodokuVariable v = var.get(i, j);
					if (v.value != 0) {
						if (!v.fixed) {
							drawString(String.valueOf(v.value), i, j, Color.white);
						} else {
							drawString(String.valueOf(v.value), i, j, Color.yellow);
						}
					}

				}
			}

			if (isRunning)
				drawString("Execute time = " + ((System.currentTimeMillis() - start_time) / 1000.0f) + " second", 30, 360.0f, Color.white);
			else
				drawString(drawStatus, 30, 360.0f, Color.white);
			drawString("Assign to Variable Counter = " + counter, 60, 360.0f, Color.white);
			//drawString("Thread.Sleep() =  " + speed, 90, 360.0f, Color.white);
			drawString("press T : goto goal", 90, 360.0f, Color.white);
			drawString("press P : pause", 120, 360.0f, Color.white);
			drawString("press C : continue", 150, 360.0f, Color.white);

			//GL11.glTranslatef(150, 150, 0);
		}
		GL11.glPopMatrix();
		//drawSlider();
	}

	Rectangle	rButtonSlider				= new Rectangle();
	Rectangle	rButtonLineDirectorSlider	= new Rectangle();
	boolean		rButtonSliderPressed		= false;
	int			rButtonSliderYLocation		= 300;

	public void drawSlider() {
		int WIDTH = 5;
		int HEIGHT = 200;

		GL11.glColor3f(1.0f, 1.0f, 1.0f);
		GL11.glBegin(GL11.GL_QUADS);
		{
			int x = 15;
			int y = Display.getHeight() - HEIGHT - this.HEIGHT;

			int X1 = x, Y1 = y;
			int X2 = x + WIDTH, Y2 = y;
			int X3 = x + WIDTH, Y3 = y + HEIGHT;
			int X4 = x, Y4 = y + HEIGHT;

			rButtonLineDirectorSlider.setLocation(x, y);
			rButtonLineDirectorSlider.setSize(WIDTH, HEIGHT);

			GL11.glVertex2f(X1, Y1);
			GL11.glVertex2f(X2, Y2);
			GL11.glVertex2f(X3, Y3);
			GL11.glVertex2f(X4, Y4);
		}
		GL11.glEnd();
		/////draw controller
		WIDTH = 20;
		HEIGHT = 8;

		if (rButtonSliderPressed) {
			GL11.glColor3f(0.2f, 0.2f, 0.2f);
			HEIGHT = 30;
		} else {
			GL11.glColor3f(1.0f, 1.0f, 1.0f);
			HEIGHT = 8;
		}

		GL11.glBegin(GL11.GL_QUADS);
		{
			int x = 8;
			int y = rButtonSliderYLocation;

			int X1 = x, Y1 = y;
			int X2 = x + WIDTH, Y2 = y;
			int X3 = x + WIDTH, Y3 = y + HEIGHT;
			int X4 = x, Y4 = y + HEIGHT;

			rButtonSlider.setLocation(x, y);
			rButtonSlider.setSize(WIDTH, HEIGHT);

			GL11.glVertex2f(X1, Y1);
			GL11.glVertex2f(X2, Y2);
			GL11.glVertex2f(X3, Y3);
			GL11.glVertex2f(X4, Y4);
		}

		GL11.glEnd();
	}

	public void drawString(String text, int i, int j, Color color) {

		int x = ((i + 1) * (WIDTH + WARP)) + (WARP * 3);
		int y = ((j + 1) * (HEIGHT + WARP)) + (WARP * 3);

		GL11.glPushMatrix();
		{
			GL11.glTranslatef(x, y, 0);
			GL11.glScalef(0.7f, -0.7f, 0.7f);
			GL11.glRotated(-90, 0, 0, 1);
			GL11.glTranslatef(-x, -y, 0);
			Color.white.bind();
			GL11.glEnable(GL11.GL_BLEND);
			font.drawString(x, y, text, color);
			GL11.glDisable(GL11.GL_BLEND);
		}
		GL11.glPopMatrix();
	}

	public void drawString(String text, float i, float j, Color color) {

		int x = (int) i;
		int y = (int) j;

		GL11.glPushMatrix();
		{
			GL11.glTranslatef(x, y, 0);
			GL11.glScalef(0.7f, -0.7f, 0.7f);
			GL11.glRotated(-90, 0, 0, 1);
			GL11.glTranslatef(-x, -y, 0);
			Color.white.bind();
			GL11.glEnable(GL11.GL_BLEND);
			font.drawString(x, y, text, color);
			GL11.glDisable(GL11.GL_BLEND);
		}
		GL11.glPopMatrix();
	}

	private void drawColor(int i, int j) {
		if (i <= 3) {
			if (j <= 3) {
				GL11.glColor3f(0.1f, 0.1f, 0.1f);
			} else if (j <= 6) {
				GL11.glColor3f(0.4f, 0.4f, 0.4f);
			} else if (j <= 9) {
				GL11.glColor3f(0.1f, 0.1f, 0.1f);
			}
		} else if (i <= 6) {
			if (j <= 3) {
				GL11.glColor3f(0.4f, 0.4f, 0.4f);
			} else if (j <= 6) {
				GL11.glColor3f(0.1f, 0.1f, 0.1f);
			} else if (j <= 9) {
				GL11.glColor3f(0.4f, 0.4f, 0.4f);
			}
		} else if (i <= 9) {
			if (j <= 3) {
				GL11.glColor3f(0.1f, 0.1f, 0.1f);
			} else if (j <= 6) {
				GL11.glColor3f(0.4f, 0.4f, 0.4f);
			} else if (j <= 9) {
				GL11.glColor3f(0.1f, 0.1f, 0.1f);
			}
		}

	}

	protected void setSpeed(int s) {
		speed = s;
	}

	public void pollInput() {

		if (Mouse.isButtonDown(0)) {
			int x = Mouse.getX();
			int y = Mouse.getY();

			if (rButtonSlider.contains(x, y)) {
				//System.out.println(x + "-" + y);
				rButtonSliderPressed = true;
				rButtonSliderYLocation = Mouse.getY() - 5;

			}
		} else {
			if (rButtonSliderPressed != false) {
				rButtonSliderPressed = false;
			}
		}
		if (Mouse.isButtonDown(1)) {
			int x = Mouse.getX();
			int y = Mouse.getY();

		}

		if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
			//if (rButtonLineDirectorSlider.intersects(rButtonSlider)) 
			{
				rButtonSliderYLocation++;

			}
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
			//			if (rButtonLineDirectorSlider.intersects(rButtonSlider))
			{
				rButtonSliderYLocation--;

			}
		}
		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				if (Keyboard.getEventKey() == Keyboard.KEY_A) {
					System.out.println("A1");
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_H) {

				}
			} else {
				if (Keyboard.getEventKey() == Keyboard.KEY_P) {
					setSpeed(Integer.MAX_VALUE);
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_C) {
					setSpeed(500);
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_T) {
					setSpeed(0);
				}

			}
		}
	}

	/** time at last frame */
	long	lastFrame;

	/** frames per second */
	int		fps;
	/** last fps time */
	long	lastFPS;

	/** 
	 * Calculate how many milliseconds have passed 
	 * since last frame.
	 * 
	 * @return milliseconds passed since last frame 
	 */
	public int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;

		return delta;
	}

	/**
	 * Get the accurate system time
	 * 
	 * @return The system time in milliseconds
	 */
	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	/**
	 * Calculate the FPS and set it in the title bar
	 */
	public void updateFPS() {
		if ((getTime() - lastFPS) > 1000) {
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}

	public void update(int delta) {

		updateFPS(); // update FPS Counter
	}

	public static void main(String[] argv) throws Exception {
		SodokuOpenGL sodokuOpenGL = new SodokuOpenGL(new File("").getAbsolutePath() + "\\res\\lab5\\sodoku\\sodoku.txt");
		sodokuOpenGL.setSpeed(500);
		sodokuOpenGL.start();
	}

}
