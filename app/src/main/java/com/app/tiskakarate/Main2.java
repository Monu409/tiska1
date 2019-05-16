package com.app.tiskakarate;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sromku.simple.fb.SimpleFacebook;

import java.util.List;
import java.util.Locale;

import static com.app.tiskakarate.Utils.isTab;
import static com.app.tiskakarate.Utils.width;

public class Main2 extends AppCompatActivity implements OnClickListener {


    SimpleFacebook simpleFacebook;
    FragmentTransaction fragmentTransaction;
    ImageView drawer, _share_icon;
    LinearLayout videos, downloads, about, train_spe;
    LinearLayout cmove_layout, frag_layout;
    static LinearLayout sharing;
    public static TextView change_text;
    String shareText = "Learn the Shotokan Karate way with a complete video instruction course. " +
            "Available on iPad and iPhone http://www.tiska.com";
    Button shocialShare, facebookShare, twitterShare;
    RelativeLayout header_lay;
    private long lastClickTime = 0;
    static boolean share_show;
    String message;
    DrawerLayout nav_drawer;
    private TextView videoTxt, downloadTxt, aboutTxt, newVideoTxt;
    NavigationView nav_view;

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
        setContentView(R.layout.activity_main2);
        simpleFacebook = SimpleFacebook.getInstance(this);
        header_lay = findViewById(R.id.header);
        frag_layout = findViewById(R.id.lay2);
        facebookShare = findViewById(R.id.facebook_share);
        twitterShare = findViewById(R.id.twitter_share);
        drawer = findViewById(R.id.drawer_img_main);
        cmove_layout = findViewById(R.id.move_layout);
        videos = findViewById(R.id.videos_img);
        downloads = findViewById(R.id.download_img);
        sharing = findViewById(R.id.sharing_tiska);
        _share_icon = findViewById(R.id.share_img);
        about = findViewById(R.id.about_img);
        change_text = findViewById(R.id.top_text);
        train_spe = findViewById(R.id.training_img);
        videoTxt = findViewById(R.id.video_txt);
        downloadTxt = findViewById(R.id.download_txt);
        aboutTxt = findViewById(R.id.about_txt);
        newVideoTxt = findViewById(R.id.new_video_txt);
        shocialShare = findViewById(R.id.shocial_share);
        facebookShare.setOnClickListener(this);
        twitterShare.setOnClickListener(this);
        _share_icon.setOnClickListener(this);
        about.setOnClickListener(this);
        change_text.setOnClickListener(this);
        train_spe.setOnClickListener(this);
        shocialShare.setOnClickListener(this);
        sharing.setOnClickListener(this);
        downloads.setOnClickListener(this);
        videos.setOnClickListener(this);
        drawer.setOnClickListener(this);
        header_lay.setOnClickListener(this);
        frag_layout.setOnClickListener(this);

        nav_view = findViewById(R.id.nav_view);
        nav_drawer = findViewById(R.id.drawer_layout);
        if (isTab) {
            DrawerLayout.LayoutParams params = (DrawerLayout.LayoutParams) nav_view.getLayoutParams();
            params.width = (int) (width / 3);
            nav_view.setLayoutParams(params);
        }
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, nav_drawer, null, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                cmove_layout.setTranslationX(slideOffset * drawerView.getWidth());
            }
        };
        nav_drawer.setDrawerListener(toggle);
        toggle.syncState();


        if (!Check_permissions.hasPermissions(this)) {
            Check_permissions.request_permissions(this);
        }


//        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
//        if (point.x == 720) {
//            params.setMargins(400, 68, 0, 0);
//        }
////		params.setMargins(400, 68, 0, 0);
//        if (point.x == 480) {
//            params.setMargins(240, 50, 0, 0);//substitute parameters for left, top, right, bottom
//        }
//        if (point.x == 1080) {
//            params.setMargins(600, 100, 0, 0);//substitute parameters for left, top, right, bottom
//        }
//        sharing.setLayoutParams(params);

        videos.performClick();

    }

    public void close_drawer() {
        if (nav_drawer.isDrawerOpen(GravityCompat.START))
            nav_drawer.closeDrawer(Gravity.START);
    }

    public void move() {
        if (nav_drawer.isDrawerOpen(GravityCompat.START))
            nav_drawer.closeDrawer(Gravity.START);
        else
            nav_drawer.openDrawer(Gravity.START);
    }

    public void set_default_bg() {
        sharing.setVisibility(View.INVISIBLE);
        share_show = false;
        videoTxt.setTextColor(Color.parseColor("#8c8c8c"));
        downloadTxt.setTextColor(Color.parseColor("#8c8c8c"));
        aboutTxt.setTextColor(Color.parseColor("#8c8c8c"));
        newVideoTxt.setTextColor(Color.parseColor("#8c8c8c"));
//        videos.setImageResource(R.drawable.videos);
//        downloads.setImageResource(R.drawable.downloads);
//        about.setImageResource(R.drawable.about);
//        train_spe.setImageResource(R.drawable.new_videos_1);
    }

    public void load_fragment(Fragment fragment, String tag) {
        close_drawer();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.lay2, fragment, tag).addToBackStack(null).commit();
        change_text.setText(tag);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if (SystemClock.elapsedRealtime() - lastClickTime < 1000) {
            return;
        }
        lastClickTime = SystemClock.elapsedRealtime();
        switch (v.getId()) {
            case R.id.drawer_img_main:
                sharing.setVisibility(View.INVISIBLE);
                share_show = false;
                move();
                break;
            case R.id.top_text:
                change_text.setTextColor(Color.parseColor("#ffffff"));
                sharing.setVisibility(View.INVISIBLE);
                share_show = false;
                break;
            case R.id.facebook_share:
                shareOnFb();
                break;
            case R.id.twitter_share:
                set_default_bg();
                shareTwitter();
                break;
            case R.id.training_img:
                set_default_bg();
//                train_spe.setImageResource(R.drawable.new_videos);
                newVideoTxt.setTextColor(Color.parseColor("#d60000"));
                load_fragment(new TrainingSpecialFragment(), "My Videos");
                break;
            case R.id.about_img:
                set_default_bg();
//                about.setImageResource(R.drawable.about_1);
                aboutTxt.setTextColor(Color.parseColor("#d60000"));
                load_fragment(new About(), "About Tiska");
                break;
            case R.id.download_img:
                set_default_bg();
//                downloads.setImageResource(R.drawable.downloads_1);
                downloadTxt.setTextColor(Color.parseColor("#d60000"));
                load_fragment(new Download(), "Download Grading Syllabus");
                break;
            case R.id.videos_img:
                set_default_bg();
//                videos.setImageResource(R.drawable.videos_1);
                videoTxt.setTextColor(Color.parseColor("#d60000"));
                load_fragment(new Videos_ch(), "Introduction To Karate");
                break;
            case R.id.share_img:
                Log.e("gtdgf", "hjvgh");
                if (!share_show) {
                    sharing.setVisibility(View.VISIBLE);
                    share_show = true;
                } else {
                    sharing.setVisibility(View.INVISIBLE);
                    share_show = false;
                }
                break;
            case R.id.shocial_share:
                sharing.setVisibility(View.INVISIBLE);
                share_show = false;
                break;

        }
    }

    public void shareTwitter() {
        Intent tweetIntent = new Intent(Intent.ACTION_SEND);
        tweetIntent.putExtra(Intent.EXTRA_TEXT, shareText);
        tweetIntent.setType("text/plain");

        PackageManager packManager = getPackageManager();
        List<ResolveInfo> resolvedInfoList = packManager.queryIntentActivities(tweetIntent, PackageManager.MATCH_DEFAULT_ONLY);

        boolean resolved = false;
        for (ResolveInfo resolveInfo : resolvedInfoList) {
            if (resolveInfo.activityInfo.packageName.startsWith("com.twitter.android")) {
                tweetIntent.setClassName(
                        resolveInfo.activityInfo.packageName,
                        resolveInfo.activityInfo.name);
                resolved = true;
//		       
                break;
            }
        }
        if (resolved) {
            startActivity(tweetIntent);
//		    Toast.makeText(Main2.this, twit, Toast.LENGTH_SHORT).show();
        } else {
            Intent i = new Intent();
            i.putExtra(Intent.EXTRA_TEXT, message);
            i.setAction(Intent.ACTION_VIEW);
            i.setData(Uri.parse("https://twitter.com/intent/tweet?text=message&via=profileName"));
            startActivity(i);
            Toast.makeText(this, "Twitter app isn't found", Toast.LENGTH_LONG).show();
        }
    }

    private void shareOnFb() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TITLE, "tiska");
        intent.putExtra(Intent.EXTRA_TEXT, shareText);

        // See if official Facebook app is found
        boolean facebookAppFound = false;
        List<ResolveInfo> matches = getPackageManager()
                .queryIntentActivities(intent, 0);
        for (ResolveInfo info : matches) {
            if (info.activityInfo.packageName.toLowerCase()
                    .startsWith("com.facebook.katana")) {
                intent.setPackage(info.activityInfo.packageName);
                facebookAppFound = true;
                break;
            }
        }

        // As fallback, launch sharer.php in a browser
        if (!facebookAppFound) {
            String sharerUrl = "https://www.facebook.com/sharer/sharer.php?u=" + shareText;
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl));
        }

        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (nav_drawer.isDrawerOpen(GravityCompat.START)) {
            close_drawer();
            return;
        }
//        int count = getSupportFragmentManager().getBackStackEntryCount();
//        if (count > 1) {
//            getSupportFragmentManager().popBackStack();
//        } else {
//            if (!share_show)
        finish();
//            else
//                sharing.setVisibility(View.INVISIBLE);
//            share_show = false;
//        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        simpleFacebook.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
//	    Intent refresh = new Intent(this, AndroidLocalize.class); 
//	    startActivity(refresh); 
    }
}
