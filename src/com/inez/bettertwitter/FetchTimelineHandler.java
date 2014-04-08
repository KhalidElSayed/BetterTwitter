package com.inez.bettertwitter;

import java.util.ArrayList;

import com.inez.bettertwitter.models.Tweet;

public abstract class FetchTimelineHandler {
	public abstract void onFetched(ArrayList<Tweet> tweets);
}
