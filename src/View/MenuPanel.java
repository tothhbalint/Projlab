package View;


import java.awt.*;
import java.util.ArrayList;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import javax.swing.*;

/**
 * This class is the graphical representation of the menu.
 */
public class MenuPanel extends JPanel {
    /**
     * The title of the game.
     */
    private JLabel title;
    /**
     * The label for the plumber team.
     */
    private JLabel plumberLabel;
    /**
     * The button to increase the number of plumbers.
     */
    private JButton plumberPlusButton;
    /**
     * The button to decrease the number of nomads.
     */
    private JButton plumberMinusButton;
    /**
     * The label for the nomad team.
     */
    private JLabel nomadLabel;
    /**
     * The text field for the number of nomads.
     */
    private JTextField nomadCountTextField;
    /**
     * The text field for the number of plumbers.
     */
    private JTextField plumberCountTextField;
    /**
     * The button to increase the number of nomads.
     */
    private JButton nomadPlusButton;
    /**
     * The button to decrease the number of nomads.
     */
    private JButton nomadMinusButton;
    /**
     * The button to start the game.
     */
    private JButton startGameButton;
    /**
     * The list that stores the names of the plumbers.
     */
    ArrayList<String> plumberNames = new ArrayList<>();
    /**
     * The list that stores the names of the nomads.
     */
    ArrayList<String> nomadNames = new ArrayList<>();

    /**
     * Constructor for MenuPanel.
     * Shows the whole main menu before the game starts.
     */
    public MenuPanel() {
        //construct components
        title = new JLabel("Drukkmakori Sivatag");
        plumberLabel = new JLabel("Plumber team");
        plumberPlusButton = new JButton("+");
        plumberMinusButton = new JButton("-");
        nomadLabel = new JLabel("Nomad team");
        nomadCountTextField = new JTextField(5);
        plumberCountTextField = new JTextField(5);
        nomadCountTextField.setText("2");
        plumberCountTextField.setText("2");
        nomadPlusButton = new JButton("+");
        nomadMinusButton = new JButton("-");
        startGameButton = new JButton("Start Game");

        //adjust size and set layout
        setPreferredSize(new Dimension(250, 309));
        setLayout(null);

        //add components
        add(title);
        add(plumberLabel);
        add(plumberPlusButton);
        add(plumberMinusButton);
        add(nomadLabel);
        add(nomadCountTextField);
        add(plumberCountTextField);
        add(nomadPlusButton);
        add(nomadMinusButton);
        add(startGameButton);

        //set component bounds (only needed by Absolute Positioning)
        title.setBounds(65, 0, 145, 75);
        plumberLabel.setBounds(25, 80, 200, 25);
        plumberPlusButton.setBounds(125, 110, 60, 25);
        plumberMinusButton.setBounds(185, 110, 60, 25);
        nomadLabel.setBounds(25, 145, 100, 25);
        nomadCountTextField.setBounds(25, 175, 100, 25);
        nomadCountTextField.setEnabled(false);
        plumberCountTextField.setBounds(25, 110, 100, 25);
        plumberCountTextField.setEnabled(false);
        nomadPlusButton.setBounds(125, 175, 60, 25);
        nomadMinusButton.setBounds(185, 175, 60, 25);
        startGameButton.setBounds(85, 240, 100, 25);

        //add action listeners
        plumberPlusButton.addActionListener(e -> {
            int count = Integer.parseInt(plumberCountTextField.getText());
            count++;
            plumberCountTextField.setText(Integer.toString(count));
        });
        plumberMinusButton.addActionListener(e -> {
            int count = Integer.parseInt(plumberCountTextField.getText());
            if (count > 2) {
                count--;
                plumberCountTextField.setText(Integer.toString(count));
            }
        });
        nomadPlusButton.addActionListener(e -> {
            int count = Integer.parseInt(nomadCountTextField.getText());
            count++;
            nomadCountTextField.setText(Integer.toString(count));
        });
        nomadMinusButton.addActionListener(e -> {
            int count = Integer.parseInt(nomadCountTextField.getText());
            if (count > 2) {
                count--;
                nomadCountTextField.setText(Integer.toString(count));
            }
        });

        startGameButton.addActionListener(e -> {
            int plumberCount = Integer.parseInt(plumberCountTextField.getText());
            int nomadCount = Integer.parseInt(nomadCountTextField.getText());
            for (int i = 0; i < plumberCount; i++) {
                String name = JOptionPane.showInputDialog("Please enter the name of plumber player " + (i + 1));
                plumberNames.add(name);

            }
            for (int i = 0; i < nomadCount; i++) {
                String name = JOptionPane.showInputDialog("Please enter the name of nomad player " + (i + 1));
                nomadNames.add(name);
            }

                GameFrame frame = new GameFrame(plumberNames, nomadNames);
                frame.setIconImage(new ImageIcon("src/View/Images/DrukkmakorIcon.png").getImage());
                this.setVisible(false);
                Thread stepperThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        frame.runGame();
                    }
                });
                stepperThread.start();
        });
    }

    /**
     * Main method for the game.
     * @param args
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel( new FlatMacDarkLaf());
            UIManager.put( "Button.arc", 15);
            UIManager.put( "Component.arc", 15);
            UIManager.put( "ProgressBar.arc", 15);
            UIManager.put( "TextComponent.arc", 15);
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }

        MenuFrame frame = new MenuFrame();
        frame.setIconImage(new ImageIcon("src/View/Images/DrukkmakorIcon.png").getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new MenuPanel());
        frame.pack();
        frame.setVisible(true);
    }
}
