package com.app.tiskakarate;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.DownloadListener;
import com.androidnetworking.interfaces.DownloadProgressListener;
import com.androidnetworking.interfaces.StringRequestListener;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.app.tiskakarate.Main2.change_text;
import static com.app.tiskakarate.Utils.isTab;

public class Download extends Fragment implements OnClickListener {

    ArrayList<Download_model> download_models;
    private String mail_subject = "TISKA Karate Grading Syllabus";
    TextView bl1_tx, rd2_tx, orng3_tx, yell4_tx, green5_tx, purpl6_tx, purpl1_7_tx, white2_7_tx, brown1_8_tx,
            kihonbr2_8_tx, brwh1_9_tx, br2_9_tx, br3_9_tx, br1_10_tx, br2_10_tx, br3_10_tx, bl_br1_11, bl_br2_11, bl_br3_11, bl_br4_11, bl_br5_11, bl_br6_11, bl_br7_11, shodan_1, shodan_2, shodan_3, shodan_4, shodan_5, shodan_6, shodan_7, shodan_8, shodan_9, shodan_10, shodan_11;
    Button button;
    RelativeLayout down;
    LinearLayout scroller;
    LinearLayout blu_belt10, red_belt9, orange_belt8, yello_belt7, green_belt6, purple_belt5, pr_wh4_1, pr_wh4_2, brown_belt3_1,
            brown_belt3_2, br_wh2_1, br_wh2_2, br_wh2_3, br_red1_1, br_red1_2, br_red1_3, br_bl_1, br_bl_2, br_bl_3, br_bl_4,
            br_bl_5, br_bl_6, br_bl_7, shodan_link_1, shodan_link_2, shodan_link_3, shodan_link_4, shodan_link_5, shodan_link_6, shodan_link_7, shodan_link_8, shodan_link_9, shodan_link_10, shodan_link_11;
    String pdf_link;
    static String extention;
    View over_lay;
    boolean isDataCame = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.download, container, false);
        change_text.setText(this.getTag());
        over_lay = view.findViewById(R.id.over_lay);
        down = view.findViewById(R.id.down_frag);
        scroller = view.findViewById(R.id.scroll_layout);
        blu_belt10 = view.findViewById(R.id.blu_belt_10_lay);
        red_belt9 = view.findViewById(R.id.blu_belt_9_lay);
        orange_belt8 = view.findViewById(R.id.blu_belt_8_lay);
        yello_belt7 = view.findViewById(R.id.blu_belt_7_lay);
        green_belt6 = view.findViewById(R.id.blu_belt_6_lay);
        purple_belt5 = view.findViewById(R.id.blu_belt_5_lay);
        pr_wh4_1 = view.findViewById(R.id.blu_belt_4_lay1);
        pr_wh4_2 = view.findViewById(R.id.blu_belt_4_lay2);
        brown_belt3_1 = view.findViewById(R.id.blu_belt_3_lay1);
        brown_belt3_2 = view.findViewById(R.id.blu_belt_3_lay2);
        br_wh2_1 = view.findViewById(R.id.blu_belt_2_lay1);
        br_wh2_2 = view.findViewById(R.id.blu_belt_2_lay2);
        br_wh2_3 = view.findViewById(R.id.blu_belt_2_lay3);
        br_red1_1 = view.findViewById(R.id.blu_belt_1_lay1);
        br_red1_2 = view.findViewById(R.id.blu_belt_1_lay2);
        br_red1_3 = view.findViewById(R.id.blu_belt_1_lay3);
        br_bl_1 = view.findViewById(R.id.brown_shadon_lay1);
        br_bl_2 = view.findViewById(R.id.brown_shadon_lay2);
        br_bl_3 = view.findViewById(R.id.brown_shadon_lay3);
        br_bl_4 = view.findViewById(R.id.brown_shadon_lay4);
        br_bl_5 = view.findViewById(R.id.brown_shadon_lay5);
        br_bl_6 = view.findViewById(R.id.brown_shadon_lay6);
        br_bl_7 = view.findViewById(R.id.brown_shadon_lay7);
        shodan_link_1 = view.findViewById(R.id.shadon_p_n1);
        shodan_link_2 = view.findViewById(R.id.shadon_p_n2);
        shodan_link_3 = view.findViewById(R.id.shadon_p_n3);
        shodan_link_4 = view.findViewById(R.id.shadon_p_n4);
        shodan_link_5 = view.findViewById(R.id.shadon_p_n5);
        shodan_link_6 = view.findViewById(R.id.shadon_p_n6);
        shodan_link_7 = view.findViewById(R.id.shadon_p_n7);
        shodan_link_8 = view.findViewById(R.id.shadon_p_n8);
        shodan_link_9 = view.findViewById(R.id.shadon_p_n9);
        shodan_link_10 = view.findViewById(R.id.shadon_p_n10);
        shodan_link_11 = view.findViewById(R.id.shadon_p_n11);

        if (isTab) {
            HorizontalScrollView scrollView = view.findViewById(R.id.scrollView1);
            scrollView.setOnTouchListener(new OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    // TODO Auto-generated method stub
                    Main2.sharing.setVisibility(View.INVISIBLE);
                    Main2.share_show = false;
                    return false;
                }
            });
        } else {
            ScrollView scrollView = view.findViewById(R.id.scrollView1);
            scrollView.setOnTouchListener(new OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    // TODO Auto-generated method stub
                    Main2.sharing.setVisibility(View.INVISIBLE);
                    Main2.share_show = false;
                    return false;
                }
            });
        }
        bl1_tx = view.findViewById(R.id.blu_belt_10_link);
        rd2_tx = view.findViewById(R.id.blu_belt_9_link);
        orng3_tx = view.findViewById(R.id.blu_belt_8_link);
        yell4_tx = view.findViewById(R.id.blu_belt_7_link);
        green5_tx = view.findViewById(R.id.blu_belt_6_link);
        purpl6_tx = view.findViewById(R.id.blu_belt_5_link);
        purpl1_7_tx = view.findViewById(R.id.blu_belt_4_link1);
        white2_7_tx = view.findViewById(R.id.blu_belt_4_link2);
        brown1_8_tx = view.findViewById(R.id.blu_belt_3_link1);
        kihonbr2_8_tx = view.findViewById(R.id.blu_belt_3_link2);
        brwh1_9_tx = view.findViewById(R.id.blu_belt_2_link1);
        br2_9_tx = view.findViewById(R.id.blu_belt_2_link2);
        br3_9_tx = view.findViewById(R.id.blu_belt_2_link3);
        br1_10_tx = view.findViewById(R.id.blu_belt_1_link1);
        br2_10_tx = view.findViewById(R.id.blu_belt_1_link2);
        br3_10_tx = view.findViewById(R.id.blu_belt_1_link3);
        bl_br1_11 = view.findViewById(R.id.brown_shadon_link1);
        bl_br2_11 = view.findViewById(R.id.brown_shadon_link2);
        bl_br3_11 = view.findViewById(R.id.brown_shadon_link3);
        bl_br4_11 = view.findViewById(R.id.brown_shadon_link4);
        bl_br5_11 = view.findViewById(R.id.brown_shadon_link5);
        bl_br6_11 = view.findViewById(R.id.brown_shadon_link6);
        bl_br7_11 = view.findViewById(R.id.brown_shadon_link7);
        shodan_1 = view.findViewById(R.id.shadon_p_n_link1);
        shodan_2 = view.findViewById(R.id.shadon_p_n_link2);
        shodan_3 = view.findViewById(R.id.shadon_p_n_link3);
        shodan_4 = view.findViewById(R.id.shadon_p_n_link4);
        shodan_5 = view.findViewById(R.id.shadon_p_n_link5);
        shodan_6 = view.findViewById(R.id.shadon_p_n_link6);
        shodan_7 = view.findViewById(R.id.shadon_p_n_link7);
        shodan_8 = view.findViewById(R.id.shadon_p_n_link8);
        shodan_9 = view.findViewById(R.id.shadon_p_n_link9);
        shodan_10 = view.findViewById(R.id.shadon_p_n_link10);
        shodan_11 = view.findViewById(R.id.shadon_p_n_link11);

        blu_belt10.setOnClickListener(this);
        red_belt9.setOnClickListener(this);
        orange_belt8.setOnClickListener(this);
        yello_belt7.setOnClickListener(this);
        green_belt6.setOnClickListener(this);
        purple_belt5.setOnClickListener(this);
        pr_wh4_1.setOnClickListener(this);
        pr_wh4_2.setOnClickListener(this);
        brown_belt3_1.setOnClickListener(this);
        brown_belt3_2.setOnClickListener(this);
        br_wh2_1.setOnClickListener(this);
        br_wh2_2.setOnClickListener(this);
        br_wh2_3.setOnClickListener(this);
        br_red1_1.setOnClickListener(this);
        br_red1_2.setOnClickListener(this);
        br_red1_3.setOnClickListener(this);
        br_bl_1.setOnClickListener(this);
        br_bl_2.setOnClickListener(this);
        br_bl_3.setOnClickListener(this);
        br_bl_4.setOnClickListener(this);
        br_bl_5.setOnClickListener(this);
        br_bl_6.setOnClickListener(this);
        br_bl_7.setOnClickListener(this);
        shodan_link_1.setOnClickListener(this);
        shodan_link_2.setOnClickListener(this);
        shodan_link_3.setOnClickListener(this);
        shodan_link_4.setOnClickListener(this);
        shodan_link_5.setOnClickListener(this);
        shodan_link_6.setOnClickListener(this);
        shodan_link_7.setOnClickListener(this);
        shodan_link_8.setOnClickListener(this);
        shodan_link_9.setOnClickListener(this);
        shodan_link_10.setOnClickListener(this);
        shodan_link_11.setOnClickListener(this);


        down.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Main2.sharing.setVisibility(View.INVISIBLE);
                Main2.share_show = false;
            }
        });

        new ConnectionTask().execute(1);
        return view;
    }

    //    private ProgressDialog dialog;
    public class ConnectionTask extends AsyncTask<Integer, Void, Void> {
        @Override
        protected void onPreExecute() {
//            dialog = new ProgressDialog(getActivity());
//            dialog.setMessage("Downloading Syllabus...");
//            dialog.setCancelable(false);
//            dialog.setCanceledOnTouchOutside(false);
            scroller.setBackgroundColor(Color.BLACK);
//            dialog.show();
        }

        @Override
        protected Void doInBackground(Integer... args) {
            AuthenticateConnection mAuth = new AuthenticateConnection();
            try {
                mAuth.connection();

            } catch (Exception e) {
//		    // TODO Auto-generated catch block
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
        }
    }


    public void set_data() {

        bl1_tx.setText(download_models.get(0).getPdfs().get(0).getTitle());

        rd2_tx.setText(download_models.get(1).getPdfs().get(0).getTitle());

        orng3_tx.setText(download_models.get(2).getPdfs().get(0).getTitle());

        yell4_tx.setText(download_models.get(3).getPdfs().get(0).getTitle());

        green5_tx.setText(download_models.get(4).getPdfs().get(0).getTitle());

        purpl6_tx.setText(download_models.get(5).getPdfs().get(0).getTitle());

        purpl1_7_tx.setText(download_models.get(6).getPdfs().get(0).getTitle());
        white2_7_tx.setText(download_models.get(6).getPdfs().get(1).getTitle());

        brown1_8_tx.setText(download_models.get(7).getPdfs().get(0).getTitle());
        kihonbr2_8_tx.setText(download_models.get(7).getPdfs().get(1).getTitle());

        brwh1_9_tx.setText(download_models.get(8).getPdfs().get(0).getTitle());
        br2_9_tx.setText(download_models.get(8).getPdfs().get(1).getTitle());
        br3_9_tx.setText(download_models.get(8).getPdfs().get(2).getTitle());

        br1_10_tx.setText(download_models.get(9).getPdfs().get(0).getTitle());
        br2_10_tx.setText(download_models.get(9).getPdfs().get(1).getTitle());
        br3_10_tx.setText(download_models.get(9).getPdfs().get(2).getTitle());

        bl_br1_11.setText(download_models.get(10).getPdfs().get(0).getTitle());
        bl_br2_11.setText(download_models.get(10).getPdfs().get(1).getTitle());
        bl_br3_11.setText(download_models.get(10).getPdfs().get(2).getTitle());
        bl_br4_11.setText(download_models.get(10).getPdfs().get(3).getTitle());
        bl_br5_11.setText(download_models.get(10).getPdfs().get(4).getTitle());
        bl_br6_11.setText(download_models.get(10).getPdfs().get(5).getTitle());
        bl_br7_11.setText(download_models.get(10).getPdfs().get(6).getTitle());

        shodan_1.setText(download_models.get(11).getPdfs().get(0).getTitle());
        shodan_2.setText(download_models.get(11).getPdfs().get(1).getTitle());
        shodan_3.setText(download_models.get(11).getPdfs().get(2).getTitle());
        shodan_4.setText(download_models.get(11).getPdfs().get(3).getTitle());
        shodan_5.setText(download_models.get(11).getPdfs().get(4).getTitle());
        shodan_6.setText(download_models.get(11).getPdfs().get(5).getTitle());
        shodan_7.setText(download_models.get(11).getPdfs().get(6).getTitle());
        shodan_8.setText(download_models.get(11).getPdfs().get(7).getTitle());
        shodan_9.setText(download_models.get(11).getPdfs().get(8).getTitle());
        shodan_10.setText(download_models.get(11).getPdfs().get(9).getTitle());
        shodan_11.setText(download_models.get(11).getPdfs().get(10).getTitle());

    }

    public class AuthenticateConnection {
        void connection() throws Exception {
            AndroidNetworking.get("http://www.media-isoftware.co.uk/tiska/api/api_live.php?method=getGroupDownloads")
                    .setPriority(Priority.HIGH)
                    .build()
                    .getAsString(new StringRequestListener() {
                        @Override
                        public void onResponse(String response) {
                            GsonBuilder gsonBuilder = new GsonBuilder();
                            gsonBuilder.setDateFormat("M/d/yy hh:mm a");
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                download_models = new ArrayList<>(Arrays.asList(gsonBuilder.create().fromJson(jsonObject.getString("data"), Download_model[].class)));

                                set_data();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            over_lay.setVisibility(View.GONE);
                            isDataCame = true;
//                            progressDialog.dismiss();
                        }

                        @Override
                        public void onError(ANError error) {
                            Log.e("error ", " is " + error.getMessage());
                            over_lay.setVisibility(View.GONE);
//                            listener.onError(error.toString());
                        }
                    });

        }
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if (isDataCame) {
            goneShareIcons();
            switch (v.getId()) {
                case R.id.blu_belt_10_lay:
                    pdf_link = download_models.get(0).getPdfs().get(0).getLink();
                    break;
                case R.id.blu_belt_9_lay:
                    pdf_link = download_models.get(1).getPdfs().get(0).getLink();
                    break;
                case R.id.blu_belt_8_lay:
                    pdf_link = download_models.get(2).getPdfs().get(0).getLink();
                    break;
                case R.id.blu_belt_7_lay:
                    pdf_link = download_models.get(3).getPdfs().get(0).getLink();
                    break;
                case R.id.blu_belt_6_lay:
                    pdf_link = download_models.get(4).getPdfs().get(0).getLink();
                    break;
                case R.id.blu_belt_5_lay:
                    pdf_link = download_models.get(5).getPdfs().get(0).getLink();
                    break;
                case R.id.blu_belt_4_lay1:
                    pdf_link = download_models.get(6).getPdfs().get(0).getLink();
                    break;
                case R.id.blu_belt_4_lay2:
                    pdf_link = download_models.get(6).getPdfs().get(1).getLink();
                    break;
                case R.id.blu_belt_3_lay1:
                    pdf_link = download_models.get(7).getPdfs().get(0).getLink();
                    break;
                case R.id.blu_belt_3_lay2:
                    pdf_link = download_models.get(7).getPdfs().get(1).getLink();
                    break;
                case R.id.blu_belt_2_lay1:
                    pdf_link = download_models.get(8).getPdfs().get(0).getLink();
                    break;
                case R.id.blu_belt_2_lay2:
                    pdf_link = download_models.get(8).getPdfs().get(1).getLink();
                    break;
                case R.id.blu_belt_2_lay3:
                    pdf_link = download_models.get(8).getPdfs().get(2).getLink();
                    break;
                case R.id.blu_belt_1_lay1:
                    pdf_link = download_models.get(9).getPdfs().get(0).getLink();
                    break;
                case R.id.blu_belt_1_lay2:
                    pdf_link = download_models.get(9).getPdfs().get(1).getLink();
                    break;
                case R.id.blu_belt_1_lay3:
                    pdf_link = download_models.get(9).getPdfs().get(2).getLink();
                    break;
                case R.id.brown_shadon_lay1:
                    pdf_link = download_models.get(10).getPdfs().get(0).getLink();
                    break;
                case R.id.brown_shadon_lay2:
                    pdf_link = download_models.get(10).getPdfs().get(1).getLink();
                    break;
                case R.id.brown_shadon_lay3:
                    pdf_link = download_models.get(10).getPdfs().get(2).getLink();
                    break;
                case R.id.brown_shadon_lay4:
                    pdf_link = download_models.get(10).getPdfs().get(3).getLink();
                    break;
                case R.id.brown_shadon_lay5:
                    pdf_link = download_models.get(10).getPdfs().get(4).getLink();

                    break;
                case R.id.brown_shadon_lay6:
                    pdf_link = download_models.get(10).getPdfs().get(5).getLink();
                    break;
                case R.id.brown_shadon_lay7:
                    pdf_link = download_models.get(10).getPdfs().get(6).getLink();
                    break;
                case R.id.shadon_p_n1:
                    pdf_link = download_models.get(11).getPdfs().get(0).getLink();
                    break;
                case R.id.shadon_p_n2:
                    pdf_link = download_models.get(11).getPdfs().get(1).getLink();
                    break;
                case R.id.shadon_p_n3:
                    pdf_link = download_models.get(11).getPdfs().get(2).getLink();
                    break;
                case R.id.shadon_p_n4:
                    pdf_link = download_models.get(11).getPdfs().get(3).getLink();
                    break;
                case R.id.shadon_p_n5:
                    pdf_link = download_models.get(11).getPdfs().get(4).getLink();
                    break;
                case R.id.shadon_p_n6:
                    pdf_link = download_models.get(11).getPdfs().get(5).getLink();
                    break;
                case R.id.shadon_p_n7:
                    pdf_link = download_models.get(11).getPdfs().get(6).getLink();
                    break;
                case R.id.shadon_p_n8:
                    pdf_link = download_models.get(11).getPdfs().get(7).getLink();
                    break;
                case R.id.shadon_p_n9:
                    pdf_link = download_models.get(11).getPdfs().get(8).getLink();
                    break;
                case R.id.shadon_p_n10:
                    pdf_link = download_models.get(11).getPdfs().get(9).getLink();
                    break;
                case R.id.shadon_p_n11:
                    pdf_link = download_models.get(11).getPdfs().get(10).getLink();
                    break;
            }
        }
        extention = pdf_link.substring(pdf_link.lastIndexOf("/"));
        downloadFile(pdf_link);
    }

    private void goneShareIcons() {
        if (Main2.sharing.getVisibility() == View.VISIBLE) {
            Main2.sharing.setVisibility(View.GONE);
        }
    }


    public void downloadFile(String url) {
        String file_name = extention;
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        final File file = new File(path, "/" + file_name);
        if (file.exists()) {
            file.mkdir();
        }
        AndroidNetworking.download(url, path, file_name)
                .setPriority(Priority.HIGH)
                .build()
                .setDownloadProgressListener(new DownloadProgressListener() {
                    @Override
                    public void onProgress(long bytesDownloaded, long totalBytes) {

                    }
                })
                .startDownload(new DownloadListener() {
                    @Override
                    public void onDownloadComplete() {
                        sharePDF(file) ;
                    }

                    @Override
                    public void onError(ANError error) {
// handle error
                        Snackbar.make(down,"Please try again", Snackbar.LENGTH_LONG).show();
                    }
                });
    }
    public void sharePDF(File pdf) {
        if (pdf.exists()) {
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setType("message/rfc822");
            Uri uri = FileProvider.getUriForFile(getActivity(), BuildConfig.APPLICATION_ID + ".provider", pdf);
            emailIntent.putExtra(Intent.EXTRA_STREAM, uri);
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, mail_subject);
            emailIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivityForResult(Intent.createChooser(emailIntent, "Send email using:"), 1);
        } else {
            Log.e("DEBUG", "File doesn't exist");
        }
    }
}
