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
	private Bitmap bmpbutbike_c;
	private Bitmap bmpbgstart;
	private Bitmap bmpfgstart;
	private Bitmap bmp1;
	private Bitmap bmp2;
	private Bitmap bmps;
	private Bitmap bmp3;
	private Bitmap bmp4;
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
		bmpbgstart = BitmapFactory.decodeResource(getResources(), R.drawable.start_back);
		bmpfgstart = BitmapFactory.decodeResource(getResources(), R.drawable.start_front);
		bmp3 = Bitmap.createScaledBitmap(bmpbgstart, width, bmpbgstart.getHeight(), true);
		bmp4 = Bitmap.createScaledBitmap(bmpfgstart, width, bmpfgstart.getHeight(), true);
		
		}
	@Override
	protected void onDraw(Canvas canvas){
		canvas.drawColor(Color.WHITE);
		rote-=0.3;
		if(moveup){
			if((sessy > (float) ((getHeight()/2)-(bmp.getHeight()*0.6))) && stop){
				sessy_add-=10;
				sessx_add+=5;
				bikex_add+=5;
			}else{
				stop = false;
			}
			rote2+=2;
		}
		transform.setRotate(rote);
		transformsess.setRotate(rote2);
		bmp2 = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), transform, true);
		bmps = Bitmap.createBitmap(bmpsessel, 0, 0, bmpsessel.getWidth(), bmpsessel.getHeight(), transformsess, true);
		bikey = (float) ((getHeight()/2)-(bmp2.getHeight()*0.6))+bikey_add;
		bikex = (getWidth()/2)-(bmp2.getWidth()/2)+bikex_add;
		sessy = (float) (getHeight()-(bmps.getHeight()*0.6))+sessy_add;
		sessx = -(bmps.getWidth()/3)+sessx_add;
		canvas.drawBitmap(bmp3, (getWidth()/2)-(bmp3.getWidth()/2), getHeight()-bmp3.getHeight(), null);
        canvas.drawBitmap(bmp2, bikex, bikey, null);
        canvas.drawBitmap(bmps, sessx, sessy, null);
        canvas.drawBitmap(bmp4, (getWidth()/2)-(bmp4.getWidth()/2), getHeight()-bmp4.getHeight(), null);



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
	                    Intent intent = new Intent(context , loginActivity.class);
	                    context.startActivity(intent);
                    }
	            	if(contact(x,y,15,15,sessx,sessy,bmps.getWidth(),bmps.getHeight())){
	            		if(moveup){
	            			final Context context = MatView.this.getContext();
		                    Intent intent = new Intent(context , registerActivity.class);
		                    context.startActivity(intent);
	            		}
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
            }catch(InterruptedException e){
                Log.d("MessagePW", "destroying went wrong: " + e.toString());
            }
        }
	}
    public boolean contact(float x1, float y1, float px1, float py1, float x2, float y2, float px2, float py2){
        return ((x1 > x2 && x1 < x2 + px2) || (x1 + px1 > x2 && x1 + px1 < x2 + px2) || (x2 > x1 && x2 < x1 + px1) || (x2 + px2 > x1 && x2 + px2 < x1 + px1)) && ((y1 > y2 && y1 < y2 + py2) || (y1 + py1 > y2 && y1 + py1 < y2 + py2) || (y2 > y1 && y2 < y1 + py1) || (y2 + py2 > y1 && y2 + py2 < y1 + py1));
    }
}