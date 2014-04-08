package com.inez.bettertwitter.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;

import com.inez.bettertwitter.R;
import com.inez.bettertwitter.fragments.HomeTimelineFragment;
import com.inez.bettertwitter.fragments.MentionsTimelineFragment;

public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ViewPager vp_pager = (ViewPager) findViewById(R.id.vp_pager);
		MainPagerAdapter mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
		vp_pager.setAdapter(mainPagerAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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
