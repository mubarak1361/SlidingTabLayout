package com.pack.mydemo.ui;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils.TruncateAt;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pack.mydemo.view.SlidingTabLayout;

public class MainActivity extends ActionBarActivity {

	private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;
    private Toolbar toolbar;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(new SamplePagerAdapter(1,0,0));
        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setCustomTabView(R.layout.custom_tab, R.id.customText);
        //mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        mSlidingTabLayout.setViewPager(mViewPager);
        
        if (toolbar != null) {
			setSupportActionBar(toolbar);
			getSupportActionBar().setDisplayShowHomeEnabled(true);
			toolbar.setTitle(getResources().getString(R.string.app_name));
			toolbar.setSubtitle(getResources().getString(R.string.sub_title));
			toolbar.setLogo(R.drawable.ic_launcher);
			
				MarqueeActionBarTextView();
				MarqueeActionBarSubTextView();
			
			
        }
        mSlidingTabLayout.setBackgroundColor(Color.parseColor("#424242"));
        mSlidingTabLayout.setSelectedIndicatorColors(Color.parseColor("#4caf50"));
        mSlidingTabLayout.setDividerColors(Color.parseColor("#424242"));
        
        
    }
    
    private void MarqueeActionBarTextView() {
	    TextView titleTextView = null;

	    try {
	        Field f = toolbar.getClass().getDeclaredField("mTitleTextView");
	        f.setAccessible(true);
	        titleTextView = (TextView) f.get(toolbar);
	        
	        titleTextView.setEllipsize(TruncateAt.MARQUEE);
	        titleTextView.setMarqueeRepeatLimit(1);
	        titleTextView.setFocusable(true);
	        titleTextView.setFocusableInTouchMode(true);
	        titleTextView.requestFocus();
	        titleTextView.setSingleLine(true);
	        titleTextView.setSelected(true);
	        titleTextView.setMarqueeRepeatLimit(-1);
	        
	    } catch (NoSuchFieldException e) {
	    } catch (IllegalAccessException e) {
	    }
	}
    
    private void MarqueeActionBarSubTextView() {
	    TextView subTitleTextView = null;

	    try {
	        Field f = toolbar.getClass().getDeclaredField("mSubtitleTextView");
	        f.setAccessible(true);
	        subTitleTextView = (TextView) f.get(toolbar);
	        
	        subTitleTextView.setEllipsize(TruncateAt.MARQUEE);
		    subTitleTextView.setFocusable(true);
		    subTitleTextView.setFocusableInTouchMode(true);
		    subTitleTextView.requestFocus();
		    subTitleTextView.setSingleLine(true);
		    subTitleTextView.setSelected(true);
		    subTitleTextView.setMarqueeRepeatLimit(-1);
		    
	    } catch (NoSuchFieldException e) {
	    } catch (IllegalAccessException e) {
	    }
	   
	}
    
    class SamplePagerAdapter extends PagerAdapter {
    	
    	private ArrayList<String> detailArray;
    	private String[] title = {"One","Two","Three","Four"};

    	public SamplePagerAdapter(int chapterID,int topicID,int subTopicID) {
			detailArray =  getDetailArray(chapterID, topicID, subTopicID);
		}
    	
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return detailArray.size();
		}
		
		  @Override
	        public CharSequence getPageTitle(int position) {
	            return title[position];
	        }

		@Override
		public boolean isViewFromObject(View view, Object o) {
			// TODO Auto-generated method stub
			return o == view;
		}

		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			
			 // Inflate a new layout from our resources
            View view = getLayoutInflater().inflate(R.layout.pager_item,
                    container, false);
            // Add the newly created View to the ViewPager
            container.addView(view);

            // Retrieve a TextView from the inflated View, and update it's text
            TextView title = (TextView) view.findViewById(R.id.item_title);
            title.setText(detailArray.get(position));
			
			return view;
		}
		
		
		 @Override
	        public void destroyItem(ViewGroup container, int position, Object object) {
	            container.removeView((View) object);
	            Log.i("Item", "destroyItem() [position: " + position + "]");
	        }

    	
    }
    
    public ArrayList<String> getDetailArray(int chapterID,int topicID,int subTopicID){
		
		Resources res = getResources();		
		TypedArray chapterArray = res.obtainTypedArray(R.array.chapter);				
		
		int requiredChapterID = 0;
		try {
			requiredChapterID = chapterArray.getResourceId(chapterID, 0);			
		} catch (ArrayIndexOutOfBoundsException e) {
			Log.e("Exception", e.getMessage());
		}		
		Log.d("ChapterID", ""+requiredChapterID);
		
		if(requiredChapterID > 0){		
		TypedArray topicArray = res.obtainTypedArray(requiredChapterID);	
		
		int requiredTopicID =0;
		try {
			requiredTopicID = topicArray.getResourceId(topicID, 0);
		} catch (ArrayIndexOutOfBoundsException e) {
			// TODO: handle exception
		}		
		Log.d("TopicID", ""+requiredTopicID);
		
		if(requiredTopicID > 0){		
		TypedArray subTopicArray = res.obtainTypedArray(requiredTopicID);
		
		int requiredSubTopicID=0;		
		try {
			requiredSubTopicID =  subTopicArray.getResourceId(subTopicID, 0);
		} catch (ArrayIndexOutOfBoundsException e) {
			// TODO: handle exception
		}
		Log.d("SubTopicID", ""+requiredSubTopicID);
		
		if(requiredSubTopicID > 0){		
		return new ArrayList<String>(
				Arrays.asList(res.getStringArray(requiredSubTopicID)));
		}
		else
			return new ArrayList<String>();
		
		}
		else
			return new ArrayList<String>();
		
		}
		else
			return new ArrayList<String>();
	}
	
    
    public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                        (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }
    
    
    public class DepthPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.75f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.setAlpha(1);
                view.setTranslationX(0);
                view.setScaleX(1);
                view.setScaleY(1);

            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.setAlpha(1 - position);

                // Counteract the default slide transition
                view.setTranslationX(pageWidth * -position);

                // Scale the page down (between MIN_SCALE and 1)
                float scaleFactor = MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    
}
