package com.example.outfitsdecider;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;


import android.os.*;
import android.content.Context;
import android.content.Intent;

import android.app.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class MainActivity extends Activity {
	private Button getInfo;
	private Button Recommend_outfits;
	private EditText displayTemp;
	private EditText displayStatus;
	private EditText displayHumidity;
	private EditText displayWind;
	static final String tempF="";
	
	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	 @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setTitle("EditTextActivity");
	    setContentView(R.layout.activity_main);
	    StrictMode.setThreadPolicy(policy); 
	    getInfo = (Button) findViewById(R.id.button1);
//	    inputCity = (EditText)findViewById(R.id.editText1);
//	    inputState = (EditText)findViewById(R.id.editText2);
	    displayTemp=(EditText) findViewById(R.id.editText6);
	    displayStatus=(EditText) findViewById(R.id.editText7);
	    displayHumidity=(EditText) findViewById(R.id.editText8);
	    displayWind=(EditText) findViewById(R.id.editText9);
	    find_and_modify_text_view();
	    addListenerOnButton();
	  }

	  private void find_and_modify_text_view() {
	    getInfo = (Button) findViewById(R.id.button1);
	    getInfo.setOnClickListener(get_edit_view_button_listener);
	  }

	  private Button.OnClickListener get_edit_view_button_listener = new Button.OnClickListener() {
	    public void onClick(View v) {
	      EditText city = (EditText) findViewById(R.id.editText1);
	      EditText state = (EditText)findViewById(R.id.editText2);
	      CharSequence cityText = city.getText();
	      CharSequence StateText = state.getText();

	      //call api server and get info from server
	      HttpClient httpclient = new DefaultHttpClient();
				HttpResponse response;
				String responseString = null;
				try {
				 HttpPost httpPost = new HttpPost("http://api.wunderground.com/api/36b799dc821d5836/conditions/q/"+StateText+"/"+cityText+".json");
	             response = httpclient.execute(httpPost);
				    StatusLine statusLine = response.getStatusLine();
				    if(statusLine.getStatusCode() == HttpStatus.SC_OK){
				        ByteArrayOutputStream out = new ByteArrayOutputStream();
				        response.getEntity().writeTo(out);
				        out.close();
				        responseString = out.toString();
				     String tempF = JSONAnalysis3(responseString);
				     String weather = JSONAnalysis1(responseString);
				     //String city = JSONAnalysis3(responseString);
				     String Hum= JSONAnalysis4(responseString);
				     String wind= JSONAnalysis5(responseString);
				     
				     displayTemp.setText(" "+tempF);
				     displayStatus.setText(" "+weather);
				     displayWind.setText(" "+wind);
				     displayHumidity.setText(" "+Hum);
				     System.out.println(tempF);
				     System.out.println(weather);
				     System.out.println(wind);
				     System.out.println(Hum);
				    } else{
				        //Closes the connection.
				        response.getEntity().getContent().close();
				        throw new IOException(statusLine.getReasonPhrase());
				    }	
				} catch (ClientProtocolException e) {
				    e.printStackTrace();
				} catch (IOException e) {
				    e.printStackTrace();
				}
			 	

			}
	    };
	    //add recommendoutfits button action
	    public void addListenerOnButton() {
		    Button Gender = (Button) findViewById(R.id.button2);
		    final Context context = this;
		    Gender.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
				EditText city = (EditText) findViewById(R.id.editText1);
			      EditText state = (EditText)findViewById(R.id.editText2);
			      CharSequence cityText = city.getText();
			      CharSequence StateText = state.getText();

			      //call api server and get info from server
			      HttpClient httpclient = new DefaultHttpClient();
						HttpResponse response;
						String responseString = null;
						try {
						 HttpPost httpPost = new HttpPost("http://api.wunderground.com/api/36b799dc821d5836/conditions/q/"+StateText+"/"+cityText+".json");
			             response = httpclient.execute(httpPost);
						    StatusLine statusLine = response.getStatusLine();
						    if(statusLine.getStatusCode() == HttpStatus.SC_OK){
						        ByteArrayOutputStream out = new ByteArrayOutputStream();
						        response.getEntity().writeTo(out);
						        out.close();
						        responseString = out.toString();
						     //String tempF = JSONAnalysis3(responseString);
						     EditText temp = (EditText) findViewById(R.id.editText6);
						     	double tempText =0.0;
								tempText = Double.parseDouble(temp.getText().toString());
							    Intent intent = new Intent(context, chooseGenderActivity.class);
							    Bundle b= new Bundle();
							    b.putDouble("tempF", tempText);
							    intent.putExtras(b);
							    System.out.println(tempText+"-------"+b);
							    System.out.println(tempF+"+++++++");
				                startActivity(intent);
				                
						    } else{
						        //Closes the connection.
						        response.getEntity().getContent().close();
						        throw new IOException(statusLine.getReasonPhrase());
						    }	
						} catch (ClientProtocolException e) {
						    e.printStackTrace();
						} catch (IOException e) {
						    e.printStackTrace();
						}
//				EditText temp = (EditText) findViewById(R.id.editText6);
//				CharSequence tempText = temp.getText().toString();
//			    Intent intent = new Intent(context, chooseGenderActivity.class);
//			    System.out.println(tempText);
//			    intent.putExtra(tempF, tempText);
//                startActivity(intent);   
 
			}
		});
	    }
	    public String JSONAnalysis3(String jsonString)
	    {
	    	String temperatureF="";
	    	JSONObject jsonObj3; 
			try {
				jsonObj3 = new JSONObject(jsonString);
				JSONObject  obser=jsonObj3.getJSONObject("current_observation");    	
		    	temperatureF=obser.getString("temp_f");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return temperatureF;
	    }
	    public String JSONAnalysis(String jsonString)
	    {
	    	String temperature="";
	    	JSONObject jsonObj; 
			try {
				jsonObj = new JSONObject(jsonString);
				JSONObject  obser=jsonObj.getJSONObject("current_observation");    	
		    	temperature=obser.getString("temperature_string");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return temperature;
	    }
	    public String JSONAnalysis1(String jsonString){
	    	String weather="";
	    	JSONObject jsonObj1;
			try{
				jsonObj1 = new JSONObject(jsonString);
				JSONObject  obser1=jsonObj1.getJSONObject("current_observation");  
				weather = obser1.getString("weather");
			} catch(JSONException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return  weather;
			
	    }
	    public String JSONAnalysis4(String jsonString){
	    	String Humidity="";
	    	JSONObject jsonObj4;
			try{
				jsonObj4 = new JSONObject(jsonString);
				JSONObject  obser1=jsonObj4.getJSONObject("current_observation");
				Humidity = obser1.getString("relative_humidity");
			} catch(JSONException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return  Humidity;
	    }
	    public String JSONAnalysis5(String jsonString){
	    	String Wind="";
	    	JSONObject jsonObj5;
			try{
				jsonObj5 = new JSONObject(jsonString);
				JSONObject  obser1=jsonObj5.getJSONObject("current_observation");
				Wind = obser1.getString("wind_string");
			} catch(JSONException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return  Wind;
	    }
	    }
	  

	
