//
//
//  @ Project : ProjlabSkeleton
//  @ File Name : NetworkMap.java
//  @ Date : 16/04/2023
//
//
import java.util.ArrayList;

/** */
public class NetworkMap {
	/** */
	private ArrayList<NetworkElement> elements;
	
	//TODO init the whole game
	public void build() {
		Source source1 = new Source();
		Source source2 = new Source();
		Source source3 = new Source();
		Pump pump1 = new Pump();
		Pump pump2 = new Pump();
		Pump pump3 = new Pump();
		Pump pump4 = new Pump();
		Pump pump5 = new Pump();
		Pump pump6 = new Pump();
		Pipe pipe1 = new Pipe();
		Pipe pipe2 = new Pipe();
		Pipe pipe3 = new Pipe();
		Pipe pipe4 = new Pipe();
		Pipe pipe5 = new Pipe();
		Pipe pipe6 = new Pipe();
		Pipe pipe7 = new Pipe();
		Pipe pipe8 = new Pipe();
		Pipe pipe9 = new Pipe();
		Pipe pipe10 = new Pipe();
		Pipe pipe11 = new Pipe();
		Pipe pipe12 = new Pipe();
		Pipe pipe13 = new Pipe();
		Pipe pipe14 = new Pipe();


	}
	
	/** */
	public static void connect(NetworkElement ne1, NetworkElement ne2) {
		ne1.addConnection(ne2);
		ne2.addConnection(ne1);
	}

	/** */
	public static void disconnect(NetworkElement ne1, NetworkElement ne2) {
		ne1.removeConnection(ne2);
		ne2.removeConnection(ne1);
	}

	public void tick(){
		//TODO this should rather be done with recursion
		for (NetworkElement ne : elements){
			ne.tick();
		}
	}

	/** */
	public int getNomadPoints() {
		return NetworkElement.getNomadPoints();
	}

	/** */
	public int getPlumberPoints() {
		return NetworkElement.getPlumberPoints();
	}
}
