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
		Skeleton.indentPrint("Pump : isConnected()");
		return false;
	}

	/** */
	public void tick() {
		Skeleton.indentPrint("Pump : tick()");
	}
	
	/** */
	public boolean accept(Player p) {
		Skeleton.indentPrint("Pump : accept()");
		return false;
	}
	
	/** */
	public void remove(Player p) {
		Skeleton.indentPrint("Pump : remove()");
	}
	
	/** */
	public void direct(NetworkElement n) {
		Skeleton.indentPrint("Pump : direct()");
	}
	
	/** */
	public void pickUpPump(Inventory inv) {
		Skeleton.indentPrint("Pump : pickUpPump()");
	}
	
	/** */
	public void addConnection(NetworkElement ne) {
		Skeleton.indentPrint("Pump : addConnection()");
	}
	
	/** */
	public void removeConnection(NetworkElement ne) {
		Skeleton.indentPrint("Pump : removeConnection()");
	}
	
	/** */
	public void recieveWater(NetworkElement ne) {
		Skeleton.indentPrint("Pump : recieveWater()");
	}
}
