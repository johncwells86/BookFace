package eng.utah.edu.bookface;

import java.io.InputStream;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;


public class BookfaceWebInterface {
	
	private final String urlRoot = "http://android.eng.utah.edu";
	DefaultHttpClient client = new DefaultHttpClient();	
	
	// Constructor
	public BookfaceWebInterface(){
		
	}
	
	// Possible cache of Feed, to avoid constant pinging.
	public void cacheRequest(){
		
	}
	
	// Executes a get request from Bookface, given a gson string to work with. Returns the web content, if possible.
	public InputStream executeRequest(Gson gson){
		
		return null;
	}
	
	
}
