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
		Skeleton.INDENT++;
		Skeleton.indentPrint("Plumber: repair()");
		ne.setDamaged(false);
		Skeleton.INDENT--;
	}
	
	/** */
	public void takePump() {
		Skeleton.indentPrint("Plumber: takePump()");

		Skeleton.INDENT++;

		NetworkElement ne = this.getPosition();

		ne.pickUpPump(Skeleton.inventory);

	}
	
	/** */
	public void placePump() { Skeleton.indentPrint("Plumber: placePump()"); }
	
	/** */
	public void connectPipe() {
		Skeleton.indentPrint("Plumber: connectPipe()");
	}
	
	/** */
	public void disconnectPipe(NetworkElement ne) {
		Skeleton.indentPrint("Plumber: disconnectPipe()");
	}
}
