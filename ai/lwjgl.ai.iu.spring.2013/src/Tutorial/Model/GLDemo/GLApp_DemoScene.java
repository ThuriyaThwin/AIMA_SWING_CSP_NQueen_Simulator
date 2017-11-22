package Tutorial.Model.GLDemo;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

import Tutorial.Model.App.GLApp;

/**
 * Create a simple 3D scene with an object, textures and a light, using functions in GLApp.
 * Responds to mouse motion and click.
 * <P>
 * GLApp initializes the LWJGL environment for OpenGL rendering,
 * ie. creates a window, sets the display mode, inits mouse and keyboard,
 * then runs a loop that calls render().
 * <P>
 * napier at potatoland dot org
 */
public class GLApp_DemoScene extends GLApp {
	// Handles for textures
	int		cubeTextureHandle	= 0;
	int		sphereTextureHandle	= 0;
	int		groundTextureHandle	= 0;
	// Light position: if last value is 0, then this describes light direction.  If 1, then light position.
	float	lightPosition[]		= { -5f, 4f, 3f, 1f };
	// World coordintates at current mouse position
	float[]	worldPos			= { 0f, 0f, 0f };
	// World coordinates of sphere
	float[]	spherePos			= { 0f, 0f, 0f };
	// Rotation of sphere
	float	rotation			= 0f;

	//------------------------------------------------------------------------
	// Run main loop of application.  Handle mouse and keyboard input.
	//------------------------------------------------------------------------

	public static void main(String args[]) {
		GLApp_DemoScene demo = new GLApp_DemoScene();
		demo.window_title = "GLApp Simple Scene";
		demo.run(); // will call init(), render(), mouse event functions
	}

	/**
	 * Initialize the scene.  Called by GLApp.run()
	 */
	public void setup() {
		// Create sphere texture
		sphereTextureHandle = makeTexture("images/eye.jpg"); //grass_1_512.jpg");

		// Create cube texture
		cubeTextureHandle = makeTexture("images/marble.jpg");

		// Create texture for ground plane
		groundTextureHandle = makeTexture("images/mahog_texture.jpg"); //grass_1_512.jpg");

		// setup perspective
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
				110f); // shininess

		// no overall scene lighting
		setAmbientLight(new float[] { 0.0f, 0.0f, 0.0f, 0.0f });

		// enable lighting and texture rendering
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_TEXTURE_2D);

		// select model view for subsequent transforms
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
	}

	/**
	 * Set the camera position, field of view, depth.
	 */
	public static void setPerspective() {
		// select projection matrix (controls perspective)
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		// fovy, aspect ratio, zNear, zFar
		GLU.gluPerspective(30f, aspectRatio, 1f, 100f);
		// return to modelview matrix
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}

	/**
	 * Render one frame.  Called by GLApp.run().
	 */
	public void draw() {
		rotation += .5f;

		// clear depth buffer and color buffers
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

		// select model view for subsequent transforms
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();

		// set the viewpoint
		GLU.gluLookAt(0f, 0f, 15f, // where is the eye
				0f, 0f, 0f, // what point are we looking at
				0f, 1f, 0f); // which way is up

		// set light position each frame so lights move with scene
		setLightPosition(GL11.GL_LIGHT1, lightPosition);

		// draw the ground plane
		GL11.glPushMatrix();
		{
			GL11.glTranslatef(0f, -2f, 0f); // down a bit
			GL11.glScalef(7f, .1f, 7f);
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

		// Draw cube at cursor position
		GL11.glPushMatrix();
		{
			GL11.glTranslatef(worldPos[0], worldPos[1], worldPos[2]); // move to mouse pos
			GL11.glScalef(.5f, .5f, .5f); // make it smaller
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, cubeTextureHandle); // activate texture
			renderCube();
		}
		GL11.glPopMatrix();

		// Draw sphere at light position
		GL11.glPushMatrix();
		{
			GL11.glTranslatef(lightPosition[0], lightPosition[1], lightPosition[2]);
			GL11.glScalef(.2f, .2f, .2f); // make it tiny
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, cubeTextureHandle);
			renderSphere();
		}
		GL11.glPopMatrix();
	}

	/**
	 * Convert the mouse position into world coordinates.
	 */
	public void mouseMove(int x, int y) {
		worldPos = getWorldCoordsAtScreen(x, y);
	}

	/**
	 * Set sphere position to current mouse x,y
	 */
	public void mouseDown(int x, int y) {
		spherePos = getWorldCoordsAtScreen(x, y);
	}

	public void mouseUp(int x, int y) {}

	public void keyDown(int keycode) {}

	public void keyUp(int keycode) {}

}