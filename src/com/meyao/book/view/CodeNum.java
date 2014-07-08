package com.meyao.book.view;


import com.meyao.book.R;
import com.meyao.book.other.ActivityMeg;
import com.meyao.book.util.otherUtil;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/***
 * 
 * �����趨
 * 
 * @author toshiba
 * 
 */
public class CodeNum extends Activity {

	private EditText mEditText;

	private TextView tv_title;

	/** �Զ������ְ��� */
	private Button btn_num[] = new Button[11];

	private StringBuffer sb = new StringBuffer();

	private String temp1;

	private Button btn_ok;
	/** ���ºõĴ��� */
	private int count = 0;

	private int index;

	private int numID[] = { R.id.num_yi, R.id.num_er, R.id.num_san,
			R.id.num_si, R.id.num_wu, R.id.num_liu, R.id.num_qi, R.id.num_ba,
			R.id.num_jiu, R.id.num_ling, R.id.num_del };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		index = getIntent().getIntExtra("kaiguan", -1);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.numcode);
		ActivityMeg.getInstance().addActivity(this);

		viewInit();

	}

	public void viewInit() {
		tv_title = (TextView) this.findViewById(R.id.num_code_title);
		for (int i = 0; i < btn_num.length; i++) {
			btn_num[i] = (Button) this.findViewById(numID[i]);
			btn_num[i].setOnClickListener(onClickListener);
		}
		btn_ok = (Button) this.findViewById(R.id.numcode_ok);
		btn_ok.setOnClickListener(onClickListener);
		mEditText = (EditText) findViewById(R.id.numcode_et);
		mEditText.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				int inType = mEditText.getInputType(); // backup the input type
				mEditText.setInputType(InputType.TYPE_NULL); // disable soft
																// input
				mEditText.onTouchEvent(event); // call native handler
				mEditText.setInputType(inType); // restore input type
				mEditText.setSelection(mEditText.getText().length());
				return true;
			}
		});
	}

	OnClickListener onClickListener = new OnClickListener() {

		public void onClick(View v) {
			int id = v.getId();
			if (id == R.id.numcode_ok) {
				// ���������
				if (index == 0) {
					count++;
					if (count == 1) {
						temp1 = sb.toString();
						tv_title.setText("���ٴ���������");
						sb.delete(0, sb.length());
					}
					if (count == 2) {
						String temp2 = sb.toString();

						System.out.println();
						if (temp1.equals(temp2)) {
							saveData();
							Toast.makeText(CodeNum.this, "�������óɹ�", 0).show();
							Intent intent = new Intent(CodeNum.this,
									CallbyeTabActivity.class);
							CodeNum.this.startActivity(intent);
							finish();
						} else {
							count = 0;
							sb.delete(0, sb.length());
							tv_title.setText("����������");
							Toast.makeText(CodeNum.this, "������������벻ͬ,����������", 0)
									.show();
						}
					}
					// �ر��������
				} else if (index == 1) {
					readDataToCancel();
					// ��������֤
				} else if (index == 2) {
					readDataToMain();
				}
			} else if (id == R.id.num_yi) {
				sb.append("1");
			} else if (id == R.id.num_er) {
				sb.append("2");
			} else if (id == R.id.num_san) {
				sb.append("3");
			} else if (id == R.id.num_si) {
				sb.append("4");
			} else if (id == R.id.num_wu) {
				sb.append("5");
			} else if (id == R.id.num_liu) {
				sb.append("6");
			} else if (id == R.id.num_qi) {
				sb.append("7");
			} else if (id == R.id.num_ba) {
				sb.append("8");
			} else if (id == R.id.num_jiu) {
				sb.append("9");
			} else if (id == R.id.num_ling) {
				sb.append("0");
			} else if (id == R.id.num_del) {
				if (sb.length() > 0) {
					sb.delete(sb.length() - 1, sb.length());
				}
			}
			mEditText.setText(sb.toString());
		}

	};
	
	

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		if((index == 2)&&(keyCode==KeyEvent.KEYCODE_BACK)){
			otherUtil.ExitApp(this);
			return true;
		}
		// TODO Auto-generated method stub
		return super.onKeyDown(keyCode, event);
	}

	/***
	 * 
	 * b��������
	 * 
	 */
	public void saveData() {
		SharedPreferences sharedPreferences = getSharedPreferences(
				"kaiqipassword", MODE_PRIVATE);
		// ��ñ༭Ȩ��
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString("password", sb.toString());
		editor.putInt("iskaiqi", 1);
		editor.commit();
	}

	public void readDataToMain() {
		SharedPreferences sharedPreferences = getSharedPreferences(
				"kaiqipassword", MODE_PRIVATE);

		String password = sharedPreferences.getString("password", "");
		if (password.equals(sb.toString())) {
			Intent intent = new Intent(CodeNum.this, CallbyeTabActivity.class);
			CodeNum.this.startActivity(intent);
			finish();
		} else {
			Toast.makeText(CodeNum.this, "���벻��ȷ", 0).show();
			sb.delete(0, sb.length());
		}
	}

	/**
	 * ������To�رշ���
	 * 
	 */
	public void readDataToCancel() {
		SharedPreferences sharedPreferences = getSharedPreferences(
				"kaiqipassword", MODE_PRIVATE);

		String password = sharedPreferences.getString("password", "");

		System.out.println(password + "---------------" + sb.toString());

		if (password.equals(sb.toString())) {
			Toast.makeText(CodeNum.this, "����ȡ���ɹ�", 0).show();
			// ��ñ༭Ȩ��
			SharedPreferences.Editor editor = sharedPreferences.edit();
			editor.putInt("iskaiqi", -1);
			editor.commit();
			Intent intent = new Intent(CodeNum.this, CallbyeTabActivity.class);
			CodeNum.this.startActivity(intent);
			finish();
		} else {

			Toast.makeText(CodeNum.this, "���벻��ȷ", 0).show();
			sb.delete(0, sb.length());
		}
	}

}
