package com.inez.bettertwitter.fragments;

import android.os.Bundle;

import com.loopj.android.http.AsyncHttpResponseHandler;


public class UserTimelineFragment extends TimelineFragment {

	private long user_id;
	
	public static UserTimelineFragment newInstance(long user_id) {
		UserTimelineFragment f = new UserTimelineFragment();
		Bundle b = new Bundle();
		b.putLong("user_id", user_id);
        f.setArguments(b);
		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Bundle args = getArguments();
		user_id = args.getLong("user_id");
	}

	protected void getTimeline(long max_id, AsyncHttpResponseHandler handler) {
		client.getUserTimeline(user_id, max_id, handler);
	}

}
