package View;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
import javax.swing.*;

public class MenuPanel extends JPanel {
    private JLabel title;
    private JLabel plumberLabel;
    private JButton plumberPlusButton;
    private JButton plumberMinusButton;
    private JLabel nomadLabel;
    private JTextField nomadCountTextField;
    private JTextField plumberCountTextField;
    private JButton nomadPlusButton;
    private JButton nomadMinusButton;
    private JButton startGameButton;
    ArrayList<String> plumberNames = new ArrayList<>();
    ArrayList<String> nomadNames = new ArrayList<>();

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

                Object lock = new Object();
                GameFrame frame = new GameFrame(plumberNames, nomadNames, lock);
                frame.setLayout(new BorderLayout());
                frame.setTitle("Drukkmakori sivatag - Game");
                frame.setSize(1280, 720);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.loadElements(plumberNames, nomadNames);

                GamePanel gamePanel = new GamePanel();
                ControlsPanel controlsPanel = new ControlsPanel(frame, lock);

                controlsPanel.setPreferredSize(new Dimension(400, 720));
                gamePanel.setPreferredSize(new Dimension(880, 720));

                frame.getContentPane().add(controlsPanel, BorderLayout.WEST);
                frame.getContentPane().add(gamePanel, BorderLayout.CENTER);
                frame.setVisible(true);
                frame.pack();
                Thread stepperThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        frame.runGame();
                    }
                });
                stepperThread.start();
        });
    }


    public static void main(String[] args) {
        MenuFrame frame = new MenuFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new MenuPanel());
        frame.pack();
        frame.setVisible(true);
    }
}
