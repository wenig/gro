package de.getridof.groandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class homeActivity extends Activity {

	private homeView view;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		view = new homeView(this);
		setContentView(view);
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		view = new homeView(this);
		setContentView(view);
	}
	
	@Override
	protected void onPause(){
		super.onPause();
		view.pause();
	}
	
	@Override
	protected void onStop(){
		super.onStop();
		view.pause();
	}
	
	@Override
	protected void onDestroy(){
		super.onDestroy();
		view.pause();
	}
}
