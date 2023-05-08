//
//
//  @ Project : ProjlabSkeleton
//  @ File Name : Nomad.java
//  @ Date : 16/04/2023
//
//


/** */
public class Nomad extends Player {
	/** */
	public void makePipeSlippery(Pipe pipe){
		if (!pipe.isSticky()){
			pipe.setSlippery(true);
		}
	}
}
