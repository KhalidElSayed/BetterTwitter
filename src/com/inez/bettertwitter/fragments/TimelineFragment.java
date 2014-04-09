package com.inez.bettertwitter.fragments;

import java.util.ArrayList;

import org.json.JSONArray;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.inez.bettertwitter.BetterTwitterClientApp;
import com.inez.bettertwitter.EndlessScrollListener;
import com.inez.bettertwitter.FetchTimelineHandler;
import com.inez.bettertwitter.R;
import com.inez.bettertwitter.TweetsAdapter;
import com.inez.bettertwitter.TwitterClient;
import com.inez.bettertwitter.models.Tweet;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

public abstract class TimelineFragment extends Fragment {

	protected TwitterClient client;
	private ArrayList<Tweet> tweets;
	private ListView lv_tweets;
	private TweetsAdapter adapter;

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_timeline, container, false);
        
        client = BetterTwitterClientApp.getRestClient();
        tweets = new ArrayList<Tweet>();
        lv_tweets = (ListView) view.findViewById(R.id.lv_tweets);
        adapter = new TweetsAdapter(getActivity(), tweets);
		lv_tweets.setAdapter(adapter);
		
		lv_tweets.setOnScrollListener(new EndlessScrollListener() {

			@Override
			public void onLoadMore(int page, int totalItemsCount) {
				fetchTimelineAsync(adapter.getItem(totalItemsCount - 1).getRemoteId() - 1, new FetchTimelineHandler() {
					@Override
					public void onFetched(ArrayList<Tweet> tweets) {
						adapter.addAll(tweets);
					}
				});
			}
			
		});
		
		fetchTimelineAsync(0, new FetchTimelineHandler() {
			@Override
			public void onFetched(ArrayList<Tweet> tweets) {
				adapter.addAll(tweets);
			}
		});
		
		return view;
    }
	
	protected abstract void getTimeline(long max_id, AsyncHttpResponseHandler handler);
	
	private void fetchTimelineAsync(long max_id, final FetchTimelineHandler handler) {
		getTimeline(max_id, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONArray jsonTweets) {
				handler.onFetched(Tweet.fromJson(jsonTweets));
			}
		});		
	}
	
	public TweetsAdapter getAdapter() {
		return adapter;
	}

}
