package AISim;

import java.awt.Font;
import java.util.Iterator;
import java.util.List;

import lab4.NQueen.ActionFactory;
import lab4.NQueen.AttackingPairsHeuristic;
import lab4.NQueen.NQueenApp;
import lab4.NQueen.NQueenResultFactory;
import lab4.NQueen.NQueensState;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import Util.TextureManager;
import aima.core.agent.Action;
import aima.core.search.framework.GoalTest;
import aima.core.search.framework.Problem;
import aima.core.search.framework.SearchAgent;
import aima.core.search.local.HillClimbingSearch;
import aima.core.search.local.Scheduler;
import aima.core.search.local.SimulatedAnnealingSearch;
import aima.core.search.local.SimulatedAnnealingSearch.SearchOutcome;
import aima.core.util.datastructure.XYLocation;

public class NQueenOpenGL {

	public static final int		WIDTH			= 30;
	public static final int		HEIGHT			= 30;
	public static final int		WARP			= 3;

	public NQueensState			nQueenState;
	private Iterator<Action>	currentAction;

	long						lastFrame;
	int							fps;
	long						lastFPS;

	long						lastMouseClick	= 0;
	TextureManager				textureQueen;
	String						outcome;

	public void start(String outcome, String newTitle, NQueensState state, Iterator<Action> currentAction) {
		try {
			Display.setDisplayMode(new DisplayMode(800, 600));
			Display.create();
			Display.setTitle(newTitle);

		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.outcome = newTitle.toUpperCase() + " >> " + outcome;
		nQueenState = state;
		this.currentAction = currentAction;
		initGL(); // init OpenGL
		getDelta(); // call once before loop to initialise lastFrame
		initFont();
		lastFPS = getTime(); // call before loop to initialise fps timer
		textureQueen = new TextureManager("res/images/chess_piece_white_queen.png");
		while (!Display.isCloseRequested()) {
			int delta = getDelta();

			if (Mouse.isButtonDown(0)) {
				if ((System.currentTimeMillis() - lastMouseClick) > 400) {
					updateNQueenState();
					lastMouseClick = System.currentTimeMillis();
				}
			}
			update(delta);
			draw();

			Display.update();
			Display.sync(60); // cap fps to 60fps
		}

		Display.destroy();
	}

	public void updateNQueenState() {
		if (currentAction.hasNext())
			nQueenState = (NQueensState) (new NQueenResultFactory()).result(nQueenState, currentAction.next());
	}

	long	timeUpdater	= 0;

	public void update(int delta) {

		timeUpdater++;
		//System.out.println("timeUpdater = " + timeUpdater);
		if ((timeUpdater % 100) == 0) {
			//updateNQueenState();
		}

		updateFPS(); // update FPS Counter
	}

	/**
	 * Calculate how many milliseconds have passed since last frame.
	 * 
	 * @return milliseconds passed since last frame
	 */

	void draw() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(0.4f, 0.29f, 0.76f, 1);
		//textureQueen.render(0, 0);

		drawString("Number of Attacking Pairs : " + nQueenState.getNumberOfAttackingPairs(), 10, 50);
		drawString(outcome, 10, Display.getHeight());

		GL11.glPushMatrix();
		{
			float translatex = ((nQueenState.getSize() + 1) * (WIDTH + WARP));
			float translatey = Display.getHeight();

			GL11.glTranslatef(0, translatey, 0);
			//GL11.glRotatef(System.currentTimeMillis() % 360, 0, 0, 1);
			GL11.glRotatef(180, 0, 0, 1);
			GL11.glScaled(-1, 1, 1);
			//GL11.glTranslatef(0, -360, 0);
			drawBoard();
			drawQueen();
		}
		GL11.glPopMatrix();
	}

	public final int	warp	= 20;

	private void drawBoard() {

		for (int i = 0; i < nQueenState.getSize(); i++) {
			for (int j = 0; j < nQueenState.getSize(); j++) {
				GL11.glColor3f(0.5f, 0.5f, 1.0f);

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
	}

	private void drawQueen() {

		List<XYLocation> l = nQueenState.getQueenPositions();

		for (XYLocation xyLocation : l) {

			int i = xyLocation.getXCoOrdinate();
			int j = xyLocation.getYCoOrdinate();

			GL11.glColor3f(1.0f, 1.0f, 1.0f);

			GL11.glBegin(GL11.GL_QUADS);
			{
				int x = (i + 1) * (WIDTH + WARP);
				int y = (j + 1) * (HEIGHT + WARP);

				int X1 = x, Y1 = y;
				int X2 = x + WIDTH, Y2 = y;
				int X3 = x + WIDTH, Y3 = y + HEIGHT;
				int X4 = x, Y4 = y + HEIGHT;

				//textureQueen.render(X1, Y1, 30, 30);

				GL11.glVertex2f(X1, Y1);
				GL11.glVertex2f(X2, Y2);
				GL11.glVertex2f(X3, Y3);
				GL11.glVertex2f(X4, Y4);

			}
			GL11.glEnd();

		}

	}

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

		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glShadeModel(GL11.GL_SMOOTH);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 800, 0, 600, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);

		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

	}

	private TrueTypeFont	font;

	private void initFont() {
		Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
		font = new TrueTypeFont(awtFont, true);
	}

	private void drawString(String text, int x, int y) {
		GL11.glPushMatrix();
		{
			GL11.glTranslatef(x, y, 0);
			//GL11.glRotatef(-0, 0f, 0f, 1f);
			GL11.glScalef(0.7f, -0.7f, 0.7f);
			GL11.glTranslatef(-x, -y, 0);
			Color.white.bind();
			GL11.glEnable(GL11.GL_BLEND);
			font.drawString(x, y, text);
			GL11.glDisable(GL11.GL_BLEND);
		}

		GL11.glPopMatrix();
	}

	public static int	TypeSimulatedAnnealingSearch	= 0;
	public static int	TypeHillClimbingSearch			= 1;

	public static void run(NQueensState start, int type) {

		try {

			NQueenApp app = new NQueenApp();
			app.start = start;

			List<Action> actions = null;
			if (type == TypeSimulatedAnnealingSearch) {

				Problem problem = new Problem(start, new ActionFactory(), new NQueenResultFactory(), new GoalTest() {

					@Override
					public boolean isGoalState(Object state) {

						NQueensState nqueen = (NQueensState) state;
						if (nqueen.getNumberOfAttackingPairs() == 0) { return true; }
						return false;
					}
				});

				SimulatedAnnealingSearch search = new SimulatedAnnealingSearch(new AttackingPairsHeuristic(), new Scheduler(20, 0.045, 100));

				SearchAgent agent = new SearchAgent(problem, search);
				actions = agent.getActions();

				if (search.getOutcome() == SearchOutcome.SOLUTION_FOUND) {
					System.out.println("Solution Found after: " + actions.size() + " actions");
				} else
					System.out.println("FAILURE after: " + actions.size() + " actions");

				System.out.println("List of Actions have been performed:");
				NQueenOpenGL queenExample = new NQueenOpenGL();
				queenExample.start("OutCome : " + search.getOutcome(), "Simulated Annealing Search", start, actions.iterator());
			} else if (type == TypeHillClimbingSearch) {

				System.out.println("HILL CLIMBING SEARCH");

				Problem problem = new Problem(start, new ActionFactory(), new NQueenResultFactory(), new GoalTest() {

					@Override
					public boolean isGoalState(Object state) {

						NQueensState nqueen = (NQueensState) state;
						if (nqueen.getNumberOfAttackingPairs() == 0) { return true; }
						return false;
					}
				});

				HillClimbingSearch search = new HillClimbingSearch(new AttackingPairsHeuristic());

				SearchAgent agent = new SearchAgent(problem, search);
				actions = agent.getActions();

				if (search.getOutcome() == HillClimbingSearch.SearchOutcome.SOLUTION_FOUND) {
					System.out.println("Solution Found after: " + actions.size() + " actions");

				} else {
					System.out.println("FAILURE after: " + actions.size() + " actions");
				}

				System.out.println("List of Actions have been performed:");
				for (Action a : actions) {
					System.out.println(a.toString());
				}

				NQueenOpenGL queenExample = new NQueenOpenGL();
				queenExample.start("OutCome : " + search.getOutcome(), "Hill Climbing Search", start, actions.iterator());

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] a) {
		NQueensState s = new NQueensState(6);
		s.setRandomBoard();

		run(s, TypeHillClimbingSearch);
	}
}