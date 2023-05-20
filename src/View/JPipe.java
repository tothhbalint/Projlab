package View;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class JPipe extends JGameElement{


    public JPipe(int _x, int _y){
        super(_x,_y);
        try {
            setImage(new File("src\\View\\Images\\pipe.png"));
        } catch (Exception e) {
            System.out.println("Error loading image");
        }
    }
    @Override
    public void draw(Graphics g, Object o) {

    }
}
