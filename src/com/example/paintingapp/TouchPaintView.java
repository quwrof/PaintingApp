package com.example.paintingapp;

import java.io.File;
import java.io.FileOutputStream;

import android.R.bool;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.os.Environment;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

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

	public void saveToFile(){
		// SDカードがマウントされているか確認 
		if(!sdcardWriteReady()){
			Toast.makeText(getContext(), "SDカードがないよ", Toast.LENGTH_LONG).show();
			return;
		}
		//SDカードのディレクトリ取得
		File file = new File(Environment.getExternalStorageDirectory().getPath());
		//SDディレクトリ＋ファイル名
		File filename = new File(file.getAbsolutePath(), System.currentTimeMillis()+".jpg");
		try{
			FileOutputStream out = new FileOutputStream(filename);
			bitmap.compress(CompressFormat.JPEG, 100, out);
			out.close();
			Toast.makeText(getContext(), "保存成功したよ", Toast.LENGTH_LONG).show();
		}catch(Exception ex){
			//失敗
			Toast.makeText(getContext(), "保存失敗だよ", Toast.LENGTH_LONG).show();
		}
	}
	private boolean sdcardWriteReady(){
		String state = Environment.getExternalStorageState();
		return Environment.MEDIA_MOUNTED.equals(state);
	}
	public void canvasint(){
		canvas.drawColor(0, Mode.CLEAR);
		invalidate();
		
	}
}
