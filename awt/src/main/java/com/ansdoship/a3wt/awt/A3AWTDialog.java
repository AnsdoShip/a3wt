package com.ansdoship.a3wt.awt;

import com.ansdoship.a3wt.graphics.A3Container;
import com.ansdoship.a3wt.graphics.A3Graphics;
import com.ansdoship.a3wt.graphics.A3Image;
import com.ansdoship.a3wt.input.A3CanvasListener;
import com.ansdoship.a3wt.input.A3ContainerListener;

import java.awt.GraphicsConfiguration;
import java.awt.Graphics;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import java.awt.Color;
import java.awt.event.ComponentListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowFocusListener;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class A3AWTDialog extends Dialog implements A3Container, ComponentListener, WindowListener, WindowFocusListener {

    protected final A3AWTComponent component;
    protected final List<A3ContainerListener> a3ContainerListeners = new ArrayList<>();

    public A3AWTDialog(Frame owner) {
        this(owner, "", false);
    }

    public A3AWTDialog(Frame owner, boolean modal) {
        this(owner, "", modal);
    }

    public A3AWTDialog(Frame owner, String title) {
        this(owner, title, false);
    }

    public A3AWTDialog(Frame owner, String title, boolean modal) {
        this(owner, title, modal ? DEFAULT_MODALITY_TYPE : ModalityType.MODELESS);
    }

    public A3AWTDialog(Frame owner, String title, boolean modal, GraphicsConfiguration gc) {
        this(owner, title, modal ? DEFAULT_MODALITY_TYPE : ModalityType.MODELESS, gc);
    }

    public A3AWTDialog(Dialog owner) {
        this(owner, "", false);
    }

    public A3AWTDialog(Dialog owner, String title) {
        this(owner, title, false);
    }

    public A3AWTDialog(Dialog owner, String title, boolean modal) {
        this(owner, title, modal ? DEFAULT_MODALITY_TYPE : ModalityType.MODELESS);
    }

    public A3AWTDialog(Dialog owner, String title, boolean modal, GraphicsConfiguration gc) {
        this(owner, title, modal ? DEFAULT_MODALITY_TYPE : ModalityType.MODELESS, gc);
    }

    public A3AWTDialog(Window owner) {
        this(owner, "", ModalityType.MODELESS);
    }

    public A3AWTDialog(Window owner, String title) {
        this(owner, title, ModalityType.MODELESS);
    }

    public A3AWTDialog(Window owner, ModalityType modalityType) {
        this(owner, "", modalityType);
    }

    public A3AWTDialog(Window owner, String title, ModalityType modalityType) {
        super(owner, title, modalityType);
        component = new A3AWTComponent();
        add(component);
        addComponentListener(this);
        addWindowListener(this);
        addWindowFocusListener(this);
    }

    public A3AWTDialog(Window owner, String title, ModalityType modalityType, GraphicsConfiguration gc) {
        super(owner, title, modalityType, gc);
        component = new A3AWTComponent();
        add(component);
        addComponentListener(this);
        addWindowListener(this);
        addWindowFocusListener(this);
    }

    @Override
    public void paint(Graphics g) {
        component.paint(component.getGraphics());
    }

    @Override
    public void update(Graphics g) {
        component.paint(component.getGraphics());
    }

    @Override
    public void repaint(long tm, int x, int y, int width, int height) {
        component.repaint(tm, x, y, width, height);
    }

    @Override
    public long elapsed() {
        return component.elapsed();
    }

    @Override
    public void paint(A3Graphics graphics) {
        component.paint(graphics);
    }

    @Override
    public void setBackground(Color bgColor) {
        super.setBackground(bgColor);
        component.setBackground(bgColor);
    }

    @Override
    public int getBackgroundColor() {
        return component.getBackgroundColor();
    }

    @Override
    public void setBackgroundColor(int color) {
        component.setBackgroundColor(color);
    }

    @Override
    public void update() {
        checkDisposed("Can't call update() on a disposed A3Container");
        component.update();
    }

    @Override
    public A3Image snapshot() {
        return component.snapshot();
    }

    @Override
    public A3Image snapshotBuffer() {
        return component.snapshotBuffer();
    }

    @Override
    public List<A3CanvasListener> getA3CanvasListeners() {
        return component.a3CanvasListeners;
    }

    @Override
    public void addA3CanvasListener(A3CanvasListener listener) {
        component.addA3CanvasListener(listener);
    }

    @Override
    public List<A3ContainerListener> getA3ContainerListeners() {
        return a3ContainerListeners;
    }

    @Override
    public void addA3ContainerListener(A3ContainerListener listener) {
        a3ContainerListeners.add(listener);
    }

    @Override
    public void componentResized(ComponentEvent e) {
        for (A3ContainerListener listener : a3ContainerListeners) {
            listener.containerResized(getWidth(), getHeight());
        }
    }

    @Override
    public void componentMoved(ComponentEvent e) {
        for (A3ContainerListener listener : a3ContainerListeners) {
            listener.containerMoved(getX(), getY());
        }
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }

    @Override
    public void windowOpened(WindowEvent e) {
        for (A3ContainerListener listener : a3ContainerListeners) {
            listener.containerCreated();
        }
        for (A3ContainerListener listener : a3ContainerListeners) {
            listener.containerStarted();
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {
        boolean close = true;
        for (A3ContainerListener listener : a3ContainerListeners) {
            close = close && listener.containerCloseRequested();
        }
        if (close) dispose();
    }

    @Override
    public void windowClosed(WindowEvent e) {
        for (A3ContainerListener listener : a3ContainerListeners) {
            listener.containerDisposed();
        }
    }

    @Override
    public void windowIconified(WindowEvent e) {
        for (A3ContainerListener listener : a3ContainerListeners) {
            listener.containerStopped();
        }
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        for (A3ContainerListener listener : a3ContainerListeners) {
            listener.containerStarted();
        }
    }

    @Override
    public void windowActivated(WindowEvent e) {
        for (A3ContainerListener listener : a3ContainerListeners) {
            listener.containerResumed();
        }
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        for (A3ContainerListener listener : a3ContainerListeners) {
            listener.containerPaused();
        }
    }

    @Override
    public void windowGainedFocus(WindowEvent e) {
        for (A3ContainerListener listener : a3ContainerListeners) {
            listener.containerFocusGained();
        }
    }

    @Override
    public void windowLostFocus(WindowEvent e) {
        for (A3ContainerListener listener : a3ContainerListeners) {
            listener.containerFocusLost();
        }
    }

    @Override
    public boolean isDisposed() {
        return component.isDisposed();
    }

    @Override
    public void dispose() {
        component.dispose();
        super.dispose();
    }

}
