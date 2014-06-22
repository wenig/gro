package de.getridof.groandroid;
import android.annotation.SuppressLint;
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

public class MatView extends SurfaceView {
	private SurfaceHolder surfaceHolder;
	private Bitmap bmp;
	private Bitmap bmpsessel;
	private Bitmap bmpsessel2;
	private Bitmap bmpbgstart;
	private Bitmap bmpfgstart;
	private Bitmap bmp2;
	private Bitmap bmps;
	private Bitmap bmp3;
	private Bitmap bmp4;
	private Bitmap yellow;
	private Bitmap yellow_draw;
	private Bitmap fb;
	private Bitmap fb_draw;
	private Bitmap gp;
	private Bitmap gp_draw;
	private float bikey = 0;
	private float bikex = 0;
	private float sessy = 0;
	private float sessx = 0;
	private float bikey_add = 0;
	private float bikex_add = 0;
	private float sessy_add = 0;
	private float sessx_add = 0;
	private float y = 0;
	private float x = 0;
	private float mPreviousX;
	private float mPreviousY;
	private boolean mIsDown = false;
    private boolean moveup = false;
    private boolean stop = true;
    private boolean rotebool = true;
    private boolean yellowbool = false;
    private float rote = 0;
    private float rote2 = 225;
	private MatLoop matThread = new MatLoop(this);
    private Matrix transform = new Matrix();
    private Matrix transformsess = new Matrix();

	@SuppressLint("WrongCall")public MatView(Context context){
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
		bmp = BitmapFactory.decodeResource(getResources(), R.drawable.bicycle_button_magenta);
		bmpsessel = BitmapFactory.decodeResource(getResources(), R.drawable.chair_button);
		bmpsessel2 = BitmapFactory.decodeResource(getResources(), R.drawable.chair_button_login);
		bmpbgstart = BitmapFactory.decodeResource(getResources(), R.drawable.start_back);
		bmpfgstart = BitmapFactory.decodeResource(getResources(), R.drawable.start_front);
		fb = BitmapFactory.decodeResource(getResources(), R.drawable.facebook_button);
		gp = BitmapFactory.decodeResource(getResources(), R.drawable.google_plus_button);
		yellow = BitmapFactory.decodeResource(getResources(), R.drawable.create_new_profile_button_yellow);
		bmp3 = Bitmap.createScaledBitmap(bmpbgstart, width, bmpbgstart.getHeight(), true);
		bmp4 = Bitmap.createScaledBitmap(bmpfgstart, width, bmpfgstart.getHeight(), true);
		yellow_draw = Bitmap.createScaledBitmap(yellow, yellow.getWidth(), yellow.getHeight(), true);
		}
	@Override
	protected void onDraw(Canvas canvas){
		canvas.drawColor(Color.WHITE);
		rote-=0.3;
		if(moveup && stop){
			if((sessy > (float) ((getHeight()/2)-(bmp.getHeight()*0.6))) && stop){
				sessy_add-=40;
				sessx_add+=20;
				bikex_add+=20;
			}else{
				stop = false;
				yellowbool = true;
			}
			rote2+=6;
		}else if(!stop){
			bmpsessel = bmpsessel2;
			rote2+=3;
			//canvas.drawBitmap(yellow_draw, (getWidth()/2)-(yellow_draw.getWidth()/2), (getHeight())-(yellow_draw.getHeight()), null);
		}
		transform.setRotate(rote);
		transformsess.setRotate(rote2);
		bmp2 = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), transform, true);
		fb_draw = Bitmap.createBitmap(fb, 0, 0, fb.getWidth(), fb.getHeight(), null, true);
		gp_draw = Bitmap.createBitmap(gp, 0, 0, gp.getWidth(), gp.getHeight(), null, true);
		bmps = Bitmap.createBitmap(bmpsessel, 0, 0, bmpsessel.getWidth(), bmpsessel.getHeight(), transformsess, true);
		bikey = (float) ((getHeight()/2)-(bmp2.getHeight()*0.6))+bikey_add;
		bikex = (getWidth()/2)-(bmp2.getWidth()/2)+bikex_add;
		sessy = (float) (getHeight()-(bmps.getHeight()*0.6))+sessy_add;
		sessx = -(bmps.getWidth()/3)+sessx_add;
		canvas.drawBitmap(bmp3, (getWidth()/2)-(bmp3.getWidth()/2), getHeight()-bmp3.getHeight(), null);
        canvas.drawBitmap(bmp2, bikex, bikey, null);
        canvas.drawBitmap(bmps, sessx, sessy, null);
        canvas.drawBitmap(bmp4, (getWidth()/2)-(bmp4.getWidth()/2), getHeight()-bmp4.getHeight(), null);
        
        if(yellowbool){
        	canvas.drawBitmap(fb, 50, 30, null);
        	canvas.drawBitmap(gp_draw, getWidth()-gp_draw.getWidth()-50, 30, null);
        	canvas.drawBitmap(yellow_draw, (getWidth()/2)-(yellow_draw.getWidth()/2), (getHeight())-(yellow_draw.getHeight())-50, null);
        }
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
	            	if(contact(x,y,15,15,bikex,bikey,bmp2.getWidth(),bmp2.getHeight())){
	            		final Context context = MatView.this.getContext();
	                    Intent intent = new Intent(context , homeActivity.class);
	                    context.startActivity(intent);
                    }
	            	if(contact(x,y,15,15,(getWidth()/2)-(yellow_draw.getWidth()/2),(getHeight())-(yellow_draw.getHeight())-50,yellow_draw.getWidth(),yellow_draw.getHeight()))
	            		{
	            			final Context context = MatView.this.getContext();
		                    Intent intent = new Intent(context , registerActivity.class);
		                    context.startActivity(intent);
	            		}
	            	if(contact(x,y,15,15,sessx,sessy,bmps.getWidth(),bmps.getHeight())){
		                moveup = true;
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
            	bmpsessel.recycle();
            	bmpsessel2.recycle();
            	bmpbgstart.recycle();
            	bmpfgstart.recycle();
            	bmp2.recycle();
            	bmps.recycle();
            	bmp3.recycle();
            	bmp4.recycle();
            }catch(InterruptedException e){
                Log.d("MessagePW", "destroying went wrong: " + e.toString());
            }
        }
	}
    public boolean contact(float x1, float y1, float px1, float py1, float x2, float y2, float px2, float py2){
        return ((x1 > x2 && x1 < x2 + px2) || (x1 + px1 > x2 && x1 + px1 < x2 + px2) || (x2 > x1 && x2 < x1 + px1) || (x2 + px2 > x1 && x2 + px2 < x1 + px1)) && ((y1 > y2 && y1 < y2 + py2) || (y1 + py1 > y2 && y1 + py1 < y2 + py2) || (y2 > y1 && y2 < y1 + py1) || (y2 + py2 > y1 && y2 + py2 < y1 + py1));
    }
}