package com.pack.mydemo.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class AlertDialog extends Dialog implements OnClickListener{
	
	private TextView txtTitle,txtMessage;
	private Button btnYes,btnNo;
	private String title,message,positiveBtnText,negativeBtnText;
/*	private Context context;*/

	public AlertDialog(Context context,String title,String message,String positiveBtnText,String negativeBtnText) {
		super(context);
/*		this.context = context;*/
		this.title = title;
		this.message = message;
		this.positiveBtnText = positiveBtnText;
		this.negativeBtnText = negativeBtnText;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.alert_dailog);
		
		txtTitle = (TextView) findViewById(R.id.alert_title);
		txtMessage = (TextView) findViewById(R.id.alert_message);
		btnYes = (Button) findViewById(R.id.btn_yes);
		btnNo = (Button) findViewById(R.id.btn_no);
		
		txtMessage.setMovementMethod(new ScrollingMovementMethod());
		
		txtTitle.setText(title);
		txtMessage.setText(message);
		btnYes.setText(positiveBtnText);
		btnNo.setText(negativeBtnText);
		
		btnYes.setOnClickListener(this);
		btnNo.setOnClickListener(this);
		
		getWindow().getAttributes().windowAnimations = R.style.MessageDialogAnimation;

	}
	
	@Override
	public void show() {
		super.show();
	}

	@Override
	public void dismiss() {
		super.dismiss();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_yes:
			
			break;
		case R.id.btn_no:
			dismiss();
			break;

		default:
			break;
		}
		
	}


}
