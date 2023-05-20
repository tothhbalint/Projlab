package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class JPlumber extends JComponent{
    private int x,y;
    private Image image;

    public JPlumber(int X, int Y) throws IOException {
        x = X;
        y = Y;

        File imagefile = new File("./src/View/plumber.png");
         image = ImageIO.read(imagefile);

    }
    public Image getImage(){
        return image;
    }
    public void move(int X, int Y){
       x = X;
       y = Y;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
}
