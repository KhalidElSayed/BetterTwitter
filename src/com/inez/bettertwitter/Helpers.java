package com.inez.bettertwitter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.ocpsoft.prettytime.PrettyTime;

public class Helpers {

	public static Date parseTwitterUTC(String date) throws ParseException {
		String twitterFormat="EEE MMM dd HH:mm:ss ZZZZZ yyyy";
		SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
		sf.setLenient(true);
		return sf.parse(date);
	}

	public static String getRelativeTime(Date date) {
		String relativeTime = (new PrettyTime()).format(date);
		// TODO: check if culture/language is EN
		relativeTime = relativeTime.replace(" minutes ago", "m");
		relativeTime = relativeTime.replace(" hours ago", "h");
		relativeTime = relativeTime.replace(" days ago", "d");
		relativeTime = relativeTime.replace(" minute ago", "m");
		relativeTime = relativeTime.replace(" hour ago", "h");
		relativeTime = relativeTime.replace(" day ago", "d");
		relativeTime = relativeTime.replace(" week ago", "w");
		return relativeTime;
	}
	
	public static String repeat(String text, int times) {
		String out = "";
		for ( int i = 0; i < times; i++ ) {
			out += text;
		}
		return out;
	}
	
	public static String makeFragmentName(int viewId, int position) {
	     return "android:switcher:" + viewId + ":" + position;
	}

}
