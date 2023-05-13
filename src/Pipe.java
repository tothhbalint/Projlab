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
		if (isDamaged()){
			increasePlumberPoint();
		}
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
		//TODO

	}
	public void setStickyTimeLeft(int i){
		stickyTimeLeft = i;
	}

	public void addConnection(NetworkElement ne){
		if (this.connections.size() < 2){
			this.connections.add(ne);
		} else {
			System.out.println("Pipe already has 2 connections");
		}
	}
	
	/** TODO */
	public void setInput(NetworkElement ne) {
		this.input = ne;
	}

	/** TODO */
	public NetworkElement getRandomConnection(){ return this; }
	/** TODO  javitasok ahol a sor utan komment van, TOTIKAAAA SEGITTS*/
	public boolean accept(Player p) {
		if (this.isOccupied()){
			return false;
		}else{
			NetworkElement ne = p.getPosition();

			if (this.isConnected(ne)){
				ne.remove(p);
				if (this.slippery){
					if (this.sticky){
						//NOTHING
					}else{
						// TODO if slippery, but not sticky DONE
						int index = rand.nextInt(this.connections.size());
						p.setPosition(this.connections.get(index));
						return true;
					}
				}else{
					if (this.sticky){
						// TODO if not slippery but sticky DONE
						p.setPosition(this);
						this.setOccupied(true);
						return true;
					}else{
						//TODO if not slippery and not sticky DONE
						this.setOccupied(true);
						p.setPosition(this);
						p.setStuck(true);
						int r = rand.nextInt(10); // majd valami szamot megadni maximumnak
						p.setStuckTimeLeft(r);
						this.setStickyTimeLeft(r);
						return true;
					}
				}
			}
			return false;
		}
	}

	public void remove(Player p) {
		this.setOccupied(false);
	}

	/** TODO */
	public void recieveWater(NetworkElement ne) {
	}

	/** */
	public void pickUpPump(Inventory inv) {

	}

	/** */
	public void direct(NetworkElement in, NetworkElement out) {

	}

	public void breakPipe(){
		if (this.repairProtectionTimeLeft <= 0)
			this.damaged = true;
	}

	public void repairPipe(){
		this.damaged = false;
		this.repairProtectionTimeLeft = 5;
	}

	public void setSticky(boolean b){
		if (!slippery){
			sticky = b;
			stickyTimeLeft = 5;
		}
	}

	public void setSlippery(boolean b){
		if (!sticky){
			slippery = b;
			slipperyTimeLeft = 5;
		}
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
	public void connectPipe(NetworkElement ne) {
		return;
	}

	/** */
	public void disconnectPipe(NetworkElement ne) {
		return;
	}
}
