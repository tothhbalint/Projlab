package View;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class JCistern extends JGameElement{

    public JCistern(int _x,int _y) {
        super(_x, _y);
        try{
            setImage(new File("src\\View\\Images\\cistern.png"));
        } catch (Exception e) {
            System.out.println("Image not found");
        }
    }

    @Override
    public void draw(Graphics g, Object o) {

    }
}
