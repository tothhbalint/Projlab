package View;

import java.awt.*;
import java.io.File;
import Model.*;

public class JPlumber extends JGameElement{
    private Plumber plumber;

    public JPlumber(int _x, int _y){
        super(_x,_y);
        try {
            setImage(new File("src\\View\\Images\\plumber.png"));
        } catch (Exception e) {
            System.out.println("Error loading image");
        }
    }

    public void setPlumber(Plumber p) {
        plumber = p;
    }

    public Plumber getPlumber() {
        return plumber;
    }

    @Override
    public void draw(Graphics g, Object o) {

    }
}
