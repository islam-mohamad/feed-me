package com.sal3awy.isalm.rssreader.utils;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.arch.persistence.room.util.StringUtil;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Window;

import com.sal3awy.isalm.rssreader.R;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class CommonUtils {

    private CommonUtils() {
        // This utility class is not publicly instantiable
    }

    @SuppressLint("all")
    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static String getTimestamp() {
        return new SimpleDateFormat(AppConstants.TIMESTAMP_FORMAT, Locale.US).format(new Date());
    }

    public static boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isMobileValid(String mobile) {
        return mobile.startsWith("01") && mobile.length() == 11;
    }

    public static int isUrlValid(String url) {
        if(TextUtils.isEmpty(url)){
            return R.string.required;
        }else if(!Patterns.WEB_URL.matcher(url).matches()){
            return R.string.invalid;
        }else if(!url.matches("^(http|https)://.*$")){
            return R.string._no_http;
        }else {
            return 0;
        }
    }

    public static int isNameValid(String name) {
        if(TextUtils.isEmpty(name)){
            return R.string.required;
        }else if(name.length()<3 || !name.matches("[a-zA-Z]+")) {
            return R.string.invalid;
        }else {
            return 0;
        }
    }

    public static String loadJSONFromAsset(Context context, String jsonFileName) throws IOException {
        AssetManager manager = context.getAssets();
        InputStream is = manager.open(jsonFileName);

        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();

        return new String(buffer, StandardCharsets.UTF_8);
    }

    public static ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }
}
