package a3wt.test;

import a3wt.app.A3Container;
import a3wt.awt.A3AWTFrame;
import a3wt.graphics.A3Graphics;
import a3wt.input.A3ContainerAdapter;
import a3wt.input.A3ContextAdapter;

import java.awt.Dimension;
import java.util.Timer;
import java.util.TimerTask;

public class SimpleLoop {

    private volatile static boolean paused = false;

    public static void main(String[] args) {
        A3AWTFrame frame = new A3AWTFrame("Simple Loop");
        frame.setMinimumSize(new Dimension(640, 480));
        frame.setLocationRelativeTo(null);
        A3Container.Holder holder = frame.getContainerHolder();
        holder.addContainerListener(new A3ContainerAdapter() {
            @Override
            public void containerCreated() {
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        frame.getContainerHolder().postRunnable(() -> {
                            if (!frame.isDisposed()) {
                                if (!paused) frame.getContainerHolder().update();
                            }
                            else System.exit(0);
                        });
                    }
                }, 0, 1000 / 60);
            }
            @Override
            public void containerPaused() {
                paused = true;
            }
            @Override
            public void containerResumed() {
                paused = false;
            }
        });
        holder.addContextListener(new A3ContextAdapter() {
            @Override
            public void contextPainted(A3Graphics graphics, boolean snapshot) {
                graphics.drawRect(
                        (float) (Math.random() * graphics.getWidth()),
                        (float) (Math.random() * graphics.getHeight()),
                        100,
                        100);
            }
        });
        frame.setVisible(true);
    }

}
