package com.app.tiskakarate;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by guddu on 1/25/2019.
 */

public class FontAwesome  extends TextView {


    public FontAwesome(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
            init();
        }

    public FontAwesome(Context context, AttributeSet attrs) {
            super(context, attrs);
            init();
        }

    public FontAwesome(Context context) {
            super(context);
            init();
        }

    private void init() {

        //Font name should not contain "/".
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                "fontawesome.ttf");
        setTypeface(tf);
    }

}
