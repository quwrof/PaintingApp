package com.example.paintingapp;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class MainActivity extends Activity {
	TouchPaintView view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		view = new TouchPaintView(this, null);
		setContentView(view);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch(item.getItemId()){
		case R.id.action_save:
			//•Û‘¶ƒ{ƒ^ƒ“‚ð‰Ÿ‚µ‚½‚Æ‚«
			view.saveToFile();
			return true;
		case R.id.action_clean:
			view.canvasint();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
