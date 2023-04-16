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
		Skeleton.elementHashMap.put("pump", new Pump());
		Skeleton.elementHashMap.put("pipe1", new Pipe());
		Skeleton.elementHashMap.put("pipe2", new Pipe());
		Skeleton.elementHashMap.put("pipe3", new Pipe());
		Skeleton.elementHashMap.put("cistern", new Cistern());
		Skeleton.elementHashMap.put("source", new Source());
	}
	
	/** */
	public void connect(NetworkElement ne1, NetworkElement ne2) {
	}
}
