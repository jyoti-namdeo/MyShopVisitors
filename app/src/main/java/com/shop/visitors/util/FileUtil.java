package com.shop.visitors.util;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtil {
    public static String readAsset(Context context, String path) {
        String text = "";
        try {
            InputStream input = context.getAssets().open("about_me.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(input));

            String strLine;


            while ((strLine = br.readLine()) != null)   {
                text += (strLine+ "\n");
            }
            br.close();
            input.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return text;
    }
}
