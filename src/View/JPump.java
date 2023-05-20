package View;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class JPump extends JGameElement {

    public JPump(int _x,int _y) {
        super(_x, _y);
        try{
            setImage(new File("src\\View\\Images\\pump.png"));
        } catch (Exception e) {
            System.out.println("Image not found");
        }
    }

    @Override
    public void draw(Graphics g, Object o) {

    }
}
