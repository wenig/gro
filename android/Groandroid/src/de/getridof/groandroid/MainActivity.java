package de.getridof.groandroid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity{

	private static final String EXTRA_MESSAGE = "1";

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
		setContentView(view);//
		/*EditText name = (EditText) findViewById(R.id.name);
		EditText lastname = (EditText) findViewById(R.id.lastname);
		EditText address = (EditText) findViewById(R.id.address);
		EditText zip = (EditText) findViewById(R.id.zip);
		EditText city = (EditText) findViewById(R.id.city);
		final EditText email = (EditText) findViewById(R.id.email);
		EditText phone = (EditText) findViewById(R.id.phone);
		final EditText password = (EditText) findViewById(R.id.password);
		final EditText passwordconfirm = (EditText) findViewById(R.id.passwordconfirm);
		EditText paypalemail = (EditText) findViewById(R.id.paypalemail);
		EditText paypalpassword = (EditText) findViewById(R.id.paypalpassword);
		final Button submit = (Button) findViewById(R.id.submit);
		
		submit.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		     
        
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        BasicNameValuePair emailJson = new BasicNameValuePair("email", email.getText().toString());
        params.add(emailJson);
        if (password.getText() != null && password.getText().toString().equals(passwordconfirm.getText().toString())) {
        	BasicNameValuePair passwordJson = new BasicNameValuePair("password", password.getText().toString());
            params.add(passwordJson);
        }
        
        /*params.add(new BasicNameValuePair("price", price));
        params.add(new BasicNameValuePair("description", description));*/

        /*Map <String,String> headers = new HashMap<String,String>();
        headers.put("accept", "application/json");
        headers.put("Content-Type", "application/json");
        // getting JSON Object
        // Note that create product url accepts POST method
        JSONObject json = jParser.makeHttpRequest(url_all_products,
                "POST", params, headers);

        // check log cat fro response
        Log.d("Create Response", json.toString());
		    }
		});*/
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		view = new MatView(this);
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
	
	public void newItem(View view) {
		Intent intent = new Intent(this, newitemActivity.class);
		String message = "newitem";
		intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
	}
	
	public void login(View view) {
		Intent intent = new Intent(this, loginActivity.class);
		String message = "login";
		intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
	}
}
