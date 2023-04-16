//
//
//  @ Project : ProjlabSkeleton
//  @ File Name : Pipe.java
//  @ Date : 16/04/2023
//
//

/** */
public class Pipe extends NetworkElement {
	/** */
	public void tick() {
	}
	
	/** */
	public void setInput() {
	}
	
	@Override
	public boolean isConnected(NetworkElement ne) {
		Skeleton.indentPrint("Pipe : isConnected()");
		return false;
	}

	/** */
	public boolean accept(Player p) {
		Skeleton.indentPrint("Pipe : accept()");
		return false;
	}
	
	/** */
	public void remove(Player p) {
		Skeleton.indentPrint("Pipe : remove()");
	}
	
	/** */
	public void direct(NetworkElement n) {
		Skeleton.indentPrint("Pipe : direct()");
	}
	
	/** */
	public void pickUpPump(Inventory inv) {
		Skeleton.indentPrint("Pipe : pickUpPump()");
	}
	
	/** */
	public void addConnection(NetworkElement ne) {
		Skeleton.indentPrint("Pipe : addConnection()");
	}
	
	/** */
	public void removeConnection(NetworkElement ne) {
		Skeleton.indentPrint("Pipe : removeConnection()");
	}
	
	/** */
	public void recieveWater(NetworkElement ne) {
		Skeleton.indentPrint("Pipe : recieveWater()");
	}
}
