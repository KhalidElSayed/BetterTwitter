package com.inez.bettertwitter.fragments;

import com.loopj.android.http.AsyncHttpResponseHandler;


public class MentionsTimelineFragment extends TimelineFragment {

	protected void getTimeline(long max_id, AsyncHttpResponseHandler handler) {
		client.getMentionsTimeline(max_id, handler);
	}
	
}
