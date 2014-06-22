package de.getridof.groandroid;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

public class homeView extends SurfaceView {
	private SurfaceHolder surfaceHolder;
	private Bitmap bmp;
	private Bitmap bmp2;
	private Bitmap leftbtm;
	private Bitmap leftbtm_draw;
	private Bitmap rightbtm;
	private Bitmap rightbtm_draw;
	private int where = 1;
	private float additional;
	private float y = 0;
	private float x = 0;
	private float mPreviousX;
	private float mPreviousY;
	private boolean mIsDown = false;
    private boolean moveup = false;
    private boolean stop = true;
    private boolean rotebool = true;
    private boolean movebool = false;
    private boolean leftbool = false;
    private boolean rightbool = false;
    private boolean rightboolon = false;
    private boolean leftboolon = false;
    private boolean startwhere = true;
    private float rote = 0;
    private float rote2 = 225;
	private homeLoop matThread = new homeLoop(this);
    

	@SuppressLint("WrongCall")public homeView(Context context){
		super(context);
		surfaceHolder = getHolder();
		surfaceHolder.addCallback(new SurfaceHolder.Callback(){
			public void surfaceDestroyed(SurfaceHolder holder){
				pause();
			}
			public void surfaceCreated(SurfaceHolder holder){
				matThread.running(true);
				matThread.start();
			}
			public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){
			}
		});
		DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
		int width = metrics.widthPixels;
		int height = metrics.heightPixels;
		bmp = BitmapFactory.decodeResource(getResources(), R.drawable.home_back);
		bmp2 = Bitmap.createScaledBitmap(bmp, width, height, true);
		leftbtm = BitmapFactory.decodeResource(getResources(), R.drawable.mys_back);
		leftbtm_draw = Bitmap.createScaledBitmap(leftbtm, width, height, true);
		rightbtm = BitmapFactory.decodeResource(getResources(), R.drawable.background_my_stuff);
		rightbtm_draw = Bitmap.createScaledBitmap(rightbtm, width, height, true);
		}
	@Override
	protected void onDraw(Canvas canvas){
		if(leftbool){
			if(additional < getWidth()){
				additional+=20;
			}else{
				leftbool = false;
			}
		}
		
		if(leftboolon){
			if(additional < 0){
				additional+=20;
			}else{
				leftboolon = false;
			}
		}
		
		if(rightboolon){
			if(additional > -getWidth()){
				additional-=20;
			}else{
				rightboolon = false;
			}
		}
		
		if(rightbool){
			if(additional > 0){
				additional-=20;
			}else{
				rightbool = false;
			}
		}
		
		canvas.drawColor(Color.WHITE);		
        canvas.drawBitmap(bmp2, additional, 0, null);
        canvas.drawBitmap(leftbtm_draw, getWidth()+additional, 0, null);
        canvas.drawBitmap(rightbtm_draw, -getWidth()+additional, 0, null);
        
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
	    synchronized (getHolder()) {
	    	float x = event.getX();
	        float y = event.getY();
	            switch (event.getAction()) {
	            case MotionEvent.ACTION_DOWN:
	                
	                mPreviousX = x;
	                mPreviousY = y;
                    
	                break;
	            case MotionEvent.ACTION_MOVE:
	                
                    break;
	            case MotionEvent.ACTION_UP:
	            	if(contact(x,y,15,15,getWidth()/2,0,getWidth()/2,getHeight())){
	            		switch(where){
	            		case 0:
	            			final Context context = homeView.this.getContext();
		                    Intent intent = new Intent(context , newitemActivity.class);
		                    context.startActivity(intent);
	            			break;
	            		case 1:
	            			rightboolon = true;
	            			where = 2;
	            			break;
	            		case 2:
	            			break;
	            		}
	            	}else if(contact(x,y,15,15,0,0,getWidth()/2,getHeight())){
	            		switch(where){
	            		case 0:
	            			rightbool = true;
	            			where = 1;
	            			break;
	            		case 1:
	            			leftbool = true;
	            			where = 0;
	            			break;
	            		case 2:
	            			leftboolon = true;
	            			where = 1;
	            			break;
	            		}
	            	}
	                mIsDown = false;
	                break;
	        }
	    }
	    return true;
	}
	public void pause(){
		Log.d("MessagePW", "destroyed");
		boolean retry = true;
        matThread.running(false);
        while(retry){
            try {
                matThread.join();
                retry=false;
                bmp.recycle();
            	bmp2.recycle();
            	leftbtm.recycle();
            	leftbtm_draw.recycle();
            	rightbtm.recycle();
            	rightbtm_draw.recycle();
            }catch(InterruptedException e){
                Log.d("MessagePW", "destroying went wrong: " + e.toString());
            }
        }
	}
    public boolean contact(float x1, float y1, float px1, float py1, float x2, float y2, float px2, float py2){
        return ((x1 > x2 && x1 < x2 + px2) || (x1 + px1 > x2 && x1 + px1 < x2 + px2) || (x2 > x1 && x2 < x1 + px1) || (x2 + px2 > x1 && x2 + px2 < x1 + px1)) && ((y1 > y2 && y1 < y2 + py2) || (y1 + py1 > y2 && y1 + py1 < y2 + py2) || (y2 > y1 && y2 < y1 + py1) || (y2 + py2 > y1 && y2 + py2 < y1 + py1));
    }
}