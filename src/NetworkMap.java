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
		Skeleton.indentPrint("NetworkMap: build()");
		Skeleton.INDENT++;
		Skeleton.elementHashMap.put("pump", new Pump());
		Skeleton.elementHashMap.put("pipe1", new Pipe());
		Skeleton.elementHashMap.put("pipe2", new Pipe());
		Skeleton.elementHashMap.put("pipe3", new Pipe());
		Skeleton.elementHashMap.put("cistern", new Cistern());
		Skeleton.elementHashMap.put("source", new Source());

		Skeleton.elementHashMap.get("pump").direct(Skeleton.elementHashMap.get("pipe1"), Skeleton.elementHashMap.get("pipe2"));

		Skeleton.elementHashMap.get("pipe1").setInput(Skeleton.elementHashMap.get("source"));
		Skeleton.elementHashMap.get("pipe1").setOutput(Skeleton.elementHashMap.get("pump"));

		Skeleton.elementHashMap.get("pipe2").setInput(Skeleton.elementHashMap.get("pump"));
		Skeleton.elementHashMap.get("pipe2").setOutput(Skeleton.elementHashMap.get("cistern"));

		Skeleton.indentPrint("NetworkMap built");
		Skeleton.INDENT--;
	}
	
	/** */
	public void connect(NetworkElement ne1, NetworkElement ne2) {
	}
}
