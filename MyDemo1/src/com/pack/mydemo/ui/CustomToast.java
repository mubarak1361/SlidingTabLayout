package com.pack.mydemo.ui;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class CustomToast extends Toast{

	public CustomToast(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setGravity(int gravity, int xOffset, int yOffset) {
		// TODO Auto-generated method stub
		super.setGravity(Gravity.CENTER, xOffset, yOffset);
	}
	
	

}
