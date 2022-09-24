package com.ansdoship.a3wt.awt;

import com.ansdoship.a3wt.graphics.A3Graphics;
import com.ansdoship.a3wt.graphics.A3Image;

import java.awt.image.BufferedImage;

import static com.ansdoship.a3wt.util.A3Asserts.checkArgNotNull;
import static com.ansdoship.a3wt.util.A3Asserts.checkArgRangeMin;

public class AWTA3Image implements A3Image {

    protected volatile BufferedImage bufferedImage;
    protected volatile AWTA3Graphics graphics;
    protected volatile boolean disposed = false;

    protected volatile long time;
    protected volatile int hotSpotX;
    protected volatile int hotSpotY;

    public AWTA3Image(final BufferedImage bufferedImage, final long time, final int hotSpotX, final int hotSpotY) {
        checkArgNotNull(bufferedImage, "bufferedImage");
        checkArgRangeMin(time, 0, true, "time");
        this.bufferedImage = bufferedImage;
        this.graphics = new AWTA3Graphics(bufferedImage);
        this.time = time;
        this.hotSpotX = hotSpotX;
        this.hotSpotY = hotSpotY;
    }

    public AWTA3Image(final BufferedImage bufferedImage) {
        this(bufferedImage, 0, 0, 0);
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    @Override
    public A3Graphics getGraphics() {
        return graphics;
    }

    @Override
    public long getTime() {
        checkDisposed("Can't call getTime() on a disposed A3Image");
        return time;
    }

    @Override
    public void setTime(final long time) {
        checkDisposed("Can't call setTime() on a disposed A3Image");
        checkArgRangeMin(time, 0, true, "time");
        this.time = time;
    }

    @Override
    public int getHotSpotX() {
        checkDisposed("Can't call getHotSpotX() on a disposed A3Image");
        return hotSpotX;
    }

    @Override
    public void setHotSpotX(final int hotSpotX) {
        checkDisposed("Can't call setHotSpotX() on a disposed A3Image");
        this.hotSpotX = hotSpotX;
    }

    @Override
    public int getHotSpotY() {
        checkDisposed("Can't call getHotSpotY() on a disposed A3Image");
        return hotSpotY;
    }

    @Override
    public void setHotSpotY(final int hotSpotY) {
        checkDisposed("Can't call setHotSpotY() on a disposed A3Image");
        this.hotSpotY = hotSpotY;
    }

    @Override
    public int getWidth() {
        checkDisposed("Can't call getWidth() on a disposed A3Image");
        return bufferedImage.getWidth();
    }

    @Override
    public int getHeight() {
        checkDisposed("Can't call getHeight() on a disposed A3Image");
        return bufferedImage.getHeight();
    }

    @Override
    public int getPixel(final int x, final int y) {
        checkDisposed("Can't call getPixel() on a disposed A3Image");
        return bufferedImage.getRGB(x, y);
    }

    @Override
    public void setPixel(final int x, final int y, final int color) {
        checkDisposed("Can't call setPixel() on a disposed A3Image");
        bufferedImage.setRGB(x, y, color);
    }

    @Override
    public void getPixels(final int[] pixels, final int offset, final int stride, final int x, final int y, final int width, final int height) {
        checkArgNotNull(pixels, "pixels");
        checkDisposed("Can't call getPixels() on a disposed A3Image");
        bufferedImage.getRGB(x, y, width, height, pixels, offset, stride);
    }

    @Override
    public void setPixels(final int[] pixels, final int offset, final int stride, final int x, final int y, final int width, final int height) {
        checkArgNotNull(pixels, "pixels");
        checkDisposed("Can't call setPixels() on a disposed A3Image");
        bufferedImage.setRGB(x, y, width, height, pixels, offset, stride);
    }

    @Override
    public boolean isDisposed() {
        return disposed;
    }

    @Override
    public void dispose() {
        if (isDisposed()) return;
        disposed = true;
        graphics.dispose();
        graphics = null;
        bufferedImage.flush();
        bufferedImage = null;
        time = -1;
    }

    @Override
    public A3Image copy() {
        checkDisposed("Can't call copy() on a disposed A3Image");
        return new AWTA3Image(A3AWTUtils.copyBufferedImage(bufferedImage));
    }

}
