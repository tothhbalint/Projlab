//
//
//  @ Project : ProjlabSkeleton
//  @ File Name : Nomad.java
//  @ Date : 16/04/2023
//
//

/** */
public class Nomad extends Player {
	/** */
	public void breakElement(NetworkElement ne) {
		Skeleton.indentPrint("Nomad: breakElement()");
	}
	
	/** */
	public void takePump(Inventory inv) {
		Skeleton.indentPrint("Nomad: takePump()");
	}
	
	/** */
	public void placePump() {
		Skeleton.indentPrint("Nomad: placePump()");
	}
	
	/** */
	public void connectPipe() {
		Skeleton.indentPrint("Nomad: connectPipe()");
	}
	
	/** */
	public void disconnectPipe(NetworkElement ne) {
		Skeleton.indentPrint("Nomad: disconnectPipe()");
	}
}
