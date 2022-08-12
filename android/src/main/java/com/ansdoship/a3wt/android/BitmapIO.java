/*
 * MIT License
 *
 * Copyright (c) 2021 Tianscar
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package com.ansdoship.a3wt.android;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A factory class that providing functions to decode and encode Android Bitmap.
 */
public final class BitmapIO {

    private BitmapIO(){}

    public static Bitmap read(@NonNull InputStream stream, @Nullable Bitmap.Config config) throws IOException {
        Bitmap bitmap = null;
        for (BIOServiceProvider provider : BIORegistry.INSTANCE.getServiceProviders()) {
            bitmap = provider.read(stream, config);
            if (bitmap != null) break;
        }
        return bitmap;
    }

    public static Bitmap read(@NonNull InputStream stream) throws IOException {
        return read(stream, (Bitmap.Config) null);
    }

    public static Bitmap read(@NonNull InputStream stream, @NonNull Rect region, @Nullable Bitmap.Config config) throws IOException {
        Bitmap bitmap = null;
        for (BIOServiceProvider provider : BIORegistry.INSTANCE.getServiceProviders()) {
            bitmap = provider.read(stream, region, config);
            if (bitmap != null) break;
        }
        return bitmap;
    }

    public static Bitmap read(@NonNull InputStream stream, @NonNull Rect region) throws IOException {
        return read(stream, region, null);
    }

    public static Bitmap read(@NonNull File file, @Nullable Bitmap.Config config) throws IOException {
        Bitmap bitmap = null;
        for (BIOServiceProvider provider : BIORegistry.INSTANCE.getServiceProviders()) {
            bitmap = provider.read(file, config);
            if (bitmap != null) break;
        }
        return bitmap;
    }

    public static Bitmap read(@NonNull File file) throws IOException {
        return read(file, (Bitmap.Config) null);
    }

    public static Bitmap read(@NonNull File file, @NonNull Rect region, @Nullable Bitmap.Config config) throws IOException {
        Bitmap bitmap = null;
        for (BIOServiceProvider provider : BIORegistry.INSTANCE.getServiceProviders()) {
            bitmap = provider.read(file, region, config);
            if (bitmap != null) break;
        }
        return bitmap;
    }

    public static Bitmap read(@NonNull File file, @NonNull Rect region) throws IOException {
        return read(file, region, null);
    }

    public static Bitmap read(@NonNull String pathname, @Nullable Bitmap.Config config) throws IOException {
        return read(new File(pathname), config);
    }

    public static Bitmap read(@NonNull String pathname) throws IOException {
        return read(pathname, (Bitmap.Config) null);
    }

    public static Bitmap read(@NonNull String pathname, @NonNull Rect region, @Nullable Bitmap.Config config) throws IOException {
        return read(new File(pathname), region, config);
    }

    public static Bitmap read(@NonNull String pathname, @NonNull Rect region) throws IOException {
        return read(pathname, region, null);
    }

    public static Bitmap read(@NonNull byte[] data, @Nullable Bitmap.Config config) {
        return read(data, 0, data.length, config);
    }

    public static Bitmap read(@NonNull byte[] data) {
        return read(data, (Bitmap.Config) null);
    }

    public static Bitmap read(@NonNull byte[] data, int offset, int length, @Nullable Bitmap.Config config) {
        Bitmap bitmap = null;
        for (BIOServiceProvider provider : BIORegistry.INSTANCE.getServiceProviders()) {
            bitmap = provider.read(data, offset, length, config);
            if (bitmap != null) break;
        }
        return bitmap;
    }

    public static Bitmap read(@NonNull byte[] data, int offset, int length) {
        return read(data, offset, length, (Bitmap.Config) null);
    }

    public static Bitmap read(@NonNull byte[] data, @NonNull Rect region, @Nullable Bitmap.Config config) {
        return read(data, 0, data.length, region, config);
    }

    public static Bitmap read(@NonNull byte[] data, @NonNull Rect region) {
        return read(data, region, null);
    }

    public static Bitmap read(@NonNull byte[] data, int offset, int length, @NonNull Rect region, @Nullable Bitmap.Config config) {
        Bitmap bitmap = null;
        for (BIOServiceProvider provider : BIORegistry.INSTANCE.getServiceProviders()) {
            bitmap = provider.read(data, offset, length, region, config);
            if (bitmap != null) break;
        }
        return bitmap;
    }

    public static Bitmap read(@NonNull byte[] data, int offset, int length, @NonNull Rect region) {
        return read(data, offset, length, region, null);
    }

    public static Bitmap read(@NonNull AssetManager assets, @NonNull String asset, @Nullable Bitmap.Config config) throws IOException {
        Bitmap bitmap = null;
        for (BIOServiceProvider provider : BIORegistry.INSTANCE.getServiceProviders()) {
            bitmap = provider.read(assets, asset, config);
            if (bitmap != null) break;
        }
        return bitmap;
    }

    public static Bitmap read(@NonNull AssetManager assets, @NonNull String asset) throws IOException {
        return read(assets, asset, (Bitmap.Config) null);
    }

    public static Bitmap read(@NonNull AssetManager assets, @NonNull String asset, @NonNull Rect region, @Nullable Bitmap.Config config) throws IOException {
        Bitmap bitmap = null;
        for (BIOServiceProvider provider : BIORegistry.INSTANCE.getServiceProviders()) {
            bitmap = provider.read(assets, asset, region, config);
            if (bitmap != null) break;
        }
        return bitmap;
    }

    public static Bitmap read(@NonNull AssetManager assets, @NonNull String asset, @NonNull Rect region) throws IOException {
        return read(assets, asset, region, null);
    }

    @SuppressLint("ResourceType")
    public static Bitmap read(@NonNull Resources res, int id, @Nullable Bitmap.Config config) throws IOException {
        Bitmap bitmap = null;
        for (BIOServiceProvider provider : BIORegistry.INSTANCE.getServiceProviders()) {
            bitmap = provider.read(res, id, config);
            if (bitmap != null) break;
        }
        return bitmap;
    }

    public static Bitmap read(@NonNull Resources res, int id) throws IOException {
        return read(res, id, (Bitmap.Config) null);
    }

    @SuppressLint("ResourceType")
    public static Bitmap read(@NonNull Resources res, int id, @NonNull Rect region, @Nullable Bitmap.Config config) throws IOException {
        Bitmap bitmap = null;
        for (BIOServiceProvider provider : BIORegistry.INSTANCE.getServiceProviders()) {
            bitmap = provider.read(res, id, region, config);
            if (bitmap != null) break;
        }
        return bitmap;
    }

    public static Bitmap read(@NonNull Resources res, int id, @NonNull Rect region) throws IOException {
        return read(res, id, region, null);
    }

    public static Bitmap read(@NonNull Drawable drawable, @Nullable Bitmap.Config config) {
        Bitmap bitmap = null;
        for (BIOServiceProvider provider : BIORegistry.INSTANCE.getServiceProviders()) {
            bitmap = provider.read(drawable, config);
            if (bitmap != null) break;
        }
        return bitmap;
    }

    public static Bitmap read(@NonNull Drawable drawable) {
        return read(drawable, (Bitmap.Config) null);
    }

    public static Bitmap read(@NonNull Drawable drawable, @NonNull Rect region, @Nullable Bitmap.Config config) {
        Bitmap bitmap = null;
        for (BIOServiceProvider provider : BIORegistry.INSTANCE.getServiceProviders()) {
            bitmap = provider.read(drawable, region, config);
            if (bitmap != null) break;
        }
        return bitmap;
    }

    public static Bitmap read(@NonNull Drawable drawable, @NonNull Rect region) {
        return read(drawable, region, null);
    }

    public static Bitmap read(@NonNull ContentResolver resolver, @NonNull Uri uri, @Nullable Bitmap.Config config) throws IOException {
        Bitmap bitmap = null;
        for (BIOServiceProvider provider : BIORegistry.INSTANCE.getServiceProviders()) {
            bitmap = provider.read(resolver, uri, config);
            if (bitmap != null) break;
        }
        return bitmap;
    }

    public static Bitmap read(@NonNull ContentResolver resolver, @NonNull Uri uri) throws IOException {
        return read(resolver, uri, (Bitmap.Config) null);
    }

    public static Bitmap read(@NonNull ContentResolver resolver, @NonNull Uri uri, @NonNull Rect region, @Nullable Bitmap.Config config) throws IOException {
        Bitmap bitmap = null;
        for (BIOServiceProvider provider : BIORegistry.INSTANCE.getServiceProviders()) {
            bitmap = provider.read(resolver, uri, region, config);
            if (bitmap != null) break;
        }
        return bitmap;
    }

    public static Bitmap read(@NonNull ContentResolver resolver, @NonNull Uri uri, @NonNull Rect region) throws IOException {
        return read(resolver, uri, region, null);
    }

    public static Bitmap read(@NonNull URI uri, @Nullable Bitmap.Config config) throws IOException {
        Bitmap bitmap = null;
        for (BIOServiceProvider provider : BIORegistry.INSTANCE.getServiceProviders()) {
            bitmap = provider.read(uri, config);
            if (bitmap != null) break;
        }
        return bitmap;
    }

    public static Bitmap read(@NonNull URI uri) throws IOException {
        return read(uri, (Bitmap.Config) null);
    }

    public static Bitmap read(@NonNull URI uri, @NonNull Rect region, @Nullable Bitmap.Config config) throws IOException {
        Bitmap bitmap = null;
        for (BIOServiceProvider provider : BIORegistry.INSTANCE.getServiceProviders()) {
            bitmap = provider.read(uri, region, config);
            if (bitmap != null) break;
        }
        return bitmap;
    }

    public static Bitmap read(@NonNull URI uri, @NonNull Rect region) throws IOException {
        return read(uri, region, null);
    }

    public static Bitmap read(@NonNull URL url, @Nullable Bitmap.Config config) throws IOException {
        Bitmap bitmap = null;
        for (BIOServiceProvider provider : BIORegistry.INSTANCE.getServiceProviders()) {
            bitmap = provider.read(url, config);
            if (bitmap != null) break;
        }
        return bitmap;
    }

    public static @Nullable Bitmap read(@NonNull URL url) throws IOException {
        return read(url, (Bitmap.Config) null);
    }

    public static Bitmap read(@NonNull URL url, @NonNull Rect region, @Nullable Bitmap.Config config) throws IOException {
        Bitmap bitmap = null;
        for (BIOServiceProvider provider : BIORegistry.INSTANCE.getServiceProviders()) {
            bitmap = provider.read(url, region, config);
            if (bitmap != null) break;
        }
        return bitmap;
    }

    public static Bitmap read(@NonNull URL url, @NonNull Rect region) throws IOException {
        return read(url, region, null);
    }

    public static boolean write(@NonNull File output, @NonNull Bitmap bitmap, @NonNull String formatName, int quality) throws IOException {
        boolean result = false;
        for (BIOServiceProvider provider : BIORegistry.INSTANCE.getServiceProviders()) {
            result = provider.write(output, bitmap, formatName, quality);
            if (result) break;
        }
        return result;
    }

    public static boolean write(@NonNull OutputStream output, @NonNull Bitmap bitmap, @NonNull String formatName, int quality) throws IOException {
        boolean result = false;
        for (BIOServiceProvider provider : BIORegistry.INSTANCE.getServiceProviders()) {
            result = provider.write(output, bitmap, formatName, quality);
            if (result) break;
        }
        return result;
    }

    public static String[] getReaderFormatNames() {
        List<String> formatNames = new ArrayList<>();
        for (BIOServiceProvider provider : BIORegistry.INSTANCE.getServiceProviders()) {
            formatNames.addAll(Arrays.asList(provider.getReaderFormatNames()));
        }
        return formatNames.toArray(new String[0]);
    }

    public static String[] getWriterFormatNames() {
        List<String> formatNames = new ArrayList<>();
        for (BIOServiceProvider provider : BIORegistry.INSTANCE.getServiceProviders()) {
            formatNames.addAll(Arrays.asList(provider.getWriterFormatNames()));
        }
        return formatNames.toArray(new String[0]);
    }

}
