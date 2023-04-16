//
//
//  @ Project : ProjlabSkeleton
//  @ File Name : Inventory.java
//  @ Date : 16/04/2023
//
//

/** */
public class Inventory {
	Inventory() {
		Skeleton.indentPrint("Inventory : Inventory()");
		Skeleton.INDENT++;
		Skeleton.indentPrint("Inventory created.");
		Skeleton.INDENT--;
	}
	/** */
	public void addItem(NetworkElement ne) {
		Skeleton.indentPrint("Inventory: addItem()");
		Skeleton.INDENT++;
		Skeleton.indentPrint("Item added to inventory");
		Skeleton.INDENT--;
	}
	
	/** */
	public void removeItem(NetworkElement ne) {
		Skeleton.indentPrint("Inventory: removeItem()");
		Skeleton.INDENT++;
		Skeleton.indentPrint("Item removed from inventory");
		Skeleton.INDENT--;
	}
}
