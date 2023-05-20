package View;

import javax.swing.*;
import java.awt.*;
import java.io.File;

import Model.*;

public class JPipe extends JGameElement {
    private Pipe pipe;

    public JPipe(int _x, int _y) {
        super(_x, _y);
        try {
            setImage(new File("src\\View\\Images\\pipe.png"));
        } catch (Exception e) {
            System.out.println("Error loading image");
        }
    }

    @Override
    public void setObject(Object o) {
        pipe = (Pipe) o;
    }

    @Override
    public Object getObject() {
        return pipe;
    }

    @Override
    public void draw(Graphics g) {

    }
}
