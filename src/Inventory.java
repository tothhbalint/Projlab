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

	/** */
	public boolean isFull() {
		Skeleton.indentPrint("Inventory: isFull()");
		Skeleton.INDENT++;
		Skeleton.indentPrint("Should the inventory be full?");
		Skeleton.indentPrint("0 - No");
		Skeleton.indentPrint("1 - Yes");
		String choice = Skeleton.scanner.nextLine();

		switch (choice) {
			case "0" -> {
				Skeleton.indentPrint("Inventory is not full");
				Skeleton.INDENT--;
				return false;
			}
			case "1" -> {
				Skeleton.indentPrint("Inventory is full");
				Skeleton.INDENT--;
				return true;
			}
			default -> {
				Skeleton.indentPrint("Invalid input");
				Skeleton.INDENT--;
				return isFull();
			}
		}
	}


}
