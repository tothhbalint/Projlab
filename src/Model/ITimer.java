//
//
//  @ Project : ProjlabSkeleton
//  @ File Name : ITimer.java
//  @ Date : 16/04/2023
//
//


/**
 * This class is responsible for the Timer, which ticks at the end of a round
 */
public interface ITimer {
	/**
	 * This method is implemented in inherited classes, and called every round
	 */
	public void tick();
}
