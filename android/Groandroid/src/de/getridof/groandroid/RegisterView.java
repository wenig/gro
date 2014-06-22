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

public class RegisterView extends SurfaceView {
	private SurfaceHolder surfaceHolder;
	private Bitmap bmp;
	private Bitmap bmpsec;
	private Bitmap bmpsec_draw;
	private Bitmap bmpsessel;
	private Bitmap bmpavatar;
	private Bitmap bmpbgstart;
	private Bitmap bmpfgstart;
	private Bitmap bmp1;
	private Bitmap bmp2;
	private Bitmap bmps;
	private Bitmap bmp3;
	private Bitmap bmp4;
	private Bitmap right;
	private Bitmap left;
	private Bitmap right_draw;
	private Bitmap left_draw;
	private float bikey = 0;
	private float bikex = 0;
	private float bikex2 = 0;
	private float sessy = 0;
	private float sessx = 0;
	private float bikey_add = 0;
	private float bikex_add = 0;
	private float rightx = 0;
	private float righty = 0;
	private float lefty = 0;
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
    private boolean movebool = false;
    private boolean leftbool = false;
    private boolean rightbool = false;
    private boolean startwhere = true;
    private float rote = 0;
    private float rote2 = 225;
	private RegisterLoop matThread = new RegisterLoop(this);
    private Matrix transform = new Matrix();
    private Matrix transformsess = new Matrix();
    

	@SuppressLint("WrongCall")public RegisterView(Context context){
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
		bmp = BitmapFactory.decodeResource(getResources(), R.drawable.registration_strokes_01);
		bmpsec = BitmapFactory.decodeResource(getResources(), R.drawable.registration_strokes_02);
		bmpsessel = BitmapFactory.decodeResource(getResources(), R.drawable.chair_button_login_clicked);
		bmpavatar = BitmapFactory.decodeResource(getResources(), R.drawable.avatar_button);
		bmpbgstart = BitmapFactory.decodeResource(getResources(), R.drawable.start_back);
		bmpfgstart = BitmapFactory.decodeResource(getResources(), R.drawable.start_front);
		right = BitmapFactory.decodeResource(getResources(), R.drawable.goon_button);
		left = BitmapFactory.decodeResource(getResources(), R.drawable.back_button);
		bmp3 = Bitmap.createScaledBitmap(bmpbgstart, width, bmpbgstart.getHeight(), true);
		bmp4 = Bitmap.createScaledBitmap(bmpfgstart, width, bmpfgstart.getHeight(), true);
		transformsess.setRotate(45);
		bmp1 = Bitmap.createBitmap(bmpavatar, 0, 0, bmpavatar.getWidth(), bmpavatar.getHeight(), null, true);
		bmps = Bitmap.createBitmap(bmpsessel, 0, 0, bmpsessel.getWidth(), bmpsessel.getHeight(), transformsess, true);
		right_draw = Bitmap.createBitmap(right, 0, 0, right.getWidth(), right.getHeight(), null, true);
		left_draw = Bitmap.createBitmap(left, 0, 0, left.getWidth(), left.getHeight(), null, true);
		}
	@Override
	protected void onDraw(Canvas canvas){
		canvas.drawColor(Color.WHITE);
		
		if(movebool){
			if(leftbool){
			if(bikex_add >= -(getWidth()-((getWidth()/2)-(bmp2.getWidth()/2)))){
				bikex_add-=10*(getWidth()/((getWidth()/2)-(bmp2.getWidth()/2)));
			}else{
				movebool = false;
				leftbool = false;
			}
			}else if(rightbool){
				if(bikex_add <= 0){
					bikex_add+=10*(getWidth()/((getWidth()/2)-(bmp2.getWidth()/2)));
				}else{
					movebool = false;
					rightbool = false;
				}
			}
		}
		
		bmp2 = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), null, true);
		bmpsec_draw = Bitmap.createBitmap(bmpsec, 0, 0, bmpsec.getWidth(), bmpsec.getHeight(), null, true);
		bikex = (getWidth()/2)-(bmp2.getWidth()/2)+bikex_add;
		bikex2 = getWidth()+bikex_add;
		sessx = -(bmps.getWidth()/3)+sessx_add;
		rightx = getWidth()-right_draw.getWidth();
		righty = getHeight()-right_draw.getHeight();
		lefty = getHeight()-left_draw.getHeight();
		canvas.drawBitmap(bmp3, (getWidth()/2)-(bmp3.getWidth()/2), getHeight()-bmp3.getHeight(), null);
        
        canvas.drawBitmap(bmps, -20, -20, null);
        canvas.drawBitmap(bmp4, (getWidth()/2)-(bmp4.getWidth()/2), getHeight()-bmp4.getHeight(), null);
        canvas.drawBitmap(bmp1, getWidth()/2, bmp1.getHeight()/3, null);

        canvas.drawBitmap(bmp2, bikex, getHeight()/3, null);
        canvas.drawBitmap(bmpsec_draw, bikex2, getHeight()/3, null);
        canvas.drawBitmap(right_draw, rightx, righty, null);
        canvas.drawBitmap(left_draw, 0, lefty, null);
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
	            		final Context context = RegisterView.this.getContext();
	                    Intent intent = new Intent(context , loginActivity.class);
	                    context.startActivity(intent);
                    }
	            	if(contact(x,y,15,15,rightx,righty,right.getWidth(),right.getHeight()) && startwhere){
	            		movebool = true;
	            		leftbool = true;
	            		startwhere = false;
	            	}else if(contact(x,y,15,15,0,lefty,left.getWidth(),left.getHeight()) && !startwhere){
	            		movebool = true;
	            		rightbool = true;
	            		startwhere = true;
	            	}else if(contact(x,y,15,15,0,lefty,left.getWidth(),left.getHeight()) && startwhere){
	            		Activity activity = (Activity)getContext();
	            		   activity.finish();
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
            	bmpsec.recycle();
            	bmpsec_draw.recycle();
            	bmpsessel.recycle();
            	bmpavatar.recycle();
            	bmpbgstart.recycle();
            	bmpfgstart.recycle();
            	bmp1.recycle();
            	bmp2.recycle();
            	bmps.recycle();
            	bmp3.recycle();
            	bmp4.recycle();
            	right.recycle();
            	left.recycle();
            	right_draw.recycle();
            	left_draw.recycle();
            }catch(InterruptedException e){
                Log.d("MessagePW", "destroying went wrong: " + e.toString());
            }
        }
	}
    public boolean contact(float x1, float y1, float px1, float py1, float x2, float y2, float px2, float py2){
        return ((x1 > x2 && x1 < x2 + px2) || (x1 + px1 > x2 && x1 + px1 < x2 + px2) || (x2 > x1 && x2 < x1 + px1) || (x2 + px2 > x1 && x2 + px2 < x1 + px1)) && ((y1 > y2 && y1 < y2 + py2) || (y1 + py1 > y2 && y1 + py1 < y2 + py2) || (y2 > y1 && y2 < y1 + py1) || (y2 + py2 > y1 && y2 + py2 < y1 + py1));
    }
}