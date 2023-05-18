package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class JPlumber   {
    private int x,y;

    private final BufferedImage plumberImage;

    public JPlumber(int X, int Y) throws IOException {
        x = X;
        y = Y;

        File imagefile = new File("./src/View/plumber.png");
         plumberImage = ImageIO.read(imagefile);

    }

    public Image getImage(){
        return plumberImage;
    }

    public void move(int X, int Y){
       x = X;
       y = Y;
    }
}
