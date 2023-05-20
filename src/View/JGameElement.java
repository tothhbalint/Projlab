package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public abstract class JGameElement extends JComponent implements IDrawable{
    protected int x,y;

    protected Image elementImage;


    public JGameElement(int _x, int _y){
        x = _x;
        y = _y;
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

    public Image getImage(){
        return elementImage;
    }

    public void setImage(File image){
        try {
            elementImage = ImageIO.read(image);
        } catch (Exception e) {
            System.out.println("Error loading image");
        }
    }



    public abstract void draw(Graphics g, Object o);
}
