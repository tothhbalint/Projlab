package View;

import java.awt.*;
import java.io.File;

import Model.*;

public class JCistern extends JGameElement {
    private Cistern cistern;

    public JCistern(int _x, int _y) {
        super(_x, _y);
        try {
            setImage(new File("src\\View\\Images\\cistern.png"));
        } catch (Exception e) {
            System.out.println("Image not found");
        }
    }

    @Override
    public void setObject(Object o) {
        cistern = (Cistern) o;
    }

    @Override
    public Object getObject() {
        return cistern;
    }

    @Override
    public void draw(Graphics g) {
    }
}
