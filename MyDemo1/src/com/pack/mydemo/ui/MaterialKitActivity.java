package com.pack.mydemo.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MaterialKitActivity extends ActionBarActivity implements OnClickListener {

	private Button btnShowDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_material_kit);
		
		btnShowDialog = (Button)findViewById(R.id.btn_show_dialog);
		btnShowDialog.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_show_dialog :
			new AlertDialog(this, "Message", 
					"It is a long established fact that a reader will be distracted by the readable.", "Yes", "No").show();
			break;

		default:
			break;
		}
		
	}
	

}
