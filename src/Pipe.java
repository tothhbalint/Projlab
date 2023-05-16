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
		Proto.tab++;
		if(output == null){
			Proto.print("no output");
			Proto.tab--;
			return;
		}else if(isDamaged()){
			Proto.print("damaged");
			Proto.tab--;
			return;
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
		Proto.tab++;
		if (this.connections.size() < 2){
			this.connections.add(ne);
			Proto.print("connection added");
		} else {
			System.out.println("Pipe already has 2 connections");
		}
		Proto.tab--;
	}
	

	public void setInput(NetworkElement ne) {
		this.input = ne;
	}


	public NetworkElement getRandomConnection(){
		Proto.print("pipe.getRandomConnection");
		Proto.tab++;
		int index = rand.nextInt(this.connections.size());
		NetworkElement neighbour = this.connections.get(index);
		Proto.print("neighbour: " + neighbour.toString());
		Proto.tab--;
		return neighbour;
	}

	public boolean accept(Player p) {
		Proto.print("pipe.accept");
		Proto.tab++;
		if (this.isOccupied()){
			Proto.print(this.toString() + " occupied");
			Proto.tab--;
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
						Proto.tab--;
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
						Proto.tab--;
						return true;
					}else{ //Normal
						this.setOccupied(true);
						p.setPosition(this);
						Proto.print("player_accepted");
						Proto.tab--;
						return true;
					}
				}
			}
			Proto.print("player_rejected");
			Proto.tab--;
			return false;
		}
	}

	public void remove(Player p) {
		Proto.print("pipe.remove");
		Proto.tab++;
		this.setOccupied(false);
		this.occupants.remove(p);
		Proto.print("player_removed");
		Proto.tab--;
	}

	public void recieveWater(NetworkElement ne) {
		Proto.print("pipe.receiveWater");
		Proto.tab++;
		if (isDamaged()){
			this.setWaterState(false);
			increaseNomadPoint();
			Proto.print("Nomad points increased");
		}
		else{
			this.setWaterState(true);
			Proto.print("Water state set to true");
		}
		Proto.tab--;
	}

	/** */
	public void pickUpPump(Inventory inv) throws UnsupportedOperationException{
		throw new UnsupportedOperationException("Pump cannot be picked up here");
	}

	/** */
	public void direct(NetworkElement in, NetworkElement out) throws UnsupportedOperationException{
		throw new UnsupportedOperationException("Cannot direct here");
	}

	public void breakPipe(){
		Proto.print("pipe.breakPipe");
		Proto.tab++;
		if (this.repairProtectionTimeLeft <= 0)
			this.damaged = true; this.hasWater = false;
		Proto.print("pipe broken");
		Proto.tab--;
	}

	public void repair(){
		Proto.print("pipe.repairPipe");
		Proto.tab++;
		this.damaged = false;
		this.repairProtectionTimeLeft = 5;
		Proto.print("pipe repaired");
		Proto.tab--;
	}

	public void setSticky(){
		Proto.print("pipe.setSticky");
		Proto.tab++;
		if (!slippery){
			sticky = true;
			stickyTimeLeft = 5;
		}
		Proto.print("pipe now sticky");
		Proto.tab--;
	}

	public void setSlippery(){
		Proto.print("pipe.setSlippery");
		Proto.tab++;
		if (!sticky){
			slippery = true;
			slipperyTimeLeft = 5;
		}
		Proto.print("pipe_now_slippery");
		Proto.tab--;
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
	public void connectPipe(NetworkElement ne) throws UnsupportedOperationException{
		throw new UnsupportedOperationException("Cannot connect a Pipe with another Pipe");
	}

	/** */
	public void disconnectPipe(NetworkElement ne) throws UnsupportedOperationException{
		throw new UnsupportedOperationException("Cannot disconnect a Pipe from itself");
	}
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
