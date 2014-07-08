package com.meyao.book.view;

import com.meyao.book.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class ViewKinds extends Activity implements View.OnClickListener
 {
	private LinearLayout layout;
	private View currentView1;
	public static int vegetable=0;
	private Button btn_yuecai_qingchaoyoucai,btn_yuecai_danhuangniangao,
					btn_lucai_shuiguohui,btn_lucai_xianchaohuamo,
					btn_chuancai_laqiezizi,
					btn_guozai_xiaojidunmogugu;
	
	private Button btn_jiushui_putaojiujiu,btn_jiushui_chengzhizhi;
	private Button btn_fenlei_return;
	private Animation animation;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		try {
			whichkind();
			animation=AnimationUtils.loadAnimation(ViewKinds.this, R.anim.anim_click_info);
			

			
		} catch (NullPointerException e) {
			// TODO: handle exception
			System.out.println(e);
		}
	
	}
	
	
	public void whichkind()	{
		
//		btn_fenlei_return=(Button)findViewById(R.id.btn_fenlei_return);
//		btn_fenlei_return.setOnClickListener(this);
		
		if(ViewKinds.vegetable==1){
			LayoutInflater inflater = LayoutInflater.from(ViewKinds.this);
		    layout = (LinearLayout) inflater.inflate(R.layout.book5, null);
            setContentView(layout);
            btn_yuecai_qingchaoyoucai=(Button)this.findViewById(R.id.btn_yuecai_qingchaoyoucai);
            btn_yuecai_danhuangniangao=(Button)this.findViewById(R.id.btn_yuecai_danghuang);
            
            btn_yuecai_qingchaoyoucai.setOnClickListener(this);
            btn_yuecai_danhuangniangao.setOnClickListener(this);
		}
		if(ViewKinds.vegetable==2){	
			LayoutInflater inflater = LayoutInflater.from(ViewKinds.this);
		    layout = (LinearLayout) inflater.inflate(R.layout.book4, null);
            setContentView(layout);
            btn_lucai_shuiguohui=(Button)this.findViewById(R.id.btn_lucai_shuiguohuihui);
            btn_lucai_xianchaohuamo=(Button)this.findViewById(R.id.btn_lucai_xianchaohuamomo);
            
            btn_lucai_shuiguohui.setOnClickListener(this);
            btn_lucai_xianchaohuamo.setOnClickListener(this);
		}
		if(ViewKinds.vegetable==3){	
			LayoutInflater inflater = LayoutInflater.from(ViewKinds.this);
		    layout = (LinearLayout) inflater.inflate(R.layout.book1, null);
            setContentView(layout);
            btn_chuancai_laqiezizi=(Button)this.findViewById(R.id.btn_chuancai_laqiezizi);
            
            btn_chuancai_laqiezizi.setOnClickListener(this);
		}
		if(ViewKinds.vegetable==4){
			LayoutInflater inflater = LayoutInflater.from(ViewKinds.this);
		    layout = (LinearLayout) inflater.inflate(R.layout.book2, null);
            setContentView(layout);
            btn_guozai_xiaojidunmogugu=(Button)this.findViewById(R.id.btn_guozai_xiaojidunmogugu);
            btn_guozai_xiaojidunmogugu.setOnClickListener(this);
		}
		if(ViewKinds.vegetable==5){
			LayoutInflater inflater = LayoutInflater.from(ViewKinds.this);
		    layout = (LinearLayout) inflater.inflate(R.layout.book3, null);
            setContentView(layout);
            btn_jiushui_putaojiujiu=(Button)this.findViewById(R.id.btn_jiushui_putaojiujiu);
            btn_jiushui_chengzhizhi=(Button)this.findViewById(R.id.btn_jiushui_chengzhizhi);
            
            btn_jiushui_putaojiujiu.setOnClickListener(this);
            btn_jiushui_chengzhizhi.setOnClickListener(this);
		}
		
		}


	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.btn_yuecai_qingchaoyoucai) {
			btn_yuecai_qingchaoyoucai.startAnimation(animation);
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
					// TODO Auto-generated method stub
					  Intent intent=new Intent(ViewKinds.this,Bookview.class);
					  Bundle bundle=new Bundle();
					  bundle.putString("name", "9787538259995");
					  intent.putExtra("cainame",bundle);
					  startActivity(intent); 
				}
			});
		} else if (id == R.id.btn_yuecai_danghuang) {
			btn_yuecai_danhuangniangao.startAnimation(animation);
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
					// TODO Auto-generated method stub
					  Intent intent=new Intent(ViewKinds.this,Bookview.class);
				  Bundle bundle=new Bundle();
				  bundle.putString("name", "9787535464750");
				  intent.putExtra("cainame",bundle);
				  startActivity(intent); 
				}
			});
		} else if (id == R.id.btn_lucai_shuiguohuihui) {
			btn_lucai_shuiguohui.startAnimation(animation);
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
					// TODO Auto-generated method stub
					  Intent intent=new Intent(ViewKinds.this,Bookview.class);
				      Bundle bundle=new Bundle();
				  bundle.putString("name", "9787807639565");
				  intent.putExtra("cainame",bundle);
				  startActivity(intent); 
				}
			});
		} else if (id == R.id.btn_lucai_xianchaohuamomo) {
			btn_lucai_xianchaohuamo.startAnimation(animation);
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
					// TODO Auto-generated method stub
					  Intent intent=new Intent(ViewKinds.this,Bookview.class);
				      Bundle bundle=new Bundle();
				  bundle.putString("name", "9787508344980");
				  intent.putExtra("cainame",bundle);
				  startActivity(intent); 
				}
			});
		} else if (id == R.id.btn_chuancai_laqiezizi) {
			btn_chuancai_laqiezizi.startAnimation(animation);
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
					// TODO Auto-generated method stub
					  Intent intent=new Intent(ViewKinds.this,Bookview.class);
				      Bundle bundle=new Bundle();
				      bundle.putString("name", "9787535464750");
				     intent.putExtra("cainame",bundle);
				     startActivity(intent); 
				}
			});
		} else if (id == R.id.btn_guozai_xiaojidunmogugu) {
			btn_guozai_xiaojidunmogugu.startAnimation(animation);
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
					// TODO Auto-generated method stub
					  Intent intent=new Intent(ViewKinds.this,Bookview.class);
				      Bundle bundle=new Bundle();
				      bundle.putString("name", "9787807639565");
				     intent.putExtra("cainame",bundle);
				     startActivity(intent); 
				}
			});
		} else if (id == R.id.btn_jiushui_putaojiujiu) {
			btn_jiushui_putaojiujiu.startAnimation(animation);
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
					// TODO Auto-generated method stub
					  Intent intent=new Intent(ViewKinds.this,Bookview.class);
				      Bundle bundle=new Bundle();
				      bundle.putString("name", "9787040212778");
				     intent.putExtra("cainame",bundle);
				     startActivity(intent); 
				}
			});
		} else if (id == R.id.btn_jiushui_chengzhizhi) {
			btn_jiushui_chengzhizhi.startAnimation(animation);
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
					// TODO Auto-generated method stub
					  Intent intent=new Intent(ViewKinds.this,Bookview.class);
				      Bundle bundle=new Bundle();
				      bundle.putString("name", "9787508344980");
				     intent.putExtra("cainame",bundle);
				     startActivity(intent); 
				}
			});
		} else {
		}
		
	}
	
	

}
