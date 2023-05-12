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
	
	/**
	 * ???
	 * */
	/*
	*public void takePump() {
	* 	this.inventory.addPump(new Pump());
	*}
	*/

	/**
	 * TODO check if position is Cistern
	 * */
	public void takePump(Inventory inv) {
		position.pickUpPump(this.inventory);
	}

	/** TODO */
	public void placePump() {
	}
	
	/** TODO
	 * tempP is local, ?is it good?
	 * IDEA: inventory should have getters (based on 7.0.3.3 sequence diagram)
	 * */
	public void connectPipe() {
		Pipe tempP = this.inventory.removePipe();
		position.addConnection(tempP);
		tempP.addConnection(this.position);
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
