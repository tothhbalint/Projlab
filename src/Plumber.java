//
//
//  @ Project : ProjlabSkeleton
//  @ File Name : Plumber.java
//  @ Date : 16/04/2023
//
//

/** */
public class Plumber extends Player {
	/** */
	public void repair(NetworkElement ne) {
		Skeleton.indentPrint("Plumber: repair()");
	}
	
	/** */
	public void takePump(Inventory inv) {
		Skeleton.indentPrint("Plumber: takePump()");
	}
	
	/** */
	public void placePump() {
		Skeleton.indentPrint("Plumber: placePump()");
	}
	
	/** */
	public void connectPipe() {
		Skeleton.indentPrint("Plumber: connectPipe()");
	}
	
	/** */
	public void disconnectPipe(NetworkElement ne) {
		Skeleton.indentPrint("Plumber: disconnectPipe()");
	}
}
