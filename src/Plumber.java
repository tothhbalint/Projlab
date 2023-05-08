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
	public void repair(Pump pump) {
		pump.repairPump();
	}

	public void repair(Pipe pipe){
		pipe.repairPipe();
	}
	
	/** */
	public void takePump() {
		this.inventory.addPump(new Pump());
	}
	
	/** TODO */
	public void placePump() {
	}
	
	/** TODO */
	public void connectPipe() {
	}
	
	/** TODO */
	public void disconnectPipe(NetworkElement ne) {
		Pipe pipe = (Pipe) position;
		if (pipe.isConnected(ne)){
			pipe.removeConnection(ne);
			ne.removeConnection(pipe);
			inventory.addPipe(pipe);
		}
	}
}
