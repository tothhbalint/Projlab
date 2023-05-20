package View;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import Model.*;

public class JPipe extends JGameElement{
private Pipe pipe;

    public JPipe(int _x, int _y){
        super(_x,_y);
        try {
            setImage(new File("src\\View\\Images\\pipe.png"));
        } catch (Exception e) {
            System.out.println("Error loading image");
        }
    }

    public void setPipe(Pipe p) {
        pipe = p;
    }

    public Pipe getPipe() {
        return pipe;
    }

    @Override
    public void draw(Graphics g, Object o) {

    }
}
