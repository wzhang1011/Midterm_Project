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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;



public class chooseGenderActivity extends Activity{
	Button maleB;
	Button femaleB;
	static final String temp = "editText6";
	static final String outfitRE="recommendation";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gender);
		addListenerOnButton();
	}
	
	public void addListenerOnButton() {
		 
		final Context context = this;
 
		maleB = (Button) findViewById(R.id.button1);
		femaleB = (Button) findViewById(R.id.button2);
		maleB.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) { 
				TextView displayOF= (TextView)findViewById(R.id.recommendation);
				//get intent data
				//get temp from previous
				Bundle b=getIntent().getExtras();
				double tempf = b.getDouble("tempF");
				System.out.println(">>>>>>> "+tempf);
				HttpClient httpclient = new DefaultHttpClient();
				HttpResponse response;
				String responseString = null;
				try {
					if(tempf<=35.0){
						HttpPost httpPost = new HttpPost("http://134.193.136.127:8983/solr/collection1_shard1_replica1/select?q=*B35FM*&wt=json&indent=true");
						response = httpclient.execute(httpPost);
						StatusLine statusLine = response.getStatusLine();
						if(statusLine.getStatusCode() == HttpStatus.SC_OK){
					        ByteArrayOutputStream out = new ByteArrayOutputStream();
					        response.getEntity().writeTo(out);
					        out.close();
					        responseString = out.toString();
					     String of = JSONAnalysis(responseString);
					     displayOF.setText(of);
					     System.out.println(of);	    

					    } else{
					        //Closes the connection.
					        response.getEntity().getContent().close();
					        throw new IOException(statusLine.getReasonPhrase());
					    }
					}else if(tempf>=36.0 && tempf<=55.0){
						HttpPost httpPost = new HttpPost("http://134.193.136.127:8983/solr/collection1_shard1_replica1/select?q=*36to55FM*&wt=json&indent=true");
						response = httpclient.execute(httpPost);
						StatusLine statusLine = response.getStatusLine();
						if(statusLine.getStatusCode() == HttpStatus.SC_OK){
					        ByteArrayOutputStream out = new ByteArrayOutputStream();
					        response.getEntity().writeTo(out);
					        out.close();
					        responseString = out.toString();
					     String of = JSONAnalysis(responseString);
					     displayOF.setText(of);
					     System.out.println(of);	    
					     String outfitR=((TextView) findViewById(R.id.recommendation)).getText().toString();

					    } else{
					        //Closes the connection.
					        response.getEntity().getContent().close();
					        throw new IOException(statusLine.getReasonPhrase());
					    }
					}else if(tempf>=56.0 && tempf<=75.0){
						HttpPost httpPost = new HttpPost("http://134.193.136.127:8983/solr/collection1_shard1_replica1/select?q=*56to75fm*&wt=json&indent=true");
						response = httpclient.execute(httpPost);
						StatusLine statusLine = response.getStatusLine();
						if(statusLine.getStatusCode() == HttpStatus.SC_OK){
					        ByteArrayOutputStream out = new ByteArrayOutputStream();
					        response.getEntity().writeTo(out);
					        out.close();
					        responseString = out.toString();
					     String of = JSONAnalysis(responseString);
					     displayOF.setText(of);
					     System.out.println(of);	    
					     String outfitR=((TextView) findViewById(R.id.recommendation)).getText().toString();

					    } else{
					        //Closes the connection.
					        response.getEntity().getContent().close();
					        throw new IOException(statusLine.getReasonPhrase());
					    }
					}else if(tempf>=76.0 && tempf<=100.0){
						HttpPost httpPost = new HttpPost("http://134.193.136.127:8983/solr/collection1_shard1_replica1/select?q=*76to100fm*&wt=json&indent=true");
						response = httpclient.execute(httpPost);
						StatusLine statusLine = response.getStatusLine();
						if(statusLine.getStatusCode() == HttpStatus.SC_OK){
					        ByteArrayOutputStream out = new ByteArrayOutputStream();
					        response.getEntity().writeTo(out);
					        out.close();
					        responseString = out.toString();
					     String of = JSONAnalysis(responseString);
					     displayOF.setText(of);
					     System.out.println(of);	    
					     String outfitR=((TextView) findViewById(R.id.recommendation)).getText().toString();

					    } else{
					        //Closes the connection.
					        response.getEntity().getContent().close();
					        throw new IOException(statusLine.getReasonPhrase());
					    }
					}else{
						HttpPost httpPost = new HttpPost("http://134.193.136.127:8983/solr/collection1_shard1_replica1/select?q=*a100fm*&wt=json&indent=true");
						response = httpclient.execute(httpPost);
						StatusLine statusLine = response.getStatusLine();
				    
				    if(statusLine.getStatusCode() == HttpStatus.SC_OK){
				        ByteArrayOutputStream out = new ByteArrayOutputStream();
				        response.getEntity().writeTo(out);
				        out.close();
				        responseString = out.toString();
				     String of = JSONAnalysis(responseString);
				     displayOF.setText(of);
				     System.out.println(of);	    
				     String outfitR=((TextView) findViewById(R.id.recommendation)).getText().toString();

				    } else{
				        //Closes the connection.
				        response.getEntity().getContent().close();
				        throw new IOException(statusLine.getReasonPhrase());
				    }}	
				} catch (ClientProtocolException e) {
				    e.printStackTrace();
				} catch (IOException e) {
				    e.printStackTrace();
				}

 
			}
 
		});
		femaleB.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
				TextView displayOF= (TextView)findViewById(R.id.recommendation);
				//get intent data
				//get temp from previous
				Bundle b=getIntent().getExtras();
				double tempf = b.getDouble("tempF");
				System.out.println(">>>>>>> "+tempf);
				HttpClient httpclient = new DefaultHttpClient();
				HttpResponse response;
				String responseString = null;
				try {
					if(tempf<=35.0){
						HttpPost httpPost = new HttpPost("http://134.193.136.127:8983/solr/collection1_shard1_replica1/select?q=*B35FF*&wt=json&indent=true");
						response = httpclient.execute(httpPost);
						StatusLine statusLine = response.getStatusLine();
						if(statusLine.getStatusCode() == HttpStatus.SC_OK){
					        ByteArrayOutputStream out = new ByteArrayOutputStream();
					        response.getEntity().writeTo(out);
					        out.close();
					        responseString = out.toString();
					     String of = JSONAnalysis(responseString);
					     displayOF.setText(of);
					     System.out.println(of);	    

					    } else{
					        //Closes the connection.
					        response.getEntity().getContent().close();
					        throw new IOException(statusLine.getReasonPhrase());
					    }
					}else if(tempf>=36.0 && tempf<=55.0){
						HttpPost httpPost = new HttpPost("http://134.193.136.127:8983/solr/collection1_shard1_replica1/select?q=*36to55FF*&wt=json&indent=true");
						response = httpclient.execute(httpPost);
						StatusLine statusLine = response.getStatusLine();
						if(statusLine.getStatusCode() == HttpStatus.SC_OK){
					        ByteArrayOutputStream out = new ByteArrayOutputStream();
					        response.getEntity().writeTo(out);
					        out.close();
					        responseString = out.toString();
					     String of = JSONAnalysis(responseString);
					     displayOF.setText(of);
					     System.out.println(of);	    
					     String outfitR=((TextView) findViewById(R.id.recommendation)).getText().toString();

					    } else{
					        //Closes the connection.
					        response.getEntity().getContent().close();
					        throw new IOException(statusLine.getReasonPhrase());
					    }
					}else if(tempf>=56.0 && tempf<=75.0){
						HttpPost httpPost = new HttpPost("http://134.193.136.127:8983/solr/collection1_shard1_replica1/select?q=*56to75ff*&wt=json&indent=true");
						response = httpclient.execute(httpPost);
						StatusLine statusLine = response.getStatusLine();
						if(statusLine.getStatusCode() == HttpStatus.SC_OK){
					        ByteArrayOutputStream out = new ByteArrayOutputStream();
					        response.getEntity().writeTo(out);
					        out.close();
					        responseString = out.toString();
					     String of = JSONAnalysis(responseString);
					     displayOF.setText(of);
					     System.out.println(of);	    
					     String outfitR=((TextView) findViewById(R.id.recommendation)).getText().toString();

					    } else{
					        //Closes the connection.
					        response.getEntity().getContent().close();
					        throw new IOException(statusLine.getReasonPhrase());
					    }
					}else if(tempf>=76.0 && tempf<=100.0){
						HttpPost httpPost = new HttpPost("http://134.193.136.127:8983/solr/collection1_shard1_replica1/select?q=*76to100ff*&wt=json&indent=true");
						response = httpclient.execute(httpPost);
						StatusLine statusLine = response.getStatusLine();
						if(statusLine.getStatusCode() == HttpStatus.SC_OK){
					        ByteArrayOutputStream out = new ByteArrayOutputStream();
					        response.getEntity().writeTo(out);
					        out.close();
					        responseString = out.toString();
					     String of = JSONAnalysis(responseString);
					     displayOF.setText(of);
					     System.out.println(of);	    
					     String outfitR=((TextView) findViewById(R.id.recommendation)).getText().toString();

					    } else{
					        //Closes the connection.
					        response.getEntity().getContent().close();
					        throw new IOException(statusLine.getReasonPhrase());
					    }
					}else{
						HttpPost httpPost = new HttpPost("http://134.193.136.127:8983/solr/collection1_shard1_replica1/select?q=*a100ff*&wt=json&indent=true");
						response = httpclient.execute(httpPost);
						StatusLine statusLine = response.getStatusLine();
				    
				    if(statusLine.getStatusCode() == HttpStatus.SC_OK){
				        ByteArrayOutputStream out = new ByteArrayOutputStream();
				        response.getEntity().writeTo(out);
				        out.close();
				        responseString = out.toString();
				     String of = JSONAnalysis(responseString);
				     displayOF.setText(of);
				     System.out.println(of);	    
				     String outfitR=((TextView) findViewById(R.id.recommendation)).getText().toString();

				    } else{
				        //Closes the connection.
				        response.getEntity().getContent().close();
				        throw new IOException(statusLine.getReasonPhrase());
				    }}	
				} catch (ClientProtocolException e) {
				    e.printStackTrace();
				} catch (IOException e) {
				    e.printStackTrace();
				}

 
 
			}
 
		});
	}
	public String JSONAnalysis(String jsonString)
    {
		String outfits="";
    	JSONObject jSONObj;
    	//String temp="";
		try {
			jSONObj = new JSONObject(jsonString);
			JSONObject  obser=jSONObj.getJSONObject("response");
			JSONArray  obser1=obser.getJSONArray("docs");
			obser = obser1.getJSONObject(0);
			outfits=obser.get("id").toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return outfits;
    }
 
}
