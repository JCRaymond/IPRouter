import Drawing.ImageDrawer;
import Drawing.ImageItem;

import javax.swing.*;

public class Testing {

    public static void main(String[] args) {
        JFrame frame = buildFrame();
        ImageDrawer id = new ImageDrawer();

        Router r = new Router("Router 2")
                .addRoute("135.46.56.0/22", "Interface 0")
                .addRoute("135.46.60.0/22", "Interface 1")
                .addRoute("192.53.40.0/23", "Router 1");

        RouterSim rs = new RouterSim(frame, id, r);
        frame.add(id);

        frame.setVisible(true);


        rs.route("135.46.63.10");


        id.waitForImages();
        System.out.println("Done!");
    }

    private static JFrame buildFrame() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);
        frame.setResizable(false);
        return frame;
    }
}
