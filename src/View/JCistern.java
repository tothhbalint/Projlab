package View;

import java.awt.*;
import java.io.File;

import Model.*;

public class JCistern extends JGameElement {
    private Cistern cistern;

    public JCistern(int _x, int _y) {
        super(_x, _y);
        try {
            setImage(new File(".\\src\\View\\Images\\cistern.png"));
        } catch (Exception e) {
            System.out.println("Error loading cistern.png");
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
        super.paint(g);

        g.drawImage(elementImage, x, y - 55, null);
        g.drawString(((NetworkElement)getObject()).toString(), x, y - 40);
    }
}
