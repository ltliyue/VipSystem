package com.meyao.book.view;

import com.meyao.book.R;
import com.meyao.book.util.OrderHttpUtil;
import com.meyao.book.util.OrderStringUtil;
import com.meyao.book.util.OrderUrlUtil;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class UserRegister extends Activity{
	
	private ImageButton m_register;
	private ImageButton m_reset;
	private ImageButton m_go_back;
	
	private EditText username;
	private EditText password;
	private EditText email;

	private RadioGroup m_gender;
	private RadioButton m_boy;
	private RadioButton m_gril;

	private CheckBox m_accept;
	
	private String gender = "";
	private boolean accept = false;
	
	private ProgressDialog proDlg;
	private String res;
	
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.register);
		
		/**
		 * ʵ�������
		 */
		findAllViewById();
		
		/**
		 * ע���¼�
		 */
		registerViewListener();
		
	}

	/**
	 * ע���¼�
	 */
	private void registerViewListener() {
		// ע��
		m_register.setOnClickListener(new ImageButton.OnClickListener() {
			public void onClick(View v) {
				final String uname = username.getText().toString();
				final String upwd = password.getText().toString();
				final String umail = email.getText().toString();
								
				if("".equals(uname.trim())){ // �û���Ϊ�գ�

					AlertDialog.Builder builder = new AlertDialog.Builder(UserRegister.this);
					builder.setIcon(R.drawable.alert_wanring)
							.setTitle(R.string.login_account_null)
							.setMessage(R.string.login_account_null)
							.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
								// ���ȷ����ť
								public void onClick(DialogInterface dialog, int which) {
									username.setText("");
									password.setText("");
									email.setText("");
								}
							}).show();
					return ;
					
				} 
				if("".equals(upwd)){
					AlertDialog.Builder builder = new AlertDialog.Builder(UserRegister.this);
					builder.setIcon(R.drawable.alert_wanring)
							.setTitle(R.string.login_password_null)
							.setMessage(R.string.login_password_null)
							.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
								// ���ȷ����ť
								public void onClick(DialogInterface dialog, int which) {
									password.setText("");
									email.setText("");
								}
							}).show();
					return ;
				} 
				if("".equals(umail.trim())){
					AlertDialog.Builder builder = new AlertDialog.Builder(UserRegister.this);
					builder.setIcon(R.drawable.alert_wanring)
							.setTitle(R.string.login_email_null)
							.setMessage(R.string.login_email_null)
							.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
								// ���ȷ����ť
								public void onClick(DialogInterface dialog, int which) {
									email.setText("");
								}
							}).setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
								// ���ȡ����ť
								public void onClick(DialogInterface dialog, int which) {
									email.setText("");
								}
							}).show();
					return ;
				} 
				if(!OrderStringUtil.emailRule(umail)){
					AlertDialog.Builder builder = new AlertDialog.Builder(UserRegister.this);
					builder.setIcon(R.drawable.alert_wanring)
							.setTitle(R.string.login_email_invalid)
							.setMessage(R.string.login_email_invalid)
							.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
								// ���ȷ����ť
								public void onClick(DialogInterface dialog, int which) {
									email.setText("");
								}
							}).setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
								// ���ȡ����ť
								public void onClick(DialogInterface dialog, int which) {
									email.setText("");
								}
							}).setNeutralButton("����ȡ��", new DialogInterface.OnClickListener() {
								// ���ȡ����ť
								public void onClick(DialogInterface dialog, int which) {
									email.setText("");
								}
							}).show();
					return ;
				} 
				if("".equals(gender)){
					AlertDialog.Builder builder = new AlertDialog.Builder(UserRegister.this);
					builder.setIcon(R.drawable.alert_wanring)
							.setTitle(R.string.login_gender_null)
							.setMessage(R.string.login_gender_null)
							.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
								// ���ȷ����ť
								public void onClick(DialogInterface dialog, int which) {									
								}
							}).show();
					return ;
				} 
				if(!accept){
					AlertDialog.Builder builder = new AlertDialog.Builder(UserRegister.this);
					builder.setIcon(R.drawable.alert_wanring)
							.setTitle(R.string.login_accept_no)
							.setMessage(R.string.login_accept_no)
							.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
								// ���ȷ����ť
								public void onClick(DialogInterface dialog, int which) {									
								}
							}).show();
					return ;
				}
				
				proDlg = OrderStringUtil.createProgressDialog(UserRegister.this, getResources().getString(R.string.pro_title),
						getResources().getString(R.string.pro_message), true, true);
				proDlg.show();
				
				new Thread(){
					@Override
					public void run() {
						/**
						 * 1  ��֤�û��Ƿ���ڣ������ڣ�ע��
						 * 2  ע��ɹ��������˺ź�������ʾ
						 * 3  ��¼
						 */
						String registerString = "loginId=" + uname + "&password=" + upwd + "&email=" + umail + "&gender=" + gender;
						String url = OrderHttpUtil.BASE_URL + OrderUrlUtil.REGISTER_URL + registerString;					
						
						res = OrderHttpUtil.getHttpPostResultForUrl(url);
						handler.sendEmptyMessage(1);
					}
				}.start();
								
			}
			
		});
		
		// ���
		m_reset.setOnClickListener(new ImageButton.OnClickListener() {
			public void onClick(View v) {
				username.setText("");
				password.setText("");
				email.setText("");
				m_accept.setChecked(false);
				m_boy.setChecked(false);
				m_gril.setChecked(false);
			}
		});
		
		// ����
		m_go_back.setOnClickListener(new ImageButton.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(UserRegister.this, LoginActivity.class);
				startActivity(intent);
			}
		});
		
		// �Ա�
		m_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(m_boy.getId() == checkedId)
					gender = "M";
				else if(m_gril.getId() == checkedId)
					gender = "F";
				else 
					gender = "";
			}
		});
		
		// ͬ������
		m_accept.setOnClickListener(new RadioGroup.OnClickListener() {
			public void onClick(View v) {
				if(m_accept.isChecked())
					accept = true;
				else
					accept = false;
			}
		});
		
	}
	
	/**
	 * ��ʾע����Ϣ
	 * 1 ע��ɹ�
	 * 0 ע��ʧ��
	 * 2 �����Ѵ���
	 * 3 ��½ID�Ѵ���
	 * exception �����쳣
	 * @param res
	 */
	protected void showRegisterMesg(String res) {
		if("0".equals(res)){
			AlertDialog.Builder builder = new AlertDialog.Builder(UserRegister.this);
			builder.setIcon(R.drawable.alert_error)
					.setTitle("ע��ʧ��")
					.setMessage("ע��ʧ�ܣ����Ժ����ԣ�")
					.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
						// ���ȷ����ť
						public void onClick(DialogInterface dialog, int which) {
						}
					}).show();
			return ;
		}
		if("1".equals(res)){
			AlertDialog.Builder builder = new AlertDialog.Builder(UserRegister.this);
			builder.setIcon(R.drawable.alert_add)
					.setTitle("ע��ɹ�")
					.setMessage("��ϲ����ע��ɹ������½��")
					.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
						// ���ȷ����ť
						public void onClick(DialogInterface dialog, int which) {
							Intent intent = new Intent(UserRegister.this, LoginActivity.class);
							startActivity(intent);
						}
					}).show();
			return ;
		}
		if("2".equals(res)){
			AlertDialog.Builder builder = new AlertDialog.Builder(UserRegister.this);
			builder.setIcon(R.drawable.alert_error)
					.setTitle("�����Ѵ���")
					.setMessage("�����Ѵ��ڣ���ʹ���������䣡")
					.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
						// ���ȷ����ť
						public void onClick(DialogInterface dialog, int which) {
							email.setText("");
						}
					}).show();
			return ;
		}
		if("3".equals(res)){
			AlertDialog.Builder builder = new AlertDialog.Builder(UserRegister.this);
			builder.setIcon(R.drawable.alert_error)
					.setTitle("��½�˺��Ѵ���")
					.setMessage("��½�˺��Ѵ��ڣ���ʹ�������˺ţ�")
					.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
						// ���ȷ����ť
						public void onClick(DialogInterface dialog, int which) {
							username.setText("");
						}
					}).show();
			return ;
		}
	}

	/**
	 * ʵ�������
	 */
	private void findAllViewById() {
		m_register = (ImageButton)findViewById(R.id.imb_reg_register);
		m_reset = (ImageButton)findViewById(R.id.imb_reg_reset);
		m_go_back = (ImageButton)findViewById(R.id.imb_reg_go_back);
		
		username = (EditText)findViewById(R.id.text_reg_username);
		password = (EditText)findViewById(R.id.text_reg_password);
		email = (EditText)findViewById(R.id.text_reg_email);
		
		m_gender = (RadioGroup)findViewById(R.id.gender_radio);
		m_boy = (RadioButton)findViewById(R.id.radio_boy);
		m_gril = (RadioButton)findViewById(R.id.radio_gril);
		
		m_accept = (CheckBox)findViewById(R.id.cb_accept);
	}
	
	private Handler handler = new Handler(){
		public void dispatchMessage(Message msg) {
			proDlg.dismiss();
			showRegisterMesg(res);
		};
	};
	
}
