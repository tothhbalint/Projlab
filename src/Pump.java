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
	
	/** TODO */
	public boolean accept(Player p) {
		return false;
	}
	
	/** */
	public void remove(Player p) {
	}
	
	/** */
	public void direct(NetworkElement n) {
		this.output = n;
	}
	
	/** */
	public void recieveWater(NetworkElement ne) {

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
}
