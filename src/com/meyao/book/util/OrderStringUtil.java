package com.meyao.book.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.meyao.book.R;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

public class OrderStringUtil {
	
	/**
	 * �����ṩ������
	 */
	public static final String USER_DATA_PROVIDE = "user";
	
	/**
	 * ����Լ�� constraint
	 */
	public static final String TEXT_NOT_NULL = "text not null";
	public static final String TEXT_DEFAULT_NULL = "text default null";
	public static final String INTEGER_NOT_NULL = "integer not null";
	public static final String INTEGER_DEFAULT_NULL = "integer default null";
	
	/**
	 * �˵�����
	 */
	public static final String HOT_ORDER_MENU = "recai";
	public static final String COOL_ORDER_MENU = "liangcai";
	public static final String NOOLDE_ORDER_MENU = "mianshi";
	
	/**
	 * Handler ��Ϣ�����б�
	 */
	public static final int LOGIN_ERROR = 0; // ��½ʧ��
	public static final int LOGIN_SUCCESS = 1; // ��½�ɹ�
	public static final int SERVER_ERROR = 2; // �������쳣
	public static final int SERVER_NO_DATA = 3; // ������������
	public static final int DATA_DETAIL = 4; // �����ݣ���ʾ��ϸ��Ϣ
	public static final int GO_ORDER = 5; // ���Ե��
	public static final int ERROR = 6; // �������
	public static final int OK = 7; // ��������
	public static final int NEW_ORDER_FINASH = 8; // �����������
	public static final int UPDATE_ORDER_FINASH = 9; // �޸ĸ������
	public static final int DELETE_ORDER_FINASH = 10; // ɾ���������
	public static final int ERROR_ORDER_FINASH = 11; // ����������
	public static final int MAKE_ORDER_OK = 12; // ������ݷ������
	public static final int MAKE_ORDER_ERROR = 13; // ������ݷ��ʹ���
	public static final int BASE_MODIFY_OK = 14; // ��Ϣ�޸ĳɹ�
	public static final int PASSWORD_MODIFY_OK = 15; // ��Ϣ�޸ĳɹ�
	public static final int EMAIL_EXISTS = 16; // �����Ѿ�����
	public static final int BASE_ERROR = 17; // ������Ϣ�޸Ĵ���
	public static final int PASSWORD_ERROR = 18; // �����޸Ĵ���
	public static final int PASSWORD_OLD_REEOR = 19; // ԭ�������
	
	/**
	 * ��������
	 */
	public static final String[] orderTypeName = {"�Ȳ�", "����", "��ʳ"};
	
	/**
	 * ����ֵ
	 */
	public static final String[] orderTypeValue = {"recai", "liangcai", "mianshi"};
	
	/**
	 * �Ƿ񱣳��û��� key
	 */
	public static final String IS_USER_NAME = "is_save_uname";
	
	/**
	 * �Ƿ񱣳����� key
	 */
	public static final String IS_PASSWORD = "is_save_pwd";
	
	/**
	 * �û���  key
	 */
	public static final String USERNAME = "username";
	
	/**
	 * ���� key
	 */
	public static final String PASSWORD = "password";
	
	
	/**
	 * ��������
	 */
	public static String city[] = {
		"����",
		"�Ϻ�",
		"���",
		"����",
		"��ɽ",
		"ʯ��ׯ",
		"����",
		"������",
		"����",
		"����",
		"��ɳ",
		"�ɶ�",
		"����",
		"����",
		"����",
		"����",
		"�Ϸ�",
		"���ͺ���",
		"����",
		"����",
		"����",
		"����",
		"�ϲ�",
		"�Ͼ�",
		"����",
		"�ൺ",
		"����",
		"����",
		"̫ԭ",
		"��³ľ��",
		"�人",
		"����",
		"����",
		"����",
		"����",
		"����",
		"֣��"
    };
	
	/**
	 * ���д��� ��������
	 */
	public static String cityCode[] = {
		"39930000,116279998",//����
		"31399999,121470001",//�Ϻ�
		"39099998,117169998",//���
		"29520000,106480003",//����
		"39669998,118150001",//��ɽ
		"38029998,114419998",//ʯ��ׯ
		"38900001,121629997",//����
		"45750000,126769996",//������
		"20030000,110349998",//����
		"43900001,125220001",//����
		"28229999,112870002",//��ɳ
		"30670000,104019996",//�ɶ�
		"26079999,119279998",//����
		"23129999,113319999",//����
		"26579999,106720001",//����
		"30229999,120169998",//����
		"31870000,117230003",//�Ϸ�
		"40819999,111680000",//���ͺ���
		"36680000,116980003",//����
		"25020000,102680000",//����
		"29657589,91132050",//����
		"36040000,103879997",//����
		"28600000,115919998",//�ϲ�
		"32000000,118800003",//�Ͼ�
		"22819999,108349998",//����
		"36069999,120330001",//�ൺ
		"22549999,114099998",//����
		"41770000,123430000",//����
		"37779998,112550003",//̫ԭ
		"43779998,87620002",//��³ľ��
		"30620000,114129997",//�人
		"34299999,108930000",//����
		"36619998,101769996",//����
		"24479999,118080001",//����
		"34279998,117150001",//����
		"38479999,106220001",//����
		"34720001,113650001"//֣��
	};
	
	/**
	 * ��֤�����Ƿ�Ϸ�
	 * @param umail
	 * @return
	 */
	public static boolean emailRule(String umail) {
		boolean result = false;
		String reg = "[a-zA-Z0-9][a-zA-Z0-9._-]{2,16}[a-zA-Z0-9]@[a-zA-Z0-9]+.[a-zA-Z0-9]+";
		if(umail.matches(reg)){
			result = true;
		}else{
			result = false;
		}
		  
		return result;
	}

	/**
	 * ���� format ��ʽ��ʱ���ַ���
	 * ʱ���ʽΪ yyyy-MM-dd HH:mm:ss
	 * yyyy ����4λ���
	 * MM ����2λ�·�
	 * dd ����2λ��
	 * ʱ����ͬ
	 * @return ��Ӧ�������͵��ַ���
	 */
	public static String getCurrentDate(String format){
		 SimpleDateFormat sdf = new SimpleDateFormat(format);
		 return sdf.format(new Date()).toString();
	}
	
	/**
	 * ͨ��URL���������BitMapͼƬ
	 * @param url
	 * @return Bitmap
	 */
	public static Bitmap getBitMapForStringURL(String urlString) {  
		
		URL url = null; 
		Bitmap bitmap = null;  

		try {  
			url = new URL(urlString);  
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();  

			conn.setDoInput(true);  
			conn.connect();  

			InputStream is = conn.getInputStream();  
			bitmap = BitmapFactory.decodeStream(is);  

			is.close();  

		} catch (IOException e) {  
		  e.printStackTrace();  
		}  
		return bitmap;
	}
	
	/**
	 * ���ȶԻ���
	 * @param context
	 * @param title
	 * @param message
	 * @param canCelable
	 * @param indeterminate
	 */
	public static ProgressDialog createProgressDialog(Context context, String title,
			String message, boolean canCelable, boolean indeterminate) {
		ProgressDialog p = new ProgressDialog(context);
		p.setIcon(R.drawable.progress);
		p.setTitle(title);
		p.setMessage(message);
		p.setCancelable(canCelable);
		p.setIndeterminate(indeterminate);
		return p;
	}

	/**
	 * ���ݵ�½���ݣ�������ݵ�Intent�� Bundle->Intent ����key��Ϊdata
	 * ��� getDataFromIntent(Intent) ʹ��
	 * @param intent
	 * @param str id,loginid,password,nikename,phone,email,gender,create_at	
	 */
	public static void putDataIntoIntent(Intent intent, String str) {
		Bundle bundle = new Bundle();
		bundle.putString("data", str);				
		intent.putExtra("data", bundle);
	}
	
	/**
	 * ���ݵ�½���ݣ���Intent�л�ȡBundle����
	 * ��� putDataIntoIntent(Intent, String) ʹ��
	 * @param intent
	 * @return String id,loginid,password,nikename,phone,email,gender,create_at	
	 */
	public static String getDataFromIntent(Intent intent) {
		Bundle bundle = intent.getBundleExtra("data");
		String res = bundle.getString("data");;				
		return res;
	}

	/**
	 * �ж��ֻ������Ƿ�Ϸ�
	 * @param trim
	 * @return
	 */
	public static boolean phoneNumberRule(String phone) {
		boolean result = false;
		long min = 13000000000L;
		long max = 18999999999L;
		long data = 0;
		try {
			data = Long.parseLong(phone);
		} catch (NumberFormatException e) {
			result = false;
			e.printStackTrace();
		}
		if(phone.length()!=11)
			result = false;
		else if(data < min || data > max)
			result = false;
		else 
			result = true;
		return result;
	}
}
