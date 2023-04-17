//
//
//  @ Project : ProjlabSkeleton
//  @ File Name : Game.java
//  @ Date : 16/04/2023
//
//

/** */
public class Game implements ITimer{
	/** */
	public void startGame() {
		Skeleton.indentPrint("Game: startGame()");
	}
	
	/** */
	public void endGame() {
		Skeleton.indentPrint("Game: endGame()");
	}

	@Override
	public void tick() {
		Skeleton.indentPrint("Game: tick()");
		Skeleton.INDENT++;
		Skeleton.nomadTeam.updatePoints(Skeleton.nMap.getNomadPoints());
		Skeleton.plumberTeam.updatePoints(Skeleton.nMap.getPlumberPoints());
	}
}
