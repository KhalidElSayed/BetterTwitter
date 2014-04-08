package com.inez.bettertwitter.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.codepath.oauth.OAuthLoginActivity;
import com.inez.bettertwitter.R;
import com.inez.bettertwitter.TwitterClient;

public class LoginActivity extends OAuthLoginActivity<TwitterClient> {

	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public void onLoginSuccess() {
		if ( progressDialog != null ) { 
			progressDialog.dismiss();
		}
		Intent i = new Intent(this, MainActivity.class);
    	startActivity(i);
	}

	@Override
	public void onLoginFailure(Exception e) {
		if ( progressDialog != null ) { 
			progressDialog.dismiss();
		}
		e.printStackTrace();
	}

    public void onLoginClick(View view) {
    	progressDialog = ProgressDialog.show(LoginActivity.this, "", getString(R.string.logging_in), true);
        getClient().connect();
    }
}
