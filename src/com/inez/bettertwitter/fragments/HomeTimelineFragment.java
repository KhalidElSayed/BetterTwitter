package com.inez.bettertwitter.fragments;

import com.loopj.android.http.AsyncHttpResponseHandler;


public class HomeTimelineFragment extends TimelineFragment {

	protected void getTimeline(long max_id, AsyncHttpResponseHandler handler) {
		client.getHomeTimeline(max_id, handler);
	}
	
}
