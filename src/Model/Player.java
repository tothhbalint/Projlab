package Model;//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Player.java
//  @ Date : 16/04/2023
//  @ Author : 
//
//

/**
 * This class is responsible for the players
 */
public abstract class Player {
    /**
     * Stores the inventory of the player
     */
    protected Inventory inventory;
    /**
     * Stores the position of the player
     */
    protected NetworkElement position;
    /**
     * Stores if the player is stuck
     */
    protected boolean stuck;
    /**
     * Stores how much time left till the player is stuck
     */
    protected int stuckTimeLeft;

    /**
     * Constructor
     * Sets the starting values
     */
    public Player() {
        position = null;
        stuck = false;
        stuckTimeLeft = 0;
        inventory = new Inventory(this);
    }

    /**
     * Constructor with 1 parameter
     * Sets the starting values, and the position of a player
     * @param ne NetworkElement, the position that need to be set
     */
    public Player(NetworkElement ne) {
        Proto.print("Player.Player(NetworkElement)");
        position = ne;
        stuck = false;
        stuckTimeLeft = 0;
        inventory = new Inventory(this);
    }

    /**
     * This method gets the position of the player
     * @return position
     */
    public NetworkElement getPosition() {
        return this.position;
    }

    /**
     * This method sets the position of the player
     * @param ne NetworkElement, that need to be set as position
     */
    public void setPosition(NetworkElement ne) {
        this.position = ne;
    }

    /**
     * This method checks if the player is stuck
     * @return true, if the player is stuck, false if it is not
     */
    public boolean getStuck() {
        return this.stuck;
    }

    /**
     * This method sets if the player is stuck
     * @param b true/flase
     */
    public void setStuck(boolean b) {
        this.stuck = b;
    }

    /**
     * This method gets how much time left till the player is stuck
     * @return time till the player is stuck
     */
    public int getStuckTimeLeft() {
        return this.stuckTimeLeft;
    }

    /**
     * This method sets how much time left till the player is stuck
     * @return time till the player is stuck
     */
    public void setStuckTimeLeft(int i) {
        this.stuckTimeLeft = i;
    }

    /**
     * This method gets the inventory of the player
     * @return inventory
     */
    public Inventory getInventory() {
        return this.inventory;
    }

    /**
     * This method sets the inventory of the player
     * @param inv inventory, that need to be set
     */
    public void setInventory(Inventory inv) {
        this.inventory = inv;
    }

    /**
     * This method is implemented in inherited classes
     */
    public abstract void takePump(Inventory inv);

    /**
     * This method is implemented in inherited classes
     */
    public abstract void placePump();

    /**
     * This method is implemented in inherited classes
     */
    public abstract void connectPipe();

    /**
     * This method is implemented in inherited classes
     */
    public abstract void disconnectPipe(NetworkElement ne);

    /**
     * This method is called in every round and decreases how much time left till the player is stuck
     */
    public void tick() {
        Proto.print("Player.tick()");
        Proto.tab++;
        if (stuck) {
            stuckTimeLeft--;
            if (stuckTimeLeft <= 0) {
                stuck = false;
            }
        }
        Proto.tab--;
    }

    /**
     * This method is responsible the movement of the player
     * @param ne NetworkElement, where the player wants to step
     */
    public void move(NetworkElement ne) {
        Proto.print("Player.move()");
        Proto.tab++;
        if (stuck) {
            Proto.log("player stuck");
            Proto.tab--;
            return;
        }
        ne.accept(this);
        Proto.tab--;
    }

    /**
     * This method is responsible for directing
     * @param in NetworkElement, that need to be set as input
     * @param out NetworkElement, that need to be set as output
     */
    public void directPump(NetworkElement in, NetworkElement out) {
        Proto.print("Player.directPump()");
        Proto.tab++;
        position.direct(in, out);
        Proto.tab--;
    }

    public void makePipeSticky(){
        Proto.print("Nomad.makePipeSticky()");
        position.setSticky();
    }

    /**
     * This method is responsible for breaking NetworkElements, players can only break pipes
     */
    public void breakPipe() {
        Proto.print("Player.breakPipe()");
        Proto.tab++;
        position.breakPipe();
        Proto.tab--;
    }

    /**
     * This method is responsible for taking a pipe
     * It disconnects the pipe of the player's position and adds it to the inventory of player, if it is possible
     * Taking is not possible if the inventory of the player is full or the pipe is already in its inventory or the pipe is in another player's inventory
     * @param pipeToDisconnect Pipe, that need to be taken
     */
    public void takePipe(NetworkElement pipeToDisconnect) {
        Proto.print("Player.takePipe()");
        Proto.tab++;
        try {
            if (inventory.isFull()) {
                if (!inventory.contains((Pipe) pipeToDisconnect)) {
                    Proto.log("inventory is full");
                    Proto.tab--;
                    return;
                } else {
                    position.disconnectPipe(pipeToDisconnect);
                    Proto.log("pipe already in inventory");
                    Proto.log("pipe taken");
                    Proto.tab--;
                    return;
                }
            } else if (pipeToDisconnect.inInventory && inventory.isEmpty()) {
                Proto.log("pipe is in an other inventory");
                Proto.log("pickup failed");
                return;
            }
            inventory.addPipe((Pipe) pipeToDisconnect);
            position.disconnectPipe(pipeToDisconnect);
            Proto.log("pipe taken");
            Proto.tab--;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Proto.tab--;
    }

    /**
     * This method is responsible for taking pumps into player's inventory
     */
    public void takePump() {
        Proto.print("Player.takePump()");
        Proto.tab++;
        try {
            position.pickUpPump(inventory);
        } catch (Exception e) {
            Proto.log("pickup failed");
        }
        Proto.tab--;
    }

    public abstract void makePipeSlippery();

    public abstract void repair();

    /**
     * This method creates and returns the values of the player in a string
     * @return values of a player in string
     */
    public String toString() {
        return "Player stuck: " + stuck + " " + stuckTimeLeft + " " + inventory.toString() + "position: ";
    }
}
