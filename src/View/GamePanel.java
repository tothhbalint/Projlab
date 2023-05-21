package View;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    public GamePanel(){
        super();
        setLayout(null);
        setBackground(Color.BLACK);
        //setPreferredSize(new Dimension(800, 600));
    }

    public void draw(){
        repaint();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int i = 0; i < getComponentCount(); i++){
            JGameElement element = (JGameElement) getComponent(i);
            element.draw(g);
        }
    }

    public void addElement(JGameElement element){
        add(element);
    }

    public void removeElement(JGameElement element){
        remove(element);
    }


}
