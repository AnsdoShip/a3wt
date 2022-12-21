package com.ansdoship.a3wt.graphics;

import com.ansdoship.a3wt.bundle.A3ExtensiveBundle;
import com.ansdoship.a3wt.util.A3Copyable;
import com.ansdoship.a3wt.util.A3Resetable;

public interface A3Point extends A3Copyable<A3Point>, A3Resetable, A3ExtensiveBundle.Delegate {

    float getX();
    float getY();

    A3Point setX(final float x);
    A3Point setY(final float y);

    void set(final float x, final float y);

    String KEY_X = "x";
    String KEY_Y = "y";

    @Override
    default void save(final A3ExtensiveBundle.Saver saver) {
        saver.putFloat(KEY_X, getX());
        saver.putFloat(KEY_Y, getY());
    }

    @Override
    default void restore(final A3ExtensiveBundle.Restorer restorer) {
        set(restorer.getFloat(KEY_X, 0), restorer.getFloat(KEY_Y, 0));
    }

}
