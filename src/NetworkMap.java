//
//
//  @ Project : ProjlabSkeleton
//  @ File Name : NetworkMap.java
//  @ Date : 16/04/2023
//
//

/**
 * The network map class, which is responsible for creating the connections between the elements.
 * @author Buzas
 * */
public class NetworkMap {
	/**
	 * Builds the network map, thus creating the connections between the elements.
	 * @return void
	 * @author Buzas
	 * */
	public void build() {
		Skeleton.indentPrint("NetworkMap: build()");
		Skeleton.INDENT++;
		Skeleton.elementHashMap.put("pump", new Pump());
		Skeleton.elementHashMap.put("pipe1", new Pipe());
		Skeleton.elementHashMap.put("pipe2", new Pipe());
		Skeleton.elementHashMap.put("pipe3", new Pipe());
		Skeleton.elementHashMap.put("cistern", new Cistern());
		Skeleton.elementHashMap.put("source", new Source());


		Skeleton.nMap.connect(Skeleton.elementHashMap.get("source"), Skeleton.elementHashMap.get("pipe1"));
		Skeleton.nMap.connect(Skeleton.elementHashMap.get("pipe1"), Skeleton.elementHashMap.get("pump"));
		Skeleton.nMap.connect(Skeleton.elementHashMap.get("pump"), Skeleton.elementHashMap.get("pipe2"));
		Skeleton.nMap.connect(Skeleton.elementHashMap.get("pipe2"), Skeleton.elementHashMap.get("cistern"));

		Skeleton.indentPrint("NetworkMap built");
		Skeleton.INDENT--;
	}

	/**
	 * Returns nomad points
	 * @return int - nomad points
	 * @author Buzas
	 */
	public int getNomadPoints() {
		Skeleton.INDENT++;
		Skeleton.indentPrint("NetworkMap : getNomadPoints()");
		return NetworkElement.getNomadPoints();
	}

	/**
	 * Returns plumber points
	 * @return int - plumber points
	 * @author Buzas
	 */
	public int getPlumberPoints() {
		Skeleton.INDENT++;
		Skeleton.indentPrint("NetworkMap : getPlumberPoints()");
		return NetworkElement.getPlumberPoints();
	}
	
	/**
	 * Connects two network elements
	 * @param ne1 - the input element
	 * @param ne2 - the output element
	 * @return void
	 * @author Buzas
	 * */
	public void connect(NetworkElement ne1, NetworkElement ne2) {
		Skeleton.indentPrint("NetworkMap: connect()");
		Skeleton.INDENT++;
		ne1.setOutput(ne2);
		ne2.setInput(ne1);
		Skeleton.indentPrint("NetworkElements connected");
		Skeleton.INDENT--;
	}
	public void tick(){
		Skeleton.indentPrint("NetworkMap : tick()");
		Skeleton.INDENT++;
		Cistern cistern = (Cistern) Skeleton.elementHashMap.get("cistern");
		cistern.spawnPipe();
		Skeleton.nMap.connect(Skeleton.elementHashMap.get("cistern"),Skeleton.elementHashMap.get("newElement"));
		cistern.addConnection(Skeleton.elementHashMap.get("newElement"));
		Skeleton.INDENT--;
	}

	public void addElement(NetworkElement ne){
		Skeleton.indentPrint("NetworkMap : addElement()");
		Skeleton.elementHashMap.put("newElement",ne);

	}
}
