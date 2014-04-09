package com.inez.bettertwitter.activities;

import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.inez.bettertwitter.BetterTwitterClientApp;
import com.inez.bettertwitter.Helpers;
import com.inez.bettertwitter.Ids;
import com.inez.bettertwitter.R;
import com.inez.bettertwitter.TwitterClient;
import com.inez.bettertwitter.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

public class ComposeActivity extends Activity {

	private MenuItem mi_tweet;
	private MenuItem mi_remaining;
	private EditText et_tweet;
	private AsyncCalculating runner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compose);

		et_tweet = (EditText) findViewById(R.id.et_tweet);
		et_tweet.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				String tweet = et_tweet.getText().toString();
				if (mi_tweet != null) {
					if (tweet.length() > 0) {
						mi_tweet.setEnabled(true);
					} else {
						mi_tweet.setEnabled(false);
					}
				}
				if (mi_remaining != null) {
					if ( runner != null ) {
						runner.cancel(true);
					}
					runner = new AsyncCalculating();
				    runner.execute();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.compose, menu);

		mi_tweet = (MenuItem) menu.findItem(R.id.mi_tweet);
		mi_remaining = (MenuItem) menu.findItem(R.id.mi_remaining);

		//mi_remaining.setTitle();

		return true;
	}
	
	public void onTweetClick(MenuItem menuItem) {
		final ProgressDialog progressDialog = ProgressDialog.show(this, "", "Please wait...", true);
		TwitterClient client = BetterTwitterClientApp.getRestClient();
		client.postUpdate(et_tweet.getText().toString(), 0, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject jsonTweet) {
				progressDialog.dismiss();

				Tweet tweet = Tweet.fromJson(jsonTweet);
				Intent intent = new Intent();
				intent.putExtra(Ids.TWEET_KEY, tweet);
				setResult(RESULT_OK, intent);
				finish();
			}
		});
	}

	private class AsyncCalculating extends AsyncTask<Void, String, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			try {
				Thread.sleep(400);
				for ( int i = 0; i < (1+(int)(Math.random()*3)); i++ ) {
					publishProgress("Calculating" + Helpers.repeat(".", i + 1));
					Thread.sleep(150);
				}
			} catch (Exception e) {
			}
			return null;
		}

		@Override
		protected void onProgressUpdate(String... text) {
			mi_remaining.setTitle(text[0]);
		}

		@Override
		protected void onPostExecute(Void result) {
			mi_remaining.setTitle(String.valueOf(140 - et_tweet.getText().toString().length()));
		}
	}

}