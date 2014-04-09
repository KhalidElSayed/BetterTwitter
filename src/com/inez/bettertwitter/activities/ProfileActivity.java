package com.inez.bettertwitter.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.inez.bettertwitter.Ids;
import com.inez.bettertwitter.R;
import com.inez.bettertwitter.fragments.UserTimelineFragment;
import com.inez.bettertwitter.models.User;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ProfileActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);

		Intent i = getIntent();
		User user = (User) i.getSerializableExtra(Ids.USER_KEY);

		ImageView iv_profile = (ImageView) findViewById(R.id.iv_profile);
		ImageLoader.getInstance().displayImage(user.getProfileImageUrl(), iv_profile);

		TextView tv_name = (TextView) findViewById(R.id.tv_name);
		tv_name.setText(user.getName());

		TextView tv_screen_name = (TextView) findViewById(R.id.tv_screen_name);
		tv_screen_name.setText("@" + user.getScreenName());

		TextView tv_description = (TextView) findViewById(R.id.tv_description);
		tv_description.setText(user.getDescription());

		UserTimelineFragment userTimelineFragment = UserTimelineFragment.newInstance(user.getRemoteId());
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.replace(R.id.flContainer, userTimelineFragment);
		ft.commit();

	    TextView tv_tweets = (TextView) findViewById(R.id.tv_tweets);
	    tv_tweets.setText(Html.fromHtml("TWEETS<br/><b>" + user.getNumTweets() + "</b>"));

	    TextView tv_following = (TextView) findViewById(R.id.tv_following);
	    tv_following.setText(Html.fromHtml("FOLLOWING<br/><b>" + user.getFriendsCount() + "</b>"));

	    TextView tv_followers = (TextView) findViewById(R.id.tv_followers);
	    tv_followers.setText(Html.fromHtml("FOLLOWERS<br/><b>" + user.getFriendsCount() + "</b>"));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
		return true;
	}

}
