//
//
//  @ Project : ProjlabSkeleton
//  @ File Name : Source.java
//  @ Date : 16/04/2023
//
//

/** */
public class Source extends NetworkElement {

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
	public void recieveWater(NetworkElement ne) {
		Proto.print("source_cannot_receive_water");
	}

	/** */
	public void pickUpPump(Inventory inv) {
		Proto.print("source.pickUpPump");
		Proto.print("no_pump_available_for_pickup");
	}

	/** */
	public void direct(NetworkElement in, NetworkElement out) {
		Proto.print("source.direct");
		Proto.print("source_cannot_be_directed");
	}

	public void connectPipe(NetworkElement ne) {
		Proto.print("source.connectPipe");
		this.addConnection(ne);
		ne.addConnection(this);
		Proto.print("pipe_connected");
	}

	public void disconnectPipe(NetworkElement ne) {
		Proto.print("source.disconnectPipe");
		this.removeConnection(ne);
		ne.removeConnection(this);
		Proto.print("pipe_disconnected");
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
