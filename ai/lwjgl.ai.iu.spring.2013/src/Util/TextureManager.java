package Util;

import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class TextureManager {
	private Texture			texture;
	private final String	r;

	public TextureManager(String res) {
		r = res;
		init();
	}

	public void init() {
		try {
			texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(r));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void render(int x, int y, int width, int height) {

		Color.white.bind();

		texture.bind();
		//Color.blue.bind();
		//Color color = new Color(0.3f, 0.29f, 0.76f);
		//color.bind();

		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2f(0.0f, 1.0f);
		GL11.glVertex2f(x, y);
		GL11.glTexCoord2f(1.0f, 1.0f);
		GL11.glVertex2f(x + width, y);
		GL11.glTexCoord2f(1.0f, 0.0f);
		GL11.glVertex2f(x + width, y + height);
		GL11.glTexCoord2f(0.0f, 0.0f);
		GL11.glVertex2f(x, y + height);
		GL11.glEnd();
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
	}

	public void render(int x, int y) {

		Color.white.bind();

		texture.bind();
		//Color.blue.bind();
		//Color color = new Color(0.3f, 0.29f, 0.76f);
		//color.bind();

		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2f(0.0f, 1.0f);
		GL11.glVertex2f(x, y);
		GL11.glTexCoord2f(1.0f, 1.0f);
		GL11.glVertex2f(x + texture.getTextureWidth(), y);
		GL11.glTexCoord2f(1.0f, 0.0f);
		GL11.glVertex2f(x + texture.getTextureWidth(), y + texture.getTextureHeight());
		GL11.glTexCoord2f(0.0f, 0.0f);
		GL11.glVertex2f(x, y + texture.getTextureHeight());
		GL11.glEnd();
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
	}

	public Texture getTexture() {
		return texture;
	}

}