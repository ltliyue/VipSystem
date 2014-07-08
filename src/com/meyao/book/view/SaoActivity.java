package com.meyao.book.view;

import org.json.JSONException;
import org.json.JSONObject;

import com.meyao.book.R;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SaoActivity extends Activity {
    /** Called when the activity is first created. */
	private EditText bookcode;
	private TextView bookname;
	private TextView code;
	private TextView bookprice;
	private Button serch;
	private Button reset;
	private Button saomiao;
	
	public TextView name2;
	
//	UserManager2 userManager = new UserManager2();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sao);
        
        bookcode = (EditText)findViewById(R.id.bookcode);
        bookname=(TextView)findViewById(R.id.bookname);
        code=(TextView)findViewById(R.id.code);
        bookprice=(TextView)findViewById(R.id.bookprice);
        serch = (Button)findViewById(R.id.serch);
        reset = (Button)findViewById(R.id.reset);
        saomiao = (Button)findViewById(R.id.saomiao);
                
//        saomiao.setOnClickListener(scan);
        
        serch.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
//				String resp = userManager.isRightUser(bookcode.getText().toString());
//				try {
//					JSONObject data1 = new JSONObject(resp);
//					
//					//JSONObject User = data1.getJSONObject("User");					    					
//					//JSONObject trade = trade_fullinfo_get_response.getJSONObject("User");
//					String barCode = data1.getString("barCode");
//					String dtlDscntPrice = data1.getString("dtlDscntPrice");
//					String name = data1.getString("name");
//				if(barCode != null){
//					//Toast.makeText(MainActivity.this, "登陆成功", Toast.LENGTH_LONG).show();
//					bookname.setText(name);	
//					code.setText(barCode);
//					bookprice.setText(dtlDscntPrice +" 元");
//				}else{
//					Toast.makeText(SaoActivity.this, "未知错误", Toast.LENGTH_LONG).show();
//					
//				}
//				
//				} catch (JSONException e) {
//					e.printStackTrace();
//				}
			}
		});
        reset.setOnClickListener(new OnClickListener() {
    		
    		public void onClick(View v) {
    			// TODO Auto-generated method stub
    			bookcode.setText("");
    		}
    	});

}
////	@Override
////	public View getView(int position, View convertView, ViewGroup parent) {
////		View satusView = null;
////		if (convertView != null) {
////			// 获取原来内存中保存的条目信息
////			satusView = convertView;
////		} else {
////			satusView = LayoutInflater.from(getApplication()).inflate(
////					R.layout.index_item, null);
////		}
////	}
//    
//    private Button.OnClickListener scan=new Button.OnClickListener()
//	{
//		@Override
//		public void onClick(View v)
//		{
//			Intent intent=new Intent();
//			intent.setClass(SaoActivity.this, CaptureActivity.class);
//			startActivityForResult(intent, 0);
//		}
//	};
//	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent)
	{
		if(requestCode==0)
		{
			if (resultCode==RESULT_OK)
			{
				String num=intent.getStringExtra("RESULT");
				bookcode.setText(num);
			}
			else if(resultCode==RESULT_CANCELED) {}
		}
		else
		{
			return;
		}
	}

}