package com.iu.ai.lab.EightPuzzle;

import java.util.ArrayList;
import java.util.List;

import aima.core.environment.eightpuzzle.EightPuzzleBoard;
import aima.core.util.datastructure.XYLocation;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;

import com.iu.ai.lab.R;

public class EightPuzzleCalculationActivity extends Activity {

	EightPuzzleBoard		showed_board;
	EightPuzzleBoard		init_board;
	EightPuzzleBoard		target_board;
	public static int		screen_width	= 0;
	public static int		screen_height	= 0;
	public static int		size_board		= 3;
	public static int		size_tile		= 0;
	public static final int	warp			= 4;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

		Intent intent = getIntent();
		String id_target = intent.getStringExtra("target");
		String id_init = intent.getStringExtra("init");

		int[] _target = new int[9];
		int[] _init = new int[9];

		for (int i = 0; i < 9; i++) {
			_target[i] = Integer.parseInt(String.valueOf(id_target.charAt(i)));
			_init[i] = Integer.parseInt(String.valueOf(id_init.charAt(i)));
		}

		target_board = new EightPuzzleBoard(_target);
		init_board = new EightPuzzleBoard(_init);

		EightPuzzleCalculationActivity.screen_height = displaymetrics.heightPixels;
		EightPuzzleCalculationActivity.screen_width = displaymetrics.widthPixels - 1;
		EightPuzzleCalculationActivity.size_tile = (EightPuzzleCalculationActivity.screen_width / EightPuzzleCalculationActivity.size_board);

		this.setContentView(new EightPuzzleGameView(this));
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		return true;
	}

	public class EightPuzzleGameView extends SurfaceView implements SurfaceHolder.Callback {

		private final List<Bitmap>		number;
		// private final SurfaceHolder holder;
		private final GameLoopThread	gameLoopThread;

		Paint							paint_background_tile		= new Paint();
		Paint							paint_background_tile_targe	= new Paint();
		Paint							paint_content_tile			= new Paint();
		Paint							paint_content_subtile		= new Paint();
		int[]							_target;

		public void resetTarget() {
			_target = new int[] { -1, -1, -1, -1, -1, -1, -1, -1, -1 };
		}

		public EightPuzzleGameView(final Context context) {
			super(context);
			resetTarget();
			paint_background_tile.setColor(Color.parseColor("#00bfff"));
			paint_background_tile_targe.setColor(Color.parseColor("#0080FF"));
			paint_content_tile.setColor(Color.parseColor("#636C7B"));
			paint_content_tile.setTextSize(16);
			paint_content_tile.setTypeface(Typeface.DEFAULT_BOLD);

			// paint_content_subtile.setColor(Color.parseColor("#8000FF"));
			ColorFilter filter = new LightingColorFilter(Color.parseColor("#636C7B"), 1);
			paint_content_subtile.setColorFilter(filter);
			gameLoopThread = new GameLoopThread(this);
			getHolder().addCallback(this);

			number = new ArrayList<Bitmap>();
			number.add(BitmapFactory.decodeResource(getResources(), R.drawable.n1));
			number.add(BitmapFactory.decodeResource(getResources(), R.drawable.n2));
			number.add(BitmapFactory.decodeResource(getResources(), R.drawable.n3));
			number.add(BitmapFactory.decodeResource(getResources(), R.drawable.n4));
			number.add(BitmapFactory.decodeResource(getResources(), R.drawable.n5));
			number.add(BitmapFactory.decodeResource(getResources(), R.drawable.n6));
			number.add(BitmapFactory.decodeResource(getResources(), R.drawable.n7));
			number.add(BitmapFactory.decodeResource(getResources(), R.drawable.n8));
			number.add(BitmapFactory.decodeResource(getResources(), R.drawable.n9));

		}

		@Override
		public void surfaceDestroyed(final SurfaceHolder holder) {
			gameLoopThread.setRunning(false);
		}

		@Override
		public void surfaceCreated(final SurfaceHolder holder) {

			try {
				gameLoopThread.setRunning(true);
				gameLoopThread.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public void surfaceChanged(final SurfaceHolder holder, final int format, final int width, final int height) {}

		@Override
		protected void onDraw(final Canvas canvas) {

			try {
				canvas.drawColor(Color.WHITE);
				DrawBoard(canvas);
				DrawTileInit(canvas);
				DrawTileTarget(canvas);

				canvas.drawText("Misplaced : " + init_board.getMissplaceTile(target_board.getState()), (EightPuzzleCalculationActivity.size_tile * 20) / 100, (EightPuzzleCalculationActivity.size_tile * 3) + 20, paint_content_tile);
				canvas.drawText("Mahattan : " + init_board.getMahattan(target_board), (EightPuzzleCalculationActivity.size_tile * 20) / 100, (EightPuzzleCalculationActivity.size_tile * 3) + 35, paint_content_tile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void DrawTileInit(final Canvas canvas) {
			for (int i = 0; i < 3; i++)
				for (int j = 0; j < 3; j++) {
					int x = init_board.getValueAt(new XYLocation(i, j));
					DrawTileAt(j, i, x, canvas);
				}
		}

		public void DrawTileTarget(final Canvas canvas) {
			for (int i = 0; i < 3; i++)
				for (int j = 0; j < 3; j++) {
					int x = target_board.getValueAt(new XYLocation(i, j));
					DrawSubTileAt(j, i, x, canvas);
				}
		}

		public void DrawStringAt(final int x, final int y, final String a, final Canvas canvas) {
			canvas.drawText(a, (x * EightPuzzleCalculationActivity.size_tile) + EightPuzzleCalculationActivity.warp + EightPuzzleCalculationActivity.warp, ((y * EightPuzzleCalculationActivity.size_tile) + EightPuzzleCalculationActivity.warp) + 15, paint_content_tile);
		}

		public void DrawBoard(final Canvas canvas) {

			for (int i = 0; i < 3; i++)
				for (int j = 0; j < 3; j++)
					canvas.drawRect((i * EightPuzzleCalculationActivity.size_tile) + EightPuzzleCalculationActivity.warp, (j * EightPuzzleCalculationActivity.size_tile) + EightPuzzleCalculationActivity.warp, (i * EightPuzzleCalculationActivity.size_tile) + EightPuzzleCalculationActivity.size_tile, (j * EightPuzzleCalculationActivity.size_tile) + EightPuzzleCalculationActivity.size_tile, paint_background_tile);
		}

		public void DrawTileAt(final int x, final int y, final int a, final Canvas canvas) {
			if (a == 0)
				return;

			RectF destf = new RectF((x * EightPuzzleCalculationActivity.size_tile) + EightPuzzleCalculationActivity.warp, (y * EightPuzzleCalculationActivity.size_tile) + EightPuzzleCalculationActivity.warp, (x * EightPuzzleCalculationActivity.size_tile) + EightPuzzleCalculationActivity.size_tile, (y * EightPuzzleCalculationActivity.size_tile) + EightPuzzleCalculationActivity.size_tile);
			Bitmap bm = number.get(a - 1);

			canvas.drawBitmap(bm, new Rect(0, 0, bm.getWidth(), bm.getHeight()), destf, null);
			return;

		}

		public void DrawSubTileAt(final int x, final int y, final int a, final Canvas canvas) {
			if (a == 0)
				return;
			RectF destf = new RectF((x * EightPuzzleCalculationActivity.size_tile) + EightPuzzleCalculationActivity.warp, (y * EightPuzzleCalculationActivity.size_tile) + EightPuzzleCalculationActivity.warp, (x * EightPuzzleCalculationActivity.size_tile) + (EightPuzzleCalculationActivity.size_tile / 3), (y * EightPuzzleCalculationActivity.size_tile) + (EightPuzzleCalculationActivity.size_tile / 3));
			Bitmap bm = number.get(a - 1);

			// Matrix max = new Matrix();

			canvas.drawBitmap(bm, new Rect(0, 0, bm.getWidth(), bm.getHeight()), destf, paint_content_subtile);
			return;

		}

		static final int	MIN_DISTANCE	= 100;
		private float		downX, downY, upX, upY;

		@Override
		public boolean onTouchEvent(final MotionEvent event) {
			switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN: {
					downX = event.getX();
					downY = event.getY();
					return true;
				}
				case MotionEvent.ACTION_UP: {
					upX = event.getX();
					upY = event.getY();
					// processControll((int) upX, (int) upY);
					float deltaX = downX - upX;
					float deltaY = downY - upY;

					// swipe horizontal?
					if (Math.abs(deltaX) > EightPuzzleGameView.MIN_DISTANCE) {
						// left or right
						if (deltaX < 0) {
							System.out.println("xxxxx = Right");
							init_board.moveGapLeft();
							return true;
						}

						if (deltaX > 0) {
							System.out.println("xxxxx = Left");
							init_board.moveGapRight();
							return true;
						}
					} else if (Math.abs(deltaY) > EightPuzzleGameView.MIN_DISTANCE) {
						// top or down
						if (deltaY < 0) {
							System.out.println("xxxxx = Down");
							init_board.moveGapUp();
							return true;
						}
						if (deltaY > 0) {
							System.out.println("xxxxx = Up");
							init_board.moveGapDown();
							return true;
						}
					} else
						return false;
					return true;
				}
			}
			return false;
		}

		Rect[][]	rect	= new Rect[2][9];

	}

}
