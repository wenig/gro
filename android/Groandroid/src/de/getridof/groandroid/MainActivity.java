package de.getridof.groandroid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity{

	private MatView view;
	
	private static String url_all_products = "http://groapi.cloudcontrolled.com/gro-api/services/registration";
	JSONParser jParser = new JSONParser();
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		view = new MatView(this);
		setContentView(view);
        
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        BasicNameValuePair paramsJson = new BasicNameValuePair("email", "test");
        params.add(paramsJson);
        /*params.add(new BasicNameValuePair("price", price));
        params.add(new BasicNameValuePair("description", description));*/

        Map <String,String> headers = new HashMap<String,String>();
        headers.put("accept", "application/json");
        headers.put("Content-Type", "application/json");
        // getting JSON Object
        // Note that create product url accepts POST method
        JSONObject json = jParser.makeHttpRequest(url_all_products,
                "POST", params, headers);

        // check log cat fro response
        Log.d("Create Response", json.toString());
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
