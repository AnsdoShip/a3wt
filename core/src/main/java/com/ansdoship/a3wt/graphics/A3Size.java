package com.ansdoship.a3wt.graphics;

import com.ansdoship.a3wt.bundle.A3ExtensiveBundle;
import com.ansdoship.a3wt.util.A3Copyable;
import com.ansdoship.a3wt.util.A3Resetable;

public interface A3Size extends A3Copyable<A3Size>, A3Resetable, A3ExtensiveBundle.Delegate {

    float getWidth();
    float getHeight();

    A3Size setWidth(final float width);
    A3Size setHeight(final float height);

    void set(final float width, final float height);

    String KEY_WIDTH = "width";
    String KEY_HEIGHT = "height";

    @Override
    default void save(final A3ExtensiveBundle.Saver saver) {
        saver.putFloat(KEY_WIDTH, getWidth());
        saver.putFloat(KEY_HEIGHT, getHeight());
    }

    @Override
    default void restore(final A3ExtensiveBundle.Restorer restorer) {
        set(restorer.getFloat(KEY_WIDTH, 0), restorer.getFloat(KEY_HEIGHT, 0));
    }
    
}
