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
	private static int currentID = 0;
	private ArrayList<NetworkElement> elements = new ArrayList<NetworkElement>();

	private ArrayList<Source> sources = new ArrayList<Source>();

	private ArrayList<Cistern> cisterns = new ArrayList<Cistern>();
	
	//TODO init the whole game
	public void build() {
		for (int i = 0; i < 3; i++){
			Source s = new Source();
			addElement(s);
			sources.add(s);
		}

		for (int i = 0; i < 3; i++){
			Cistern s = new Cistern();
			addElement(s);
			cisterns.add(s);
		}

		for (int i = 0; i < 14; i++){
			Pipe s = new Pipe();
			addElement(s);
		}

		for (int i = 0; i < 6; i++){
			Pump s = new Pump();
			addElement(s);
		}

		//TODO REFACTOR THIS PoS WITH some kind of Markup-language
		//Connecting sources with pipes
		connect(elements.get(0), elements.get(6));
		connect(elements.get(1), elements.get(12));
		connect(elements.get(2), elements.get(17));

		//Connecting pumps with pipes
		connect(elements.get(20), elements.get(6));
		connect(elements.get(20), elements.get(7));
		connect(elements.get(20), elements.get(9));

		connect(elements.get(21), elements.get(12));
		connect(elements.get(21), elements.get(9));
		connect(elements.get(21), elements.get(10));
		connect(elements.get(21), elements.get(13));

		connect(elements.get(22), elements.get(17));
		connect(elements.get(22), elements.get(15));
		connect(elements.get(22), elements.get(18));

		connect(elements.get(23), elements.get(7));
		connect(elements.get(23), elements.get(8));
		connect(elements.get(23), elements.get(11));
		connect(elements.get(23), elements.get(10));

		connect(elements.get(24), elements.get(13));
		connect(elements.get(24), elements.get(14));
		connect(elements.get(24), elements.get(16));

		connect(elements.get(25), elements.get(18));
		connect(elements.get(25), elements.get(19));
		connect(elements.get(25), elements.get(16));

		//Connecting cistern with pipes
		connect(elements.get(3), elements.get(8));
		connect(elements.get(4), elements.get(11));
		connect(elements.get(4), elements.get(14));
		connect(elements.get(5), elements.get(19));
	}

	public ArrayList<Source> getSources() {
		return sources;
	}

	public ArrayList<Cistern> getCisterns() {
		return cisterns;
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

	/** */
	public void addElement(NetworkElement ne) {
		this.elements.add(ne);
	}

	/** */
	public void removeElement(NetworkElement ne) {
		this.elements.remove(ne);
	}

	/** */
	public static int generateID() {
		return currentID++;
	}

	/** */
	public NetworkElement getElementByID(int id) {
		for (NetworkElement ne : elements) {
			if (ne.getID() == id) {
				return ne;
			}
		}
		return null;
	}

	public ArrayList<NetworkElement> getElements(){
		return elements;
	}
}
