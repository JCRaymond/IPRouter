package Drawing;

import java.awt.*;

public class ImageItem {

    public double xPos, yPos;
    public int width, height;
    public boolean visible;
    public final java.awt.Image image;

    private boolean moving;
    private int targetX, targetY;
    private long timeLeft;

    public ImageItem(String path, int xPos, int yPos, int width, int height) {
        this.image = ImageIO.read(path);
        this.visible = true;
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.moving = false;
    }

    public int getXPos(){
        return (int)(xPos - width/2);
    }

    public int getYPos(){
        return (int)(yPos - height/2);
    }

    public void moveTo(int newX, int newY, long time) {
        this.moving = true;
        this.targetX = newX;
        this.targetY = newY;
        this.timeLeft = time;
    }

    void step(long lftime) {
        if (!moving || lftime < 1) {
            return;
        }
        double timePercent = (double)lftime/timeLeft;
        timeLeft -= lftime;
        if (timeLeft > 0) {
            this.xPos += (targetX - xPos) * timePercent;
            this.yPos += (targetY - yPos) * timePercent;
        } else {
            this.xPos = targetX;
            this.yPos = targetY;
            this.moving = false;
        }
    }

    public void waitFor() {
        while (this.xPos != this.targetX || this.yPos != this.targetY) {
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void moveAndWait(int newX, int newY, long time) {
        this.moveTo(newX, newY, time);
        this.waitFor();
    }

    public void draw(Graphics g){
        if (visible) {
            g.drawImage(image, getXPos(), getYPos(), width, height, null);
        }
    }
}
