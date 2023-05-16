//
//
//  @ Project : ProjlabSkeleton
//  @ File Name : Source.java
//  @ Date : 16/04/2023
//
//

/** */
public class Source extends NetworkElement {
	Source() {
		hasWater = true;
	}

	/** */
	public void tick() {
		Proto.print("source.tick");
		Proto.tab++;
		for(NetworkElement n : this.connections){
			n.recieveWater(this);
		}
		Proto.tab--;
	}

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
	public void remove(Player p) {
		Proto.print("source.remove");
		Proto.tab++;
		this.occupants.remove(p);
		Proto.print("player_removed");
		Proto.tab--;
	}

	/** */
	public void recieveWater(NetworkElement ne) throws UnsupportedOperationException{
		throw new UnsupportedOperationException("Source cannot recieve water");
	}

	/** */
	public void pickUpPump(Inventory inv) throws UnsupportedOperationException{
		throw new UnsupportedOperationException("Pump cannot be picked up from Source");
	}

	/** */
	public void direct(NetworkElement in, NetworkElement out) throws UnsupportedOperationException{
		throw new UnsupportedOperationException("Source cannot be direct");
	}

	public void connectPipe(NetworkElement ne) {
		Proto.print("source.connectPipe");
		Proto.tab++;
		this.addConnection(ne);
		ne.addConnection(this);
		Proto.print("pipe_connected");
		Proto.tab--;
	}

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
