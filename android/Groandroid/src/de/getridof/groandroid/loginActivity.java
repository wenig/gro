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
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class loginActivity extends Activity{

	JSONParser jParser = new JSONParser();
	private EditText password;
	private EditText email;
	private static String login_url = "http://groapi.cloudcontrolled.com/gro-api/services/authentication/login";
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        password = (EditText) findViewById(R.id.password);
        email = (EditText) findViewById(R.id.email);
	}
	
	public void login(View view){
	     
    
    List<NameValuePair> params = new ArrayList<NameValuePair>();
    BasicNameValuePair emailJson = new BasicNameValuePair("email", email.getText().toString());
    params.add(emailJson);
	BasicNameValuePair passwordJson = new BasicNameValuePair("password", password.getText().toString());
    params.add(passwordJson);
    
    /*params.add(new BasicNameValuePair("price", price));
    params.add(new BasicNameValuePair("description", description));*/

    Map <String,String> headers = new HashMap<String,String>();
    headers.put("accept", "application/json");
    headers.put("Content-Type", "application/json");
    // getting JSON Object
    // Note that create product url accepts POST method
    JSONObject json = jParser.makeHttpRequest(login_url,
            "POST", params, headers);

    // check log cat fro response
    Log.d("Create Response", json.toString());
    }
}
	

