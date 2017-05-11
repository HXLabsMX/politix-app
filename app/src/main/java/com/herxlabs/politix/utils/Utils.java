package com.herxlabs.politix.utils;

import android.content.Context;

import com.herxlabs.politix.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.nio.CharBuffer;

/**
 * Created by HX on 08/05/2017.
 */
//https://inducesmile.com/android/how-to-make-circular-imageview-and-rounded-corner-imageview-in-android/
//adopted from http://ruibm.com/2009/06/16/rounded-corner-bitmaps-on-android/
public class Utils {
    private Context context;

    public Utils(Context current) {
        context = current;
    }

    public void setContext(Context current) {
        context = current;
    }

    public Context getContext() {
        return context;
    }

    public static int getDrawableId(String resName) {
        try {
            Class res = R.drawable.class;
            Field field = res.getField(resName);
            return field.getInt(null);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static String openJson(String resName, Context context) throws IOException {
        InputStream is;
        if (resName == "senadores") {
            is = context.getResources().openRawResource(R.raw.senadores);
        } else {
            return null;
        }
        Writer writer = new StringWriter();
        char[] buffer = new char[Character.MAX_VALUE];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            is.close();
        }
        return writer.toString();
    }
}
