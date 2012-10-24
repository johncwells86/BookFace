package eng.utah.edu.bookface;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import android.os.AsyncTask;

public class InternetTaskHandler extends AsyncTask<String, Integer, String> {
	Gson gson = new Gson();
	
	protected String doInBackground(String... params) {
		try{
		URL url = new URL(params[0]);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		InputStream is = conn.getInputStream();
		String s = new Scanner(is).useDelimiter("\\A").next();
		String json = gson.toJson(s);

		return json;
		}
		catch(Exception e){
			return "An error occurred, please try again later";
		}
	}
	
	protected void onProgressUpdate(Integer progress){
		
	}
	
	protected void onPostExecute(String result){
		
	}
}
