package com.ansdoship.a3wt.awt;

import com.ansdoship.a3wt.graphics.A3Graphics;
import com.ansdoship.a3wt.graphics.A3Image;
import com.ansdoship.a3wt.graphics.A3Container;
import com.ansdoship.a3wt.input.A3CanvasListener;
import com.ansdoship.a3wt.input.A3ContainerListener;

import java.awt.GraphicsConfiguration;
import java.awt.Graphics;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.ComponentListener;
import java.awt.event.FocusListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowFocusListener;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class A3AWTDialog extends Dialog implements A3Container, ComponentListener, FocusListener, WindowListener, WindowFocusListener {

    protected final A3AWTComponent component;
    protected final List<A3CanvasListener> a3CanvasListeners = new ArrayList<>();
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
        addFocusListener(this);
        addWindowListener(this);
        addWindowFocusListener(this);
    }

    public A3AWTDialog(Window owner, String title, ModalityType modalityType, GraphicsConfiguration gc) {
        super(owner, title, modalityType, gc);
        component = new A3AWTComponent();
        add(component);
        addComponentListener(this);
        addFocusListener(this);
        addWindowListener(this);
        addWindowFocusListener(this);
    }

    @Override
    public void paint(Graphics g) {
    }

    @Override
    public void update(Graphics g) {
    }

    @Override
    public void repaint(long tm, int x, int y, int width, int height) {
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
    public void update() {
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
        return a3CanvasListeners;
    }

    @Override
    public void addA3CanvasListener(A3CanvasListener listener) {
        a3CanvasListeners.add(listener);
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
        for (A3CanvasListener listener : a3CanvasListeners) {
            listener.canvasResized(e.getComponent().getWidth(), e.getComponent().getHeight());
        }
    }

    @Override
    public void componentMoved(ComponentEvent e) {
        for (A3CanvasListener listener : a3CanvasListeners) {
            listener.canvasMoved(e.getComponent().getX(), e.getComponent().getY());
        }
    }

    @Override
    public void componentShown(ComponentEvent e) {
        for (A3CanvasListener listener : a3CanvasListeners) {
            listener.canvasShown();
        }
    }

    @Override
    public void componentHidden(ComponentEvent e) {
        for (A3CanvasListener listener : a3CanvasListeners) {
            listener.canvasHidden();
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        for (A3CanvasListener listener : a3CanvasListeners) {
            listener.canvasFocusGained();
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        for (A3CanvasListener listener : a3CanvasListeners) {
            listener.canvasFocusLost();
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        for (A3ContainerListener listener : a3ContainerListeners) {
            listener.containerOpened();
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {
        for (A3ContainerListener listener : a3ContainerListeners) {
            listener.containerClosing();
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {
        for (A3ContainerListener listener : a3ContainerListeners) {
            listener.containerClosed();
        }
    }

    @Override
    public void windowIconified(WindowEvent e) {
        for (A3ContainerListener listener : a3ContainerListeners) {
            listener.containerIconified();
        }
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        for (A3ContainerListener listener : a3ContainerListeners) {
            listener.containerDeiconified();
        }
    }

    @Override
    public void windowActivated(WindowEvent e) {
        for (A3ContainerListener listener : a3ContainerListeners) {
            listener.containerActivated();
        }
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        for (A3ContainerListener listener : a3ContainerListeners) {
            listener.containerDeactivated();
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

}
