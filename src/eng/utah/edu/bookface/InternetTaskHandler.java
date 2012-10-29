package eng.utah.edu.bookface;

import java.io.DataOutputStream;
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
	JsonParser jp = new JsonParser();
	URL url;

	protected String doInBackground(String... params) {
		HttpURLConnection conn = null;
		try {
			
			if (params[1].equals("GET")) {
				url = new URL(params[0] + "?" + params[2]);
				conn = (HttpURLConnection) url.openConnection();
				conn.setRequestProperty("Accept-Charset", "utf-8");
				conn.setInstanceFollowRedirects(false);
				conn.setRequestMethod(params[1]);
			}
			else if (params[1].equals("POST")) {
				url = new URL(params[0]);
				conn = (HttpURLConnection) url.openConnection();
				conn.setRequestProperty("Accept-Charset", "utf-8");
				conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
				conn.setRequestMethod(params[1]);
				conn.setDoOutput(true);
				DataOutputStream out = new DataOutputStream(conn.getOutputStream());
				out.writeBytes(params[2]);
				out.flush();
				out.close();
			}
			InputStream is = conn.getInputStream();
			String s = new Scanner(is).useDelimiter("\\A").next();

			return s;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			conn.disconnect();
		}

	}

}
