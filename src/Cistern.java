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
	private int maxConnectionSize = 1;
	Random rand = new Random();

	/** */
	@Override
	public boolean isConnected(NetworkElement ne) {
		return ne.isConnected(this);
	}

	/** */
	public void tick() {
		if (this.hasWater){
			increasePlumberPoint();
			this.setWaterState(false);
		}
		if (rand.nextInt(10) < 2){
			maxConnectionSize++;
		}

	}

	public boolean accept(Player p) {
		NetworkElement ne = p.getPosition();
		for (NetworkElement n:
				this.connections) {
			if (n == ne){
				ne.remove(p);
				return true;
			}
		}
		return false;
	}

	/** */
	public void remove(Player p) {
		// Nothing
	}

	/** */
	public void pickUpPump(Inventory inv) {
		inv.addPump(new Pump());
	}

	/** */
	public void direct(NetworkElement in, NetworkElement out) {

	}

	public String toString(){
		return "Cistern" + super.toString();
	}

	/** */
	public void addConnection(NetworkElement ne) {
		if (this.connections.size() < this.maxConnectionSize) {
			this.connections.add(ne);
		}
	}

	/** */
	public void removeConnection(NetworkElement ne) {
		this.connections.remove(ne);
	}

	/** TODO */
	public void recieveWater(NetworkElement ne) {
		//TODO
	}

	public void connectPipe(NetworkElement ne) {
		this.addConnection(ne);
		ne.addConnection(this);
	}

	public void disconnectPipe(NetworkElement ne) {
		this.removeConnection(ne);
		ne.removeConnection(this);
	}


	public boolean placePump() {
		return false;
	}

	public NetworkElement getPipeOutput() {
		return null;
	}

	public void removePipeOutput(NetworkElement ne) {

	}

	public void addPipeOutput(NetworkElement ne) {

	}

	public void addPipeInput(NetworkElement ne) {

	}
}
