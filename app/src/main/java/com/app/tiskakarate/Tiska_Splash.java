package com.app.tiskakarate;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.WindowManager;

import static com.app.tiskakarate.Utils.getDisplaySize;
import static com.app.tiskakarate.Utils.height;
import static com.app.tiskakarate.Utils.isTab;
import static com.app.tiskakarate.Utils.isTablet;
import static com.app.tiskakarate.Utils.width;

public class Tiska_Splash extends Activity {
    @Override
    protected void onStart() {
        super.onStart();

        if (isTablet(this)) {
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            isTab = true;
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            isTab = false;
        }
        width = getDisplaySize(this).widthPixels;
        height = getDisplaySize(this).heightPixels;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tiska_splash);

        Thread t = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (Exception e) {
                    System.out.println(e);
                }

                Intent i = new Intent(Tiska_Splash.this, Main2.class);
                startActivity(i);
                finish();
            }
        };
        t.start();
    }

}
