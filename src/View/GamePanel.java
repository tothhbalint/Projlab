package View;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    ArrayList<JGameElement> elements;
    ArrayList<JNomad> nomads;
    ArrayList<JPlumber> plumbers;


    public GamePanel(ArrayList<JGameElement> _elements, ArrayList<JNomad> _nomads, ArrayList<JPlumber> _plumbers){
        super();
        setLayout(null);
        setBackground(Color.ORANGE);
        setVisible(true);
        setPreferredSize(new Dimension(880, 720));
        elements = _elements;
        nomads = _nomads;
        plumbers = _plumbers;
        addToPanel();
        giveCoordinates();
        giveCoordinatesToPlayers();
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

        //Draw the nomads and plumbers
        giveCoordinatesToPlayers();
        for(JNomad nomad : nomads){
            nomad.draw(g);
        }
        for(JPlumber plumber : plumbers){
            plumber.draw(g);
        }

    }

    private void giveCoordinates(){
        //The sources
        elements.get(0).initPosition(20, 600);
        elements.get(1).initPosition(20, 350);
        elements.get(2).initPosition(20, 100);
        //The cisterns
        elements.get(3).initPosition(800, 600);
        elements.get(4).initPosition(800, 350);
        elements.get(5).initPosition(800, 100);
        //The pumps
        elements.get(20).initPosition(300, 600);
        elements.get(21).initPosition(300, 350);
        elements.get(22).initPosition(300, 100);
        elements.get(23).initPosition(600, 600);
        elements.get(24).initPosition(600, 350);
        elements.get(25).initPosition(600, 100);
    }

    private void giveCoordinatesToPlayers() {
        for (int i = 0; i < nomads.size(); i++) {
            JNomad nomad = nomads.get(i);
            int x = GameFrame.findElement(GameFrame.getGame().getNomadTeam().getPlayer(i).getPosition()).getX();
            int y = GameFrame.findElement(GameFrame.getGame().getNomadTeam().getPlayer(i).getPosition()).getY();
            nomad.move(x, y);
        }

        for (int i = 0; i < plumbers.size(); i++) {
            JPlumber plumber = plumbers.get(i);
            int x = GameFrame.findElement(GameFrame.getGame().getPlumberTeam().getPlayer(i).getPosition()).getX();
            int y = GameFrame.findElement(GameFrame.getGame().getPlumberTeam().getPlayer(i).getPosition()).getY();
            plumber.move(x, y);
        }
    }
}
