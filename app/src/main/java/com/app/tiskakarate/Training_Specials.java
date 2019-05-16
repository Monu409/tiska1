package com.app.tiskakarate;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import static com.app.tiskakarate.Main2.change_text;

public class Training_Specials extends Fragment implements OnClickListener {
	
	Button play_video,play_pause_btn,fullscreen_btn;
	ProgressBar progressBar;
	VideoView videoView;
	MediaController controller;
	RelativeLayout play_pause_layout,video_Layout;
	boolean train_max;
	LinearLayout training_back;
	String video_link="https://dl.dropboxusercontent.com/u/15153339/tiska/specialvideo/tiska_special-intro.mp4";
	
	@SuppressLint("ClickableViewAccessibility")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.training_specials,container, false);
		change_text.setText(this.getTag());
		play_video= view.findViewById(R.id.video_play_img_train);
		progressBar= view.findViewById(R.id.progressBar_train);
		videoView= view.findViewById(R.id.show_video);
		controller= view.findViewById(R.id.mediaController_vdo_train);
		play_pause_layout= view.findViewById(R.id.trans_layout_train);
		play_pause_btn= view.findViewById(R.id.play_train);
		fullscreen_btn= view.findViewById(R.id.fullScreen_train);
		video_Layout= view.findViewById(R.id.video_layout_train);
		training_back= view.findViewById(R.id.train_back);
		play_pause_layout.getBackground().setAlpha(100);
		play_video.setOnClickListener(this);
		play_pause_btn.setOnClickListener(this);
		fullscreen_btn.setOnClickListener(this);
		training_back.setOnClickListener(this);
		
		videoView.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
//				
				play_pause_layout.getBackground().setAlpha(100);
				Main2.sharing.setVisibility(View.INVISIBLE);
				Main2.share_show=false;
				if(controller.isShown())
				{
					controller.setVisibility(View.INVISIBLE);
				}
				else
				{
				final Handler handler = new Handler();
				controller.setVisibility(View.VISIBLE);
				// TODO Auto-generated method stub
				new Thread(new Runnable() { 
				    public void run() {
				         try{
				             Thread.sleep(10000);
				         }
				         catch (Exception e) {
				         	e.getMessage();
						 } //
				         handler.post(new Runnable() { 
				            public void run() {
				               controller.setVisibility(View.INVISIBLE);
				            }
				        });
				    }
				}).start();
				}
				return false;
			}
		});

		return view;
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		if(v.getId()==R.id.video_play_img_train)
		{
			Main2.sharing.setVisibility(View.INVISIBLE);
			Main2.share_show=false;
			play_video.setVisibility(View.INVISIBLE);
			playVideo(video_link);
		}
		if(v.getId()==R.id.play_train)
		{
			if(videoView.isPlaying()){
				videoView.pause();
				play_pause_btn.setBackgroundResource(R.drawable.play_small);
			} else {
				play_pause_btn.setBackgroundResource(R.drawable.pause_small);
				videoView.start();
			}
		}
		if(v.getId()==R.id.fullScreen_train)
		{
			if(!train_max)
			{
//			video_Layout.setBackgroundColor(Color.parseColor("#000000"));
				DisplayMetrics metrics = new DisplayMetrics();
				getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
			    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) videoView.getLayoutParams();
			    params.width =  metrics.widthPixels;
			    params.height = metrics.heightPixels;
			    params.leftMargin = 0;
			    params.rightMargin=0;
			    videoView.setLayoutParams(params);
			    training_back.setBackgroundColor(Color.parseColor("#000000"));
			    train_max=true;
			}
			else
			{
//				video_Layout.setBackgroundResource(R.drawable.bg_720);
					DisplayMetrics metrics = new DisplayMetrics();
					getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
				    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) videoView.getLayoutParams();
				    params.width =  metrics.widthPixels;
				    params.height = metrics.heightPixels;
				    params.leftMargin = 60;
				    params.rightMargin=105;
				    videoView.setLayoutParams(params);
				    training_back.setBackgroundResource(R.drawable.train_special);
				    train_max=false;
			}
		}
		if(v.getId()==R.id.train_back)
		{
			Main2.sharing.setVisibility(View.INVISIBLE);
			Main2.share_show=false;
		}
	}
	
	public void playVideo(String video_link)
	{
		progressBar.setVisibility(View.VISIBLE);
		Uri uri=Uri.parse(video_link);
		videoView.setVideoURI(uri);
		videoView.start();
		videoView.requestFocus();
		videoView.setOnPreparedListener(new OnPreparedListener() {
		public void onPrepared(MediaPlayer mp) {
		progressBar.setVisibility(View.INVISIBLE);
		videoView.setBackgroundResource(0);
		videoView.start();
		controller.setVisibility(View.VISIBLE);
		final Handler handler = new Handler();
		controller.setVisibility(View.VISIBLE);
		// TODO Auto-generated method stub
		new Thread(new Runnable() { 
		    public void run() {
		         try{
		             Thread.sleep(10000);
		         }
		         catch (Exception e) { }
		         handler.post(new Runnable() { 
		            public void run() {
		               controller.setVisibility(View.INVISIBLE);
		            }
		        });
		    }
		}).start();
		}
		});
	}

	

}
