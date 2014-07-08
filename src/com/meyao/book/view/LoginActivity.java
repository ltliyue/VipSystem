package com.meyao.book.view;

import com.meyao.book.R;
import com.meyao.book.util.OrderHttpUtil;
import com.meyao.book.util.OrderStringUtil;
import com.meyao.book.util.OrderUrlUtil;
import com.meyao.book.util.Util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;


public class LoginActivity extends Activity {
	private ImageButton m_login;
	private ImageButton m_clear;
	private ImageButton m_register;
	
	private EditText m_username;
	private EditText m_password;
	
	private String uname;
	private String pwd;
	private ProgressDialog prgDialog;
	private String res;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        m_login = (ImageButton)findViewById(R.id.imb_login);
        m_clear = (ImageButton)findViewById(R.id.imb_clear);
        m_register = (ImageButton)findViewById(R.id.imb_register);
        
        m_username = (EditText)findViewById(R.id.text_username);
        m_password = (EditText)findViewById(R.id.text_password);
        
        initUserNamePassword();
        
        /**
         * 添加监听器
         */
        setImageButtonListener();
        
    }
    /**
     * 更具用的个性设置来初始化用户名和密码
     */
	private void initUserNamePassword() {
		
//		SharedPreferences sp = getSharedPreferences("user" , MODE_PRIVATE);
//		String username = sp.getString("username", "");
//		String password = sp.getString("password", "");

		SharedPreferences share = getSharedPreferences(OrderStringUtil.USER_DATA_PROVIDE, PreferenceActivity.MODE_PRIVATE);

		String u_name = share.getString(OrderStringUtil.USERNAME, "");
		m_username.setText(u_name);

		String u_pwd = share.getString(OrderStringUtil.PASSWORD, "");
		m_password.setText(u_pwd);
		
	}

	@Override
	protected void onStop() {
		super.onStop();
		SharedPreferences share = getSharedPreferences(OrderStringUtil.USER_DATA_PROVIDE, PreferenceActivity.MODE_PRIVATE);
		SharedPreferences.Editor editor = share.edit();
		
		boolean name = share.getBoolean(OrderStringUtil.IS_USER_NAME, false);
		boolean pwd = share.getBoolean(OrderStringUtil.IS_PASSWORD, false);
		if(name)
			editor.putString(OrderStringUtil.USERNAME, m_username.getText().toString());			
		else
			editor.remove(OrderStringUtil.USERNAME);
		if(pwd)
			editor.putString(OrderStringUtil.PASSWORD, m_password.getText().toString());
		else
			editor.remove(OrderStringUtil.PASSWORD);
		editor.commit();
	}

	/**
     * 添加监听器
     */
	private void setImageButtonListener() {
		
		// 登陆
		m_login.setOnClickListener(new ImageButton.OnClickListener() {
			public void onClick(View v) {				
				uname = m_username.getText().toString().trim();
				pwd = m_password.getText().toString().trim();
				if("".equals(uname)){
					AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
					builder.setIcon(R.drawable.alert_wanring)
							.setTitle(R.string.login_account_null)
							.setMessage(R.string.login_account_null)
							.setPositiveButton("确定", new DialogInterface.OnClickListener() {
								// 点击确定按钮
								public void onClick(DialogInterface dialog, int which) {}
							}).show();
					return ;
				}
				if("".equals(pwd)){
					AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
					builder.setIcon(R.drawable.alert_wanring)
							.setTitle(R.string.login_password_null)
							.setMessage(R.string.login_password_null)
							.setPositiveButton("确定", new DialogInterface.OnClickListener() {
								// 点击确定按钮
								public void onClick(DialogInterface dialog, int which) {									
								}
							}).show();
					return ;
				}
				
				// 显示登陆对话框
				prgDialog = new ProgressDialog(LoginActivity.this);
				prgDialog.setIcon(R.drawable.progress);
				prgDialog.setTitle("请稍等");
				prgDialog.setMessage("正在登陆，请稍等...");
				prgDialog.setCancelable(false);
				prgDialog.setIndeterminate(true);
				prgDialog.show();
				
				login();

			}
			
		});
		
		// 清空用户名和密码
		m_clear.setOnClickListener(new ImageButton.OnClickListener() {
			public void onClick(View v) {
				m_username.setText("");
				m_password.setText("");
			}
		});
		
		// 注册
		m_register.setOnClickListener(new ImageButton.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(LoginActivity.this, UserRegister.class);
				startActivity(intent);
			}
		});
		
	}

	protected void login() {
		new Thread(){
			@Override
			public void run() {
				String loginString = "loginid=" + uname + "&password=" + pwd;
				String url = OrderHttpUtil.BASE_URL + OrderUrlUtil.LOGIN_URL + loginString;
				System.out.println(url);
				res = OrderHttpUtil.getHttpPostResultForUrl(url);
				Message m = new Message();
				if("-1".equals(res))
					m.what = OrderStringUtil.LOGIN_ERROR;
				else
					Util.uname=uname;
					m.what = OrderStringUtil.LOGIN_SUCCESS;
				
				proHandle.sendMessage(m);
				
			}
		}.start();
	}
	
	private Handler proHandle = new Handler(){
		@Override
		public void handleMessage(Message msg) {

			AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
			
			prgDialog.dismiss();
			switch(msg.what){
			case OrderStringUtil.LOGIN_ERROR:
				builder.setIcon(R.drawable.alert_error)
						.setTitle("错误")
						.setMessage("用户名或密码错误，请确认")
						.setPositiveButton("确定", new DialogInterface.OnClickListener() {
							// 点击确定按钮
							public void onClick(DialogInterface dialog, int which) {									
							}
						}).show();
				break;
			case OrderStringUtil.LOGIN_SUCCESS:	
				builder.setIcon(R.drawable.alert_ok)
						.setTitle("登陆成功")
						.setMessage("恭喜您，登陆成功")
						.setPositiveButton("确定", new DialogInterface.OnClickListener() {
							// 点击确定按钮
							public void onClick( DialogInterface dialog, int which) {
								Intent intent = new Intent(LoginActivity.this, CallbyeTabActivity.class);
								
								
								Bundle bundle = new Bundle();
								bundle.putString("data", res);
								
								intent.putExtra("data", bundle);
								
								startActivity(intent);
							}
						}).show();
				break;			
			}
		}
	};
	
}
