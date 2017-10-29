package Drawing;

import java.io.File;
import java.io.IOException;

import java.awt.Image;

public class ImageIO{

    private ImageIO(){}

    public static Image read(String path){
        try {
            return javax.imageio.ImageIO.read(new File(path));
        } catch (IOException e) {
            System.err.println("Could not find " + path);
        }
        return null;
    }
}
