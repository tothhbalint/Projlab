package View;

import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import Model.*;

/**
 * This class is responsible for the whole game logic and the graphical representation of the game.
 */
public class ControlsPanel extends JPanel {
    /**
     * The object that is used for synchronization.
     */
    private final Object lock;
    /**
     * The list of items that can be moved to.
     */
    private JList moveToList;
    /**
     * The label for the move to list.
     */
    private JLabel moveToLabel;
    /**
     * The button to break a pipe.
     */
    private JButton breakPipeButton;
    /**
     * The button to take a pump.
     */
    private JButton takePumpButton;
    /**
     * The button to place a pump.
     */
    private JButton placePumpButton;
    /**
     * The label that shows the current player.
     */
    private JLabel playerTurnLabel;
    /**
     * The button to make a pipe slippery.
     */
    private JButton pipeSlipperyButton;
    /**
     * The button to make a pipe sticky.
     */
    private JButton pipeStickyButton;
    /**
     * The label for the inventory list.
     */
    private JLabel inventoryLabel;
    /**
     * The label that shows the pipes that can be disconnected.
     */
    private JLabel pipeDisconnectLabel;
    /**
     * The list of pipes that can be disconnected.
     */
    private JList pipeDisconnectList;
    /**
     * The button to connect a pipe.
     */
    private JButton connectPipeButton;
    /**
     * The label that shows the optional inputs that can be directed.
     */
    private JLabel directFromLabel;
    /** */
    private String[] directItems = {"directOptions"};
    /**
     * The JComboBox that shows the optional inputs that can be directed.
     */
    private JComboBox<String> directFromList;
    /**
     * The label that shows the optional outputs that can be directed.
     */
    private JLabel directToLabel;
    /**
     * The JComboBox that shows the optional outputs that can be directed.
     */
    private JComboBox<String> directToList;
    /**
     * The buttob ro direct a pump.
     */
    private JButton directPumpButton;
    /**
     * The JList that shows the inventory.
     */
    private JList inventoryList;
    /**
     * The button to fix a pipe.
     */
    private JButton fixButton;
    /**
     * The label that shows the points of the plumbers.
     */
    private JLabel plumberPoints;
    /**
     * The label that shows the points of the nomads.
     */
    private JLabel nomadPoints;
    /**
     * The frame that shows the game.
     */
    private GameFrame gameFrame;
    /**
     * The button to skip a turn.
     */
    private JButton skipButton;
    /**
     * The list stores the name of the items that can be moved to.
     */
    private ArrayList<String> moveToListItems = new ArrayList<>();
    /**
     * The list stores the name of the items that can be disconnected.
     */
    private ArrayList<String> pipeDisconnectListItems = new ArrayList<>();
    /** */
    private ArrayList<String> inventoryListItems = new ArrayList<>();
    /** */
    private DefaultListModel<String> inventoryListModel = new DefaultListModel<>();

    /**
     * Constructor that initializes the components in the control panel and adds listeners to all buttons.
     * @param gameFrame The frame that shows the game.
     * @param lock The object that is used for synchronization.
     */
    public ControlsPanel(GameFrame gameFrame, Object lock) {
        this.lock = lock;
        this.gameFrame = gameFrame;

        this.constructComponents();
        this.updateLists();
        this.disableButtons();

        //adjust size and set layout
        setPreferredSize(new Dimension(400, 720));
        setLayout(null);
        //make playerTurnLabel font bigger
        playerTurnLabel.setFont(new Font("Arial", Font.BOLD, 28));

        this.addComponents();

        //set component bounds (only needed by Absolute Positioning)
        moveToList.setBounds(10, 70, 115, 150);
        moveToLabel.setBounds(10, 50, 100, 25);
        breakPipeButton.setBounds(135, 70, 135, 40);
        takePumpButton.setBounds(135, 120, 135, 40);
        placePumpButton.setBounds(135, 170, 135, 40);
        playerTurnLabel.setBounds(10, 5, 240, 45);
        pipeSlipperyButton.setBounds(135, 250, 135, 40);
        pipeStickyButton.setBounds(135, 300, 135, 40);
        inventoryLabel.setBounds(10, 550, 80, 30);
        pipeDisconnectLabel.setBounds(10, 225, 115, 25);
        pipeDisconnectList.setBounds(10, 245, 115, 125);
        connectPipeButton.setBounds(140, 400, 135, 40);
        skipButton.setBounds(140, 500, 135, 40);
        directPumpButton.setBounds(10, 490, 115, 20);
        directFromLabel.setBounds(10, 375, 90, 25);
        directFromList.setBounds(10, 400, 115, 25);
        directToLabel.setBounds(10, 425, 20, 25);
        directToList.setBounds(10, 450, 115, 25);
        inventoryList.setBounds(10, 575, 100, 75);
        fixButton.setBounds(140, 450, 135, 40);
        plumberPoints.setBounds(150, 570, 125, 25);
        nomadPoints.setBounds(150, 600, 125, 25);

        //Add action listeners
        breakPipeButton.addActionListener(e -> {
            Player player = (Player) gameFrame.getCurrentPlayer().getObject();
            if (player.getPosition() instanceof Pipe)
                player.breakPipe();
            else
                System.out.println("Player is not on a pipe");
            synchronized (lock) {
                gameFrame.setUserAction(true);
                lock.notifyAll();
            }
            System.out.println("break pipe");
        });

        skipButton.addActionListener(e -> {
            synchronized (lock) {
                gameFrame.setUserAction(true);
                lock.notifyAll();
            }
        });

        takePumpButton.addActionListener(e -> {
            if (gameFrame.getCurrentPlayer().getObject() instanceof Nomad) {
                System.out.println("Nomad cannot take pump");
            } else {
                Plumber plumber = (Plumber) gameFrame.getCurrentPlayer().getObject();
                if (plumber.getPosition() instanceof Cistern) {
                    plumber.takePump();
                    System.out.println("take pump");
                } else {
                    System.out.println("Plumber is not on a cistern");
                }
            }
            synchronized (lock) {
                gameFrame.setUserAction(true);
                lock.notifyAll();
            }
        });

        placePumpButton.addActionListener(e -> {
            if (gameFrame.getCurrentPlayer().getObject() instanceof Nomad) {
                System.out.println("Nomad cannot place pump");
            } else {
                Plumber plumber = (Plumber) gameFrame.getCurrentPlayer().getObject();
                if (plumber.getInventory().hasPump()) {
                    plumber.placePump();
                    System.out.println("place pump");
                } else {
                    System.out.println("Plumber does not have pump");
                }
            }
            synchronized (lock) {
                gameFrame.setUserAction(true);
                lock.notifyAll();
            }
        });

        pipeSlipperyButton.addActionListener(e -> {
            if (gameFrame.getCurrentPlayer().getObject() instanceof Plumber) {
                System.out.println("Plumber cannot make pipe slippery");
            } else {
                Nomad nomad = (Nomad) gameFrame.getCurrentPlayer().getObject();
                nomad.makePipeSlippery();
                System.out.println("make pipe slippery");
            }
            synchronized (lock) {
                gameFrame.setUserAction(true);
                lock.notifyAll();
            }
        });

        pipeStickyButton.addActionListener(e -> {
            Player player = (Player) gameFrame.getCurrentPlayer().getObject();
            if (player.getPosition() instanceof Pipe) {
                player.makePipeSticky();
                System.out.println("make pipe sticky");
            } else {
                System.out.println("Player is not on a pipe");
            }
            synchronized (lock) {
                gameFrame.setUserAction(true);
                lock.notifyAll();
            }
            System.out.println("make pipe sticky");
        });

        connectPipeButton.addActionListener(e -> {
            Plumber plumber = (Plumber) gameFrame.getCurrentPlayer().getObject();
            plumber.connectPipe();
            synchronized (lock) {
                gameFrame.setUserAction(true);
                lock.notifyAll();
            }
            System.out.println("connect pipe");
        });

        moveToList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    if (moveToList.getSelectedIndex() != -1) {
                        Player player = (Player) gameFrame.getCurrentPlayer().getObject();
                        NetworkElement moveTo = player.getPosition().getConnections().get(moveToList.getSelectedIndex());
                        player.move(moveTo);
                        synchronized (lock) {
                            gameFrame.setUserAction(true);
                            lock.notifyAll();
                        }
                        System.out.println("move to");
                    }

                }
            }
        });


        pipeDisconnectList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    if (pipeDisconnectList.getSelectedIndex() != -1) {
                        Plumber player = (Plumber) gameFrame.getCurrentPlayer().getObject();
                        NetworkElement disConnect = player.getPosition().getConnections().get(pipeDisconnectList.getSelectedIndex());
                        player.takePipe(disConnect);
                        synchronized (lock) {
                            gameFrame.setUserAction(true);
                            lock.notifyAll();
                        }
                        System.out.println("disconnect Pump");
                    }
                }
            }
        });

        fixButton.addActionListener(e -> {
            Plumber plumber = (Plumber) gameFrame.getCurrentPlayer().getObject();
            plumber.repair();
            synchronized (lock) {
                gameFrame.setUserAction(true);
                lock.notifyAll();
            }
            System.out.println("fix pipe");
        });

        directPumpButton.addActionListener(e -> {
            Player player = (Player) gameFrame.getCurrentPlayer().getObject();
            ArrayList<NetworkElement> networkElements = player.getPosition().getConnections();
            NetworkElement from = networkElements.get(directFromList.getSelectedIndex());
            NetworkElement to = networkElements.get(directToList.getSelectedIndex());
            if (from == to) {
                JOptionPane.showMessageDialog(null, "You can't direct pump to the same element!");
                return;
            }
            player.directPump(from, to);
            synchronized (lock) {
                gameFrame.setUserAction(true);
                lock.notifyAll();
            }
        });
    }

    /**
     * Updates the lists
     */
    private void updateLists() {
        synchronized (lock) {
            //Delete the previous lists
            moveToListItems.clear();
            pipeDisconnectListItems.clear();
            inventoryListModel.clear();

            //inventoryListItems
            if (gameFrame.getCurrentPlayer().getObject() instanceof Plumber) {
                for (String item : ((Plumber) gameFrame.getCurrentPlayer().getObject()).getInventory().toString().split(" ")) {
                    if (item.contains("Pump")) {
                        inventoryListModel.addElement("Pump");
                    } else if (item.contains("Pipe")) {
                        inventoryListModel.addElement("Pipe");
                    }
                }
            } else {
                for (String item : ((Nomad) gameFrame.getCurrentPlayer().getObject()).getInventory().toString().split(" ")) {
                    if (item.contains("Pump")) {
                        inventoryListModel.addElement("Pump");
                    } else if (item.contains("Pipe")) {
                        inventoryListModel.addElement("Pipe");
                    }
                }
            }

            //moveToListItems
            for (NetworkElement neighbour : ((Player) gameFrame.getCurrentPlayer().getObject()).getPosition().getConnections()) {
                if (!neighbour.isOccupied() && neighbour instanceof Pipe && neighbour.getConnections().size() == 2) {
                    moveToListItems.add(neighbour.toString());
                } else if (!(neighbour instanceof Pipe)){
                    moveToListItems.add(neighbour.toString());
                }
            }

            //pipeDisconnectListItems
            for (NetworkElement neighbour : ((Player) gameFrame.getCurrentPlayer().getObject()).getPosition().getConnections()) {
                if (gameFrame.getCurrentPlayer().getObject() instanceof Nomad) {
                    break;
                }
                if (neighbour instanceof Pipe) {
                    pipeDisconnectListItems.add(neighbour.toString());
                }
            }

            //Update the lists
            if (((Player) gameFrame.getCurrentPlayer().getObject()).getPosition() instanceof Pump) {
                Pump pump = ((Pump) ((Player) gameFrame.getCurrentPlayer().getObject()).getPosition());
                ArrayList<NetworkElement> tempList = pump.getConnections();
                NetworkElement in = pump.getInput();
                NetworkElement out = pump.getOutput();
                String[] temp = new String[tempList.size()];
                for (int i = 0; i < tempList.size(); i++) {
                    temp[i] = tempList.get(i).toString();
                }

                directItems = temp;
                directFromList.removeAllItems();
                directToList.removeAllItems();
                for (String item : directItems) {
                    directFromList.addItem(item);
                    directToList.addItem(item);
                }
                for (int i = 0; i < tempList.size(); i++) {
                    if (temp[i].equals(in.toString())) {
                        directFromList.setSelectedIndex(i);
                    }
                    if (temp[i].equals(out.toString())) {
                        directToList.setSelectedIndex(i);
                    }
                }
            } else {
                directFromList.removeAllItems();
                directToList.removeAllItems();
            }

            // Update moveToList
            moveToList.setListData(moveToListItems.toArray());

            // Update pipeDisconnectList
            pipeDisconnectList.setListData(pipeDisconnectListItems.toArray());

            // Update inventoryList
            inventoryList.setModel(inventoryListModel);
        }
    }

    /**
     * Updates the labels
     */
    private void updateLabels() {
        synchronized (lock) {
            playerTurnLabel.setText(gameFrame.getCurrentPlayer().getName() + "'s turn");
            plumberPoints.setText("Plumber points: " + NetworkElement.getPlumberPoints());
            nomadPoints.setText("Nomad points: " + NetworkElement.getNomadPoints());
        }
    }

    /**
     * Adds the components to the panel
     */
    private void addComponents() {

        //add components
        add(moveToList);

        add(skipButton);

        add(moveToLabel);

        add(breakPipeButton);

        add(takePumpButton);

        add(placePumpButton);

        add(playerTurnLabel);

        add(pipeSlipperyButton);

        add(pipeStickyButton);

        add(inventoryLabel);

        add(pipeDisconnectLabel);

        add(pipeDisconnectList);

        add(connectPipeButton);

        add(directPumpButton);

        add(directFromLabel);

        add(directFromList);

        add(directToLabel);

        add(directToList);

        add(inventoryList);

        add(fixButton);

        add(plumberPoints);

        add(nomadPoints);
    }

    /**
     * Constructs the components
     */
    private void constructComponents() {
        //construct components
        moveToList = new JList(moveToListItems.toArray());
        pipeDisconnectList = new JList(pipeDisconnectListItems.toArray());
        inventoryList = new JList(inventoryListModel.toArray());
        moveToLabel = new JLabel("Move to:");
        breakPipeButton = new JButton("Break pipe");
        takePumpButton = new JButton("Take pump");
        placePumpButton = new JButton("Place pump");
        playerTurnLabel = new JLabel(gameFrame.getCurrentPlayer().getName() + " player's turn!");
        pipeSlipperyButton = new JButton("Pipe slippery");
        pipeStickyButton = new JButton("Pipe sticky");
        inventoryLabel = new JLabel("Inventory:");
        pipeDisconnectLabel = new JLabel("Pipe disconnect:");
        connectPipeButton = new JButton("Connect pipe");
        skipButton = new JButton("Skip");
        directPumpButton = new JButton("Direct pump");
        directFromLabel = new JLabel("from");
        directFromList = new JComboBox<>(directItems);
        directToLabel = new JLabel("to");
        directToList = new JComboBox<>(directItems);
        fixButton = new JButton("Fix");
        plumberPoints = new JLabel("Plumber points: 0");
        nomadPoints = new JLabel("Nomad points: 0");
    }

    /**
     * This method checks if the buttons should be enabled or disabled
     */
    public void disableButtons() {
        synchronized (lock) {
            Player player = (Player) gameFrame.getCurrentPlayer().getObject();
            boolean checkDirectPumpEnabled = player.getPosition() instanceof Pump;
            if (checkDirectPumpEnabled) {
                directPumpButton.setEnabled(true);
            } else {
                directPumpButton.setEnabled(false);
            }

            boolean checkTakePumpEnabled = player.getPosition() instanceof Cistern && player instanceof Plumber && !player.getInventory().isFull();
            if (checkTakePumpEnabled) {
                takePumpButton.setEnabled(true);
            } else {
                takePumpButton.setEnabled(false);
            }

            boolean checkPlacePumpEnabled = player.getInventory().hasPump() && player instanceof Plumber && player.getPosition() instanceof Pipe;
            if (checkPlacePumpEnabled) {
                placePumpButton.setEnabled(true);
            } else {
                placePumpButton.setEnabled(false);
            }

            boolean checkBreakPipeEnabled = player.getPosition() instanceof Pipe && !player.getPosition().isDamaged() && !((Pipe) player.getPosition()).isRepairProtected();
            if (checkBreakPipeEnabled) {
                breakPipeButton.setEnabled(true);
            } else {
                breakPipeButton.setEnabled(false);
            }

            boolean checkConnectPipeEnabled = player.getInventory().hasPipe() && player instanceof Plumber;
            if (checkConnectPipeEnabled) {
                connectPipeButton.setEnabled(true);
            } else {
                connectPipeButton.setEnabled(false);
            }

            boolean checkFixEnabled = player.getPosition().isDamaged() && player instanceof Plumber;
            if (checkFixEnabled) {
                fixButton.setEnabled(true);
            } else {
                fixButton.setEnabled(false);
            }

            boolean checkSlipperyEnabled = player.getPosition() instanceof Pipe && player instanceof Nomad;
            if (checkSlipperyEnabled) {
                pipeSlipperyButton.setEnabled(true);
            } else {
                pipeSlipperyButton.setEnabled(false);
            }

            boolean checkStickyEnabled = player.getPosition() instanceof Pipe && !((Pipe) player.getPosition()).isSlippery();
            if (checkStickyEnabled) {
                pipeStickyButton.setEnabled(true);
            } else {
                pipeStickyButton.setEnabled(false);
            }
        }
    }

    /**
     * This method is responsible for updating the game after every move
     */
    @Override
    public void repaint() {
        if (this.gameFrame != null) {
            synchronized (lock) {
                disableButtons();
                updateLists();
                updateLabels();
            }
        }
        super.revalidate();
        super.repaint();
        System.out.println("repaint");
    }
}
