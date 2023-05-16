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
		Proto.print("pump.tick");
		Random rand = new Random();
		int randomInt = rand.nextInt(this.connections.size());
		if(output == null)
			output = this.connections.get(randomInt);

		if(input == null){
			int temp = rand.nextInt(this.connections.size());
			if(temp == randomInt){
				temp = (temp + 1) % this.connections.size();
			}
			input = this.connections.get(temp);
		}

		Proto.tab++;
		if (rand.nextInt(10) < 2)
			this.breakPump();

		if(!isDamaged())
			output.recieveWater(this);

		Proto.tab--;
	}

	public boolean accept(Player p) {
		Proto.print("pump.accept");
		Proto.tab++;
		NetworkElement ne = p.getPosition();
		if(this.isConnected(ne)){
			this.occupied = true;
			p.setPosition(this);
			ne.remove(p);
			Proto.log("player accepted");
			Proto.tab--;
			return true;
		}
		Proto.log("player rejected");
		Proto.tab--;
		return false;
	}
	public void remove(Player p) {
		Proto.print("pump.remove");
		Proto.tab++;
		this.occupants.remove(p);
		this.occupied = false;
		Proto.log("player removed");
		Proto.tab--;
	}
	
	public void direct(NetworkElement in, NetworkElement out) {
		Proto.print("pump.direct");
		Proto.tab++;
		this.input = in;
		Proto.print("new_input_" + in.toString());
		this.output = out;
		Proto.print("new_output_" + out.toString());
		Proto.log("pump direction changed");
		Proto.tab--;
	}

	public String toString(){
		return "Pump" + super.toString();
	}

	@Override
	public void breakPipe() throws UnsupportedOperationException{
		throw new UnsupportedOperationException("Pump is not a Pipe");
	}

	/** */
	public void recieveWater(NetworkElement ne) {
		Proto.print("pump.recieveWater");
		Proto.tab++;
		if(!damaged){
			hasWater = true;
			output.recieveWater(this);
			Proto.log("pump has water");
		}else
			hasWater = false;
			Proto.log("pump is broken");
		Proto.tab--;
	}

	public void pickUpPump(Inventory inv) throws UnsupportedOperationException{
		throw new UnsupportedOperationException("Pump cannot be picked up");
	}

	public void setInput(NetworkElement ne) {
		this.input = ne;
	}

	public void setOutput(NetworkElement ne) {
		this.output = ne;
	}

	public void repair(){
		Proto.print("pump.repairPump");
		Proto.tab++;
		this.damaged = false;
		age = 0;
		Proto.log("pump repaired");
		Proto.tab--;
	}

	@Override
	public void setSlippery() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Pump cannot be slippery");
	}

	@Override
	public void setSticky() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Pump cannot be sticky");
	}

	private void breakPump(){
		Proto.print("pump.breakPump");
		Proto.tab++;
		int randNum = rand.nextInt(20 - age);
		if (randNum == 0){
			this.damaged = true;
		}
		age++;
		Proto.print("pump broken");
		Proto.tab--;
	}

	public void connectPipe(NetworkElement ne) {
		Proto.print("pump.connectPipe");
		Proto.tab++;
		this.addConnection(ne);
		ne.addConnection(this);
		Proto.print("pipe connected");
		Proto.tab--;
	}

	public void disconnectPipe(NetworkElement ne) {
		Proto.print("pump.disconnectPipe");
		Proto.tab++;
		this.removeConnection(ne);
		ne.removeConnection(this);
		Proto.print("pipe disconnected");
		Proto.tab--;
	}


	public boolean placePump() throws UnsupportedOperationException{
		throw new UnsupportedOperationException("Pump cannot be placed here");
	}

	public NetworkElement getPipeOutput() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Pump is not a Pipe");
	}

	public void removePipeOutput(NetworkElement ne) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Pump is not a Pipe");
	}

	public void addPipeOutput(NetworkElement ne) throws UnsupportedOperationException{
		throw new UnsupportedOperationException("Pump is not a Pipe");
	}

	public void addPipeInput(NetworkElement ne) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Pump is not a pipe");
	}
}
