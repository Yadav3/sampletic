package com.taico.android;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.util.Log;

@SuppressWarnings("deprecation")
public class WebServiceCalls {

	public String result = null;
	String ff = null;

	public String urlPost(String url) {

		HttpParams httpParameters = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParameters, 5000);
		HttpConnectionParams.setSoTimeout(httpParameters, 5000);
		HttpClient client = new DefaultHttpClient(httpParameters);
		try {

			HttpPost post = new HttpPost(url);

			List<NameValuePair> pairs = new ArrayList<NameValuePair>();
			post.setEntity(new UrlEncodedFormEntity(pairs));
			HttpResponse response = client.execute(post);

			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				String re = EntityUtils.toString(entity, HTTP.UTF_8);
				// ff = re;

				result = re.trim();
			}

		} catch (SocketTimeoutException e) {
			client.getConnectionManager().shutdown();
			urlPost(url);

		} catch (Exception e) {
			client.getConnectionManager().shutdown();
			// urlPost(url);
			return null;
		}

		return result;

	}

	public String urlGet(String url) {

		try {
			HttpClient client = new DefaultHttpClient();
			HttpGet post = new HttpGet(url);
			HttpResponse response = client.execute(post);

			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				String re = EntityUtils.toString(entity, HTTP.UTF_8);

				result = re.trim();

			}
			
			
		} catch (Exception e) {
		}
	  	return result;

	}

	public String urlPostNew(String url) {
		HttpClient hClient = new DefaultHttpClient();
		HttpPost hPost = new HttpPost(url);
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		try {
			hPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = hClient.execute(hPost);
			HttpEntity hEntity = response.getEntity();
			InputStream is = hEntity.getContent();

			// hClient.getConnectionManager().shutdown();

			result = convertStreamToString(is);

		} catch (UnsupportedEncodingException e) {
			hClient.getConnectionManager().shutdown();
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			hClient.getConnectionManager().shutdown();
			e.printStackTrace();
		} catch (IOException e) {
			hClient.getConnectionManager().shutdown();
			e.printStackTrace();
		}

		return result;

	}

	static InputStream is;
	static JSONObject jObj = null;
	static String json = "";

	public static String getJSONFromUrl(String url, List<NameValuePair> params) {

		
		String jsonString = null;

		try {

			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(url);

			List<NameValuePair> pairs = new ArrayList<NameValuePair>();
			post.setEntity(new UrlEncodedFormEntity(pairs));
			HttpResponse response = client.execute(post);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				String re = EntityUtils.toString(entity, HTTP.UTF_8);
				jsonString = re.trim();
			}

		} catch (Exception e) {
			Log.e("", ""+e);
		}
		return jsonString;
		
		// Making HTTP request
		/*try {
			// defaultHttpClient
			System.out.println("params"+params+url);
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);
			httpPost.setEntity(new UrlEncodedFormEntity(params));

			HttpResponse httpResponse = httpClient.execute(httpPost);
			 
			HttpEntity httpEntity = httpResponse.getEntity();
		
			is = httpEntity.getContent();

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "n");
			}
			is.close();
			json = sb.toString();
		} catch (Exception e) {
		}*/

		// try parse the string to a JSON object
//		try {
//			jObj = new JSONObject(json);
//		} catch (JSONException e) {
//		}

		// return JSON String
//		return json;

	}

	private static String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
		return sb.toString();
	}

	public static String getJSONString(String url) {
		String jsonString = null;
		System.out.println("url is" + url);
		try {
			HttpClient Client = new DefaultHttpClient(); 

			HttpGet httpget = new HttpGet(url.replace(" ", "%20"));
//			httpget.setHeader("Content-Type", "text/json; charset=utf-8");
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			jsonString = Client.execute(httpget, responseHandler);
 
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("response" + jsonString);
				/*try {

			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(url);

			List<NameValuePair> pairs = new ArrayList<NameValuePair>();
			post.setEntity(new UrlEncodedFormEntity(pairs));
			HttpResponse response = client.execute(post);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				String re = EntityUtils.toString(entity, HTTP.UTF_8);

				jsonString = re.trim();
			}

		}*/
		return jsonString;
	}

}
