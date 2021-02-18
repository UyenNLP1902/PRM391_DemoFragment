package uyennlp.demo.demofragment.utils;

import android.content.Context;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;

import uyennlp.demo.demofragment.dtos.SearchDTO;

public class BikesJsonReader implements Serializable {
    private static String readText(Context context, int resId) throws IOException {
        InputStream is = context.getResources().openRawResource(resId);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String s = null;

        while ((s = br.readLine()) != null) {
            sb.append(s);
            sb.append("\n");
        }

        return sb.toString();
    }

    public static SearchDTO readJson(Context context) throws IOException, JSONException {
//        String jsonText = readText(context, R.raw.)
        return null;
    }
}
