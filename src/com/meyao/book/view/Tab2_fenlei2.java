package com.meyao.book.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.meyao.book.R;
import com.meyao.book.Zxing.CaptureActivity;
import com.meyao.book.modle.Book;
import com.meyao.book.util.AsyncImageLoader;
import com.meyao.book.util.AsyncImageLoader.ImageCallback;
import com.meyao.book.util.OrderHttpUtil;
import com.meyao.book.util.OrderUrlUtil;
import com.meyao.book.util.Util;
import com.umeng.fb.NotificationType;
import com.umeng.fb.UMFeedbackService;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Tab2_fenlei2 extends Activity implements View.OnClickListener {
	private ListView listview;
	private Button btn_fenleiall;
	private Button a22_sao;
	private EditText searchbyname;
	LinearLayout loadingLayout;
	List<Book> book;
	private SimpleAdapter adapter;
	
	private PopupWindow popupWindow, popupWindow1, popupWindow2, popupWindow3,
			popupWindow4, popupWindow5;
	private View popupWindow_view, popupWindow_view1, popupWindow_view2,
			popupWindow_view3, popupWindow_view4, popupWindow_view5;
	private Button btn_yuecai, btn_lucai, btn_chuancai, btn_guozai,
			btn_jiushui;// 五分类

	private Button btn_yuecai_qingchaoyoucai, btn_yuecai_danhuangniangao;
	private Button btn_lucai_shuiguohui, btn_lucai_xianchaohuamo;
	private Button btn_chuancai_laqiezi;
	private Button btn_guozai_xiaojidunmogu;
	private Button btn_jiushui_putaojiu, btn_jiushui_chengzhi;

	private boolean bln_yuecai = false;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.tab_a22);
		intview();
		loadlist();
	}

	private void intview() {

		a22_sao = (Button) findViewById(R.id.a22_sao);
		a22_sao.setOnClickListener(scan);
		searchbyname = (EditText) findViewById(R.id.searchbyname);
		searchbyname.setOnClickListener(onclicklisten);
		loadingLayout = (LinearLayout) findViewById(R.id.loadingLayout);
		listview = (ListView) findViewById(R.id.fenlei_listview);
		btn_fenleiall = (Button) findViewById(R.id.btn_fenlei_all);
		btn_fenleiall.setOnClickListener(this);
		
	}
	OnClickListener onclicklisten = new OnClickListener() {

		public void onClick(View v) {
			
			searchbyname.setText("");
			//未添加查找函数

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
			intent.setClass(Tab2_fenlei2.this, CaptureActivity.class);
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
				
				Intent intent2 = new Intent(Tab2_fenlei2.this, Bookview.class);
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
	/**
	 * 获取book info
	 */
	public void loadlist() {
		new Thread() {
			@Override
			public void run() {
				loadingLayout.setVisibility(View.VISIBLE);
				String url = OrderHttpUtil.BASE_URL
						+ OrderUrlUtil.SEARCHALL_URL;
				System.out.println(url);
				String result = OrderHttpUtil.getHttpPostResultForUrl(url);

				JSONArray data;
				try {
					JSONObject data1 = new JSONObject(result);

					data = data1.getJSONArray("rows");
					// System.out.println(data);
					for (int i = 0; i < data.length(); i++) {

						JSONObject item = data.getJSONObject(i);

						String id = item.getString("id");
						String code = item.getString("code");
						String name = item.getString("name");
						String writer = item.getString("writer");
						String publisher = item.getString("publisher");
						String price = item.getString("price");
						String seprice = item.getString("seprice");
						String desc = item.getString("desc");
						String image = item.getString("image");
						
						if (book == null) {
							book = new ArrayList<Book>();
						}
						
						Book books = new Book();

						books.setCode(code);
						books.setDesc(desc);
						books.setId(id);
						books.setImage(image);
						books.setName(name);
						books.setPrice(price);
						books.setPublisher(publisher);
						books.setSeprice(seprice);
						books.setWriter(writer);
						
						book.add(books);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}catch (NullPointerException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				Message message = handler.obtainMessage(0);
				handler.sendMessage(message);
			}
		}.start();

	}

	Handler handler = new Handler() {
		public void handleMessage(Message message) {
			if (book != null) { // 如果获取的消息列表不为空
				// 当每一条消息被点击时的响应
				listview.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> arg0, View view,
							int arg2, long arg3) {
						Object obj = view.getTag();
						if (obj != null) {
							Intent intent = new Intent(Tab2_fenlei2.this,
									Bookview.class);
							String codes = obj.toString();
							System.out.println("click codes:" + codes);

							Bundle bundle0 = new Bundle();
							bundle0.putString("name", codes);
							intent.putExtra("cainame", bundle0);

							startActivity(intent);
						}
					}

				});
				OnSaleAdapter adapter = new OnSaleAdapter(Tab2_fenlei2.this);
				listview.setAdapter(adapter);
				loadingLayout.setVisibility(View.GONE); // 更新完UI，让总转的进度条视图消失
			}
		}
	};

	public class OnSaleAdapter extends BaseAdapter {
		private AsyncImageLoader asyncImageLoader = new AsyncImageLoader();
		private Context context;
		
		public OnSaleAdapter(Context con) {
			this.context = con;
		}

		@Override
		public int getCount() {
			return book.size();
		}

		@Override
		public Object getItem(int position) {
			return book.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View satusView = null;
			if (convertView != null) {
				// 获取原来内存中保存的条目信息
				satusView = convertView;
			} else {
				satusView = LayoutInflater.from(getApplication()).inflate(
						R.layout.tav_a22_item, null);
			}
			BookHolder bh = new BookHolder(); // 微博消息实体
			bh.a2_imge = (ImageView) satusView.findViewById(R.id.a2_imge);
			bh.bname = (TextView) satusView.findViewById(R.id.bname);
			bh.writer = (TextView) satusView.findViewById(R.id.writer);
			bh.publisher = (TextView) satusView.findViewById(R.id.publisher);
			bh.price = (TextView) satusView.findViewById(R.id.price);
			bh.seprice = (TextView) satusView.findViewById(R.id.seprice);
			
			Book bt = book.get(position);
			if (bt != null) {
				satusView.setTag(bt.getCode());
				
				bh.bname.setText(bt.getName());
				
				bh.writer.setText("作者："+bt.getWriter());
				
				bh.publisher.setText("出版社："+bt.getPublisher());
				
				bh.price.setText("原价："+bt.getPrice());
				
				bh.seprice.setText("￥"+bt.getSeprice());
				
				String image_url = OrderHttpUtil.BASE_URL
						+ OrderUrlUtil.IMAGE_URL + bt.getImage() + ".jpg";
				
				Drawable cachedImage = asyncImageLoader.loadDrawable(
						image_url, bh.a2_imge, new ImageCallback() {
							@Override
							public void imageLoaded(Drawable imageDrawable,
									ImageView imageView, String imageUrl) {
								imageView.setImageDrawable(imageDrawable);
							}
						});

				if (cachedImage == null) {
					// 如果没有图片，就以一个载入的图片代替显示
					bh.a2_imge.setImageResource(R.drawable.zzsbd);
				} else {
					// 如果有图片，就载入图片
					bh.a2_imge.setImageDrawable(cachedImage);
				}
			}
			return satusView;
		}

	}
	public static class BookHolder {
		public ImageView a2_imge;
		public TextView bname;
		public TextView writer;
		public TextView publisher;
		public TextView price;
		public TextView seprice;
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			return true;
		}// TODO Auto-generated method stub
		return false;
	}

	protected void initPopuptWindow() {

		LayoutInflater mLayoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

		popupWindow_view = mLayoutInflater.inflate(R.layout.pop, null, false);

		// 创建PopupWindow实例,200,150分别是宽度和高度
		popupWindow = new PopupWindow(popupWindow_view, 250, 510, true);
		popupWindow.setBackgroundDrawable(new BitmapDrawable());
		popupWindow_view.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (popupWindow != null && popupWindow.isShowing()) {
					popupWindow.dismiss();
					popupWindow = null;
				}
				return false;
			}
		});
		// pop.xml视图里面的控件
		btn_yuecai = (Button) popupWindow_view.findViewById(R.id.btn_yuecai);
		btn_lucai = (Button) popupWindow_view.findViewById(R.id.btn_lucai);
		btn_chuancai = (Button) popupWindow_view
				.findViewById(R.id.btn_chuancai);
		btn_guozai = (Button) popupWindow_view.findViewById(R.id.btn_guozai);
		btn_jiushui = (Button) popupWindow_view.findViewById(R.id.btn_jiushui);

		btn_yuecai.setOnClickListener(this);
		btn_lucai.setOnClickListener(this);
		btn_chuancai.setOnClickListener(this);
		btn_guozai.setOnClickListener(this);
		btn_jiushui.setOnClickListener(this);
	}

	protected void initPopuptWindow1() {
		LayoutInflater mLayoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

		popupWindow_view1 = mLayoutInflater.inflate(R.layout.pop5, null, false);

		btn_yuecai_qingchaoyoucai = (Button) popupWindow_view1
				.findViewById(R.id.btn_yuecai_qingchaoyoucai);
		btn_yuecai_danhuangniangao = (Button) popupWindow_view1
				.findViewById(R.id.btn_yuecai_danhuangniangao);

		btn_yuecai_qingchaoyoucai.setOnClickListener(this);
		btn_yuecai_danhuangniangao.setOnClickListener(this);

		popupWindow_view1.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (popupWindow1 != null && popupWindow1.isShowing()) {
					popupWindow1.dismiss();
					popupWindow1 = null;
				}
				return false;
			}
		});
		// 创建PopupWindow实例,200,150分别是宽度和高度
		popupWindow1 = new PopupWindow(popupWindow_view1, 250, 515, true);
		popupWindow1.setAnimationStyle(R.style.AnimationFade);
		popupWindow1.setBackgroundDrawable(new BitmapDrawable());
	}

	protected void initPopuptWindow2() {
		LayoutInflater mLayoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		popupWindow_view2 = mLayoutInflater.inflate(R.layout.pop4, null, false);

		btn_lucai_shuiguohui = (Button) popupWindow_view2
				.findViewById(R.id.btn_lucai_shuiguohui);
		btn_lucai_xianchaohuamo = (Button) popupWindow_view2
				.findViewById(R.id.btn_lucai_xianchaohuamo);

		btn_lucai_shuiguohui.setOnClickListener(this);
		btn_lucai_xianchaohuamo.setOnClickListener(this);

		popupWindow_view2.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (popupWindow2 != null && popupWindow2.isShowing()) {
					popupWindow2.dismiss();
					popupWindow2 = null;
				}
				return false;
			}
		});
		// 创建PopupWindow实例,200,150分别是宽度和高度
		popupWindow2 = new PopupWindow(popupWindow_view2, 250, 515, true);
		popupWindow2.setAnimationStyle(R.style.AnimationFade);
		popupWindow2.setBackgroundDrawable(new BitmapDrawable());
	}

	protected void initPopuptWindow3() {
		LayoutInflater mLayoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		popupWindow_view3 = mLayoutInflater.inflate(R.layout.pop1, null, false);
		btn_chuancai_laqiezi = (Button) popupWindow_view3
				.findViewById(R.id.btn_chuancai_laqiezi);
		btn_chuancai_laqiezi.setOnClickListener(this);
		popupWindow_view3.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (popupWindow3 != null && popupWindow3.isShowing()) {
					popupWindow3.dismiss();
					popupWindow3 = null;
				}
				return false;
			}
		});
		// 创建PopupWindow实例,200,150分别是宽度和高度
		popupWindow3 = new PopupWindow(popupWindow_view3, 250, 515, true);
		popupWindow3.setAnimationStyle(R.style.AnimationFade);
		popupWindow3.setBackgroundDrawable(new BitmapDrawable());
	}

	protected void initPopuptWindow4() {
		LayoutInflater mLayoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		popupWindow_view4 = mLayoutInflater.inflate(R.layout.pop2, null, false);
		btn_guozai_xiaojidunmogu = (Button) popupWindow_view4
				.findViewById(R.id.btn_guozai_xiaojidunmogu);
		btn_guozai_xiaojidunmogu.setOnClickListener(this);
		popupWindow_view4.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (popupWindow4 != null && popupWindow4.isShowing()) {
					popupWindow4.dismiss();
					popupWindow4 = null;
				}
				return false;
			}
		});
		// 创建PopupWindow实例,200,150分别是宽度和高度
		popupWindow4 = new PopupWindow(popupWindow_view4, 250, 515, true);
		popupWindow4.setAnimationStyle(R.style.AnimationFade);
		popupWindow4.setBackgroundDrawable(new BitmapDrawable());
	}

	protected void initPopuptWindow5() {
		LayoutInflater mLayoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		popupWindow_view5 = mLayoutInflater.inflate(R.layout.pop3, null, false);

		btn_jiushui_putaojiu = (Button) popupWindow_view5
				.findViewById(R.id.btn_jiushui_putaojiu);
		btn_jiushui_chengzhi = (Button) popupWindow_view5
				.findViewById(R.id.btn_jiushui_chengzhi);

		btn_jiushui_putaojiu.setOnClickListener(this);
		btn_jiushui_chengzhi.setOnClickListener(this);

		popupWindow_view5.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (popupWindow5 != null && popupWindow5.isShowing()) {
					popupWindow5.dismiss();
					popupWindow5 = null;
				}
				return false;
			}
		});
		// 创建PopupWindow实例,200,150分别是宽度和高度
		popupWindow5 = new PopupWindow(popupWindow_view5, 250, 515, true);
		popupWindow5.setAnimationStyle(R.style.AnimationFade);
		popupWindow5.setBackgroundDrawable(new BitmapDrawable());
	}

	/*** * 获取PopupWindow实例 */
	private void getPopupWindow() {
		// initPopuptWindow();

		if (null != popupWindow) {
			popupWindow.dismiss();
			return;
		} else {
			initPopuptWindow();
		}
	}

	private void getPopupWindow1() {
		// initPopuptWindow();

		if (null != popupWindow1) {
			popupWindow1.dismiss();
			return;
		} else {
			initPopuptWindow1();
		}
	}

	private void getPopupWindow2() {
		// initPopuptWindow();

		if (null != popupWindow2) {
			popupWindow2.dismiss();
			return;
		} else {
			initPopuptWindow2();
		}
	}

	private void getPopupWindow3() {
		// initPopuptWindow();

		if (null != popupWindow3) {
			popupWindow3.dismiss();
			return;
		} else {
			initPopuptWindow3();
		}
	}

	private void getPopupWindow4() {
		// initPopuptWindow();

		if (null != popupWindow4) {
			popupWindow4.dismiss();
			return;
		} else {
			initPopuptWindow4();
		}
	}

	private void getPopupWindow5() {
		// initPopuptWindow();

		if (null != popupWindow5) {
			popupWindow5.dismiss();
			return;
		} else {
			initPopuptWindow5();
		}
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.btn_fenlei_all) {
			getPopupWindow();
			// 这里是位置显示方式,在按钮的左下角
			popupWindow.showAsDropDown(v);
		} else if (id == R.id.btn_yuecai) {
			getPopupWindow1();
			// 这里是位置显示方式,在按钮的左下角
			popupWindow1.showAsDropDown(findViewById(R.id.btn_fenlei_all), 300,
					0);
		} else if (id == R.id.btn_lucai) {
			getPopupWindow2();
			// 这里是位置显示方式,在按钮的左下角
			popupWindow2.showAsDropDown(findViewById(R.id.btn_fenlei_all), 300,
					0);
		} else if (id == R.id.btn_chuancai) {
			getPopupWindow3();
			// 这里是位置显示方式,在按钮的左下角
			popupWindow3.showAsDropDown(findViewById(R.id.btn_fenlei_all), 300,
					0);
		} else if (id == R.id.btn_guozai) {
			getPopupWindow4();
			// 这里是位置显示方式,在按钮的左下角
			popupWindow4.showAsDropDown(findViewById(R.id.btn_fenlei_all), 300,
					0);
		} else if (id == R.id.btn_jiushui) {
			getPopupWindow5();
			// 这里是位置显示方式,在按钮的左下角
			popupWindow5.showAsDropDown(findViewById(R.id.btn_fenlei_all), 300,
					0);
		} else if (id == R.id.btn_yuecai_qingchaoyoucai) {
			popupWindow.dismiss();
			popupWindow1.dismiss();
			ViewKinds.vegetable = 1;
			Intent intent = new Intent(Tab2_fenlei2.this, ViewKinds.class);
			startActivity(intent);
		} else if (id == R.id.btn_yuecai_danhuangniangao) {
			popupWindow.dismiss();
			popupWindow1.dismiss();
			ViewKinds.vegetable = 1;
			Intent intent1 = new Intent(Tab2_fenlei2.this, ViewKinds.class);
			startActivity(intent1);
		} else if (id == R.id.btn_lucai_shuiguohui) {
			popupWindow.dismiss();
			popupWindow2.dismiss();
			ViewKinds.vegetable = 2;
			Intent intent2 = new Intent(Tab2_fenlei2.this, ViewKinds.class);
			startActivity(intent2);
		} else if (id == R.id.btn_lucai_xianchaohuamo) {
			popupWindow.dismiss();
			popupWindow2.dismiss();
			ViewKinds.vegetable = 2;
			Intent intent3 = new Intent(Tab2_fenlei2.this, ViewKinds.class);
			startActivity(intent3);
		} else if (id == R.id.btn_chuancai_laqiezi) {
			popupWindow.dismiss();
			popupWindow3.dismiss();
			ViewKinds.vegetable = 3;
			Intent intent4 = new Intent(Tab2_fenlei2.this, ViewKinds.class);
			startActivity(intent4);
		} else if (id == R.id.btn_guozai_xiaojidunmogu) {
			popupWindow.dismiss();
			popupWindow4.dismiss();
			ViewKinds.vegetable = 4;
			Intent intent5 = new Intent(Tab2_fenlei2.this, ViewKinds.class);
			startActivity(intent5);
		} else if (id == R.id.btn_jiushui_putaojiu) {
			popupWindow.dismiss();
			popupWindow5.dismiss();
			ViewKinds.vegetable = 5;
			Intent intent6 = new Intent(Tab2_fenlei2.this, ViewKinds.class);
			startActivity(intent6);
		} else if (id == R.id.btn_jiushui_chengzhi) {
			popupWindow.dismiss();
			popupWindow5.dismiss();
			ViewKinds.vegetable = 5;
			Intent intent7 = new Intent(Tab2_fenlei2.this, ViewKinds.class);
			startActivity(intent7);
		} else {
		}

	}

}
