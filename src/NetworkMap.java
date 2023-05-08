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

	}
	
	/** */
	public void connect(NetworkElement ne1, NetworkElement ne2) {
		ne1.addConnection(ne2);
		ne2.addConnection(ne1);
	}

	public void tick(){
		//TODO this should rather be done with recursion
		for (NetworkElement ne : elements){
			ne.tick();
		}
	}
}
