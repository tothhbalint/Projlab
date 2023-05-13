//
//  @ Project : ProjlabSkeleton
//  @ File Name : Pump.java
//  @ Date : 16/04/2023
//
//


import java.util.Random;

/** */
public class Pump extends NetworkElement {
	private int age = 0;
	private NetworkElement input;
	private NetworkElement output;
	private Random rand = new Random();

	/** TODO randombly break */
	public void tick() {

	}
	public boolean accept(Player p) {
		NetworkElement ne = p.getPosition();
		for (NetworkElement n:
				this.connections) {
			if (n == ne){
				ne.remove(p);
				return true;
			}
		}
		return false;
	}
	public void remove(Player p) {
		// Nothing
	}
	
	/**
	 * CHANGED: +1 param for input
	 * */
	public void direct(NetworkElement in, NetworkElement out) {
		this.input = in;
		this.output = out;
	}

	public String toString(){
		return "Pump" + super.toString();
	}

	/** */
	public void recieveWater(NetworkElement ne) {

	}

	public void pickUpPump(Inventory inv) {

	}

	public void setInput(NetworkElement ne) {
		this.input = ne;
	}

	public void repairPump(){
		this.damaged = false;
		age = 0;
	}

	private void breakPump(){
		int randNum = rand.nextInt(20 - age);
		if (randNum == 0){
			this.damaged = true;
		}
		age++;
	}

	public void connectPipe(NetworkElement ne) {
		this.addConnection(ne);
		ne.addConnection(this);
	}

	public void disconnectPipe(NetworkElement ne) {
		this.removeConnection(ne);
		ne.removeConnection(this);
	}


	public boolean placePump() {
		return false;
	}

	public NetworkElement getPipeOutput() {
		return null;
	}

	public void removePipeOutput(NetworkElement ne) {

	}

	public void addPipeOutput(NetworkElement ne) {

	}

	public void addPipeInput(NetworkElement ne) {

	}
}
