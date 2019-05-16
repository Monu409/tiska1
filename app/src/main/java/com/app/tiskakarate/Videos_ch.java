package com.app.tiskakarate;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.google.gson.GsonBuilder;
import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;

import java.util.ArrayList;
import java.util.Arrays;

import static com.app.tiskakarate.Main2.change_text;
import static com.app.tiskakarate.Utils.isTab;

public class Videos_ch extends Fragment {

    ArrayList<Video_model> list = new ArrayList<>();
    View view;
    CustomRecyclerView belts_list;
    View over_lay;
    Belts_listAdapter belts_listAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.videos_ch, container, false);
        change_text.setText(this.getTag());
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        find_id();
        new MyAsyncTask().execute(1);
        return view;
    }

    RecyclerViewPager belts_list_page;

    public void find_id() {
        over_lay = view.findViewById(R.id.over_lay);
        belts_listAdapter = new Belts_listAdapter((AppCompatActivity) getActivity(), list);
        if (isTab) {
            belts_list_page = view.findViewById(R.id.belts_list_page);
            LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            belts_list_page.setLayoutManager(manager);
            belts_list_page.setAdapter(belts_listAdapter);
        } else {
            belts_list = view.findViewById(R.id.belts_list);
            belts_list.setLayoutManager(new LinearLayoutManager(getActivity()));
            belts_list.setAdapter(belts_listAdapter);
        }
    }

    public class MyAsyncTask extends AsyncTask<Integer, Void, Void> {

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Integer... params) {
            // TODO Auto-generated method stub
            Authenticate authenticate = new Authenticate();
            try {
                authenticate.connection();
            } catch (Exception e) {
                // TODO Auto-generated catch bloc
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
        }
    }

    class Authenticate {
        void connection() throws Exception {
            AndroidNetworking.get("http://www.media-isoftware.co.uk/tiska/api/api_live.php?method=getAllVideos")
                    .setPriority(Priority.HIGH)
                    .build()
                    .getAsString(new StringRequestListener() {
                        @Override
                        public void onResponse(String response) {
                            GsonBuilder gsonBuilder = new GsonBuilder();
                            gsonBuilder.setDateFormat("M/d/yy hh:mm a");
                            list = new ArrayList<>(Arrays.asList(gsonBuilder.create().fromJson(response, Video_model[].class)));
                            belts_listAdapter.update(list);
                            over_lay.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError(ANError error) {
                            over_lay.setVisibility(View.GONE);
                            Log.e("error ", " is " + error.getMessage());
                        }
                    });

        }
    }


}
