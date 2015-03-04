package com.pack.mydemo.ui;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.widget.TextView;

public class TextViewCollectionActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_text_view_collection);
		
		TextView view = (TextView)findViewById(R.id.textView1);
		String formattedText = getString(R.string.text_collection);
		Spanned result = Html.fromHtml(formattedText);
		view.setText(result);
		
		for (String detail : getDetailArray(1, 0, 0)) {
			Log.d("detail", detail);
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
