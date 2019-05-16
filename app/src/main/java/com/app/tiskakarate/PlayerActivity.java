package com.app.tiskakarate;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.app.tiskakarate.Utils.height;
import static com.app.tiskakarate.Utils.isTab;
import static com.app.tiskakarate.Utils.width;

public class PlayerActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    Uri targetUri;

    MediaPlayer mediaPlayer;
    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;
    boolean pausing = false;
    boolean isFirstTime = true;
    private SeekBar progressBar;
    private Button fullScreen, mute_btn;
    private Button btnPayPause;
    private TextView timeForMediaPlayer;
    RelativeLayout root_view;
    ProgressBar progressdialog;

    ImageView close_btn;

    @Override
    protected void onStart() {
        super.onStart();
        if (isTab) {
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playerlayout);
        targetUri = Uri.parse(getIntent().getExtras().getString("url"));
        initialize();
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setFixedSize(16, 14);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setScreenOnWhilePlaying(true);

        surfaceView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                Main2.sharing.setVisibility(View.INVISIBLE);
                Main2.share_show = false;
                final View view = ((ViewGroup) btnPayPause.getParent());
                if (view.isShown()) {
                    view.setVisibility(View.INVISIBLE);
                } else {
                    final Handler handler = new Handler();
                    view.setVisibility(View.VISIBLE);
                    // TODO Auto-generated method stub
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                Thread.sleep(10000);
                            } catch (Exception e) {
                            }
                            handler.post(new Runnable() {
                                public void run() {
                                    view.setVisibility(View.INVISIBLE);
                                }
                            });
                        }
                    }).start();
                }
                return false;
            }
        });


        btnPayPause.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                play_video();

            }
        });

        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                progressBar.setSecondaryProgress(percent);
            }

        });

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.e("video", " prepared");
                progressdialog.setVisibility(View.INVISIBLE);
                new Thread(runnable).start();
                final Handler handler = new Handler();
                ((ViewGroup) btnPayPause.getParent()).setVisibility(View.VISIBLE);
                // TODO Auto-generated method stub
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            Thread.sleep(10000);
                        } catch (Exception e) {
                        }
                        handler.post(new Runnable() {
                            public void run() {
                                // Set the View's visibility back on the main UI Thread
                                ((ViewGroup) btnPayPause.getParent()).setVisibility(View.INVISIBLE);
                            }
                        });
                    }
                }).start();
            }
        });

        progressBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (mediaPlayer != null && fromUser) {
                    int timeToSet = (mediaPlayer.getDuration() * progress) / 100;
                    mediaPlayer.seekTo(timeToSet);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        fullScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFullScreen();
            }
        });

        setVolumeControl(mediaPlayer);

        mediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                switch (what) {
                    case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                        progressdialog.setVisibility(View.VISIBLE);
                        break;
                    case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                        progressdialog.setVisibility(View.GONE);
                        break;
                }
                return false;
            }
        });
    }

    boolean mVolumePlaying = true;

    private void setVolumeControl(final MediaPlayer mp) {

        mute_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mp != null) {
                    if (mVolumePlaying) {
                        Log.d("volume off ", "setVolume OFF");
                        mute_btn.setBackgroundResource(R.drawable.mute);
                        mp.setVolume(0F, 0F);
                    } else {
                        Log.d("volume on", "setVolume ON");
                        mute_btn.setBackgroundResource(R.drawable.unmute);
                        mp.setVolume(1F, 1F);
                    }
                    mVolumePlaying = !mVolumePlaying;
                }
            }
        });
    }


    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(100);

                    int currentTime = mediaPlayer.getCurrentPosition();
                    if (mediaPlayer.getDuration() > 0) {
                        int percent = (100 * currentTime) / mediaPlayer.getDuration();
                        progressBar.setProgress(percent);
                        final String timeToShow = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(currentTime),
                                TimeUnit.MILLISECONDS.toMinutes(currentTime) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(currentTime)),
                                TimeUnit.MILLISECONDS.toSeconds(currentTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(currentTime)));
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                timeForMediaPlayer.setText(timeToShow);
                            }
                        });

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IllegalStateException e) {
//                    mediaPlayer.reset();
//                    progressBar.setProgress(runtime);
                    break;
                }
            }
        }
    };

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        mediaPlayer.release();
    }

    @Override
    public void onPause() {
        super.onPause();
        pausing = true;
        mediaPlayer.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mediaPlayer.reset();
    }

    @Override
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub

        Log.e("video", " surfaceChanged");

    }

    @Override
    public void surfaceCreated(SurfaceHolder arg0) {
        // TODO Auto-generated method stub
        progressdialog.setVisibility(View.VISIBLE);
        Log.e("video", " surfaceCreated");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressdialog.setVisibility(View.VISIBLE);
                play_video();
            }
        }, 1000);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder arg0) {
        // TODO Auto-generated method stub

        Log.e("video", " surfaceDestroyed");
    }

    private void initialize() {
        timeForMediaPlayer = findViewById(R.id.timeForMediaPlayer);
        progressBar = findViewById(R.id.progressBar);
        progressdialog = findViewById(R.id.progressdialog);
        mute_btn = findViewById(R.id.mute_btn);
        fullScreen = findViewById(R.id.fullScreen);
        btnPayPause = findViewById(R.id.play);
        surfaceView = findViewById(R.id.surfaceview);
        root_view = findViewById(R.id.root_view);
        close_btn = findViewById(R.id.close_btn);

        if (isTab) {
            fullScreen.setVisibility(View.GONE);
        } else {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) surfaceView.getLayoutParams();
            params.width = width;
            params.height = (int) (height / 3.0);
        }
        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    boolean mFullScreen;

    public void setFullScreen() {
        if (!mFullScreen) {
            Log.v("FullScreen", "-----------Set full screen SCREEN_ORIENTATION_LANDSCAPE------------");
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) surfaceView.getLayoutParams();
//            params.width = root_view.getHeight();
            params.width = height;
//            params.height = hight;
            params.height = width;
            params.setMargins(0, 0, 0, 0);
            //set icon is full screen
            mFullScreen = !mFullScreen;
        } else {
            Log.v("FullScreen", "-----------Set small screen SCREEN_ORIENTATION_PORTRAIT------------");
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//            final FrameLayout mFrame = (FrameLayout) findViewById(R.id.videoSurfaceContainer);
            int height = root_view.getHeight();//get height Frame Container video
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) surfaceView.getLayoutParams();
            params.width = width;
            params.height = (int) (height / 1.5);
//            params.height = height;
            params.setMargins(0, 0, 0, 0);
            //set icon is small screen
            mFullScreen = !mFullScreen;
        }
    }

    @Override
    public void onBackPressed() {
        if (!mFullScreen) {
            super.onBackPressed();
        } else {
            setFullScreen();
        }
    }

    //  if (!pausing) {
//
//        new Player().execute(getIntent().getExtras().getString("url"));
//
//        pausing = true;
//    } else {
//        if (mediaPlayer.isPlaying())
//            mediaPlayer.pause();
//        pausing = false;
//    }
    public void play_video() {
        if (pausing) {
            pausing = false;
            mediaPlayer.start();
            btnPayPause.setBackgroundResource(R.drawable.pause_small);
        } else {
            if (isFirstTime) {
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setDisplay(surfaceHolder);
                new Player().execute(targetUri);

//                try {
//                    mediaPlayer.setDataSource(this, targetUri);
//                    mediaPlayer.prepare();
//                } catch (IllegalArgumentException e) {
//                    e.printStackTrace();
//                } catch (IllegalStateException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                mediaPlayer.start();
                isFirstTime = false;
                btnPayPause.setBackgroundResource(R.drawable.pause_small);
            } else {
                pausing = true;
                mediaPlayer.pause();
                btnPayPause.setBackgroundResource(R.drawable.play_small);
            }
        }
    }

    class Player extends AsyncTask<Uri, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Uri... uris) {
            // TODO Auto-generated method stub
            Boolean prepared;
            try {

                mediaPlayer.setDataSource(PlayerActivity.this, uris[0]);

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        // TODO Auto-generated method stub
                        pausing = false;
//                        btn.setBackgroundResource(R.drawable.button_play);
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });
                mediaPlayer.prepare();
                prepared = true;
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                Log.d("IllegarArgument", e.getMessage());
                prepared = false;
                e.printStackTrace();
            } catch (SecurityException e) {
                // TODO Auto-generated catch block
                prepared = false;
                e.printStackTrace();
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                prepared = false;
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                prepared = false;
                e.printStackTrace();
            }
            return prepared;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            if (progressdialog.getVisibility() == View.VISIBLE) {
                progressdialog.setVisibility(View.GONE);
            }
            Log.d("Prepared", "//" + result);
            mediaPlayer.start();
        }


        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            progressdialog.setVisibility(View.VISIBLE);

        }
    }

}
