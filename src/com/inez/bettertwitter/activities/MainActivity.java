package com.inez.bettertwitter.activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.inez.bettertwitter.R;
import com.inez.bettertwitter.fragments.HomeTimelineFragment;
import com.inez.bettertwitter.fragments.MentionsTimelineFragment;
import com.viewpagerindicator.UnderlinePageIndicator;

public class MainActivity extends FragmentActivity {

	private ViewPager vp_pager;
	private TextView tv_home_timeline;
	private TextView tv_mentions_timeline;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tv_home_timeline = (TextView) findViewById(R.id.tv_home_timeline);
		tv_mentions_timeline = (TextView) findViewById(R.id.tv_mentions_timeline);
		vp_pager = (ViewPager) findViewById(R.id.vp_pager);
		MainPagerAdapter mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
		vp_pager.setAdapter(mainPagerAdapter);
		
		UnderlinePageIndicator underlinePageIndicator = (UnderlinePageIndicator) findViewById(R.id.indicator);
		underlinePageIndicator.setViewPager(vp_pager);
		underlinePageIndicator.setFades(false);
		underlinePageIndicator.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				if ( position == 0 ) {
					tv_home_timeline.setTypeface(null, Typeface.BOLD);
					tv_mentions_timeline.setTypeface(null, Typeface.NORMAL);
				} else if ( position == 1 ) {
					tv_mentions_timeline.setTypeface(null, Typeface.BOLD);
					tv_home_timeline.setTypeface(null, Typeface.NORMAL);
				}
				
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onHomeClick(View v) {
		vp_pager.setCurrentItem(0);
	}
	
	public void onMentionsClick(View v) {
		vp_pager.setCurrentItem(1);
	}

	public static class MainPagerAdapter extends FragmentPagerAdapter {
		
		 private static int NUM_ITEMS = 2;

		public MainPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			if ( position == 0 ) {
				return new HomeTimelineFragment();
			} else if ( position == 1 ) {
				return new MentionsTimelineFragment();
			} else {
				return null;
			}
		}

		@Override
		public int getCount() {
			return NUM_ITEMS;
		}

	}

}
