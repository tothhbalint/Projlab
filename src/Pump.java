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

	/** */
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
}
