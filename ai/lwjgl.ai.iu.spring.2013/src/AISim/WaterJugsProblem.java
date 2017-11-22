package AISim;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import AISim.WaterJugsProblem.Jugs.JugAction;
import aima.core.util.datastructure.XYLocation;

public class WaterJugsProblem {

	float	x		= 400, y = 300;
	long	lastFrame;
	int		fps;
	long	lastFPS;

	Jugs	jug1	= new Jugs(new XYLocation(100, 200), 3, 2);
	Jugs	jug2	= new Jugs(new XYLocation(200, 200), 3, 2);

	public void start() {
		try {
			Display.setDisplayMode(new DisplayMode(800, 600));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		initGL(); // init OpenGL
		getDelta(); // call once before loop to initialise lastFrame
		lastFPS = getTime(); // call before loop to initialise fps timer

		jug1.location = new XYLocation(100, 200);
		while (!Display.isCloseRequested()) {
			int delta = getDelta();

			update(delta);
			renderGL();

			Display.update();
			Display.sync(60); // cap fps to 60fps
		}

		Display.destroy();
	}

	public void update(int delta) {

		updateFPS(); // update FPS Counter
	}

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

	public void initGL() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 800, 0, 600, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}

	public void renderGL() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glColor3f(0.5f, 0.5f, 1.0f);
		jug2.jugAction = JugAction.FillOutRight;

		jug1.draw();
		jug2.draw();
	}

	public static class Jugs {

		public int			timeUpdate		= 0;

		public XYLocation	location;
		public XYLocation	fixed_location;
		public int			maxLevel		= 3;
		public int			currentLevel	= 2;
		public float		rotation;

		public Jugs(XYLocation location, int maxlevel, int currentLevel) {
			this.location = location;
			fixed_location = new XYLocation(this.location.getXCoOrdinate(), this.location.getYCoOrdinate());
			maxLevel = maxlevel;
			this.currentLevel = currentLevel;
		}

		public void moveUp() {}

		private void drawJug() {

			int x = location.getXCoOrdinate();
			int y = location.getYCoOrdinate();

			GL11.glPushMatrix();
			{
				GL11.glTranslatef(x, y, 0);
				GL11.glRotatef(rotation, 0.0f, 0.0f, 1f);
				GL11.glTranslated(-x, -y, 0);

				GL11.glBegin(GL11.GL_QUADS);
				{
					GL11.glVertex2f(x, y);
					GL11.glVertex2f(x + 50, y);
					GL11.glVertex2f(x + 50, y + (50 * currentLevel));
					GL11.glVertex2f(x, y + (50 * currentLevel));
				}
				GL11.glEnd();

			}
			GL11.glPopMatrix();
		}

		public enum JugAction {
			None, FillIn, FillOutLeft, FillOutRight
		}

		public JugAction	jugAction		= JugAction.None;
		int					timerCounter	= 0;

		public void draw() {
			switch (jugAction) {
				case None:

					break;
				case FillOutLeft:
					timerCounter++;
					if (timerCounter < 100) {
						location = new XYLocation(location.getXCoOrdinate(), location.getYCoOrdinate() + 2);
						rotation = 0;
					} else if (timerCounter < 400) {
						rotation += -0.40f;
					} else if (timerCounter < 700) {
						rotation += +0.40f;
					} else if (timerCounter < 800) {
						rotation = 0;
						location = location.up();
					}

					drawJug();
					break;
				case FillOutRight:
					timerCounter++;
					if (timerCounter < 100) {
						location = new XYLocation(location.getXCoOrdinate(), location.getYCoOrdinate() + 2);
						rotation = 0;
					} else if (timerCounter < 400) {
						rotation += +0.40f;
					} else if (timerCounter < 700) {
						rotation += -0.40f;
					} else if (timerCounter < 800) {
						rotation = 0;
						location = location.up();
					}

					break;

				case FillIn:

					break;
				default:
					break;
			}
			drawJug();
		}

		public void fillOut() {

		}
	}

	public static void main(String[] argv) {
		WaterJugsProblem jugsProblem = new WaterJugsProblem();
		jugsProblem.start();
	}

}
