package com.inez.bettertwitter.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.inez.bettertwitter.Helpers;
import com.inez.bettertwitter.Ids;
import com.inez.bettertwitter.R;
import com.inez.bettertwitter.fragments.TimelineFragment;
import com.inez.bettertwitter.models.Tweet;
import com.inez.bettertwitter.models.User;
import com.nostra13.universalimageloader.core.ImageLoader;

public class TweetDetailsActivity extends Activity {

	private Tweet tweet;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tweet_details);

		Intent i = getIntent();
		tweet = (Tweet) i.getSerializableExtra(Ids.TWEET_KEY);
		User user = tweet.getUser();

		ImageView iv_profile = (ImageView) findViewById(R.id.iv_profile);
		ImageLoader.getInstance().displayImage(user.getProfileImageUrl(), iv_profile);

		TextView tv_name = (TextView) findViewById(R.id.tv_name);
		tv_name.setText(user.getName());

		TextView tv_screen_name = (TextView) findViewById(R.id.tv_screen_name);
		tv_screen_name.setText(Html.fromHtml("<font color='#777777'>@" + user.getScreenName() + "</font>"));

		TextView tv_body = (TextView) findViewById(R.id.tv_body);
		tv_body.setText(Html.fromHtml(tweet.getBody()));

		TextView tv_time = (TextView) findViewById(R.id.tv_time);
		tv_time.setText(Html.fromHtml("<small><font color='#777777'> --- " + tweet.getCreatedAt() + "</font></small>"));

		ImageView iv_medium = (ImageView) findViewById(R.id.iv_medium);
		if(tweet.getMediaUrls().size() > 0) {
			ImageLoader.getInstance().displayImage(tweet.getMediaUrls().get(0), iv_medium);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tweet_details, menu);
		return true;
	}
	
	public void onReplyClick(View v) {
		Intent i = new Intent(this, ComposeActivity.class);
		i.putExtra(Ids.TWEET_KEY, tweet);
		startActivityForResult(i, Ids.COMPOSE_REQUEST);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == Ids.COMPOSE_REQUEST) {
			if (resultCode == RESULT_OK) {
				Tweet tweet = (Tweet) data.getSerializableExtra(Ids.TWEET_KEY);
				Intent i = new Intent(this, MainActivity.class);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivity(i);
			}
		}
	}

}
