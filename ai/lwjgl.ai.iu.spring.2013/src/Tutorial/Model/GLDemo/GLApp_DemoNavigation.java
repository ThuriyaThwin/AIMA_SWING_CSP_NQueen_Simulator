package Tutorial.Model.GLDemo;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

import Tutorial.Model.App.GLApp;

/**
 * Navigate through a simple 3D scene.
 * <P>
 * Set up a scene with an object, textures and a light.  Create mipmaps so
 * that textures scale gracefully.  Arrow keys move camera through scene.
 * <P>
 * GLApp initializes the LWJGL environment for OpenGL rendering,
 * ie. creates a window, sets the display mode, inits mouse and keyboard,
 * then runs a loop that calls draw().
 * <P>
 * Special thanks to NeHe and Lionel Brits for the "Moving Through A 3D World"
 * tutorial (http://nehe.gamedev.net).
 * <P>
 * napier at potatoland dot org
 */
public class GLApp_DemoNavigation extends GLApp {
	// Handles for textures
	int				sphereTextureHandle	= 0;
	int				groundTextureHandle	= 0;
	// Lighting colors
	float			faWhite[]			= { 1.0f, 1.0f, 1.0f, 1.0f };
	float			faLightBlue[]		= { 0.8f, 0.8f, .9f, 1f };
	// Light position: if last value is 0, then this describes light direction.  If 1, then light position.
	float			lightPosition[]		= { -2f, 2f, 2f, 1.0f };
	// World coordinates of sphere
	float[]			spherePos			= { 0f, 0f, 0f };
	// Rotation of sphere
	float			rotation			= 0f;
	// Camera position
	static float[]	cameraPos			= { 0f, 0f, 20f };
	float			cameraRotation		= 0f;
	// cursor will be drawn as a texture on a quad
	int				cursorTextureHandle	= 0;

	//------------------------------------------------------------------------
	// Run main loop of application.  Handle mouse and keyboard input.
	//------------------------------------------------------------------------

	private GLApp_DemoNavigation() {}

	public static void main(String args[]) {
		GLApp_DemoNavigation demo = new GLApp_DemoNavigation();
		demo.run(); // will call init(), render(), mouse functions
	}

	/**
	 * Initialize the scene.  Called by GLApp.run()
	 */
	public void setup() {
		// setup and enable perspective
		setPerspective();

		// Create a point light (white)
		setLight(GL11.GL_LIGHT1, new float[] { 1.0f, 1.0f, 1.0f, 1.0f }, // diffuse color
				new float[] { .35f, 0.3f, 0.3f, 1.0f }, // ambient
				new float[] { 1.0f, 1.0f, 1.0f, 1.0f }, // specular
				lightPosition); // position

		// Create a directional light (dark red, to simulate reflection off wood surface)
		setLight(GL11.GL_LIGHT2, new float[] { 0.3f, 0.15f, 0.1f, 1.0f }, // diffuse color
				new float[] { 0.0f, 0.0f, 0.0f, 1.0f }, // ambient
				new float[] { 0.0f, 0.0f, 0.0f, 1.0f }, // specular
				new float[] { 0.0f, -10f, 0.0f, 0f }); // direction (pointing up)

		// material color
		setMaterial(new float[] { 0.9f, 0.9f, 0.9f, 1.0f }, // diffuse color
				new float[] { 0.9f, 0.9f, 0.9f, 1.0f }, // ambient
				new float[] { 1.0f, 1.0f, 1.0f, 1.0f }, // specular
				new float[] { 0.0f, 0.0f, 0.0f, 0.0f }, // emissive
				100); // shininess

		// enable lighting and texture rendering
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_TEXTURE_2D);

		// Create texture and mipmap for sphere
		sphereTextureHandle = makeTexture("images/eye.jpg");

		// Create texture and mipmap for ground plane
		groundTextureHandle = makeTexture("images/mahog_texture.jpg");

		// Load a cursor image and make it a texture
		cursorTextureHandle = makeTexture("images/cursorCrosshair32.gif");
	}

	/**
	 * Render one frame.  Called by GLApp.run().
	 */
	public void draw() {
		rotation += .5f;

		// clear depth buffer and color
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

		// adjust camera position according to arrow key events
		setCameraPosition();

		// select model view for subsequent transforms
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();

		// set the viewpoint
		GLU.gluLookAt(cameraPos[0], cameraPos[1], cameraPos[2], // where is the eye
				// look at a point directly in front of camera
				cameraPos[0] - (float) Math.sin(cameraRotation * PIOVER180), cameraPos[1], cameraPos[2] - (float) Math.cos(cameraRotation * PIOVER180), 0f, 1f, 0f); // which way is up

		// draw the ground plane
		GL11.glPushMatrix();
		{
			GL11.glTranslatef(0f, -2.1f, 0f); // down a bit
			GL11.glScalef(10f, .1f, 10f);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, groundTextureHandle);
			renderCube();
		}
		GL11.glPopMatrix();

		// draw sphere at center
		GL11.glPushMatrix();
		{
			GL11.glTranslatef(spherePos[0], spherePos[1], spherePos[2]);
			GL11.glRotatef(-90, 1, 0, 0); // rotate around X axis
			GL11.glRotatef(rotation, 0, 0, 1); // rotate around Y axis
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, sphereTextureHandle); // activate texture
			renderSphere(); // draw the sphere
		}
		GL11.glPopMatrix();

		// Place the light.  Light will move with the rest of the scene
		GL11.glLight(GL11.GL_LIGHT1, GL11.GL_POSITION, allocFloats(lightPosition));

		// draw the cursor on top of scene, in 2D
		drawCursor(cursorTextureHandle);
	}

	/**
	 * set the field of view and view depth.
	 */
	public static void setPerspective() {
		// select projection matrix (controls perspective)
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		// fovy, aspect ratio, zNear, zFar
		GLU.gluPerspective(30f, // zoom in or out of view
				aspectRatio, // shape of viewport rectangle
				.1f, // Min Z: how far from eye position does view start
				500f); // max Z: how far from eye position does view extend
		// return to modelview matrix
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}

	/**
	 * Adjust the Camera position based on keyboard arrow key input.
	 */
	public void setCameraPosition() {
		// Turn left
		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
			cameraRotation += 1.0f;
		}
		// Turn right
		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			cameraRotation -= 1.0f;
		}
		// move forward in current direction
		if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
			cameraPos[0] -= (float) Math.sin(cameraRotation * PIOVER180) * .3f;
			cameraPos[2] -= (float) Math.cos(cameraRotation * PIOVER180) * .3f;
		}
		// move backward in current direction
		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
			cameraPos[0] += (float) Math.sin(cameraRotation * PIOVER180) * .3f;
			cameraPos[2] += (float) Math.cos(cameraRotation * PIOVER180) * .3f;
		}
		// move camera down
		if (Keyboard.isKeyDown(Keyboard.KEY_PRIOR)) {
			cameraPos[1] += .3f;
		}
		// move camera up
		if (Keyboard.isKeyDown(Keyboard.KEY_NEXT)) {
			cameraPos[1] -= .3f;
		}
	}

	public void mouseMove(int x, int y) {}

	/**
	 * Just for yacks, move sphere to mouse click position.
	 */
	public void mouseDown(int x, int y) {
		spherePos = getWorldCoordsAtScreen(x, y);
	}

	public void mouseUp(int x, int y) {}

	public void keyDown(int keycode) {}

	public void keyUp(int keycode) {}
}