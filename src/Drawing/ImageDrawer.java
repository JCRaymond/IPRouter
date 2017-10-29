package Drawing;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class ImageDrawer extends JPanel {

    private ArrayList<ImageItem> imageItems;
    private long lastPaintTime;

    public ImageDrawer(){
        imageItems = new ArrayList<>();
    }

    public ImageDrawer addImage(ImageItem i){
        this.imageItems.add(i);
        return this;
    }

    public ImageDrawer removeImage(ImageItem i){
        this.imageItems.remove(i);
        return this;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if (lastPaintTime == 0) {
            lastPaintTime = System.currentTimeMillis();
        }
        long currPaintTime = System.currentTimeMillis();
        long frameTime = currPaintTime - lastPaintTime;
        Iterator<ImageItem> iter = imageItems.iterator();
        while (iter.hasNext()) {
            ImageItem i;
            try{
                i = iter.next();
            } catch (ConcurrentModificationException cmex) {
                break;
            }
            i.draw(g);
            i.step(frameTime);
        }
        lastPaintTime = currPaintTime;
        repaint();
    }

    public void waitForImages(){
        for (ImageItem i : imageItems){
            i.waitFor();
        }
    }

}
