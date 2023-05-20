package View;

import java.awt.*;
import java.io.File;

public class JPlumber extends JGameElement{

    public JPlumber(int _x, int _y){
        super(_x,_y);
        try {
            setImage(new File("src\\View\\Images\\plumber.png"));
        } catch (Exception e) {
            System.out.println("Error loading image");
        }
    }

    @Override
    public void draw(Graphics g, Object o) {

    }
}
