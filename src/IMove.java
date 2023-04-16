//
//
//  @ Project : ProjlabSkeleton
//  @ File Name : IMove.java
//  @ Date : 16/04/2023
//
//


/** */
public interface IMove {
	/** */
	abstract boolean accept(Player p);

	/** */
	abstract void remove(Player p);
}
