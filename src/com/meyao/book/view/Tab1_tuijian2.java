package com.meyao.book.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.meyao.book.R;

public class Tab1_tuijian2 extends Activity implements android.view.View.OnClickListener{

//	private Button top_btn1, top_btn2, top_btn3, top_btn4;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_a11);

		intview();

	}

	private void intview() {
		// TODO 自动生成的方法存根
		ImageButton top_btn1 = (ImageButton) findViewById(R.id.top_btn1);
		top_btn1.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO 自动生成的方法存根
		
		switch (v.getId()) {
		case R.id.top_btn1:
			Intent intent = new Intent();
			intent.setClass(Tab1_tuijian2.this, Tab2_fenlei2.class);
			startActivity(intent);
			
			break;

		default:
			break;
		}
	}
}
