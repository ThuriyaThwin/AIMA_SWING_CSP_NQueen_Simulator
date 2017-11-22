package com.iu.ai.lab;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.iu.ai.lab.CNFConverter.CNFConveterActivity;
import com.iu.ai.lab.EightPuzzle.SetterTargetEightPuzzleActivity;
import com.iu.ai.lab.FirstOderLogic.FOLActivity;
import com.iu.ai.lab.NQueenAttackPairs.NQueenAttackingPairsActivity;
import com.iu.ai.lab.NQueenHillClimbing.HillClimbingNQueenAttackingPairsActivity;
import com.iu.ai.lab.Run.GameLoopThread;

public class MenuActivity extends Activity {

	public static int		screen_width	= 0;
	public static int		screen_height	= 0;
	public static int		size_board		= 8;
	public static final int	warp			= 4;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		MenuActivity.screen_height = displaymetrics.heightPixels;
		MenuActivity.screen_width = displaymetrics.widthPixels;

		// this.setContentView(new GameView(this));
		this.setContentView(R.layout.activity_home);

		final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);

		Button btnqueeAttacking = (Button) findViewById(R.id.button1);
		Button bt8puzzle = (Button) findViewById(R.id.button2);
		Button btfol = (Button) findViewById(R.id.button3);
		Button btCNFConverter = (Button) findViewById(R.id.button4);

		btnqueeAttacking.setBackgroundColor(Color.parseColor("#CC0059b3"));
		btfol.setBackgroundColor(Color.parseColor("#CC0059b3"));
		bt8puzzle.setBackgroundColor(Color.parseColor("#CC0059b3"));
		btCNFConverter.setBackgroundColor(Color.parseColor("#CC0059b3"));

		btnqueeAttacking.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(final View v) {
				v.startAnimation(animAlpha);
				Intent intent = new Intent(MenuActivity.this.getApplicationContext(), HillClimbingNQueenAttackingPairsActivity.class);
				MenuActivity.this.startActivity(intent);
			}
		});

		bt8puzzle.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(final View v) {
				v.startAnimation(animAlpha);
				Intent intent = new Intent(MenuActivity.this.getApplicationContext(), SetterTargetEightPuzzleActivity.class);
				MenuActivity.this.startActivity(intent);
			}
		});

		btfol.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(final View v) {
				v.startAnimation(animAlpha);
				Intent intent = new Intent(MenuActivity.this.getApplicationContext(), FOLActivity.class);
				MenuActivity.this.startActivity(intent);
			}
		});

		btCNFConverter.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(final View v) {
				v.startAnimation(animAlpha);
				Intent intent = new Intent(MenuActivity.this.getApplicationContext(), CNFConveterActivity.class);
				MenuActivity.this.startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		getMenuInflater().inflate(R.menu.activity_hill_climbing_nqueen_attacking_pairs, menu);
		return true;
	}

	public class GameView extends SurfaceView {

		private final SurfaceHolder		holder;
		private final GameLoopThread	gameLoopThread;

		private long					lastClick;
		Paint							paint_background_tile1	= new Paint();
		Paint							paint_background_tile2	= new Paint();
		Paint							paint_background_tile3	= new Paint();
		Rect							rect1, rect2, rect3;;

		Context							context;

		public GameView(final Context context) {
			super(context);
			holder = getHolder();
			this.context = context;
			paint_background_tile1.setColor(Color.parseColor("#00BFFF"));
			paint_background_tile2.setColor(Color.parseColor("#6B8E23"));
			paint_background_tile3.setColor(Color.parseColor("#3F92D2"));
			int top = 30;
			rect1 = new Rect(30, top, MenuActivity.screen_width - 30, top + 30);
			top = top + 40;
			rect2 = new Rect(30, top, MenuActivity.screen_width - 30, top + 30);
			top = top + 40;
			rect3 = new Rect(30, top, MenuActivity.screen_width - 30, top + 30);
			gameLoopThread = new GameLoopThread(this);
			holder.addCallback(new SurfaceHolder.Callback() {

				@Override
				public void surfaceDestroyed(final SurfaceHolder holder) {
					System.out.println("xxxx surfaceDestroyed");
					gameLoopThread.setActive(false);
				}

				@Override
				public void surfaceCreated(final SurfaceHolder holder) {
					System.out.println("xxxx surfaceCreated");
					try {
						gameLoopThread.setActive(true);
						gameLoopThread.setRunning(true);
						gameLoopThread.start();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				@Override
				public void surfaceChanged(final SurfaceHolder holder, final int format, final int width, final int height) {
					System.out.println("xxxx sufacechaned");
					// gameLoopThread.setRunning(false);
				}
			});

		}

		@Override
		public void onDraw(final Canvas canvas) {

			try {
				canvas.drawColor(Color.WHITE);
				canvas.drawRect(rect1, paint_background_tile1);
				canvas.drawRect(rect2, paint_background_tile2);
				canvas.drawRect(rect3, paint_background_tile3);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public boolean onTouchEvent(final MotionEvent event) {
			switch (event.getAction()) {
				case MotionEvent.ACTION_UP:
					if ((System.currentTimeMillis() - lastClick) > 500) {
						lastClick = System.currentTimeMillis();
						synchronized (getHolder()) {
							int x = (int) event.getX();
							int y = (int) event.getY();
							if (rect1.contains(x, y)) {
								Intent intent = new Intent(context, NQueenAttackingPairsActivity.class);
								startActivity(intent);
							}
							if (rect2.contains(x, y)) {
								Intent intent = new Intent(context, HillClimbingNQueenAttackingPairsActivity.class);
								startActivity(intent);
							}
							if (rect3.contains(x, y)) {
								Intent intent = new Intent(context, SetterTargetEightPuzzleActivity.class);
								startActivity(intent);
							}
						}
					}
					break;

				default:
					break;
			}

			return true;
		}
	}
}
