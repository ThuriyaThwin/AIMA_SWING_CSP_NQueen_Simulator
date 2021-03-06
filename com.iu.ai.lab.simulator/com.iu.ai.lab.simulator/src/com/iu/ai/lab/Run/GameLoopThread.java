package com.iu.ai.lab.Run;

import android.graphics.Canvas;

import com.iu.ai.lab.MenuActivity.GameView;

public class GameLoopThread extends Thread {
	static final long		FPS		= 10;
	private final GameView	view;
	private boolean			running	= false;

	public GameLoopThread(GameView view) {
		this.view = view;
	}

	public void setRunning(boolean run) {
		running = run;
	}

	boolean	active	= true;

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public void run() {
		long ticksPS = 1000 / FPS;
		long startTime;
		long sleepTime;
		while (running) {
			Canvas c = null;
			startTime = System.currentTimeMillis();
			if (active) {
				try {
					c = view.getHolder().lockCanvas();
					synchronized (view.getHolder()) {
						view.onDraw(c);
					}
				} finally {
					if (c != null) {
						view.getHolder().unlockCanvasAndPost(c);
					}
				}
			}
			sleepTime = ticksPS - (System.currentTimeMillis() - startTime);
			try {
				if (sleepTime > 0)
					sleep(sleepTime);
				else
					sleep(10);
			} catch (Exception e) {}
		}
	}
}