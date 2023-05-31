package Model;//
//
//  @ Project : ProjlabSkeleton
//  @ File Name : Pipe.java
//  @ Date : 16/04/2023
//
//

import java.io.Console;
import java.util.Random;

/**
 * This class is responsible for pipes
 */
public class Pipe extends NetworkElement {
    /**
     * Stores if the pipe is sticky
     */
    private boolean sticky = false;
    /**
     * Stores if the pipe is slippery
     */
    private boolean slippery = false;
    /**
     * Stores how much time left till the pipe is sticky
     */
    private int stickyTimeLeft = 0;
    /**
     * Stores how much time left till the pipe is slippery
     */
    private int slipperyTimeLeft = 0;
    /**
     * Stores how much time left till the pipe is protected
     */
    private int repairProtectionTimeLeft = 0;
    /**
     * Random generator
     */
    private Random rand = new Random();

    public Pipe(){
        super();
    }

    /**
     * This method is responsible for the flowing of water and timing
     * This method returns if the pipe has no output or the pipe is damaged
     * This method is called in every turn
     */
    public void tick() {
        Proto.print("pipe.tick");
        Proto.tab++;
        if (output == null) {
            Proto.log("water cant flow no output");
            Proto.tab--;
            return;
        } else if (isDamaged()) {
            Proto.log("water cant flow pipe is damaged");
            Proto.tab--;
            return;
        }
        Proto.tab--;
        if (sticky) {
            stickyTimeLeft--;
            if (stickyTimeLeft <= 0) {
                sticky = false;
            }
        }
        if (slippery) {
            slipperyTimeLeft--;
            if (slipperyTimeLeft <= 0) {
                slippery = false;
            }
        }
        if (repairProtectionTimeLeft > 0) {
            repairProtectionTimeLeft--;
        }
        if(this.input == null){
            this.hasWater = false;
        }else if(!this.input.hasWater){
            this.hasWater = false;
        }
    }

    /**
     * This method adds a NetworkElement to the list of connections
     *
     * @param ne NetworkElement, that need to be added
     */
    public void addConnection(NetworkElement ne) {
        Proto.print("pipe.addConnection");
        Proto.tab++;
        if (this.connections.size() < 2) {
            this.connections.add(ne);
            Proto.log("connection added");
        } else {
            System.out.println("Pipe already has 2 connections");
        }
        Proto.tab--;
    }

    /**
     * This method sets the input of the pipe
     *
     * @param ne NetworkElement, that need to be set as input
     */
    public void setInput(NetworkElement ne) {
        this.input = ne;
    }

    /**
     * This method gets a random NetworkElement that is connected to the pipe
     *
     * @return random NetworkElement
     */
    public NetworkElement getRandomConnection() {
        Proto.print("pipe.getRandomConnection");
        Proto.tab++;
        int index = rand.nextInt(this.connections.size());
        NetworkElement neighbour = this.connections.get(index);
        Proto.print("neighbour: " + neighbour.toString());
        Proto.tab--;
        return neighbour;
    }

    /**
     * This method is responsible for the move of a player
     *
     * @param p Player, which wants to move
     * @return true, if the step is available, false if it is not
     */
    public boolean accept(Player p) {
        Proto.print("pipe.accept");
        Proto.tab++;
        if (this.isOccupied()) {
            Proto.log("pipe occupied");
        } else {
            NetworkElement ne = p.getPosition();
            if (this.isConnected(ne) || ne == null) {
                if (this.slippery) {
                    if (this.sticky) { //Slippery AND Sticky
                        Proto.print("Error: Pipe slippery AND sticky");
                    } else { //Slippery
                        NetworkElement neighbour = this.getRandomConnection();
                        p.setPosition(this);
                        if (neighbour.accept(p)) {
                            if (ne != neighbour) {
                                assert ne != null;
                                ne.remove(p);
                            }
                            Proto.log("player slipped");
                            Proto.tab--;
                            return true;
                        } else {
                            p.setPosition(ne);
                            this.setOccupied(true);
                            Proto.log("player accepted");
                            Proto.tab--;
                            return true;
                        }
                    }
                } else {
                    if (this.sticky) { //Sticky
                        p.setPosition(this);
                        p.setStuck(true);
                        p.setStuckTimeLeft(rand.nextInt(12, 20) + 1);
                        this.setOccupied(true);
                        Proto.log("player accepted");
                        Proto.tab--;
                        return true;
                    } else if (!this.occupied) { //Normal
                        this.setOccupied(true);
                        p.setPosition(this);
                        Proto.log("player accepted");
                        Proto.tab--;
                        return true;
                    }
                }
            }
        }
        Proto.log("player rejected");
        Proto.tab--;
        return false;
    }

    /**
     * This player removes a player from the pipe
     *
     * @param p The player, which needs to be removed
     */
    public void remove(Player p) {
        Proto.print("pipe.remove");
        Proto.tab++;
        this.setOccupied(false);
        this.occupants.remove(p);
        Proto.log("player removed");
        Proto.tab--;
    }

    /**
     * This method controls the flowing of water
     *
     * @param ne NetworkElement
     */
    public void receiveWater(NetworkElement ne) {
        Proto.print("pipe.receiveWater");
        Proto.tab++;
        if (isDamaged()) {
            this.setWaterState(false);
            increaseNomadPoint();
            Proto.log("Nomad points increased");
        } else if (this.input == null || !this.input.hasWater) {
            this.setWaterState(false);
            Proto.log("water cant flow no input");
        } else {
                this.setWaterState(true);
//            if (this.output != null) lehet fog meg kelleni
            Proto.log("Water state set to true");
        }
        Proto.tab--;
    }

    /**
     * This method is not implemented
     */
    public void pickUpPump(Inventory inv) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Pump cannot be picked up here");
    }

    /**
     * This method is not implemented
     */
    public void direct(NetworkElement in, NetworkElement out) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Cannot direct here");
    }

    /**
     * This method sets the pipe damaged if it has no protection
     */
    public void breakPipe() {
        Proto.print("pipe.breakPipe");
        Proto.tab++;
        if (this.repairProtectionTimeLeft <= 0)
            this.damaged = true;
        this.hasWater = false;
        Proto.log("pipe broken");
        Proto.tab--;
    }

    /**
     * This method sets the pipe repaired (not damaged)
     */
    public void repair() {
        Proto.print("pipe.repairPipe");
        Proto.tab++;
        this.damaged = false;
        this.repairProtectionTimeLeft = 20;
        Proto.log("pipe repaired");
        Proto.tab--;
    }

    /**
     * This method sets the pipe sticky
     */
    public void setSticky() {
        Proto.print("pipe.setSticky");
        Proto.tab++;
        if (!slippery) {
            sticky = true;
            stickyTimeLeft = 15;
            Proto.log("pipe now sticky");
        } else {
            Proto.log("Error: Pipe sticky AND slippery");
        }
        Proto.tab--;
    }

    /**
     * this method sets the pipe slippery
     */
    public void setSlippery() {
        Proto.print("pipe.setSlippery");
        Proto.tab++;
        if (!sticky) {
            slippery = true;
            slipperyTimeLeft = 20;
            Proto.log("pipe now slippery");
        } else {
            Proto.log("Error: Pipe sticky AND slippery");
        }
        Proto.tab--;
    }

    /**
     * This method gets if the pipe is sticky
     *
     * @return true, if the pipe is sticky, false if it is not
     */
    public boolean isSticky() {
        return sticky;
    }

    /**
     * This method gets if the pipe is slippery
     *
     * @return true, if the pipe is slippery, false if it is not
     */
    public boolean isSlippery() {
        return slippery;
    }

    /**
     * This method gets if the pipe is protected
     *
     * @return true, if it has protection, false if it has not
     */
    public boolean isRepairProtected() {
        return repairProtectionTimeLeft > 0;
    }

    /**
     * This method creates and returns the current values of the pipe in a string
     *
     * @return string that contains the values
     */
    public String toString() {
        return "Pipe" + super.toString() + " sticky: " + sticky + " slippery: " + slippery;
    }

    /**
     * This method is not implemented
     */
    public void connectPipe(NetworkElement ne) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Cannot connect a Pipe with another Pipe");
    }

    /**
     * This method is not implemented
     */
    public void disconnectPipe(NetworkElement ne) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Cannot disconnect a Pipe from itself");
    }

    /**
     * This method checks if the pipe is allowed to place pump
     *
     * @return true, because it is allowed
     */
    public boolean placePump() {
        return true;
    }

    /**
     * This method gets the output of the pipe
     *
     * @return output of pipe
     */
    public NetworkElement getPipeOutput() {
        return output;
    }

}
