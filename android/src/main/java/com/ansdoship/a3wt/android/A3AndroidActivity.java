package com.ansdoship.a3wt.android;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import com.ansdoship.a3wt.app.A3Platform;
import com.ansdoship.a3wt.app.A3Assets;
import com.ansdoship.a3wt.app.A3Clipboard;
import com.ansdoship.a3wt.app.A3Preferences;
import com.ansdoship.a3wt.app.A3Context;
import com.ansdoship.a3wt.app.A3Container;
import com.ansdoship.a3wt.graphics.A3Cursor;
import com.ansdoship.a3wt.graphics.A3Graphics;
import com.ansdoship.a3wt.graphics.A3GraphicsKit;
import com.ansdoship.a3wt.graphics.A3Image;
import com.ansdoship.a3wt.input.A3ContextListener;
import com.ansdoship.a3wt.input.A3ContainerListener;
import com.ansdoship.a3wt.input.A3InputListener;
import com.ansdoship.a3wt.media.A3MediaKit;
import com.ansdoship.a3wt.media.A3MediaPlayer;

import java.io.File;
import java.util.List;
import java.util.ArrayList;

import static com.ansdoship.a3wt.android.A3AndroidUtils.commonOnTouchEvent;
import static com.ansdoship.a3wt.android.A3AndroidUtils.commonOnHoverEvent;
import static com.ansdoship.a3wt.android.A3AndroidUtils.commonOnKeyEvent;
import static com.ansdoship.a3wt.util.A3Preconditions.checkArgNotNull;

public class A3AndroidActivity extends Activity implements AndroidA3Container, View.OnLayoutChangeListener, View.OnHoverListener {

    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        return commonOnTouchEvent(handle.inputListeners, event) || super.onTouchEvent(event);
    }

    @Override
    public boolean onHover(final View v, final MotionEvent event) {
        return commonOnHoverEvent(handle.inputListeners, event);
    }

    @Override
    public boolean onKeyDown(final int keyCode, final KeyEvent event) {
        return commonOnKeyEvent(handle.inputListeners, event) || super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(final int keyCode, final KeyEvent event) {
        return commonOnKeyEvent(handle.inputListeners, event) || super.onKeyUp(keyCode, event);
    }

    protected static class A3AndroidActivityHandle implements A3Context.Handle, A3Container.Handle {

        @Override
        public A3Platform getPlatform() {
            return activity.surfaceView.handle.getPlatform();
        }

        @Override
        public A3GraphicsKit getGraphicsKit() {
            return activity.surfaceView.handle.getGraphicsKit();
        }

        @Override
        public A3MediaKit getMediaKit() {
            return null;
        }

        @Override
        public A3MediaPlayer getMediaPlayer() {
            return null;
        }

        @Override
        public int getScreenWidth() {
            return activity.surfaceView.handle.getScreenWidth();
        }

        @Override
        public int getScreenHeight() {
            return activity.surfaceView.handle.getScreenHeight();
        }

        @Override
        public int getMinScreenWidth() {
            return 0;
        }

        @Override
        public int getMinScreenHeight() {
            return 0;
        }

        @Override
        public int getMaxScreenWidth() {
            return 0;
        }

        @Override
        public int getMaxScreenHeight() {
            return 0;
        }

        @Override
        public int getPPI() {
            return activity.surfaceView.handle.getPPI();
        }

        @Override
        public float getDensity() {
            return activity.surfaceView.handle.getDensity();
        }

        @Override
        public float getScaledDensity() {
            return activity.surfaceView.handle.getScaledDensity();
        }

        @Override
        public void postRunnable(final Runnable runnable) {
            checkArgNotNull(runnable, "runnable");
            activity.runOnUiThread(runnable);
        }

        protected final A3AndroidActivity activity;
        
        public A3AndroidActivityHandle(final A3AndroidActivity activity) {
            checkArgNotNull(activity, "activity");
            this.activity = activity;
        }

        protected final List<A3ContainerListener> containerListeners = new ArrayList<>();
        protected final List<A3InputListener> inputListeners = new ArrayList<>();

        @Override
        public A3Graphics getGraphics() {
            return activity.surfaceView.handle.getGraphics();
        }

        @Override
        public int getWidth() {
            return activity.getWindow().getDecorView().getWidth();
        }

        @Override
        public int getHeight() {
            return activity.getWindow().getDecorView().getHeight();
        }

        @Override
        public int getBackgroundColor() {
            return activity.surfaceView.handle.getBackgroundColor();
        }

        @Override
        public void setBackgroundColor(final int color) {
            activity.surfaceView.handle.setBackgroundColor(color);
        }

        @Override
        public long elapsed() {
            return activity.surfaceView.handle.elapsed();
        }

        @Override
        public void paint(final A3Graphics graphics) {
            activity.surfaceView.handle.paint(graphics);
        }

        @Override
        public void update() {
            activity.checkDisposed("Can't call update() on a disposed A3Container");
            activity.surfaceView.handle.update();
        }

        @Override
        public A3Image snapshot() {
            return activity.surfaceView.handle.snapshot();
        }

        @Override
        public List<A3ContextListener> getContextListeners() {
            return activity.surfaceView.handle.getContextListeners();
        }

        @Override
        public void addContextListener(final A3ContextListener listener) {
            activity.surfaceView.handle.addContextListener(listener);
        }

        @Override
        public void setIconImages(final List<A3Image> images) {
        }

        @Override
        public List<A3Image> getIconImages() {
            return null;
        }

        @Override
        public List<A3ContainerListener> getContainerListeners() {
            return containerListeners;
        }

        @Override
        public void addContainerListener(final A3ContainerListener listener) {
            containerListeners.add(listener);
        }

        @Override
        public List<A3InputListener> getContextInputListeners() {
            return activity.surfaceView.handle.getContextInputListeners();
        }

        @Override
        public void addContextInputListener(final A3InputListener listener) {
            activity.surfaceView.handle.addContextInputListener(listener);
        }

        @Override
        public List<A3InputListener> getContainerInputListeners() {
            return inputListeners;
        }

        @Override
        public void addContainerInputListener(final A3InputListener listener) {
            inputListeners.add(listener);
        }

        @Override
        public A3Preferences getPreferences(final String name) {
            return activity.surfaceView.handle.getPreferences(name);
        }

        @Override
        public boolean deletePreferences(final String name) {
            return activity.surfaceView.handle.deletePreferences(name);
        }

        @Override
        public A3Assets getAssets() {
            return activity.surfaceView.handle.getAssets();
        }

        @Override
        public File getCacheDir() {
            return activity.surfaceView.handle.getCacheDir();
        }

        @Override
        public File getConfigDir() {
            return activity.surfaceView.handle.getConfigDir();
        }

        @Override
        public File getFilesDir(final String type) {
            return activity.surfaceView.handle.getFilesDir(type);
        }

        @Override
        public File getHomeDir() {
            return activity.surfaceView.handle.getHomeDir();
        }

        @Override
        public File getTmpDir() {
            return activity.surfaceView.handle.getTmpDir();
        }

        @Override
        public void setFullscreen(final boolean fullscreen) {
            // FIXME
        }

        @Override
        public boolean isFullscreen() {
            // FIXME
            return false;
        }

        @Override
        public A3Clipboard getClipboard() {
            return activity.surfaceView.handle.getClipboard();
        }

        @Override
        public A3Clipboard getSelection() {
            return null;
        }

        @Override
        public void setCursor(A3Cursor cursor) {

        }

        @Override
        public A3Cursor getCursor() {
            return null;
        }

    }

    @Override
    public Context getContext() {
        return this;
    }
    
    protected A3AndroidActivityHandle handle;

    @Override
    public A3Context.Handle getContextHandle() {
        return surfaceView.handle;
    }

    @Override
    public A3Container.Handle getContainerHandle() {
        return handle;
    }

    protected A3AndroidSurfaceView surfaceView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (surfaceView == null) surfaceView = new A3AndroidSurfaceView(this);
        setContentView(surfaceView);
        getWindow().getDecorView().addOnLayoutChangeListener(this);
        getWindow().getDecorView().setOnHoverListener(this);
        if (handle == null) handle = new A3AndroidActivityHandle(this);
        handle.postRunnable(new Runnable() {
            @Override
            public void run() {
                for (A3ContainerListener listener : handle.containerListeners) {
                    listener.containerCreated();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        for (A3ContainerListener listener : handle.containerListeners) {
            listener.containerStarted();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        for (A3ContainerListener listener : handle.containerListeners) {
            listener.containerResumed();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        for (A3ContainerListener listener : handle.containerListeners) {
            listener.containerPaused();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        for (A3ContainerListener listener : handle.containerListeners) {
            listener.containerStopped();
        }
    }

    @Override
    public void onWindowFocusChanged(final boolean hasFocus) {
        if (hasFocus) {
            for (final A3ContainerListener listener : handle.containerListeners) {
                listener.containerFocusGained();
            }
        }
        else {
            for (final A3ContainerListener listener : handle.containerListeners) {
                listener.containerFocusLost();
            }
        }
    }

    @Override
    public boolean isDisposed() {
        return surfaceView.isDisposed();
    }

    @Override
    public void dispose() {
        if (isDisposed()) return;
        surfaceView.dispose();
    }

    @Override
    protected void onDestroy() {
        if (isFinishing()) {
            dispose();
            super.onDestroy();
            for (final A3ContainerListener listener : handle.containerListeners) {
                listener.containerDisposed();
            }
            handle = null;
        }
        else {
            super.onDestroy();
        }
    }

    @Override
    public void onLayoutChange(final View v, final int left, final int top, final int right, final int bottom,
                               final int oldLeft, final int oldTop, final int oldRight, final int oldBottom) {
        final int width = right - left;
        final int height = bottom - top;
        for (final A3ContainerListener listener : handle.containerListeners) {
            listener.containerResized(width, height);
        }
        for (final A3ContainerListener listener : handle.containerListeners) {
            listener.containerMoved(left, top);
        }
    }

    @Override
    public void finish() {
        boolean close = true;
        for (final A3ContainerListener listener : handle.containerListeners) {
            close = close && listener.containerCloseRequested();
        }
        if (close) super.finish();
    }

}
