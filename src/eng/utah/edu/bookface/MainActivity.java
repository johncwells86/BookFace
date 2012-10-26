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
import android.content.res.Resources;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	public GSONTaskHandler gsonHandler = new GSONTaskHandler();
	public Button loginButton;
	public EditText cadeLoginTextbox;
	public EditText magicNumberTextbox;
	public CheckBox rememberUserName;
	public CheckBox rememberPass;

	private Gson gson = new Gson();
	private JsonParser jp = new JsonParser();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		loginButton = (Button) findViewById(R.id.login_button);
		cadeLoginTextbox = (EditText) findViewById(R.id.cade_login_textbox);
		magicNumberTextbox = (EditText) findViewById(R.id.magic_number_textbox);
		rememberUserName = (CheckBox) findViewById(R.id.remember_username);
		rememberPass = (CheckBox) findViewById(R.id.remember_pw);

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
}
