//
//
//  @ Project : ProjlabSkeleton
//  @ File Name : NetworkMap.java
//  @ Date : 16/04/2023
//
//
import java.util.ArrayList;

/**
 * This class is responsible for the map
 */
public class NetworkMap {
	/**
	 * Stores the last ID (biggest) of a NetworkElement
	 */
	private static int currentID = 0;
	/**
	 * Stores all the NetworkElements in a list
	 */
	private static ArrayList<NetworkElement> elements = new ArrayList<NetworkElement>();
	/**
	 * Stores all the sources in a list
	 */
	private ArrayList<Source> sources = new ArrayList<Source>();

	/**
	 * Stores all the cisterns in a list
	 */
	private ArrayList<Cistern> cisterns = new ArrayList<Cistern>();
	
	//TODO init the whole game
	/**
	 * This method builds a base map and creates the connections between NetworkElements
	 */
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

		//Setting a default way for the water to flow through with in and outputs
		setInAndOutput(elements.get(0), elements.get(6));
		setInAndOutput(elements.get(6), elements.get(20));
		setInAndOutput(elements.get(20), elements.get(7));
		setInAndOutput(elements.get(7), elements.get(23));
		setInAndOutput(elements.get(23), elements.get(8));
		setInAndOutput(elements.get(8), elements.get(3));

		setInAndOutput(elements.get(1), elements.get(12));
		setInAndOutput(elements.get(12), elements.get(21));
		setInAndOutput(elements.get(21), elements.get(13));
		setInAndOutput(elements.get(13), elements.get(24));
		setInAndOutput(elements.get(24), elements.get(14));
		setInAndOutput(elements.get(14), elements.get(4));

		setInAndOutput(elements.get(2), elements.get(17));
		setInAndOutput(elements.get(17), elements.get(22));
		setInAndOutput(elements.get(22), elements.get(18));
		setInAndOutput(elements.get(18), elements.get(25));
		setInAndOutput(elements.get(25), elements.get(19));
		setInAndOutput(elements.get(19), elements.get(5));
	}

	/**
	 * This method gets the list of sources
	 * @return list of sources
	 */
	public ArrayList<Source> getSources() {
		return sources;
	}

	/**
	 * This method gets the list of cisterns
	 * @return list of cisterns
	 */
	public ArrayList<Cistern> getCisterns() {
		return cisterns;
	}

	/**
	 * This method connect two NetworkElements
	 * @param ne1 first NetworkElement, that need to be connected
	 * @param ne2 second NetworkElement, that need to be connected
	 */
	public static void connect(NetworkElement ne1, NetworkElement ne2) {
		ne1.addConnection(ne2);
		ne2.addConnection(ne1);
	}

	/**
	 * This method adds a NetworkElement to the list that contains all the NetworkElements
	 * @param ne NetworkElement, that need to be added
	 */
	public static void add(NetworkElement ne){
		elements.add(ne);
	}

	/**
	 * Connects two NetworkElements. The first one will be the input of the second one, and vice-versa.
	 * @param ne1 The first NetworkElement. It will be the input of the second one (ne2).
	 * @param ne2 The second NetworkElement. It will be the output of the first one (ne1).
	 *  */
	public static void setInAndOutput(NetworkElement ne1, NetworkElement ne2) {
		Proto.print("Setting in and output for " + ne1 + " and " + ne2);
		ne1.setOutput(ne2);
		ne2.setInput(ne1);
	}

	/**
	 * This method disconnects two NetworkElements from each other
	 * @param ne1 first NetworkElement, that need to be disconnected form the second
	 * @param ne2 second NetworkElement, that need to be disconnected from the first
	 */
	public static void disconnect(NetworkElement ne1, NetworkElement ne2) {
		ne1.removeConnection(ne2);
		ne2.removeConnection(ne1);
	}

	/**
	 *
	 * This method is called in every rounds
	 */
	public void tick(){
		//TODO this should rather be done with recursion
		for (NetworkElement ne : elements){
			ne.tick();
		}
	}

	/**
	 * This method gets the points of nomad team
	 * @return points of nomad team
	 */
	public int getNomadPoints() {
		return NetworkElement.getNomadPoints();
	}

	/**
	 * This method gets the points of plumber team
	 * @return pooints of plumber team
	 */
	public int getPlumberPoints() {
		return NetworkElement.getPlumberPoints();
	}

	/**
	 * This method adds a NetworkElement to the list that contains all the NetworkElements
	 * @param ne NetworkElement, that need to be added
	 */
	public void addElement(NetworkElement ne) {
		this.elements.add(ne);
	}

	/**
	 * This method removes a NetworkElement from the list that contains all the NetworkElement
	 * @param ne NetworkElement, that need to be removed
	 */
	public void removeElement(NetworkElement ne) {
		this.elements.remove(ne);
	}

	/**
	 * This method generates an unique ID
	 * @return unique ID
	 */
	public static int generateID() {
		return currentID++;
	}

	/**
	 * This method gets a NetworkElement from the list by its own ID
	 * @param id ID of a NetworkElement, that we need
	 * @return NetworkElement, that we needed
	 */
	public NetworkElement getElementByID(int id) {
		for (NetworkElement ne : elements) {
			if (ne.getID() == id) {
				return ne;
			}
		}
		return null;
	}

	/**
	 * This method returns the list of all the NetworkElements
	 * @return list of NetworkElements
	 */
	public ArrayList<NetworkElement> getElements(){
		return elements;
	}
}
