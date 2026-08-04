package com.example.carmarket.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {

    public static void show(Context ctx,String message) {
        Toast.makeText(ctx, message, Toast.LENGTH_SHORT).show();
    }
}
