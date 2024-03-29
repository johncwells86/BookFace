package eng.utah.edu.bookface;

import java.util.concurrent.ExecutionException;

import org.json.JSONException;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	public static final String PREFERENCES = "BookFacePrefs";
	public Button loginButton;
	public EditText cadeLoginTextbox;
	public EditText magicNumberTextbox;
	public CheckBox rememberUserName;
	public CheckBox rememberPass;
	
	protected SharedPreferences sp;
	protected SharedPreferences.Editor editor;
	private Gson gson = new Gson();
	private JsonParser jp = new JsonParser();

	// SharedPreferences prefs = getSharedPreferences(PREFERENCES, 0);
	// SharedPreferences.Editor editor = prefs.edit();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sp = this.getSharedPreferences(PREFERENCES, Activity.MODE_PRIVATE);
		editor = sp.edit();
		String uName = sp.getString("user", null);
		String pass= sp.getString("pass", null);
		
		loginButton = (Button) findViewById(R.id.login_button);
		cadeLoginTextbox = (EditText) findViewById(R.id.cade_login_textbox);
		magicNumberTextbox = (EditText) findViewById(R.id.magic_number_textbox);
		rememberUserName = (CheckBox) findViewById(R.id.remember_username);
		rememberPass = (CheckBox) findViewById(R.id.remember_pw);
		
		if(uName != null){
		cadeLoginTextbox.setText(uName);
		rememberUserName.setChecked(true);
		}
		if(pass != null){
		magicNumberTextbox.setText(pass);
		rememberPass.setChecked(true);
		}
		loginButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				
				if ((cadeLoginTextbox.getText() != null)
						&& (magicNumberTextbox.getText() != null)) {
					Login();
				}
			}
		});
	}

	@Override
	public void onPause(){
		super.onPause();
		if(rememberPass.isChecked()){
			Variables.setRememberPass(true);
			editor.putString("pass", magicNumberTextbox.getText().toString());
		}
		else{
			Variables.setRememberPass(false);
			editor.remove("pass");
		}
		if(rememberUserName.isChecked()){
			Variables.setRememberUser(true);
			editor.putString("user", cadeLoginTextbox.getText().toString());
		}
		else{
			Variables.setRememberUser(false);
			editor.remove("user");
		}
		editor.apply();
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public boolean Login() {
		Resources res = getResources();
		try {
			Students login;
			String cade = cadeLoginTextbox.getText().toString().trim();
			int magic = Integer.parseInt(magicNumberTextbox.getText()
					.toString().trim());
			String url = String.format(res
					.getString(R.string.url_students_login));
			String query = String.format("CadeLogin=%s&MagicNumber=%d", cade,
					magic);
			InternetTaskHandler it = (InternetTaskHandler) new InternetTaskHandler()
					.execute(url, "POST", query);

			String result = it.get();
			if (result != null) {

				login = gson.fromJson(jp.parse(result), Students.class);

				if (login.Success) {
					Variables.setCadeLogin(cade);
					Variables.setMagicNumber(magic);
					findStudentID(cade); // Store student ID for later use.
					
					Intent i = new Intent(this, FeedActivity.class);
					startActivity(i);
				} else if (login.Message != null) {
					Toast.makeText(this, login.Message, Toast.LENGTH_LONG)
							.show();
				}
			}

		} catch (JsonParseException e) {
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
			return false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void findStudentID(String CadeLogin) {
		Students[] students = null;
		
		String url = String.format(getResources()
				.getString(R.string.url_students_list));
		InternetTaskHandler it = (InternetTaskHandler) new InternetTaskHandler()
				.execute(url, "GET", "");

		String result = null;
		try {
			result = it.get();
			students = gson.fromJson(jp.parse(result), Students[].class);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		if (students != null) {
			for(Students s : students){
				if(s.CadeLogin.contains(CadeLogin)){
					Variables.setStudentID(s.StudentID);
					String[] n = s.Name.split(" ");
					if(n.length > 1)
					Variables.setFullName(n[0], n[1]);
				}
			}
		}
	}
}
