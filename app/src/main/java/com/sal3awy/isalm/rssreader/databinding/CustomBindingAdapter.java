package com.sal3awy.isalm.rssreader.databinding;

import android.databinding.BindingAdapter;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sal3awy.isalm.rssreader.R;
import com.squareup.picasso.Picasso;

public class CustomBindingAdapter {
    @BindingAdapter("image")
    public static void setImage(ImageView view, String url) {
        if (view != null && !TextUtils.isEmpty(url)) {
            Picasso.with(view.getContext()).load(url)
                    .placeholder(R.drawable.no_image).error(R.drawable.no_image)
                    .into(view);
        }
    }

    @BindingAdapter("typeFace")
    public static void setTypeFace(View view, String type) {
        if (!TextUtils.isEmpty(type) && view instanceof TextView) {
            ((TextView) view).setTypeface(Typeface.createFromAsset(view.getContext().getAssets(), type));
        }
    }

    @BindingAdapter("watcher")
    public static void bindTextWatcher(EditText editText, TextWatcher textWatcher) {
        editText.addTextChangedListener(textWatcher);
    }

    @BindingAdapter("error")
    public static void bindError(TextView textView, int resId) {
        Log.e("resId", ""+resId);
        if (resId > 0) {
            textView.setText(textView.getContext().getString(resId));
            textView.setVisibility(View.VISIBLE);
        }
        else {
            textView.setText("");
            textView.setVisibility(View.GONE);
        }
    }

}
