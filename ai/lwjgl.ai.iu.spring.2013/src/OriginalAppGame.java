

import java.awt.Font;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.TrueTypeFont;

public class OriginalAppGame {
	private static final int	height	= 700;
	private static final int	width	= 800;

	public static final int		WIDTH	= 95;
	public static final int		HEIGHT	= 55;
	public static final int		WARP	= 5;

	public void start() {

		initGL();
		initFont();

		while (!Display.isCloseRequested()) {
			// Clear the screen and depth buffer

			int delta = getDelta();
			update(delta);

			drawGL();

			Display.update();
			Display.sync(60);
		}

		Display.destroy();
	}

	private void initGL() {
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		Display.setTitle("FB Sim");
		GL11.glShadeModel(GL11.GL_SMOOTH);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, width, 0, height, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);

	}

	private TrueTypeFont	font;

	private void initFont() {
		Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
		font = new TrueTypeFont(awtFont, true);
	}

	private void drawGL() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(0.3f, 0.29f, 0.76f, 1);
	}

	public void pollInput() {

		if (Mouse.isButtonDown(0)) {

		}

		if (Mouse.isButtonDown(1)) {
			int x = Mouse.getX();
			int y = Mouse.getY();
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {

		}

		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				if (Keyboard.getEventKey() == Keyboard.KEY_A) {

				}
				if (Keyboard.getEventKey() == Keyboard.KEY_H) {

				}
			} else {
				if (Keyboard.getEventKey() == Keyboard.KEY_A) {

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
		OriginalAppGame simFBExample = new OriginalAppGame();

		simFBExample.start();
	}
}
