package View;

import java.awt.*;
import java.io.File;

import Model.*;

public class JPlumber extends JPlayer {
    private Plumber plumber;

    public JPlumber(int _x, int _y) {
        super(_x, _y);
        try {
            setImage(new File("src\\View\\Images\\plumber.png"));
        } catch (Exception e) {
            System.out.println("Error loading plumber.png");
        }
    }

    @Override
    public void setObject(Object o) {
        plumber = (Plumber) o;
    }

    @Override
    public Object getObject() {
        return plumber;
    }

    public void checkStuck(){
        if(plumber.getStuck()){
            try {
                setImage(new File("src\\View\\Images\\plumberStuck.png"));
            } catch (Exception e) {
                System.out.println("Error loading plumberStuck.png");
            }
        }
        else{
            try {
                setImage(new File("src\\View\\Images\\plumber.png"));
            } catch (Exception e) {
                System.out.println("Error loading plumber.png");
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        super.paint(g);
        checkStuck();
        g.drawImage(elementImage, x, y, null);
        g.drawString(name, x, y - 40);
    }
}
