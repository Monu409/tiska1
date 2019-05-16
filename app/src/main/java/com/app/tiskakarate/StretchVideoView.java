package com.app.tiskakarate;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

public class StretchVideoView extends VideoView {
	
	int mVideoWidth=500;
	int mVideoHeight=500;
	MediaController media_Controller;

	public StretchVideoView(Context context) {
		super(context);
	}

	public StretchVideoView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public StretchVideoView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		 int width = getDefaultSize(mVideoWidth, widthMeasureSpec);
		 int height = getDefaultSize(mVideoHeight, heightMeasureSpec);
		 Log.e("width",""+width);
		 Log.e("width",""+height);
		 Log.e("widthMeasureSpec",""+widthMeasureSpec);
		 Log.e("heightMeasureSpec",""+heightMeasureSpec);
		 if (mVideoWidth > 0 && mVideoHeight > 0) {
		 if ( mVideoWidth * height > width * mVideoHeight ) {
		 //Log.i("@@@", "image too tall, correcting");
		 height = width * mVideoHeight / mVideoWidth;
		 } else if ( mVideoWidth * height < width * mVideoHeight ) {
		 //Log.i("@@@", "image too wide, correcting");
		 width = height * mVideoWidth / mVideoHeight;
		 } else {
		 //Log.i("@@@", "aspect ratio is correct: " +
		 //width+"/"+height+"="+
		 //mVideoWidth+"/"+mVideoHeight);
		 }
		 }
		 //Log.i("@@@@@@@@@@", "setting size: " + width + 'x' + height);
		 setMeasuredDimension(525,600);
		 Log.e("width",""+width);
		 Log.e("width",""+height);
		 } 
	public boolean checkStatus()
	{
		if(isPlaying())
		{
			return true;
		}
		else
		{
		return false;
		}
		
	}
	public void stopVideo()
	{
		stopPlayback();
	}
	public void myMediaControll()
	{
		setMediaController(media_Controller);
	}
}