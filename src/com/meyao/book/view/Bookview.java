package com.meyao.book.view;

import org.json.JSONException;
import org.json.JSONObject;

import com.meyao.book.R;
import com.meyao.book.db.DBHelpe;
import com.meyao.book.util.AsyncImageLoader;
import com.meyao.book.util.OrderHttpUtil;
import com.meyao.book.util.OrderUrlUtil;
import com.meyao.book.util.Util;
import com.meyao.book.util.AsyncImageLoader.ImageCallback;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Bookview extends Activity implements View.OnClickListener,
		OnTouchListener {
	private TextView tv_price, tv_name, tv_jiesao;
	private TextView tiaoma, zuozhe;
	private TextView price;
	private Button btn_yuding, btn_caiview_return;
	private Animation animation;
	private Intent intent;
	private String str_intent;
	private ImageButton image;
	private String str_name, str_price, str_number, str_image;
	private RelativeLayout relative;
	private EditText edit;
	private boolean isfirst = false;
	public static int number = 0;
	public boolean cup = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bookview);
		this.intview();
		animation = AnimationUtils.loadAnimation(Bookview.this,
				R.anim.anim_click_info);
		intent = getIntent();
		Bundle b = intent.getBundleExtra("cainame");
		try {

			str_intent = b.getString("name");

			if (str_intent == null) {
				str_intent = Util.code;

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		this.whichkindofvegetable();
		System.out.println(str_intent);
	}

	private void whichkindofvegetable() {
		if (str_intent != null) {
			String loginString = "code=" + str_intent;
			String url = OrderHttpUtil.BASE_URL + OrderUrlUtil.SEARCH_URL
					+ loginString;
			System.out.println(url);
			String res = OrderHttpUtil.getHttpPostResultForUrl(url);
			try {
				JSONObject data = new JSONObject(res);
				String ids = data.getString("id");
				String codes = data.getString("code");
				String name = data.getString("name");
				String zuozhe = data.getString("writer");
				String price = data.getString("price");
				String seprice = data.getString("seprice");
				String desc = data.getString("desc");
				String images = data.getString("image");
				str_image = images;

				String image_url = OrderHttpUtil.BASE_URL
						+ OrderUrlUtil.IMAGE_URL + images + ".jpg";

				AsyncImageLoader asyncImageLoader = new AsyncImageLoader(); // 异步获取图片
				Drawable cachedImage = asyncImageLoader.loadDrawable(image_url,
						image, new ImageCallback() {
							@Override
							// 这里是重写了回调接口
							public void imageLoaded(Drawable imageDrawable,
									ImageView imageView, String imageUrl) {
								imageView.setImageDrawable(imageDrawable);
							}
						});

				if (ids != null) {
					tv_name.setText(name);
					tv_price.setText(seprice);
					tiaoma.setText(codes);
					this.zuozhe.setText(zuozhe);
					this.price.setText(price);
					tv_jiesao.setText(desc);

					image.setBackgroundDrawable(cachedImage);

				} else {
					Toast.makeText(Bookview.this, "未知错误", Toast.LENGTH_LONG)
							.show();
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

	}

	private void intview() {
		tiaoma = (TextView) findViewById(R.id.tiaoma);
		zuozhe = (TextView) this.findViewById(R.id.zuozhe);
		price = (TextView) findViewById(R.id.price);

		tv_price = (TextView) findViewById(R.id.tv_price);
		tv_name = (TextView) findViewById(R.id.tv_name);
		tv_jiesao = (TextView) findViewById(R.id.tv_jiesao);
		btn_yuding = (Button) findViewById(R.id.btn_yuding);
		image = (ImageButton) findViewById(R.id.imageButton1);
		relative = (RelativeLayout) findViewById(R.id.ralative);
		edit = (EditText) findViewById(R.id.edit);

		relative.setOnTouchListener(this);
		btn_caiview_return = (Button) findViewById(R.id.btn_caiview_return);
		btn_yuding.setOnClickListener(this);
		btn_caiview_return.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.btn_yuding) {
			btn_yuding.startAnimation(animation);
			animation.setAnimationListener(new AnimationListener() {

				@Override
				public void onAnimationStart(Animation arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onAnimationRepeat(Animation arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onAnimationEnd(Animation arg0) {
					str_name = tv_name.getText().toString();
					str_price = tv_price.getText().toString();
					str_number = edit.getText().toString();

					System.out.println(str_name);
					System.out.println(str_price);
					System.out.println(str_number);

					ContentValues values = new ContentValues();
					DBHelpe helper = new DBHelpe(getApplicationContext());

					if (!str_number.equals("") && isfirst == false) {
						values.put("name", str_name);
						values.put("price", str_price);
						values.put("number", str_number);
						values.put("image", str_image);

						helper.insert(values);

						helper.close();

						btn_yuding
								.setBackgroundResource(R.drawable.have_ordered);
						btn_yuding.setText("已购买");
						isfirst = true;
						Bookview.number++;
						Toast.makeText(Bookview.this, "已经加入购物车",
								Toast.LENGTH_SHORT).show();
						edit.setText("");
						conTrol();
					}
				}
			});
		} else if (id == R.id.btn_caiview_return) {
			btn_caiview_return.startAnimation(animation);
			animation.setAnimationListener(new AnimationListener() {

				@Override
				public void onAnimationStart(Animation arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onAnimationRepeat(Animation arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onAnimationEnd(Animation arg0) {
					Bookview.this.finish();
				}
			});
		} else {
		}

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		conTrol();// TODO Auto-generated method stub
		return false;
	}

	// public boolean onKeyDown(int keyCode, KeyEvent event) {
	// if(keyCode==KeyEvent.KEYCODE_BACK){
	// return true;
	// }// TODO Auto-generated method stub
	// return false;
	// }

	private void conTrol() {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(edit.getWindowToken(), 0);
	}

}
