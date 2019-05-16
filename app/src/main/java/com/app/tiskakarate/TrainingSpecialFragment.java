package com.app.tiskakarate;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by guddu on 2/5/2019.
 */

public class TrainingSpecialFragment extends Fragment {
    private ImageView linkImg;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.training_special,container,false);
        linkImg = view.findViewById(R.id.link_img);
        linkImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mPackageName = "com.app.tiskakarate2";
                Intent appStoreIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + mPackageName));
                startActivity(appStoreIntent);
            }
        });
        return view;
    }
}
