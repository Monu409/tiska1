package com.app.tiskakarate;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import static com.app.tiskakarate.Main2.change_text;

public class About extends Fragment {
	
	LinearLayout back;
	ScrollView scroll_about;
	Button scrol_btn;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.about,container, false);
		change_text.setText(this.getTag());
		scroll_about= view.findViewById(R.id.scrollView_about);
		scrol_btn= view.findViewById(R.id.button_scrool);
		back= view.findViewById(R.id.about_back);
		back.setBackgroundColor(Color.BLACK);
		
		scroll_about.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				Main2.sharing.setVisibility(View.INVISIBLE);
				Main2.share_show=false;
				return false;
			}
		});
		scrol_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Main2.sharing.setVisibility(View.INVISIBLE);
				Main2.share_show=false;
			}
		});
		return view;
	}

}
