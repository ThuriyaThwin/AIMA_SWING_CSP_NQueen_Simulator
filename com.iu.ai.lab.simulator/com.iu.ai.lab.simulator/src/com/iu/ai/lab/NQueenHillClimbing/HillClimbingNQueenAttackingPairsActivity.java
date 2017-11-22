package com.iu.ai.lab.NQueenHillClimbing;

import java.util.ArrayList;
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

public class HillClimbingNQueenAttackingPairsActivity extends Activity {
	public final NQueensBoard	board				= new NQueensBoard(8);
	public static int				screen_width	= 0;
	public static int				screen_height	= 0;
	public static int				size_board		= 8;
	public static int				size_tile		= 0;
	public static final int		warp				= 4;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// board = new NQueensBoard(8);
		this.board.addQueenAt(new XYLocation(0, 0));
		this.board.addQueenAt(new XYLocation(0, 2));
		this.board.addQueenAt(new XYLocation(1, 1));
		this.board.addQueenAt(new XYLocation(2, 4));

		DisplayMetrics displaymetrics = new DisplayMetrics();
		this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		HillClimbingNQueenAttackingPairsActivity.screen_height = displaymetrics.heightPixels;
		HillClimbingNQueenAttackingPairsActivity.screen_width = displaymetrics.widthPixels - 1;
		HillClimbingNQueenAttackingPairsActivity.size_tile = (HillClimbingNQueenAttackingPairsActivity.screen_width / HillClimbingNQueenAttackingPairsActivity.size_board);

		this.setContentView(new NQueenAttackingGameView(this));
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		this.getMenuInflater().inflate(R.menu.activity_hill_climbing_nqueen_attacking_pairs, menu);
		return true;
	}

	public class NQueenAttackingGameView extends SurfaceView {

		private final Bitmap				queen;
		private final SurfaceHolder	holder;
		private final GameLoopThread	gameLoopThread;
		private final List<Sprite>		sprites						= new ArrayList<Sprite>();
		private long						lastClick;
		Paint									paint_background_tile	= new Paint();
		Paint									paint_content_tile		= new Paint();

		public NQueenAttackingGameView(final Context context) {
			super(context);
			this.holder = this.getHolder();
			this.paint_background_tile.setColor(Color.parseColor("#00bfff"));
			this.paint_content_tile.setColor(Color.BLUE);

			this.paint_content_tile.setTextSize(16);
			this.paint_content_tile.setTypeface(Typeface.DEFAULT_BOLD);

			this.gameLoopThread = new GameLoopThread(this);
			this.holder.addCallback(new SurfaceHolder.Callback() {

				@Override
				public void surfaceDestroyed(final SurfaceHolder holder) {
				}

				@Override
				public void surfaceCreated(final SurfaceHolder holder) {
					NQueenAttackingGameView.this.createSprites();
					NQueenAttackingGameView.this.gameLoopThread.setRunning(true);
					NQueenAttackingGameView.this.gameLoopThread.start();
				}

				@Override
				public void surfaceChanged(final SurfaceHolder holder, final int format, final int width, final int height) {
				}
			});
			this.queen = BitmapFactory.decodeResource(this.getResources(), R.drawable.chess_piece_white_queen);
		}

		private Sprite createSprite(final int resouce) {
			Bitmap bmp = BitmapFactory.decodeResource(this.getResources(), resouce);
			return new Sprite(this, bmp);
		}

		private void createSprites() {
			// sprites.add(createSprite(R.drawable.ic_launcher));
			// sprites.add(createSprite(R.drawable.ic_launcher));
			// sprites.add(createSprite(R.drawable.ic_launcher));
			// sprites.add(createSprite(R.drawable.ic_launcher));
			// sprites.add(createSprite(R.drawable.ic_launcher));
			// sprites.add(createSprite(R.drawable.ic_launcher));
			// sprites.add(createSprite(R.drawable.ic_launcher));
			// sprites.add(createSprite(R.drawable.ic_launcher));
			// sprites.add(createSprite(R.drawable.ic_launcher));
			// sprites.add(createSprite(R.drawable.ic_launcher));
			// sprites.add(createSprite(R.drawable.ic_launcher));
			// sprites.add(createSprite(R.drawable.ic_launcher));
		}

		@Override
		protected void onDraw(final Canvas canvas) {

			try {
				canvas.drawColor(Color.WHITE);
				this.DrawBoard(canvas);
				this.DrawQueen(canvas);
				this.DrawTileAt(0, 8, "Attaking Pairs = " + HillClimbingNQueenAttackingPairsActivity.this.board.getNumberOfAttackingPairs(), canvas);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void DrawQueen(final Canvas canvas) {
			List<XYLocation> xy = HillClimbingNQueenAttackingPairsActivity.this.board.getQueenPositions();
			for (XYLocation xyLocation : xy)
				this.DrawTileAt(xyLocation.getXCoOrdinate(), xyLocation.getYCoOrdinate(), "Q", canvas);

			for (int i = 0; i < HillClimbingNQueenAttackingPairsActivity.this.board.getSize(); i++) {
				XYLocation firstQueen = HillClimbingNQueenAttackingPairsActivity.this.board.getFirstNQueenInXLine(i);
				if (firstQueen == null)
					continue;

				for (int j = 0; j < HillClimbingNQueenAttackingPairsActivity.this.board.getSize(); j++) {
					XYLocation xyLocation = new XYLocation(i, j);
					int x = HillClimbingNQueenAttackingPairsActivity.this.board.getNumberOfAttacksWhenMoveMove(firstQueen, xyLocation);
					this.DrawTileAt(xyLocation.getXCoOrdinate(), xyLocation.getYCoOrdinate(), "" + x, canvas);
				}
			}

		}

		public void DrawBoard(final Canvas canvas) {

			// canvas.drawLine(0, 0, 20, 20, paint);
			for (int i = 0; i < HillClimbingNQueenAttackingPairsActivity.this.board.getSize(); i++)
				for (int j = 0; j < HillClimbingNQueenAttackingPairsActivity.this.board.getSize(); j++)
					canvas.drawRect((i * HillClimbingNQueenAttackingPairsActivity.size_tile) + HillClimbingNQueenAttackingPairsActivity.warp, (j * HillClimbingNQueenAttackingPairsActivity.size_tile) + HillClimbingNQueenAttackingPairsActivity.warp, (i * HillClimbingNQueenAttackingPairsActivity.size_tile) + HillClimbingNQueenAttackingPairsActivity.size_tile, (j * HillClimbingNQueenAttackingPairsActivity.size_tile) + HillClimbingNQueenAttackingPairsActivity.size_tile, this.paint_background_tile);
		}

		public void DrawTileAt(final int x, final int y, final String a, final Canvas canvas) {
			if (a.equals("Q")) {

				Rect scr = new Rect(0, 0, this.queen.getWidth(), this.queen.getHeight());
				Rect dest = new Rect((x * HillClimbingNQueenAttackingPairsActivity.size_tile) + HillClimbingNQueenAttackingPairsActivity.warp, (y * HillClimbingNQueenAttackingPairsActivity.size_tile) + HillClimbingNQueenAttackingPairsActivity.warp, HillClimbingNQueenAttackingPairsActivity.size_tile, HillClimbingNQueenAttackingPairsActivity.size_tile);
				RectF destf = new RectF((x * HillClimbingNQueenAttackingPairsActivity.size_tile) + HillClimbingNQueenAttackingPairsActivity.warp, (y * HillClimbingNQueenAttackingPairsActivity.size_tile) + HillClimbingNQueenAttackingPairsActivity.warp, (x * HillClimbingNQueenAttackingPairsActivity.size_tile) + HillClimbingNQueenAttackingPairsActivity.size_tile, (y * HillClimbingNQueenAttackingPairsActivity.size_tile) + HillClimbingNQueenAttackingPairsActivity.size_tile);
				// canvas.drawBitmap(queen, (x * size_tile) + 5 + 5, ((y *
				// size_tile) + 5) + 5, null);
				canvas.drawBitmap(this.queen, scr, destf, null);
				return;
			}
			canvas.drawText(a, (x * HillClimbingNQueenAttackingPairsActivity.size_tile) + HillClimbingNQueenAttackingPairsActivity.warp + HillClimbingNQueenAttackingPairsActivity.warp, ((y * HillClimbingNQueenAttackingPairsActivity.size_tile) + HillClimbingNQueenAttackingPairsActivity.warp) + 15, this.paint_content_tile);
			// canvas.drawText(a, 30, 30, paint_content_tile);
		}

		@Override
		public boolean onTouchEvent(final MotionEvent event) {
			if ((System.currentTimeMillis() - this.lastClick) > 500) {
				this.lastClick = System.currentTimeMillis();
				synchronized (this.getHolder()) {
					float x = event.getX();
					float y = event.getY();

					int idx = (int) Math.ceil(x / HillClimbingNQueenAttackingPairsActivity.size_tile);
					int idy = (int) Math.ceil(y / HillClimbingNQueenAttackingPairsActivity.size_tile);
					try {
						XYLocation xyLocation = new XYLocation(idx - 1, idy - 1);
						if (!HillClimbingNQueenAttackingPairsActivity.this.board.queenExistsAt(xyLocation))
							HillClimbingNQueenAttackingPairsActivity.this.board.addQueenAt(xyLocation);
						else
							HillClimbingNQueenAttackingPairsActivity.this.board.removeQueenFrom(xyLocation);
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
