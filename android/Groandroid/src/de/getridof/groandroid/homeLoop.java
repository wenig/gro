package de.getridof.groandroid;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.util.Log;

public class homeLoop extends Thread {

	private homeView view;
	private boolean running = false;
    static final long FPS = 5000;

	public homeLoop(homeView view) {
        this.view = view;
    }
	
	public void running(boolean run) {
        running = run;
    }
	
	public boolean isRunning(){
		return running;
	}
	
	@SuppressLint("WrongCall")
	@Override
    public void run() {
        long TPS = 1000 / FPS;
        long startTime, sleepTime;
		Log.d("MessagePW", "run()yeah");
        while (running) {
            Canvas canvas = null;
            startTime = System.currentTimeMillis();
            try {
                canvas = view.getHolder().lockCanvas();
                synchronized (view.getHolder()) {
                    view.onDraw(canvas);
                }
            } finally {
                if (canvas != null) {
                    view.getHolder().unlockCanvasAndPost(canvas);
                }
            }
            sleepTime = TPS - (System.currentTimeMillis() - startTime);
            try {
                if (sleepTime > 0)
                    sleep(sleepTime);
                else
                    sleep(10);
            } catch (Exception e) {

            }
        }
    }
	
}
