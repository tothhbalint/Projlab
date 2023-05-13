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
		for(NetworkElement n : this.connections){
			n.recieveWater(this);
		}
	}

	public boolean accept(Player p) {
		Proto.print("source.accept");
		NetworkElement ne = p.getPosition();
		if (this.isConnected(ne)) {
			ne.remove(p);
			this.occupants.add(p);
			Proto.print("player_accepted");
			return true;
		}
		Proto.print("player_not_accepted");
		return false;
	}
	public void remove(Player p) {
		Proto.print("source.remove");
		this.occupants.remove(p);
		Proto.print("player_removed");
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



	public boolean placePump() {
		return false;
	}

	public NetworkElement getPipeOutput() {
		return null;
	}

	public void removePipeOutput(NetworkElement ne) {
		//NOTHING
	}

	public void addPipeOutput(NetworkElement ne) {
		//NOTHING
	}

	public void addPipeInput(NetworkElement ne) {
		//NOTHING
	}

	public String toString(){
		return "Source" + super.toString();
	}

}
