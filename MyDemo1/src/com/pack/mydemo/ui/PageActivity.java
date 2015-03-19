package com.pack.mydemo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class PageActivity extends Activity {

	private ViewPager mViewPager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_page);
		
		 mViewPager = (ViewPager) findViewById(R.id.viewpager);
		 mViewPager.setAdapter(new SamplePagerAdapter());
	}
	
	 class SamplePagerAdapter extends PagerAdapter {

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return 3;
			}
		

			@Override
			public boolean isViewFromObject(View view, Object o) {
				// TODO Auto-generated method stub
				return o == view;
			}
			
			
			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				
				 // Inflate a new layout from our resources
	            View view = getLayoutInflater().inflate(R.layout.page_view,
	                    container, false);
	            // Add the newly created View to the ViewPager
	            container.addView(view);

	            // Retrieve a TextView from the inflated View, and update it's text
	            TextView description = (TextView) view.findViewById(R.id.description);
	            //TextView reference = (TextView) view.findViewById(R.id.reference);
	            description.setText(String.valueOf(position + 1));
				
				return view;
			}
			
			 @Override
		        public void destroyItem(ViewGroup container, int position, Object object) {
		            container.removeView((View) object);
		            Log.i("Item", "destroyItem() [position: " + position + "]");
		        }

	    	
	    }
}
