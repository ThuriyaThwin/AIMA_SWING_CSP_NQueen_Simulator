package com.iu.ai.lab.NQueenAttackPairs;

import java.util.List;

import aima.core.environment.nqueens.NQueensBoard;
import aima.core.util.datastructure.XYLocation;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
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

public class NQueenAttackingPairsActivity extends Activity {
	public final NQueensBoard	board			= new NQueensBoard(8);
	public static int			screen_width	= 0;
	public static int			screen_height	= 0;
	public static int			size_board		= 8;
	public static int			size_tile		= 0;
	public static final int		warp			= 4;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// board = new NQueensBoard(8);
		board.addQueenAt(new XYLocation(0, 0));
		board.addQueenAt(new XYLocation(0, 2));
		board.addQueenAt(new XYLocation(1, 1));
		board.addQueenAt(new XYLocation(2, 4));
		// board.addQueenAt(new XYLocation(3, 4));

		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		NQueenAttackingPairsActivity.screen_height = displaymetrics.heightPixels;
		NQueenAttackingPairsActivity.screen_width = displaymetrics.widthPixels - 1;
		NQueenAttackingPairsActivity.size_tile = (NQueenAttackingPairsActivity.screen_width / NQueenAttackingPairsActivity.size_board);

		this.setContentView(new SimpleNQueenAttackingGameView(this));
		// setContentView(R.layout.activity_hill_climbing_nqueen_attacking_pairs);
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		getMenuInflater().inflate(R.menu.activity_hill_climbing_nqueen_attacking_pairs, menu);
		return true;
	}

	public class SimpleNQueenAttackingGameView extends SurfaceView {

		private Bitmap			queen;
		private SurfaceHolder	holder;
		private GameLoopThread	gameLoopThread;

		private long			lastClick;
		Paint					paint_background_tile	= new Paint();
		Paint					paint_content_tile		= new Paint();

		public SimpleNQueenAttackingGameView(final Context context) {
			super(context);
			start();
		}

		public void start() {
			holder = getHolder();
			paint_background_tile.setColor(Color.parseColor("#00bfff"));
			paint_content_tile.setColor(Color.BLUE);
			paint_content_tile.setTextSize(16);
			paint_content_tile.setTypeface(Typeface.DEFAULT_BOLD);
			gameLoopThread = new GameLoopThread(this);
			holder.addCallback(new SurfaceHolder.Callback() {

				@Override
				public void surfaceDestroyed(final SurfaceHolder holder) {
					gameLoopThread.setRunning(false);
				}

				@Override
				public void surfaceCreated(final SurfaceHolder holder) {

					gameLoopThread.setRunning(true);
					gameLoopThread.start();
				}

				@Override
				public void surfaceChanged(final SurfaceHolder holder, final int format, final int width, final int height) {}
			});
			queen = BitmapFactory.decodeResource(getResources(), R.drawable.chess_piece_white_queen);
		}

		@Override
		protected void onDraw(final Canvas canvas) {

			try {
				canvas.drawColor(Color.WHITE);
				DrawBoard(canvas);
				DrawQueen(canvas);
				DrawTileAt(0, 8, "Attaking Pairs = " + board.getNumberOfAttackingPairs(), canvas);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void DrawQueen(final Canvas canvas) {
			List<XYLocation> xy = board.getQueenPositions();
			for (XYLocation xyLocation : xy)
				DrawTileAt(xyLocation.getXCoOrdinate(), xyLocation.getYCoOrdinate(), "Q", canvas);
		}

		public void DrawBoard(final Canvas canvas) {

			// canvas.drawLine(0, 0, 20, 20, paint);
			for (int i = 0; i < board.getSize(); i++)
				for (int j = 0; j < board.getSize(); j++)
					canvas.drawRect((i * NQueenAttackingPairsActivity.size_tile) + NQueenAttackingPairsActivity.warp, (j * NQueenAttackingPairsActivity.size_tile) + NQueenAttackingPairsActivity.warp, (i * NQueenAttackingPairsActivity.size_tile) + NQueenAttackingPairsActivity.size_tile, (j * NQueenAttackingPairsActivity.size_tile) + NQueenAttackingPairsActivity.size_tile, paint_background_tile);
		}

		public void DrawTileAt(final int x, final int y, final String a, final Canvas canvas) {
			if (a.equals("Q")) {

				Rect scr = new Rect(0, 0, queen.getWidth(), queen.getHeight());
				Rect dest = new Rect((x * NQueenAttackingPairsActivity.size_tile) + NQueenAttackingPairsActivity.warp, (y * NQueenAttackingPairsActivity.size_tile) + NQueenAttackingPairsActivity.warp, NQueenAttackingPairsActivity.size_tile, NQueenAttackingPairsActivity.size_tile);
				RectF destf = new RectF((x * NQueenAttackingPairsActivity.size_tile) + NQueenAttackingPairsActivity.warp, (y * NQueenAttackingPairsActivity.size_tile) + NQueenAttackingPairsActivity.warp, (x * NQueenAttackingPairsActivity.size_tile) + NQueenAttackingPairsActivity.size_tile, (y * NQueenAttackingPairsActivity.size_tile) + NQueenAttackingPairsActivity.size_tile);
				// canvas.drawBitmap(queen, (x * size_tile) + 5 + 5, ((y *
				// size_tile) + 5) + 5, null);
				canvas.drawBitmap(queen, scr, destf, null);
				return;
			}
			canvas.drawText(a, (x * NQueenAttackingPairsActivity.size_tile) + NQueenAttackingPairsActivity.warp + NQueenAttackingPairsActivity.warp, ((y * NQueenAttackingPairsActivity.size_tile) + NQueenAttackingPairsActivity.warp) + 15, paint_content_tile);
			// canvas.drawText(a, 30, 30, paint_content_tile);
		}

		@Override
		public boolean onTouchEvent(final MotionEvent event) {
			if ((System.currentTimeMillis() - lastClick) > 500) {
				lastClick = System.currentTimeMillis();
				synchronized (getHolder()) {
					float x = event.getX();
					float y = event.getY();

					int idx = (int) Math.ceil(x / NQueenAttackingPairsActivity.size_tile);
					int idy = (int) Math.ceil(y / NQueenAttackingPairsActivity.size_tile);
					try {
						XYLocation xyLocation = new XYLocation(idx - 1, idy - 1);
						if (!board.queenExistsAt(xyLocation))
							board.addQueenAt(xyLocation);
						else
							board.removeQueenFrom(xyLocation);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					System.out.println(idx + "xx--xx" + idy);
				}
			}
			return true;
		}
	}
}
