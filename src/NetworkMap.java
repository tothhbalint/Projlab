//
//
//  @ Project : ProjlabSkeleton
//  @ File Name : NetworkMap.java
//  @ Date : 16/04/2023
//
//

/** */
public class NetworkMap {
	/** */
	public void build() {
		Pump pump = new Pump();
		Pipe pipe = new Pipe();
		Cistern cistern = new Cistern();
		Source source = new Source();

		Skeleton.elementHashMap.put("pump", pump);
		Skeleton.elementHashMap.put("pipe", pipe);
		Skeleton.elementHashMap.put("cistern", cistern);
		Skeleton.elementHashMap.put("source", source);
	}
	
	/** */
	public void connect(NetworkElement ne1, NetworkElement ne2) {
	}
}
