package com.ansdoship.a3wt.android;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

public class A3AndroidUtils {

    private A3AndroidUtils(){}

    /**
     * Returns a drawable object associated with a particular resource ID.
     * <p>
     * Starting in {@link Build.VERSION_CODES#LOLLIPOP}, the
     * returned drawable will be styled for the specified Context's theme.
     *
     * @param id The desired resource identifier, as generated by the aapt tool.
     *           This integer encodes the package, type, and resource entry.
     *           The value 0 is an invalid identifier.
     * @return Drawable An object that can be used to draw this resource.
     */
    @SuppressLint("UseCompatLoadingForDrawables")
    public static Drawable getDrawable(@NonNull Context context, @DrawableRes int id) {
        if (Build.VERSION.SDK_INT >= 21) {
            return context.getDrawable(id);
        } else {
            return context.getResources().getDrawable(id);
        }
    }

    public static Bitmap copyBitmap(@NonNull Bitmap source) {
        return source.copy(source.getConfig(), source.isMutable());
    }

}
