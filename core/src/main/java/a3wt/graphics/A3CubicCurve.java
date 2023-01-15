package a3wt.graphics;

import a3wt.bundle.A3ExtMapBundle;

public interface A3CubicCurve extends A3Linear<A3CubicCurve> {

    float getCtrlX1();
    float getCtrlY1();
    float getCtrlX2();
    float getCtrlY2();
    A3Point getCtrlPos1();
    void getCtrlPos1(final A3Point pos);
    A3Point getCtrlPos2();
    void getCtrlPos2(final A3Point pos);

    A3CubicCurve setCtrlX1(final float ctrlX);
    A3CubicCurve setCtrlY1(final float ctrlY);
    A3CubicCurve setCtrlX2(final float ctrlX);
    A3CubicCurve setCtrlY2(final float ctrlY);
    A3CubicCurve setCtrlPos1(final A3Point pos);
    A3CubicCurve setCtrlPos2(final A3Point pos);

    A3CubicCurve set(final A3Point startPos, final A3Point ctrlPos1, final A3Point ctrlPos2, final A3Point endPos);
    A3CubicCurve set(final float startX, final float startY,
             final float ctrlX1, final float ctrlY1,
             final float ctrlX2, final float ctrlY2,
             final float endX, final float endY);

    String KEY_CTRL_X1 = "ctrlX1";
    String KEY_CTRL_Y1 = "ctrlY1";
    String KEY_CTRL_X2 = "ctrlX2";
    String KEY_CTRL_Y2 = "ctrlY2";

    @Override
    default void save(final A3ExtMapBundle.Saver saver) {
        saver.putFloat(KEY_START_X, getStartX());
        saver.putFloat(KEY_START_Y, getStartY());
        saver.putFloat(KEY_CTRL_X1, getCtrlX1());
        saver.putFloat(KEY_CTRL_Y1, getCtrlY1());
        saver.putFloat(KEY_CTRL_X2, getCtrlX2());
        saver.putFloat(KEY_CTRL_Y2, getCtrlY2());
        saver.putFloat(KEY_END_X, getEndX());
        saver.putFloat(KEY_END_Y, getEndY());
    }

    @Override
    default void restore(final A3ExtMapBundle.Restorer restorer) {
        set(restorer.getFloat(KEY_START_X, 0), restorer.getFloat(KEY_START_Y, 0),
                restorer.getFloat(KEY_CTRL_X1, 0), restorer.getFloat(KEY_CTRL_Y1, 0),
                restorer.getFloat(KEY_CTRL_X2, 0), restorer.getFloat(KEY_CTRL_Y2, 0),
                restorer.getFloat(KEY_END_X, 0), restorer.getFloat(KEY_END_Y, 0));
    }

    @Override
    default Class<? extends A3ExtMapBundle.Bundleable> typeClass() {
        return A3CubicCurve.class;
    }

}
