package View;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class JNomad extends JGameElement{
    public JNomad(int _x, int _y) {
        super(_x, _y);
        try{
            setImage(new File("src\\View\\Images\\nomad.png"));
        } catch (Exception e) {
            System.out.println("Image not found");
        }
    }
    @Override
    public void draw(Graphics g, Object o) {

    }
}
