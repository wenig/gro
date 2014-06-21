package de.getridof.groandroid;
import android.R.string;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Matrix;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MatView extends SurfaceView {
	private SurfaceHolder surfaceHolder;
	private Bitmap bmp;
	private Bitmap bmpbutbike;
	private Bitmap bmpbutbike_c;
	private Bitmap bmpbgstart;
	private Bitmap bmpfgstart;
	private Bitmap bmp1;
	private Paint wenigp = new Paint();
    private Paint circ = new Paint();
	private float bikey = 0;
	private float bikex = 0;
	private float y = 0;
	private float x = 0;
	private float mPreviousX;
	private float mPreviousY;
	private boolean mIsDown = false;
	private boolean up = false;
    private boolean down = false;
    private boolean right = false;
    private boolean left = false;
    private boolean moveup = false;
    private boolean grey = true;
    private int actAtr = 0;
	private int u = -10;
    private int r = -10;
    private int rote = 0;
    private int rote2 = 37;
	private MatLoop matThread = new MatLoop(this);
    private Matrix transform = new Matrix();

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
		bmp = BitmapFactory.decodeResource(getResources(), R.drawable.bicycle_gray_240dpi);
		bmpbutbike = BitmapFactory.decodeResource(getResources(), R.drawable.button_bicycle_240dpi);
		bmpbutbike_c = BitmapFactory.decodeResource(getResources(), R.drawable.button_bicycle_240dpi);
		bmpbgstart = BitmapFactory.decodeResource(getResources(), R.drawable.start_screen_background);
		bmpfgstart = BitmapFactory.decodeResource(getResources(), R.drawable.start_screen_frontground);
		}
	@Override
	protected void onDraw(Canvas canvas){
		canvas.drawColor(Color.WHITE);
		
		if(moveup && grey){
			if(bikey > getHeight()*0.2){
			y += 10;
			Log.d("MessagePW", ""+y);
			if(!(rote == rote2)){
			rote += 1;
			}
			}else{
				moveup = false;
				grey = false;
			}
			bmp1 = bmp;
		}else if(!moveup && grey){
			bmp1 = bmp;
		}else if(!moveup && !grey){
			bmp1 = bmpbutbike;
		}else if(!moveup && !grey && mIsDown){
			bmp1 = bmpbutbike_c;
		}
		Bitmap bmp3 = Bitmap.createScaledBitmap(bmpbgstart, getWidth(), bmpbgstart.getHeight()*(getWidth()/bmpbgstart.getWidth()), true);
		Bitmap bmp2 = Bitmap.createBitmap(bmp1, 0, 0, bmp1.getWidth(), bmp1.getHeight(), transform, true);
		Bitmap bmp4 = Bitmap.createScaledBitmap(bmpfgstart, getWidth(), bmpfgstart.getHeight()*(getWidth()/bmpfgstart.getWidth()), true);
		transform.setRotate(rote2-rote);
		bikey = getHeight()-bmp2.getHeight()-y;
		bikex = (getWidth()/2)-(bmp2.getWidth()/2)+x;
		canvas.drawBitmap(bmp3, (getWidth()/2)-(bmp3.getWidth()/2), getHeight()-bmp3.getHeight(), null);
        canvas.drawBitmap(bmp2, bikex, bikey, null);
        canvas.drawBitmap(bmp4, (getWidth()/2)-(bmp4.getWidth()/2), getHeight()-bmp4.getHeight(), null);



	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
	    synchronized (getHolder()) {
	    	float dHi = getHeight();
	    	float dWi = getWidth();
	    	float x = event.getX();
	        float y = event.getY();
	            switch (event.getAction()) {
	            case MotionEvent.ACTION_DOWN:
	                mIsDown = true;
	                mPreviousX = x;
	                mPreviousY = y;
                    
	                break;
	            case MotionEvent.ACTION_MOVE:
	                float dx = x - mPreviousX;
	                float dy = y - mPreviousY;
	                detectSwipe(dx, dy, dWi, dHi);
	                
                    break;
	            case MotionEvent.ACTION_UP:
	                mIsDown = false;
	                moveup = true;
	                break;
	        }
	    }
	    return true;
	}
	public void detectSwipe(float dx, float dy, float dWi, float dHi){
        float hypo =(float) Math.hypot(dx, dy);
        float angle =(float) Math.asin(hypo/dy);
        
		if(hypo > 0.2 * dHi){
            if(dy >= 0 && angle >= 45) {
                if (!up) {
                    up = true;
                    u = -10;
                }
                Log.d("MessagePW", "up");
            }else if(dy < 0 && angle <= -45){
                //down = true;
                Log.d("MessagePW", "down");
            }else if(dx >= 0 && angle <= 45){
                if(!right && actAtr != 0){
                    right = true;
                    r = -10;
                }
                Log.d("MessagePW", "right");
            }else if(dx < 0 && angle <= 45 && angle >= -45){
                //left = true;
                Log.d("MessagePW", "left");
            }
		}
	}
	public void jump(){
		if(u <= 10){
            //y = ((u * u) + getHeight()) - 200;
			u++;
		}
		if(u == 11){
			up = false;
		}
	}
    public void breakFree(){
        if(r <= 10){
            //x = (-(r * r) + 5) + 100;
            r++;
            if(r == 0){
                
            }
        }
        if(r == 11){
            up = false;
        }
    }
    public void moveBarrierLeft(){
        
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