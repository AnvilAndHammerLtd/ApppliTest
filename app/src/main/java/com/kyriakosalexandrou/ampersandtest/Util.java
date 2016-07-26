package com.kyriakosalexandrou.ampersandtest;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by Kyriakos on 25/06/2016.
 * <p/>
 * Useful generic re-usable methods
 */
public class Util {
    public static void showToastMessageCentered(Context context, int message) {
        if (context != null) {
            Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    public static void showToastMessageCentered(Context context, String message) {
        if (context != null) {
            Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }
}