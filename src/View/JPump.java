package View;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import Model.*;

public class JPump extends JGameElement {
    private Pump pump;

    public JPump(int _x,int _y) {
        super(_x, _y);
        try{
            setImage(new File("src\\View\\Images\\pump.png"));
        } catch (Exception e) {
            System.out.println("Image not found");
        }
    }

    public void setPump(Pump p) {
        pump = p;
    }

    public Pump getPump() {
        return pump;
    }

    @Override
    public void draw(Graphics g, Object o) {

    }
}
