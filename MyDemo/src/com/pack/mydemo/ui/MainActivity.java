package com.pack.mydemo.ui;

import java.util.ArrayList;
import java.util.Arrays;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pack.mydemo.view.SlidingTabLayout;

public class MainActivity extends ActionBarActivity {

	private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(new SamplePagerAdapter(0,0,0));
        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setCustomTabView(R.layout.custom_tab, R.id.customText);
        mSlidingTabLayout.setViewPager(mViewPager);
       
        mSlidingTabLayout.setBackgroundColor(Color.parseColor("#424242"));
        mSlidingTabLayout.setSelectedIndicatorColors(Color.parseColor("#4caf50"));
        mSlidingTabLayout.setDividerColors(Color.parseColor("#424242"));
        
    }
    
    class SamplePagerAdapter extends PagerAdapter {
    	
    	private ArrayList<String> detailArray;

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
	            return detailArray.get(position);
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
	
}
