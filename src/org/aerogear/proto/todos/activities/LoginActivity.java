package org.aerogear.proto.todos.activities;

import java.io.UnsupportedEncodingException;

import org.aerogear.android.Callback;
import org.aerogear.android.core.HeaderAndBody;
import org.aerogear.android.core.HttpException;
import org.aerogear.proto.todos.R;
import org.aerogear.proto.todos.ToDoApplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_login, menu);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		if (item.getItemId() == R.id.menu_register) {
			startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
			return true;
		}
		return super.onMenuItemSelected(featureId, item);
	}
	
	public void login(View loginButtonButton) {
		String username = text(R.id.username_field);
		String password = text(R.id.password_field);
		ToDoApplication app = (ToDoApplication)getApplication();
		app.login(username, password, new Callback<HeaderAndBody>() {

			@Override
			public void onSuccess(HeaderAndBody data) {
				startActivity(new Intent(getApplicationContext(), MainActivity.class));
			}

			@Override
			public void onFailure(Exception e) {
				try {
					if (e instanceof HttpException) {
						HttpException httpException = (HttpException) e;
						switch (httpException.getStatusCode()) {
						case 401:
							Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_LONG).show();
							break;

						default:
							throw new RuntimeException(new String(((HttpException) e)
									.getData(), "UTF-8"), e);
						}
					} else {
						throw new RuntimeException(e);
					}
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

	private String text(int field_id) {
		EditText field = (EditText)findViewById(field_id);
		return field.getText().toString();
	}

}
