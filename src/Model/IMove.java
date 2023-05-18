//
//
//  @ Project : ProjlabSkeleton
//  @ File Name : IMove.java
//  @ Date : 16/04/2023
//
//




/**
 * Interface that is responsible the player movements
 */
public interface IMove {
	/**
	 * This method checks if the player can move to the selected NetworkElement
	 * @param p Player, which wants to move
	 * @return true, if the player can step, false if it cannot
	 */
	boolean accept(Player p);

	/**
	 * This method removes a player from the right NetworkElement
	 * @param p The player, which needs to be removed
	 */
	void remove(Player p);
}
