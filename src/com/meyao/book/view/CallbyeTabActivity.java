package com.meyao.book.view;

import java.util.ArrayList;
import java.util.List;

import com.meyao.book.R;
import com.meyao.book.db.DBHelpe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class CallbyeTabActivity extends MyTabActivity {
	public static TextView textview;
	
	private  DBHelpe  helpter;
	
    public CallbyeTabActivity() {
		
		super(R.layout.tab,R.drawable.detail_tab_split );
		
	}
    @Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		CallbyeTabActivity.textview=(TextView)findViewById(R.id.count_textview);
		  helpter = new DBHelpe(this);  
		int i=Bookview.number;
		if(i==0){
			CallbyeTabActivity.textview.setVisibility(View.GONE);
		}
		else{
			CallbyeTabActivity.textview.setVisibility(View.VISIBLE);
			CallbyeTabActivity.textview.setText(""+i);
		}
	
	}

	public List<MyTab> getMyTabList() {
		List<MyTab> myTabList = new ArrayList<MyTab>();
		myTabList.add(new MyTab(R.drawable.guide_home_nm,getString(R.string.tuijian),
				Tab1_tuijian2.class));
		myTabList.add(new MyTab(R.drawable.guide_search_nm,getString(R.string.fenlei),
				Tab2_fenlei2.class));
		myTabList.add(new MyTab(R.drawable.guide_cart_nm,getString(R.string.gouwuche),
				Tab3_gouwudan.class));
		myTabList.add(new MyTab(R.drawable.guide_tfaccount_nm,getString(R.string.gengduo),
				Tab4_gengduo2.class));

		return myTabList;
	}
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		menu.add(0, 0, 0, "退出");
		
		return true;
	}
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 0:
			dialog();
			
			return true;
		
		default:
			return false;
		}

	}

	public void dialog()
	  {  
		  final AlertDialog.Builder dialog = new AlertDialog.Builder(this);  
	     
		  dialog.setTitle("提示").setMessage("真的要退出吗？").setPositiveButton("确定",  
	                  new DialogInterface.OnClickListener() {  
	                      public void onClick(DialogInterface dialog,  
	                              int which) { 
                    	  helpter.clean();
                    	  exitSystem();
                      	  CallbyeTabActivity.this.finish();
                      	  
	                                           }  
	                 }).setNegativeButton("取消",  
	                  new DialogInterface.OnClickListener() {  
	                     public void onClick(DialogInterface dialog,  
	                             int which) {  
	                           }  
	                  });  
	          AlertDialog dialog_dialog = dialog.create();  
	          dialog_dialog.show();  
	                    
    }
	public void exitSystem() {
		Intent startMain = new Intent(Intent.ACTION_MAIN);
		startMain.addCategory(Intent.CATEGORY_HOME);
		startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(startMain);
		System.exit(0);
}
}