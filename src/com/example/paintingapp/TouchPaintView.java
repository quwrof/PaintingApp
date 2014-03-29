package com.example.paintingapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class TouchPaintView extends View {
	private Paint paint;
	private Bitmap bitmap;
	private Canvas canvas;
	public TouchPaintView(Context context, AttributeSet attrs) {
		super(context, attrs);
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.LTGRAY);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(15);
		paint.setStrokeCap(Paint.Cap.ROUND);
		paint.setStrokeJoin(Paint.Join.ROUND);
		
		// TODO 自動生成されたコンストラクター・スタブ
	}
	//ビューのサイズの変更されたときに呼ばれる
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO 自動生成されたメソッド・スタブ
		super.onSizeChanged(w, h, oldw, oldh);
		bitmap =Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		canvas = new Canvas(bitmap);
		canvas.drawColor(Color.WHITE);
	}
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO 自動生成されたメソッド・スタブ
		super.onDraw(canvas);
		canvas.drawBitmap(bitmap, 0, 0, paint);
	}
	private float oldX = 0;
	private float oldY = 0;
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO 自動生成されたメソッド・スタブ
		
		switch(event.getAction()){
		//指で押したとき
		case MotionEvent.ACTION_DOWN:
			oldX = event.getX();
			oldY = event.getY();
			break;
			//指で動かしたとき
		case MotionEvent.ACTION_MOVE:
			canvas.drawLine(oldX, oldY, event.getX(), event.getY(), paint);
			oldX = event.getX();
			oldY = event.getY();
	        invalidate();
			break;
		}
		return true;
	}
	

}
