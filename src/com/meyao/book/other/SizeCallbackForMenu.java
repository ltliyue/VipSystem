package com.meyao.book.other;

import com.meyao.book.other.MyHorizontalScrollView.SizeCallback;

import android.widget.Button;

public class SizeCallbackForMenu implements SizeCallback {
	private Button leftButton;
//	private int leftButtonWidth;

	public SizeCallbackForMenu(Button leftButton) {
		super();
		this.leftButton = leftButton;
		// this.leftButton = leftButton;
	}

	
	public void onGlobalLayout() {

	}

	
	public void getViewSize(int idx, int w, int h, int[] dims) {
		dims[0] = w;
		dims[1] = h;
		if (idx != 0) {
			// ����ͼ�����м����ͼ
			dims[0] = w-40;
		}
	}
}
