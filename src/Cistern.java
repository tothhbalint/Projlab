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
	
	/** TODO */
	public boolean accept(Player p) {
		return false;
	}
	
	/** */
	public void remove(Player p) {
		//TODO
	}

	/** */
	public void pickUpPump(Inventory inv) {
		inv.addPump(new Pump());
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
}
