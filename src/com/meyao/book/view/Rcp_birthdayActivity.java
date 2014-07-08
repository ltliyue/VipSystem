package com.meyao.book.view;

import rcp.com.src.volues.activityVolues;
import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TabHost;

import com.meyao.book.R;
import com.meyao.book.other.ActivityMeg;
import com.umeng.update.UmengUpdateAgent;

//import dalvik.system.VMRuntime;

public class Rcp_birthdayActivity extends TabActivity implements Runnable {

	// ======================================================
	/** ѡ����� */
	private TabHost tabhost;
	/** ѡ���ť */
	private RadioButton radioButton[] = new RadioButton[5];

	/** ѡ� ���� ��ťID���� **/
	private int radioButtonId[] = { R.id.tab_item_1, R.id.tab_item_2,
			R.id.tab_item_3, R.id.tab_item_4, R.id.tab_item_5 };

	// ===========================================================

	/** �Ƿ���������� */
	private int isEnter;

	private ImageView img_appLoad;

	// private final static float TARGET_HEAP_UTILIZATION = 0.75f;

	// �ڳ���onCreateʱ�Ϳ��Ե���

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		// System.out.println("=================="
		// + deleteDatabase("rcpBrithday.db"));
		// VMRuntime.getRuntime()
		// .setTargetHeapUtilization(TARGET_HEAP_UTILIZATION);
		UmengUpdateAgent.setOnDownloadListener(null);
		UmengUpdateAgent.update(this);
		// VMRuntime.getRuntime().setMinimumHeapSize(CWJ_HEAP_SIZE);

		ActivityMeg.getInstance().addActivity(this);
		tabhostInit();
		viewInit();

		new Thread(this).start();

	}

	/**
	 * �ж��Ƿ��м�����
	 */

	public void readPass() {
		SharedPreferences sharedPreferences = getSharedPreferences(
				"kaiqipassword", MODE_PRIVATE);

		isEnter = sharedPreferences.getInt("iskaiqi", -1);

		if (isEnter == 1) {
			Intent intent = new Intent(this, CodeNum.class);
			intent.putExtra("kaiguan", 2);
			this.startActivity(intent);
		}
	}

	/***
	 * ��ͼ�ĳ�ʼ��
	 * 
	 */
	public void viewInit() {
		img_appLoad = (ImageView) this.findViewById(R.id.applaoding);
	}

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {

			img_appLoad.setVisibility(View.GONE);
		}

	};

	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		readbackUp();
		readPass();
		handler.sendEmptyMessage(0);
	}

	/***
	 * ѡ��ĳ�ʼ��
	 * 
	 */
	public void tabhostInit() {
		tabhost = this.getTabHost();
		tabhost.addTab(tabhost.newTabSpec("brith").setIndicator("11")
				.setContent(new Intent(this, Tab1_tuijian2.class)));
		tabhost.addTab(tabhost.newTabSpec("sms").setIndicator("22")
				.setContent(new Intent(this, Tab2_fenlei2.class)));
		tabhost.addTab(tabhost.newTabSpec("center").setIndicator("33")
				.setContent(new Intent(this, Tab3_gouwudan.class)));
		tabhost.addTab(tabhost.newTabSpec("more").setIndicator("44")
				.setContent(new Intent(this, Tab4_gengduo2.class)));
		tabhost.addTab(tabhost.newTabSpec("person").setIndicator("55")
				.setContent(new Intent(this, Tab5_person.class)));
		
		for (int i = 0; i < radioButton.length; i++) {
			radioButton[i] = (RadioButton) this.findViewById(radioButtonId[i]);
			radioButton[i].setOnClickListener(onClickListener);
		}
		tabhost.setCurrentTabByTag("brith");
		radioButton[0].setChecked(true);
		// ---------------------------------------------
	}

	/****
	 * ��������
	 * 
	 */
	OnClickListener onClickListener = new OnClickListener() {

		public void onClick(View v) {
			int id = v.getId();
			if (id == R.id.tab_item_1) {
				tabhost.setCurrentTabByTag("brith");
			} else if (id == R.id.tab_item_2) {
				tabhost.setCurrentTabByTag("sms");
			} else if (id == R.id.tab_item_3) {
				tabhost.setCurrentTabByTag("center");
			} else if (id == R.id.tab_item_4) {
				tabhost.setCurrentTabByTag("more");
			} else if (id== R.id.tab_item_5) {
				tabhost.setCurrentTabByTag("person");
			}

		}

	};

	public void readbackUp() {
		SharedPreferences sharedPreferences = getSharedPreferences(
				"backupCount", MODE_PRIVATE);
		activityVolues.backupCount = sharedPreferences.getInt("count", -1);
		activityVolues.isLoadOK = sharedPreferences.getBoolean("isloadok",
				false);
	}

	public void saveData() {
		SharedPreferences sharedPreferences = getSharedPreferences(
				"backupCount", MODE_PRIVATE);
		// ��ñ༭Ȩ��
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putBoolean("isloadok", activityVolues.isLoadOK);
		editor.putInt("count", activityVolues.backupCount);
		editor.commit();
	}

}
