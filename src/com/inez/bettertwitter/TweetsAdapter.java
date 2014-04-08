package com.inez.bettertwitter;

import java.util.List;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.inez.bettertwitter.models.Tweet;
import com.nostra13.universalimageloader.core.ImageLoader;

public class TweetsAdapter extends ArrayAdapter<Tweet> {

	public TweetsAdapter(Context context, List<Tweet> tweets) {
		super(context, 0, tweets);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	    View view = convertView;
	    if (view == null) {
	    	view = LayoutInflater.from(getContext()).inflate(R.layout.tweet, null);
	    }
	    Tweet tweet = getItem(position);
	    
        ImageView iv_profile = (ImageView) view.findViewById(R.id.iv_profile);
        ImageLoader.getInstance().displayImage(tweet.getUser().getProfileImageUrl(), iv_profile);

	    TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
	    tv_name.setText(Html.fromHtml("<b>" + tweet.getUser().getName() + "</b>" + " <small><font color='#777777'>@" + tweet.getUser().getScreenName() + "</font></small>"));
	    
	    TextView tv_body = (TextView) view.findViewById(R.id.tv_body);
	    tv_body.setText(Html.fromHtml(tweet.getBody()));
	    
	    TextView tv_time = (TextView) view.findViewById(R.id.tv_time);
	    tv_time.setText(Html.fromHtml("<font color='#777777'>" + Helpers.getRelativeTime(tweet.getCreatedAt()) + "</font>"));
	    
        return view;
	}
}
