//
//
//  @ Project : ProjlabSkeleton
//  @ File Name : Cistern.java
//  @ Date : 16/04/2023
//
//

import java.util.Random;

/**
 * Cistern class is a NetworkElement, where the water goes
 */
public class Cistern extends NetworkElement {
	/**
	 * Random generator
	 */
	Random rand = new Random();

	/**
	 * This method is to check if the parameter is connected
	 * @param ne NetworkElement
	 * @return true, if 'ne' is connected, false if it is not
	 */
	@Override
	public boolean isConnected(NetworkElement ne) {
		return ne.isConnected(this);
	}

	/**
	 * This method creates a new Pipe one side connected to a Cistern, it happens with 20% chance in every tick
	 */
	public void tick() {
		Proto.print("cistern.tick");
		Proto.tab++;
		if (rand.nextInt(10) < 2){
			Pipe newPipe = new Pipe();
			newPipe.addConnection(this);
			this.addConnection(newPipe);
			Proto.log("new pipe added");
		}
		Proto.tab--;
	}

	/**
	 * This method checks if the player can move to the selected NetworkElement
	 * @param p Player, which wants to move
	 * @return true, if the player can step, false if it cannot
	 */
	public boolean accept(Player p) {
		Proto.print("cistern.accept");
		Proto.tab++;
		NetworkElement ne = p.getPosition();
		if (this.isConnected(ne)) {
			ne.remove(p);
			this.occupants.add(p);
			Proto.log("player accepted");
			Proto.tab--;
			return true;
		}
		Proto.log("player not accepted");
		Proto.tab--;
		return false;
	}

	/**
	 * This method removes a player from the right NetworkElement
	 * @param p The player, which needs to be removed
	 */
	public void remove(Player p) {
		Proto.print("cistern.remove");
		Proto.tab++;
		this.occupants.remove(p);
		Proto.log("player removed");
		Proto.tab--;
	}

	/**
	 * This method adds the picked up pump to player's inventory
	 * @param inv player's inventory
	 */
	public void pickUpPump(Inventory inv) {
		Proto.print("cistern.pickUpPump");
		Proto.tab++;
		inv.addPump(new Pump());
		Proto.log("pump added to the inventory");
		Proto.tab--;
	}

	/**
	 * This method would direct the cistern, but it is not directable
	 * @param in input
	 * @param out output
	 */
	public void direct(NetworkElement in, NetworkElement out) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Cistern cannot be directed");
	}

	/**
	 * This method gives the Cistern's name back
	 * @return cistern's name
	 */
	public String toString(){
		return "Cistern" + super.toString();
	}

	/**
	 * This method would break NetworkElement, but cisterns cannot be braked
	 * @throws UnsupportedOperationException
	 */
	@Override
	public void breakPipe() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Cistern cannot be braked");
	}

	/**
	 * This method would repair the cistern, but it cannot be braked, so it is no need to be repaired
	 * @throws UnsupportedOperationException
	 */
	public void repair() throws UnsupportedOperationException{
		throw new UnsupportedOperationException("Cistern cannot be repaired");
	}

	/**
	 * This method add a connection to connection list of a NetworkElement
	 * @param ne NetworkElement, which need to be added
	 */
	public void addConnection(NetworkElement ne) {
		Proto.print("cistern.addConnection");
		Proto.tab++;
		this.connections.add(ne);
		Proto.log("connection added");
		Proto.tab--;
	}

	/**
	 * This method removes a connection from the connection list
	 * @param ne NetworkElement, which need to be removed
	 */
	public void removeConnection(NetworkElement ne) {
		Proto.print("cistern.removeConnection");
		Proto.tab++;
		this.connections.remove(ne);
		Proto.log("connection removed");
		Proto.tab--;
	}

	/**
	 * This method increases the Plumber's points
	 * @param ne NetworkElement, where te water comes to the finish
	 */
	public void recieveWater(NetworkElement ne) {
		Proto.print("cistern.receiveWater");
		Proto.tab++;
		increasePlumberPoint();
		Proto.log("water recieved");
		Proto.tab--;
	}

	/**
	 * This method connects a pipe with a cistern
	 * @param ne Pipe, which you want to connect with the Cistern
	 */
	public void connectPipe(NetworkElement ne) {
		Proto.print("cistern.connectPipe");
		Proto.tab++;
		this.addConnection(ne);
		ne.addConnection(this);
		Proto.log("pipe connected");
		Proto.tab--;
	}

	/**
	 * This method disconnects a Pipe from a cistern
	 * @param ne Pipe, which you want to disconnect
	 */
	public void disconnectPipe(NetworkElement ne) {
		Proto.print("cistern.disconnectPipe");
		Proto.tab++;
		this.removeConnection(ne);
		ne.removeConnection(this);
		Proto.log("pipe disconnected");
		Proto.tab--;
	}

	/** */
	public boolean placePump() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Cannot place pump next to a Cistern");
	}

	/** */
	public NetworkElement getPipeOutput() throws UnsupportedOperationException{
		throw new UnsupportedOperationException("Cistern doesn't have any outputs");
	}

	/** */
	public void removePipeOutput(NetworkElement ne) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Cistern doesn't have any outputs");
	}

	/** */
	public void addPipeOutput(NetworkElement ne) throws UnsupportedOperationException{
		throw new UnsupportedOperationException("Cistern doesn't have any outputs");
	}

	/** */
	public void addPipeInput(NetworkElement ne) throws UnsupportedOperationException{
		throw new UnsupportedOperationException("Cistern is not a Pipe");
	}

	/** */
	public void setSlippery() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Cistern cannot be slippery");
	}

	/** */
	@Override
	public void setSticky() throws UnsupportedOperationException{
		throw new UnsupportedOperationException("Cistern cannot be sticky");
	}
}
