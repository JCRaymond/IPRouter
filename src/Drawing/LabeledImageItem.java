package Drawing;

import java.awt.*;

public class LabeledImageItem extends ImageItem{

    private String label;

    public LabeledImageItem(String path, String label, int xPos, int yPos, int width, int height) {
        super(path, xPos, yPos, width, height);
        this.label = label;
    }

    @Override
    public void draw(Graphics g){
        super.draw(g);
        g.setFont(Font.getFont(Font.MONOSPACED));
        g.drawString(label, this.getXPos() + 5, this.getYPos() + height);
    }
}
