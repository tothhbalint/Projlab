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
        addToPanel();
        giveCoordinates();
    }

    private void addToPanel() {
        for (JGameElement element : elements) {
            add(element);
        }
    }

    @Override
    public void repaint(){
        if(this.elements != null){
            removeAll();
            addToPanel();
        }
        super.revalidate();
        super.repaint();

    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        ArrayList<JSource> sources = new ArrayList<>();
        ArrayList<JCistern> cisterns = new ArrayList<>();
        ArrayList<JPump> pumps = new ArrayList<>();
        ArrayList<JPipe> pipes = new ArrayList<>();

        g.drawImage(new ImageIcon(".\\src\\View\\Images\\desert.png").getImage(), 0, 0, null);

        for(JGameElement element : elements){
            if(element instanceof JSource){
                sources.add((JSource) element);
            }
            if(element instanceof JCistern){
                cisterns.add((JCistern) element);
            }
            if(element instanceof JPump){
                pumps.add((JPump) element);
            }
            if(element instanceof JPipe){
                pipes.add((JPipe) element);
            }
        }
        //In order to draw the pipes under the others
        for(JPipe pipe : pipes){
            pipe.calcMiddle();
            pipe.draw(g);
        }
        for(JSource source : sources){
            source.draw(g);
        }
        for(JCistern cistern : cisterns){
            cistern.draw(g);
        }
        for(JPump pump : pumps){
            pump.draw(g);
        }

        //TODO draw the nomads and plumbers

    }

    private void giveCoordinates(){
        //The sources
        elements.get(0).move(20, 600);
        elements.get(1).move(20, 350);
        elements.get(2).move(20, 100);
        //The cisterns
        elements.get(3).move(800, 600);
        elements.get(4).move(800, 350);
        elements.get(5).move(800, 100);
        //The pumps
        elements.get(20).move(300, 600);
        elements.get(21).move(300, 350);
        elements.get(22).move(300, 100);
        elements.get(23).move(600, 600);
        elements.get(24).move(600, 350);
        elements.get(25).move(600, 100);
    }
}
