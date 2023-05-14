//
//
//  @ Project : ProjlabSkeleton
//  @ File Name : Pipe.java
//  @ Date : 16/04/2023
//
//

import java.io.Console;
import java.util.Random;

/** */
public class Pipe extends NetworkElement {
	private NetworkElement input;
	private NetworkElement output;
	private boolean sticky = false;
	private boolean slippery = false;
	private int stickyTimeLeft = 0;
	private int slipperyTimeLeft = 0;
	private int repairProtectionTimeLeft = 0;
	private Random rand = new Random();

	public void tick() {
		Proto.print("pipe.tick");
		if(!isDamaged()){
			output.recieveWater(this);
		}
		output.recieveWater(this);
		if (sticky){
			stickyTimeLeft--;
			if (stickyTimeLeft <= 0){
				sticky = false;
			}
		}
		if (slippery){
			slipperyTimeLeft--;
			if (slipperyTimeLeft <= 0){
				slippery = false;
			}
		}
		if (repairProtectionTimeLeft > 0){
			repairProtectionTimeLeft--;
		}


	}
	public void setStickyTimeLeft(int i){
		stickyTimeLeft = i;
	}

	public void addConnection(NetworkElement ne){
		Proto.print("pipe.addConnection");
		if (this.connections.size() < 2){
			this.connections.add(ne);
		} else {
			System.out.println("Pipe already has 2 connections");
		}
	}
	

	public void setInput(NetworkElement ne) {
		this.input = ne;
	}


	public NetworkElement getRandomConnection(){
		Proto.print("pipe.getRandomConnection");
		int index = rand.nextInt(this.connections.size());
		NetworkElement neighbour = this.connections.get(index);
		return neighbour;
	}

	public boolean accept(Player p) {

		Proto.print("pipe.accept");

		if (this.isOccupied()){
			Proto.print(this.toString() + " occupied");
			return false;
		}else{
			NetworkElement ne = p.getPosition();

			if (this.isConnected(ne)){
				ne.remove(p);
				if (this.slippery){
					if (this.sticky){ //Slippery AND Sticky
						Proto.print("Error: Pipe slippery AND sticky");
					}else{ //Slippery
						Proto.print("player.getPosition()");
						NetworkElement neighbour = this.getRandomConnection();
						p.setPosition(neighbour);
						neighbour.setOccupied(true);
						neighbour.occupants.add(p);
						Proto.print("player_accepted");
						return true;
					}
				}else{
					if (this.sticky){ //Sticky
						Proto.print("player.getPosition()");
						p.setPosition(this);
						p.setStuck(true);
						p.setStuckTimeLeft(rand.nextInt(3)+1);
						this.setOccupied(true);
						Proto.print("player_accepted");
						return true;
					}else{ //Normal
						this.setOccupied(true);
						p.setPosition(this);
						Proto.print("player_accepted");
						return true;
					}
				}
			}
			return false;
		}
	}

	public void remove(Player p) {
		Proto.print("pipe.remove");
		this.setOccupied(false);
		this.occupants.remove(p);
		Proto.print("player_removed");
	}

	/** TODO */
	public void recieveWater(NetworkElement ne) {
		Proto.print("pipe.receiveWater");
		if (isDamaged()){
			this.setWaterState(false);
			increasePlumberPoint();
		}
	}

	/** */
	public void pickUpPump(Inventory inv) {
		Proto.print("pipe.pickUpPump");
		Proto.print("No_pump_available");
	}

	/** */
	public void direct(NetworkElement in, NetworkElement out) {
		Proto.print("pipe.direct");
		Proto.print("Pipe_cannot_be_directed");
	}

	public void breakPipe(){
		Proto.print("pipe.breakPipe");
		if (this.repairProtectionTimeLeft <= 0)
			this.damaged = true;
		Proto.print("pipe_broken");
	}

	public void repair(){
		Proto.print("pipe.repairPipe");
		this.damaged = false;
		this.repairProtectionTimeLeft = 5;
		Proto.print("pipe_repaired");
	}

	public void setSticky(){
		Proto.print("pipe.setSticky");
		if (!slippery){
			sticky = true;
			stickyTimeLeft = 5;
		}
		Proto.print("pipe_now_sticky");
	}

	public void setSlippery(){
		Proto.print("pipe.setSlippery");
		if (!sticky){
			slippery = true;
			slipperyTimeLeft = 5;
		}
		Proto.print("pipe_now_slippery");
	}

	public boolean isSticky(){
		return sticky;
	}

	public boolean isSlippery(){
		return slippery;
	}

	public boolean isRepairProtected(){
		return repairProtectionTimeLeft > 0;
	}

	public String toString(){
		return "Pipe" + super.toString();
	}

	/** */
	public void connectPipe(NetworkElement ne) { ; }

	/** */
	public void disconnectPipe(NetworkElement ne) { ; }
	/** */
	public boolean placePump() {
		return true;
	}

	public NetworkElement getPipeOutput() {
		return output;
	}

	public void removePipeOutput(NetworkElement ne) {
		output = null;
		removeConnection(ne);
	}

	public void addPipeOutput(NetworkElement ne) {
		output = ne;
		addConnection(ne);
	}

	public void addPipeInput(NetworkElement ne) {
		input = ne;
		addConnection(ne);
	}
}
