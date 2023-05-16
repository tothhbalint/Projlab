//
//
//  @ Project : ProjlabSkeleton
//  @ File Name : Source.java
//  @ Date : 16/04/2023
//
//

/**
 * This class represents a source in the game.
 * */
public class Source extends NetworkElement {
	Source() {
		hasWater = true;
	}

	/**
	 * This method is called every turn.
	 * The source will send water to all of its connections.
	 * */
	public void tick() {
		Proto.print("source.tick");
		Proto.tab++;
		for(NetworkElement n : this.connections){
			n.recieveWater(this);
		}
		Proto.tab--;
	}

	/**
	 * This method is called when a player steps on the source.
	 * The player will be accepted if the source is connected to the player's current position.
	 * */
	public boolean accept(Player p) {
		Proto.print("source.accept");
		Proto.tab++;
		NetworkElement ne = p.getPosition();
		if (this.isConnected(ne)) {
			ne.remove(p);
			this.occupants.add(p);
			Proto.print("player_accepted");
			Proto.tab--;
			return true;
		}
		Proto.print("player_not_accepted");
		Proto.tab--;
		return false;
	}

	/**
	 * This method is called when a player leaves the source.
	 * */
	public void remove(Player p) {
		Proto.print("source.remove");
		Proto.tab++;
		this.occupants.remove(p);
		Proto.print("player_removed");
		Proto.tab--;
	}

	/**
	 * This isn't implemented in the source.
	 * The source cannot receive water.
	 * */
	public void recieveWater(NetworkElement ne) throws UnsupportedOperationException{
		throw new UnsupportedOperationException("Source cannot recieve water");
	}

	/**
	 * This isn't implemented in the source.
	 * You cannot pick up a pump from a source.
	 * */
	public void pickUpPump(Inventory inv) throws UnsupportedOperationException{
		throw new UnsupportedOperationException("Pump cannot be picked up from Source");
	}

	/**
	 * This isn't implemented in the source.
	 * The source cannot be directed, because it sends water to all of its connections.
	 * */
	public void direct(NetworkElement in, NetworkElement out) throws UnsupportedOperationException{
		throw new UnsupportedOperationException("Source cannot be direct");
	}

	/**
	 * This method is called when a pipe is placed next to the source.
	 * The source will connect to the pipe.
	 * @param ne The pipe that is placed next to the source. The source will connect to this pipe.
	 * */
	public void connectPipe(NetworkElement ne) {
		Proto.print("source.connectPipe");
		Proto.tab++;
		this.addConnection(ne);
		ne.addConnection(this);
		Proto.print("pipe_connected");
		Proto.tab--;
	}

	/**
	 * This method is called when a pipe is removed from next to the source.
	 * The source will disconnect from the pipe.
	 * @param ne The pipe that is removed from next to the source. The source will disconnect from this pipe.
	 * */
	public void disconnectPipe(NetworkElement ne) {
		Proto.print("source.disconnectPipe");
		Proto.tab++;
		this.removeConnection(ne);
		ne.removeConnection(this);
		Proto.print("pipe_disconnected");
		Proto.tab--;
	}


	public boolean placePump() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Cannot place Pump next to a Source");
	}


	public NetworkElement getPipeOutput() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("");
	}


	public void removePipeOutput(NetworkElement ne) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Source is not a Pipe");
	}

	/**
	 * This method is not implemented.
	 * */
	public void addPipeOutput(NetworkElement ne) throws UnsupportedOperationException{
		throw new UnsupportedOperationException("Source is not a Pipe");
	}


	public void addPipeInput(NetworkElement ne) throws UnsupportedOperationException{
		throw new UnsupportedOperationException("Source doesn't have input");
	}


	public String toString(){
		return "Source" + super.toString();
	}


	@Override
	public void breakPipe() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Source cannot be braked");
	}

	public void repair() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Source cannot be repaired");

	}

	@Override
	public void setSlippery() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Source cannot be slippery");
	}

	public void setSticky() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Source cannot be sticky");
	}
}
