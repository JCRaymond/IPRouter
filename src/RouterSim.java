import Drawing.ImageDrawer;
import Drawing.ImageItem;
import Drawing.LabeledImageItem;

import javax.swing.*;
import java.util.HashMap;

public class RouterSim {

    private ImageDrawer id;
    private Router r;
    private HashMap<String, ImageItem> dests;
    private ImageItem source;
    private ImageItem router;
    private int elemSize;
    private int jwidth, jheight;

    public RouterSim(JFrame frame, ImageDrawer id, Router r) {
        this.id = id;
        this.r = r;
        this.dests = new HashMap<>();
        setupImages(frame);
    }

    private void setupImages(JFrame jf){
        int vertElems = r.getDests().size();
        jheight = jf.getHeight()-20;
        jwidth = jf.getWidth();
        elemSize = (jheight-(15*(vertElems+1)))/vertElems;
        router = new ImageItem("assets/router.png", jwidth/2, jheight/2, elemSize, elemSize);
        source = new ImageItem("assets/origin.png", 15+elemSize/2, jheight/2, elemSize, elemSize);
        id.addImage(router);
        id.addImage(source);
        int destNum = 0;
        for (String dest: r.getDests()) {
            ImageItem destImage = new LabeledImageItem("assets/interface.png", dest, jwidth-15-elemSize/2, 15*(destNum+1)+elemSize*destNum + elemSize/2,elemSize, elemSize);
            id.addImage(destImage);
            dests.put(dest, destImage);
            destNum++;
        }
    }

    public void route(String packetIP){
        int packetSize = elemSize/2;
        ImageItem packet = new LabeledImageItem("assets/packet.png", packetIP, (int)source.xPos, (int)source.yPos, packetSize, packetSize);
        id.addImage(packet);
        packet.moveTo((int)router.xPos, (int)router.yPos, 3000);
        String dest = r.getDest(packetIP);
        ImageItem destImage = dests.get(dest);
        int nextX = (int) destImage.xPos;
        int nextY = (int) destImage.yPos;
        packet.waitFor();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        packet.moveAndWait(nextX, nextY, 3000);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        id.removeImage(packet);
    }
}
