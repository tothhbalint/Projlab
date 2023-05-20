package View;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import Model.*;

public class JCistern extends JGameElement{
    private Cistern cistern;

    public JCistern(int _x,int _y) {
        super(_x, _y);
        try{
            setImage(new File("src\\View\\Images\\cistern.png"));
        } catch (Exception e) {
            System.out.println("Image not found");
        }
    }

    public void setCistern(Cistern c) {
        cistern = c;
    }

    public Cistern getCistern() {
        return cistern;
    }

    @Override
    public void draw(Graphics g, Object o) {

    }
}
