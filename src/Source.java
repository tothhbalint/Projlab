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
		Skeleton.indentPrint("Source: Source()");
		Skeleton.INDENT++;
		Skeleton.indentPrint("Source created");
		Skeleton.INDENT--;
	}
	/** */
	public void tick() {
	}
	
	/** */
	public boolean accept(Player p) {
		Skeleton.indentPrint("Source : accept()");
		return false;
	}
	
	/** */
	public void remove(Player p) {
		Skeleton.indentPrint("Source : remove()");
	}
	
	/** */
	public void direct(NetworkElement in,NetworkElement out) {
		Skeleton.indentPrint("Source : direct()");
		Skeleton.INDENT++;
		Skeleton.indentPrint("Can't direct a source");
		Skeleton.INDENT--;
	}
	
	/** */
	public void pickUpPump(Inventory inv) {
		Skeleton.indentPrint("Source : pickUpPump()");
	}
	
	/** */
	public void addConnection(NetworkElement ne) {
		Skeleton.indentPrint("Source : addConnection()");
	}
	
	/** */
	public void removeConnection(NetworkElement ne) {
		Skeleton.indentPrint("Source : removeConnection()");
	}

	@Override
	public boolean isConnected(NetworkElement ne) {
		Skeleton.indentPrint("Source : isConnected()");
		return false;
	}

	/** */
	public void recieveWater(NetworkElement ne) {
		Skeleton.indentPrint("Source : recieveWater()");
	}
}
