//
//
//  @ Project : ProjlabSkeleton
//  @ File Name : Cistern.java
//  @ Date : 16/04/2023
//
//
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * */
public class Cistern extends NetworkElement {
	Random rand = new Random();

	/** */
	@Override
	public boolean isConnected(NetworkElement ne) {
		return ne.isConnected(this);
	}

	/** */
	public void tick() {
		Proto.print("cistern.tick");
		if (rand.nextInt(10) < 2){
			Pipe newPipe = new Pipe();
			newPipe.addConnection(this);
			this.addConnection(newPipe);
		}
	}

	public boolean accept(Player p) {
		Proto.print("cistern.accept");
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

	/** */
	public void remove(Player p) {
		Proto.print("cistern.remove");
		this.occupants.remove(p);
		Proto.print("player_removed");
	}

	/** */
	public void pickUpPump(Inventory inv) {
		Proto.print("cistern.pickUpPump");
		inv.addPump(new Pump());
		Proto.print("pump_added_to_inventory");
	}

	/** */
	public void direct(NetworkElement in, NetworkElement out) {
		Proto.print("cistern.direct");
		Proto.print("cistern_cannot_be_directed");
	}

	public String toString(){
		return "Cistern" + super.toString();
	}

	@Override
	public void breakPipe() {

	}

	public void repair(){
		throw new RuntimeException("Cistern cannot be repaired");
	}

	/** */
	public void addConnection(NetworkElement ne) {
		Proto.print("cistern.addConnection");
		this.connections.add(ne);
		Proto.print("connection_added");
	}

	/** */
	public void removeConnection(NetworkElement ne) {
		Proto.print("cistern.removeConnection");
		this.connections.remove(ne);
		Proto.print("connection_removed");
	}


	public void recieveWater(NetworkElement ne) {
		Proto.print("cistern.receiveWater");
		increasePlumberPoint();
		Proto.print("water_received");
	}

	public void connectPipe(NetworkElement ne) {
		Proto.print("cistern.connectPipe");
		this.addConnection(ne);
		ne.addConnection(this);
		Proto.print("pipe_connected");
	}

	public void disconnectPipe(NetworkElement ne) {
		Proto.print("cistern.disconnectPipe");
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

	public void setSlippery() {
		throw new RuntimeException("Cistern cannot be slippery");
	}

	@Override
	public void setSticky() {
		throw new RuntimeException("Cistern cannot be sticky");
	}
}
