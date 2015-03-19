package com.pack.mydemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomTextView extends TextView{
	
	
	public CustomTextView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}	

	public CustomTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}


	public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setText(CharSequence text, BufferType type) {
		super.setText(text, type);
		
	}


}
