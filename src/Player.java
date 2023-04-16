//
//
//  @ Project : ProjlabSkeleton
//  @ File Name : Player.java
//  @ Date : 16/04/2023
//
//

/**
 *
 */
public abstract class Player {

    /**
     *
     */
    public abstract void takePump(Inventory inv);

    /**
     *
     */
    public abstract void placePump();

    /**
     *
     */
    public abstract void connectPipe();

    /**
     *
     */
    public abstract void disconnectPipe(NetworkElement ne);

    /**
     *
     */
    public void move(NetworkElement ne) {
        Skeleton.indentPrint("Player: move()");
    }

    /**
     *
     */
    public void directPump() {
        Skeleton.indentPrint("Player: directPump()");
        Skeleton.INDENT++;


        Skeleton.indentPrint("Where is the player standing?");
        Skeleton.indentPrint("0. Cistern");
        Skeleton.indentPrint("1. Pipe");
        Skeleton.indentPrint("2. Pump");
        Skeleton.indentPrint("3.Source");
        String playerPosition = Skeleton.scanner.nextLine();

        switch (playerPosition) {
            case "0":
                Skeleton.elementHashMap.get("cistern").direct(Skeleton.elementHashMap.get("pipe1"), Skeleton.elementHashMap.get("pipe2"));
                break;
            case "1":
                Skeleton.elementHashMap.get("pipe1").direct(Skeleton.elementHashMap.get("pipe1"), Skeleton.elementHashMap.get("pipe2"));
                break;
            case "2":
                Skeleton.elementHashMap.get("pump").direct(Skeleton.elementHashMap.get("pipe1"), Skeleton.elementHashMap.get("pipe2"));
                break;
        }

        Skeleton.INDENT--;
    }

    /**
     *
     */
    public void setPosition(NetworkElement ne) {
        Skeleton.indentPrint("Player: setPosition()");
    }
}
