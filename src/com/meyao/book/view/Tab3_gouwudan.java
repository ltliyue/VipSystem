package com.meyao.book.view;

import java.util.List;

import com.meyao.book.R;
import com.meyao.book.db.DBHelpe;
import com.meyao.book.modle.Mode;
import com.meyao.book.util.AsyncImageLoader;
import com.meyao.book.util.OrderHttpUtil;
import com.meyao.book.util.OrderUrlUtil;
import com.meyao.book.util.AsyncImageLoader.ImageCallback;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Tab3_gouwudan extends Activity implements View.OnClickListener,
		OnTouchListener {
	protected static final int CHANGE_IMAGE = 1;
	private Cursor cursor;
	private DBHelpe helpter;
	private SimpleCursorAdapter adapter;
	private ListView listView;
	private ImageView image;
	private String imagee;
	private String[] from = { "_id", "name", "number", "price"};
	private int[] to = { R.id.TextView1, R.id.TextView2, R.id.TextView3,
			R.id.TextView4};
	private Button btn_gouwuche_return;
	private EditText edit_kaizhuo;
	private Animation animation;
	public static boolean replace = false;
	private RelativeLayout relative;
	private TextView tv_allprice;
	private DBHelpe db_price;
	private int allprice;
	private DBHelpe db_Price;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_a33);
		intview();
		cursor = helpter.query();
		adapter = new SimpleCursorAdapter(this, R.layout.list, cursor, from, to);
		this.allprice();

		listView.setAdapter(adapter);
		tv_allprice.setText("总价￥" + allprice + "元");
		this.dialog();

		listView.setDivider(null);
	}

	public void allprice() {
		try {
			List<Mode> list_price = db_price.fetchValue();
			for (Mode price : list_price) {
				int i = Integer.valueOf(price.getNumber());
				int j = Integer.valueOf(price.getPrice());

				System.out.println(i);
				imagee = price.getImage();
				System.out.println(imagee);
				allprice += i * j;

			}

		} catch (NullPointerException e) {
			// TODO: handle exception
			System.out.println(e);
		}

	}


	public void gouwuchenumber() {
		if (Bookview.number > 0) {
			Bookview.number--;
			int i = Bookview.number;
			if (i == 0) {
				CallbyeTabActivity.textview.setVisibility(View.GONE);
			} else {
				CallbyeTabActivity.textview.setVisibility(View.VISIBLE);
				CallbyeTabActivity.textview.setText("" + i);
			}

		}
	}

	/**
	 * 
	 * 組件初始化
	 */

	private void intview() {
		btn_gouwuche_return = (Button) findViewById(R.id.btn_gouwuche_return);
		relative = (RelativeLayout) findViewById(R.id.gouwucheralative);
		tv_allprice = (TextView) findViewById(R.id.tv_zongjia);
		listView = (ListView) findViewById(R.id.list1);
		image = (ImageView) findViewById(R.id.cart_imge);

		db_price = new DBHelpe(Tab3_gouwudan.this);

		relative.setOnTouchListener(this);
		helpter = new DBHelpe(this);
		animation = AnimationUtils.loadAnimation(Tab3_gouwudan.this,
				R.anim.anim_click_info);
		btn_gouwuche_return.setOnClickListener(this);
		db_Price = new DBHelpe(this);

	}

	public void cleandialog() {
		final AlertDialog.Builder dialog = new AlertDialog.Builder(this);

		dialog.setTitle("提示")
				.setMessage("真的要清空客户的购物单数据吗？")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {

						helpter.clean();
						db_Price.clean();
						Bookview.number = 0;
						Intent intent = new Intent(Tab3_gouwudan.this,
								CallbyeTabActivity.class);
						startActivity(intent);
					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
					}
				});
		AlertDialog dialog_dialog = dialog.create();
		dialog_dialog.show();

	}

	public void diancan() {
		final AlertDialog.Builder dialog = new AlertDialog.Builder(this);

		dialog.setTitle("提示")
				.setMessage("            ")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(Tab3_gouwudan.this, "       ",
								Toast.LENGTH_SHORT).show();
					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
					}
				});
		AlertDialog dialog_dialog = dialog.create();
		dialog_dialog.show();

	}

	/**
	 * 
	 * 对话框
	 */

	public void dialog() {
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		listView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				final long temp = arg3;
				builder.setTitle("提示")
						.setMessage("确定要删除？")
						.setPositiveButton("确定",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										helpter.del((int) temp);
										db_price.del((int) temp);
										allprice = 0;
										Tab3_gouwudan.this.updateprice();
										Tab3_gouwudan.this.allprice();
										tv_allprice.setText("总价￥" + allprice
												+ "元");
										Tab3_gouwudan.this.gouwuchenumber();

										List<Mode> list = helpter.fetchValue();
										helpter.clean();
										for (Mode mode : list) {
											String s1 = mode.getName();
											String s2 = mode.getPrice();
											String s3 = mode.getNumber();
											helpter = new DBHelpe(
													Tab3_gouwudan.this);
											ContentValues values = new ContentValues();
											values.put("name", s1);
											values.put("number", s2);
											values.put("price", s3);
											helpter.insert(values);

										}
										cursor = helpter.query();
										adapter = new SimpleCursorAdapter(
												Tab3_gouwudan.this,
												R.layout.list, cursor, from, to);
										listView.setAdapter(adapter);
									}
								})
						.setNegativeButton("取消",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {

									}
								});
				AlertDialog ad = builder.create();
				ad.show();
			}
		});
		helpter.close();

	}

	public void updateprice() {
		List<Mode> list_price = db_price.fetchValue();
		db_price.clean();
		for (Mode price : list_price) {
			String s2 = price.getPrice();
			// System.out.println("s2:" + s2);
			String s3 = price.getNumber();
			// System.out.println("s3" + s3);
			ContentValues valuesprice = new ContentValues();
			valuesprice.put("number", s2);
			valuesprice.put("price", s3);
			db_price.insert(valuesprice);
		}
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			return true;
		}// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.btn_gouwuche_return) {
			btn_gouwuche_return.startAnimation(animation);
			animation.setAnimationListener(new AnimationListener() {

				@Override
				public void onAnimationStart(Animation animation) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onAnimationRepeat(Animation animation) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onAnimationEnd(Animation animation) {
					cleandialog();

				}
			});
		} else {
		}

	}

	private void KeyGone() {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(edit_kaizhuo.getWindowToken(), 0);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		KeyGone();// TODO Auto-generated method stub
		return false;
	}
}
