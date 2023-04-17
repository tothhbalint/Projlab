//
//
//  @ Project : ProjlabSkeleton
//  @ File Name : Nomad.java
//  @ Date : 16/04/2023
//
//

import javax.net.ssl.SSLContext;

/** */
public class Nomad extends Player {
	/** */
	public void breakElement(NetworkElement ne) {
		Skeleton.indentPrint("Nomad: breakElement()");
		Skeleton.INDENT++;

		ne.setDamaged(true);
	}
	
	/** */
	public void takePump(Inventory inv) {
		Skeleton.indentPrint("Nomad: takePump()");
	}
	
	/** */
	public void placePump() {
		Skeleton.indentPrint("Nomad: placePump()");
		Skeleton.INDENT++;
		Skeleton.indentPrint("Nomads cannot place pumps");
		Skeleton.INDENT--;
	}
	
	/** */
	public void connectPipe() {
		Skeleton.indentPrint("Nomad: connectPipe()");
		Skeleton.INDENT++;
		Skeleton.indentPrint("Nomads cannot connect pipes");
		Skeleton.INDENT--;
	}
	
	/** */
	public void disconnectPipe(NetworkElement ne) {
		Skeleton.indentPrint("Nomad: disconnectPipe()");
		Skeleton.INDENT++;
		Skeleton.indentPrint("Nomads cannot disconnect pipes");
		Skeleton.INDENT--;
	}
}
