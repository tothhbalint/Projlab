package View;

import javax.swing.*;
import java.awt.*;
import java.io.File;

import Model.*;

public class JSource extends JGameElement {
    private Source source;

    public JSource(int _x, int _y) {
        super(_x, _y);
        try {
            setImage(new File("src\\View\\Images\\source.png"));
        } catch (Exception e) {
            System.out.println("Image not found");
        }
    }

    @Override
    public void setObject(Object o) {
        source = (Source) o;
    }

    @Override
    public Object getObject() {
        return source;
    }

    @Override
    public void draw(Graphics g) {

    }
}