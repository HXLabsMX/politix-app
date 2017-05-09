package com.herxlabs.politix.utils;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;

import com.herxlabs.politix.R;

import java.lang.reflect.Field;

/**
 * Created by HX on 08/05/2017.
 */
//https://inducesmile.com/android/how-to-make-circular-imageview-and-rounded-corner-imageview-in-android/
//adopted from http://ruibm.com/2009/06/16/rounded-corner-bitmaps-on-android/
public class Utils {
    private Context context;

    public Utils(Context current){
        context = current;
    }
    public static int getResId(String resName) {

        try {
            Class res = R.drawable.class;
            Field field = res.getField(resName);
            return field.getInt(null);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}