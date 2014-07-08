package com.meyao.book.view;

import com.meyao.book.R;
import com.meyao.book.Zxing.CaptureActivity;
import com.meyao.book.util.Util;
import com.meyao.book.wheelview.ScreenInfo;
import com.umeng.fb.NotificationType;
import com.umeng.fb.UMFeedbackService;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class Tab4_gengduo2 extends Activity{
	
	/** 提醒设置 */
	private LinearLayout more_remind;
	private LinearLayout chazhao;
	private LinearLayout saosao;
	/*** 通知栏常驻 */
	private ToggleButton tb_notification;
	/** 密码 */
	private ToggleButton tb_password;
	
	private int isEnter;
	
	private TextView ip;
	private EditText newip,tiaoma;
//	private Button xiugai, chazhao, saosao;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_a44);
		intview();
		SharedPreferences sp = getSharedPreferences("url",MODE_WORLD_READABLE);
		ip.setText(sp.getString("url_address", ""));
		
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		isKaiqiPassword();
	}
	private void intview() {
		// TODO 自动生成的方法存根
		more_remind = (LinearLayout) this.findViewById(R.id.more_remind);
		more_remind.setOnClickListener(onClickListener);
		
		chazhao = (LinearLayout) this.findViewById(R.id.chazhao);
		chazhao.setOnClickListener(onClickListener);
		
		saosao = (LinearLayout) this.findViewById(R.id.saosao);
		saosao.setOnClickListener(scan);
		
		tb_password = (ToggleButton) this.findViewById(R.id.moreset_uselogcode);
		tb_password.setOnCheckedChangeListener(onCheckedChangeListener);
		tb_password.setOnClickListener(onClickListener);
		
		tb_notification = (ToggleButton) this.findViewById(R.id.moreset_notification);
		tb_notification.setOnCheckedChangeListener(onCheckedChangeListener);
		
		ip=(TextView)findViewById(R.id.ip);
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode==KeyEvent.KEYCODE_BACK){
			return true;
		}// TODO Auto-generated method stub
			return false;
		}
	
	OnCheckedChangeListener onCheckedChangeListener = new OnCheckedChangeListener() {

		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {

			int id = buttonView.getId();
			if (id == R.id.moreset_notification) {
				if (isChecked) {
					setNotification();
				} else {
					cancelNotification();
				}
				} else if (id == R.id.moreset_uselogcode) {
					
					if ((isEnter == -1) && isChecked) {
						Intent code = new Intent(Tab4_gengduo2.this, CodeNum.class);
						code.putExtra("kaiguan", 0);
						Tab4_gengduo2.this.startActivity(code);
					} else if ((isEnter == 1) && (isChecked == false)) {
						Intent code = new Intent(Tab4_gengduo2.this, CodeNum.class);
						code.putExtra("kaiguan", 1);
						Tab4_gengduo2.this.startActivity(code);
					}
				}
		}
	};
	/**
	 * 扫一扫
	 */
	private Button.OnClickListener scan=new Button.OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			Intent intent=new Intent();
			intent.setClass(Tab4_gengduo2.this, CaptureActivity.class);
			startActivityForResult(intent, 0);
		}
	};
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent)
	{
		if(requestCode==0)
		{
			if (resultCode==RESULT_OK)
			{
				String num=intent.getStringExtra("RESULT");
				
				Intent intent2 = new Intent(Tab4_gengduo2.this, Bookview.class);
				Util.code = num;
				startActivity(intent2);
			}
			else if(resultCode==RESULT_CANCELED) {}
		}
		else
		{
			return;
		}
	}
	public void isKaiqiPassword() {
		SharedPreferences sharedPreferences = getSharedPreferences(
				"kaiqipassword", MODE_PRIVATE);

		isEnter = sharedPreferences.getInt("iskaiqi", -1);

		if (isEnter == 1) {
			tb_password.setChecked(true);
		} else {
			tb_password.setChecked(false);
		}

	}

	/**
	 * 服务器ip
	 */
	OnClickListener onClickListener = new OnClickListener() {

		public void onClick(View v) {
			int id = v.getId();
			if (id == R.id.more_remind) {
				setmore_remind();
				Log.e("----------->>>", "more_remind被点击");
			} else if (id == R.id.chazhao) {
				setmore_find1();
				Log.e("----------->>>", "chazhao被点击");
			}else if (id == R.id.more_feedback) {
				Log.e("9999", "66666");
				UMFeedbackService.enableNewReplyNotification(Tab4_gengduo2.this,
						NotificationType.NotificationBar);
				UMFeedbackService.setGoBackButtonVisible();
				UMFeedbackService.openUmengFeedbackSDK(Tab4_gengduo2.this);
			}

		}

	};
	
//    private Button.OnClickListener scan=new Button.OnClickListener()
//	{
//		@Override
//		public void onClick(View v)
//		{
//			Intent intent=new Intent();
//			intent.setClass(Tab4_gengduo2.this, CaptureActivity.class);
//			startActivityForResult(intent, 0);
//		}
//	};
//	
//	@Override
//	protected void onActivityResult(int requestCode, int resultCode, Intent intent)
//	{
//		if(requestCode==0)
//		{
//			if (resultCode==RESULT_OK)
//			{
//				String num=intent.getStringExtra("RESULT");
//				
//				tiaoma.setText(num);
////				Intent intent2 = new Intent(Tab4_gengduo.this, Bookview.class);
////				Bundle bundle = new Bundle();
////				bundle.putString("name", num);
////				intent.putExtra("cainame", bundle);
////				startActivity(intent2);
//			}
//			else if(resultCode==RESULT_CANCELED) {}
//		}
//		else
//		{
//			return;
//		}
//	}
	
	// 添加常驻通知
		private void setNotification() {
			NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
			Notification notification = new Notification(R.drawable.ic,
					getString(R.string.app_name), System.currentTimeMillis());
			Intent intent = new Intent(this, CallbyeTabActivity.class);
			notification.flags = Notification.FLAG_ONGOING_EVENT; // 设置常驻 Flag
			PendingIntent contextIntent = PendingIntent.getActivity(this, 0,
					intent, 0);
			notification.setLatestEventInfo(getApplicationContext(),
					getString(R.string.app_name), "点击查看", contextIntent);
			notificationManager.notify(R.string.app_name, notification);
		}

		// 取消通知
		private void cancelNotification() {
			NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
			notificationManager.cancel(R.string.app_name);
		}
		/**
		 * 手动查找
		 */
	public void setmore_find1() {
			// TODO 自动生成的方法存根
		LayoutInflater inflater = LayoutInflater.from(Tab4_gengduo2.this);
		View timepickerview = inflater.inflate(R.layout.findbook1, null);
		
		ScreenInfo screenInfo = new ScreenInfo(Tab4_gengduo2.this);
		final Dialog dialog1 = new AlertDialog.Builder(this).setView(timepickerview).show();
		
		Window window = dialog1.getWindow();
		window.setGravity(Gravity.BOTTOM); // 此处可以设置dialog显示的位置
		window.setWindowAnimations(R.style.mystyle); // 添加动画

		final EditText tiaoma = (EditText) timepickerview.findViewById(R.id.tiaoma);
		
		Button btn = (Button) timepickerview.findViewById(R.id.chazhao);
		btn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				String isbninfo = tiaoma.getText().toString();
				if (isbninfo!=null) {
					Intent intent = new Intent(Tab4_gengduo2.this, Bookview.class);
					Bundle bundle = new Bundle();
					bundle.putString("name", isbninfo);
					intent.putExtra("cainame", bundle);
					startActivity(intent);
				}
				dialog1.dismiss();
			}
		});
		

	}
	/**
	 * 自动查找
	 */
public void setmore_find2() {
		// TODO 自动生成的方法存根
	LayoutInflater inflater = LayoutInflater.from(Tab4_gengduo2.this);
	View timepickerview = inflater.inflate(R.layout.findbook1, null);
	
	ScreenInfo screenInfo = new ScreenInfo(Tab4_gengduo2.this);
	final Dialog dialog1 = new AlertDialog.Builder(this).setView(timepickerview).show();
	
	Window window = dialog1.getWindow();
	window.setGravity(Gravity.BOTTOM); // 此处可以设置dialog显示的位置
	window.setWindowAnimations(R.style.mystyle); // 添加动画

	final EditText tiaoma = (EditText) timepickerview.findViewById(R.id.tiaoma);
	
	Button btn = (Button) timepickerview.findViewById(R.id.chazhao);
	btn.setOnClickListener(new OnClickListener() {

		public void onClick(View v) {
			String isbninfo = tiaoma.getText().toString();
			if (isbninfo!=null) {
				Intent intent = new Intent(Tab4_gengduo2.this, Bookview.class);
				Bundle bundle = new Bundle();
				bundle.putString("name", isbninfo);
				intent.putExtra("cainame", bundle);
				startActivity(intent);
			}
			dialog1.dismiss();
		}
	});
	

}

	/**
	 * 
	 * 设置提醒方式
	 * 
	 */
	public void setmore_remind() {
		LayoutInflater inflater = LayoutInflater.from(Tab4_gengduo2.this);
		View timepickerview = inflater.inflate(R.layout.selectbirthday, null);
		
		ScreenInfo screenInfo = new ScreenInfo(Tab4_gengduo2.this);
		final Dialog dialog1 = new AlertDialog.Builder(this).setView(timepickerview).show();
		
		Window window = dialog1.getWindow();
		window.setGravity(Gravity.BOTTOM); // 此处可以设置dialog显示的位置
		window.setWindowAnimations(R.style.mystyle); // 添加动画

		final EditText etxt = (EditText) timepickerview.findViewById(R.id.newip);
		
		Button btn = (Button) timepickerview.findViewById(R.id.btn_datetime_sure);
		btn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				
				SharedPreferences sp = getSharedPreferences("url", MODE_WORLD_READABLE+MODE_WORLD_WRITEABLE);
				SharedPreferences.Editor editor = sp.edit();
				try {
					editor.putString("url_address", etxt.getText().toString());
					Util.URL= etxt.getText().toString();
					editor.commit();
				} catch (Exception e) {
					System.out.println(e);
				}
				dialog1.dismiss();
			}
		});
	}

}
