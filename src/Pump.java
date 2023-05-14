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

	public void tick() {
		Proto.print("pump.breakPump");
		if (rand.nextInt(10) < 2){
			this.breakPump();
		}
	}
	public boolean accept(Player p) {
		Proto.print("pump.accept");
		NetworkElement ne = p.getPosition();
		if(this.isConnected(ne)){
			Proto.print("player_accepted");
			return true;
		}
		return false;
	}
	public void remove(Player p) {
		Proto.print("pump.remove");
		this.occupants.remove(p);
		Proto.print("player_removed");
	}
	
	/**
	 * CHANGED: +1 param for input
	 * */
	public void direct(NetworkElement in, NetworkElement out) {
		Proto.print("pump.direct");
		this.input = in;
		Proto.print("new_input_" + in.toString());
		this.output = out;
		Proto.print("new_output_" + out.toString());
		Proto.print("pump_direction_changed");
	}

	public String toString(){
		return "Pump" + super.toString();
	}

	@Override
	public void breakPipe() {

	}

	/** */
	public void recieveWater(NetworkElement ne) {
		if(!damaged){
			output.recieveWater(this);
		}
	}

	public void pickUpPump(Inventory inv) {
		Proto.print("pump.pickUpPump");
		Proto.print("pump_cannot_be_disconnected");
	}

	public void setInput(NetworkElement ne) {
		this.input = ne;
	}

	public void repair(){
		Proto.print("pump.repairPump");
		this.damaged = false;
		age = 0;
		Proto.print("pump_repaired");
	}

	private void breakPump(){
		Proto.print("pump.breakPump");
		int randNum = rand.nextInt(20 - age);
		if (randNum == 0){
			this.damaged = true;
		}
		age++;
		Proto.print("pump_broken");
	}

	public void connectPipe(NetworkElement ne) {
		Proto.print("pump.connectPipe");
		this.addConnection(ne);
		ne.addConnection(this);
		Proto.print("pipe_connected");
	}

	public void disconnectPipe(NetworkElement ne) {
		Proto.print("pump.disconnectPipe");
		this.removeConnection(ne);
		ne.removeConnection(this);
		Proto.print("pipe_disconnected");
	}


	public boolean placePump() {
		Proto.print("pump.placePump");
		Proto.print("pump_cannot_be_placed_on_pump");
		return false;
	}

	public NetworkElement getPipeOutput() {
		return null;
	}

	public void removePipeOutput(NetworkElement ne) {
		//NOTHING
	}

	public void addPipeOutput(NetworkElement ne) {
		//NOTHING
	}

	public void addPipeInput(NetworkElement ne) {
		//NOTHING
	}
}
