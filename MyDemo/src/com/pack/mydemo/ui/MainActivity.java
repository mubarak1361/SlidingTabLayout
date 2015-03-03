package com.pack.mydemo.ui;

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
        mViewPager.setAdapter(new SamplePagerAdapter());
        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setCustomTabView(R.layout.custom_tab, R.id.customText);
        mSlidingTabLayout.setViewPager(mViewPager);
       
        mSlidingTabLayout.setBackgroundColor(Color.parseColor("#424242"));
        mSlidingTabLayout.setSelectedIndicatorColors(Color.parseColor("#4caf50"));
        mSlidingTabLayout.setDividerColors(Color.parseColor("#424242"));
        
    }
    
    class SamplePagerAdapter extends PagerAdapter {
    	
    	private String[] tabName = {"Home","Bookmarks", "Favourites"};

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 3;
		}
		
		  @Override
	        public CharSequence getPageTitle(int position) {
	            return tabName[position];
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
            title.setText(String.valueOf(position + 1));
			
			return view;
		}
		
		 @Override
	        public void destroyItem(ViewGroup container, int position, Object object) {
	            container.removeView((View) object);
	            Log.i("Item", "destroyItem() [position: " + position + "]");
	        }

    	
    }
}