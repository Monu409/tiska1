package com.app.tiskakarate;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Arrays;

import static com.app.tiskakarate.Utils.isTab;

public class Belts_listAdapter extends RecyclerView.Adapter<Belts_listAdapter.Holder> {
    private ArrayList<Video_model> urls;
    private AppCompatActivity activity;

    ArrayList<Integer> images = new ArrayList<>(Arrays.asList(R.drawable.belt_01, R.drawable.belt_02, R.drawable.belt_03, R.drawable.belt_04,
            R.drawable.belt_05, R.drawable.belt_06, R.drawable.belt_07, R.drawable.belt_08, R.drawable.belt_09, R.drawable.belt_10, R.drawable.belt_11));

    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

    public Belts_listAdapter(AppCompatActivity activity, ArrayList<Video_model> urls) {
        this.urls = urls;
        this.activity = activity;
    }

    public void update(ArrayList<Video_model> list) {
        this.urls = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.belts_listadapter, viewGroup, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder cbHolder, final int position) {
        if (isTab) {
            for (int i = 0; i < 4; i++) {
                ImageView imageView = (ImageView) cbHolder.main_parent.getChildAt(i);
                final int final_pos = (position * 4) + i;
                if (final_pos >= images.size())
                    break;
                imageView.setImageResource(images.get((position * 4) + i));
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (urls.size() > 0) {
                            play_url(urls.get(final_pos).getVideo_url());
                        }
                    }
                });
            }
        } else {
            if (position == 0) {
                lp.setMargins(0, 0, 0, 0);
                cbHolder.itemView.setLayoutParams(lp);
            } else {
                lp.setMargins(0, (int) activity.getResources().getDimension(R.dimen.margin_10dp), 0, 0);
                cbHolder.itemView.setLayoutParams(lp);
            }
            cbHolder.belt_view.setImageResource(images.get(position));
            Log.e("cla1", "" + position / 4);
            Log.e("cla2", "" + position % 4);
            cbHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (urls.size() > 0)
                        play_url(urls.get(position).getVideo_url());
                }
            });
        }
    }

    public void play_url(String url) {
        if (Main2.sharing.getVisibility() == View.VISIBLE) {
            Main2.sharing.setVisibility(View.GONE);
            Main2.share_show = false;
        } else {
            if (urls.size() > 0) {
                Intent intent = new Intent(activity, PlayerActivity.class);
                intent.putExtra("url", url);
                activity.startActivity(intent);
            }
        }
    }

    @Override
    public int getItemCount() {
        return isTab ? (images.size() / 4) + 1 : images.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        ImageView belt_view;
        GridLayout main_parent;

        public Holder(@NonNull View itemView) {
            super(itemView);
            belt_view = itemView.findViewById(R.id.belt_view);
            main_parent = itemView.findViewById(R.id.main_parent);
        }
    }

}
