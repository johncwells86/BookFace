package eng.utah.edu.bookface;

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

				Boolean username = false;
				Boolean pass = false;
				Students s = new Students(true, "Hi");
				if (cadeLoginTextbox.getText() != null) {
					username = true;
				}
				if (magicNumberTextbox.getText() != null) {
					pass = true;
				}

				if (username && pass) {

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
			String cade = cadeLoginTextbox.getText().toString();
			int magic = Integer.parseInt(magicNumberTextbox.getText()
					.toString());
			String url = String.format("%s?%s%d",res.getString(R.string.url_students_login), cade, magic);
			InternetTaskHandler it = (InternetTaskHandler) new InternetTaskHandler().execute(url);
			
		} catch (Exception e) {
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
			return false;
		}
		return false;
	}
}

