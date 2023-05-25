package View;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    ArrayList<JGameElement> elements = new ArrayList<>();

    public GamePanel(ArrayList<JGameElement> _elements){
        super();
        setLayout(null);
        setBackground(Color.ORANGE);
        setVisible(true);
        setPreferredSize(new Dimension(880, 720));
        elements = _elements;
    }

    @Override
    public void repaint(){
        super.revalidate();
        super.repaint();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int i = 0; i < getComponentCount(); i++){
            JGameElement element = (JGameElement) getComponent(i);
            element.draw(g);
        }
    }

    private void giveCoordinates(){
        for(int i = 0; i < elements.size(); i++){
            JGameElement element = elements.get(i);
            //element.move(element.setX(), element.getY());
        }
    }
}
