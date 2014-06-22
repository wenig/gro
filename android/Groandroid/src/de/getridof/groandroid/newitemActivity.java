package de.getridof.groandroid;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class newitemActivity extends Activity{

	private static String item_url = "http://groapi.cloudcontrolled.com/gro-api/services/items/createItem";
	JSONParser jParser = new JSONParser();
	static final int REQUEST_IMAGE_CAPTURE = 1;
	private ImageView mImageView;
	private EditText name;
	private EditText quantity;
	private EditText tags;
	private String imgstr;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newitem);
        
        final Button photo = (Button) findViewById(R.id.photo);
		mImageView = (ImageView) findViewById(R.id.imageView1);
		name = (EditText) findViewById(R.id.name);
		quantity = (EditText) findViewById(R.id.quantity);
		tags = (EditText) findViewById(R.id.tags);
        
		photo.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		    	dispatchTakePictureIntent();
		    }
		});
	}
	
	private void dispatchTakePictureIntent() {
	    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
	        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
	    }
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
	        Bundle extras = data.getExtras();
	        Bitmap imageBitmap = (Bitmap) extras.get("data");
	        mImageView.setImageBitmap(imageBitmap);
	        String toSend = null;
	        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
	        ObjectOutputStream oStream;
			try {
				oStream = new ObjectOutputStream( bStream );
		        oStream.writeObject ( imageBitmap );
		        toSend = Base64.encodeToString(bStream.toByteArray(),Base64.DEFAULT);
		        imgstr = toSend;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    }
	}
	
	public void sendItem(View view){
		List<NameValuePair> params = new ArrayList<NameValuePair>();
        BasicNameValuePair emailJson = new BasicNameValuePair("name", name.getText().toString());
        params.add(emailJson);
        BasicNameValuePair tagsJson = new BasicNameValuePair("tags", tags.getText().toString());
        params.add(tagsJson);
//        BasicNameValuePair quantityJson = new BasicNameValuePair("quantity", quantity.getText().toString());
//        params.add(quantityJson);
        BasicNameValuePair photoJson = new BasicNameValuePair("image", imgstr);
        params.add(photoJson);
        
        /*params.add(new BasicNameValuePair("price", price));
        params.add(new BasicNameValuePair("description", description));*/

        Map <String,String> headers = new HashMap<String,String>();
        headers.put("accept", "application/json");
        headers.put("Content-Type", "application/json");
        // getting JSON Object
        // Note that create product url accepts POST method
        JSONObject json = jParser.makeHttpRequest(item_url,
                "POST", params, headers);

        // check log cat fro response
        Log.d("Create Response", json.toString());
        Intent intent = new Intent(this , homeActivity.class);
        this.startActivity(intent);
	}
	
}
