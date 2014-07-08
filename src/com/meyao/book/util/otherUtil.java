package com.meyao.book.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.meyao.book.other.ActivityMeg;

import android.content.Context;
import android.widget.Toast;

public class otherUtil {
	private static long exitTime = 0;

	public static void ExitApp(Context context) {
		if ((System.currentTimeMillis() - exitTime) > 2000) {
			Toast.makeText(context, "再按一次退出程序", Toast.LENGTH_SHORT).show();
			exitTime = System.currentTimeMillis();
		} else {
			ActivityMeg.getInstance().exit();
		}

	}


}
