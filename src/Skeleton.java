import java.util.HashMap;
import java.util.Scanner;

public class Skeleton {

    HashMap<Integer, Player> players = new HashMap<>();
    HashMap<Integer, Team> teams = new HashMap<>();
    HashMap<Integer, NetworkElement> elements = new HashMap<>();
    HashMap<Integer, Inventory> inventories = new HashMap<>();


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Choose a situation to simulate:");

            System.out.println("0. Init" +
                    "1. Create Team" +
                    "\n2. Move player" +
                    "\n3. Direct pump" +
                    "\n4. Fix element" +
                    "\n5. Pick up pump" +
                    "\n6. Place pump" +
                    "\n7. Destroy pipe" +
                    "\n8. Water flows" +
                    "\n9. Pipe spawns" +
                    "\n10. Disconnect pipe" +
                    "\n11. Connect pipe" +
                    "\n12. End of round" +
                    "\n13. Exit");

            switch (scanner.nextLine()){
                case "0":
                    init();
                    break;
                case "1":
                    createTeam();
                    break;
                case "2":
                    movePlayer();
                    break;
                case "3":
                    directPump();
                    break;
                case "4":
                    fixElement();
                    break;
                case "5":
                    pickUpPump();
                    break;
                case "6":
                    placePump();
                    break;
                case "7":
                    destroyPipe();
                    break;
                case "8":
                    waterFlows();
                    break;
                case "9":
                    pipeSpawns();
                    break;
                case "10":
                    disconnectPipe();
                    break;
                case "11":
                    connectPipe();
                    break;
                case "12":
                    endOfRound();
                    break;
                case "13":
                    System.exit(0);
                default:
                    System.out.println("Invalid input");
        }
    }
    }

    public static void init(){
        //TODO
    }

    public static void createTeam(){
        init();
        //TODO
    }

    public static void movePlayer(){
        //TODO
    }

    public static void directPump(){
        //TODO
    }

    public static void fixElement(){
        //TODO
    }

    public static void pickUpPump(){
        //TODO
    }

    public static void placePump(){
        //TODO
    }

    public static void destroyPipe(){
        //TODO
    }

    public static void waterFlows(){
        //TODO
    }

    public static void pipeSpawns(){
        //TODO
    }

    public static void disconnectPipe(){
        //TODO
    }

    public static void connectPipe(){
        //TODO
    }

    public static void endOfRound() {
        //TODO
    }
}