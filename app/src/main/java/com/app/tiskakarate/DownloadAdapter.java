package com.app.tiskakarate;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DownloadAdapter extends RecyclerView.Adapter<DownloadAdapter.DownloadHolder> {
    @NonNull
    @Override
    public DownloadHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull DownloadHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
//
    }

    class DownloadHolder extends RecyclerView.ViewHolder {
        public LinearLayout linkView;
        public TextView linkTxt;
        public DownloadHolder(View itemView) {
            super(itemView);
            linkView = itemView.findViewById(R.id.link_view);
            linkTxt = itemView.findViewById(R.id.link_txt);
        }
    }
}
