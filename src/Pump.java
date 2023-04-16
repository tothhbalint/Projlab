//
//  @ Project : ProjlabSkeleton
//  @ File Name : Pump.java
//  @ Date : 16/04/2023
//
//

/** */
public class Pump extends NetworkElement {
	@Override
	public boolean isConnected(NetworkElement ne) {
		System.out.println("Pump : isConnected()");
		return false;
	}

	/** */
	public void tick() {
		System.out.println("Pump : tick()");
	}
	
	/** */
	public boolean accept(Player p) {
		System.out.println("Pump : accept()");
		return false;
	}
	
	/** */
	public void remove(Player p) {
		System.out.println("Pump : remove()");
	}
	
	/** */
	public void direct(NetworkElement n) {
		System.out.println("Pump : direct()");
	}
	
	/** */
	public void pickUpPump(Inventory inv) {
		System.out.println("Pump : pickUpPump()");
	}
	
	/** */
	public void addConnection(NetworkElement ne) {
		System.out.println("Pump : addConnection()");
	}
	
	/** */
	public void removeConnection(NetworkElement ne) {
		System.out.println("Pump : removeConnection()");
	}
	
	/** */
	public void recieveWater(NetworkElement ne) {
		System.out.println("Pump : recieveWater()");
	}
}
