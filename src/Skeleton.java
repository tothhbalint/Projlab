import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Skeleton {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Choose a situation to simulate:");

            System.out.println("1. Create Team" +
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
                case "1":
                    System.out.println("Create Team");
                    break;
                case "2":
                    System.out.println("Move player");
                    break;
                case "3":
                    System.out.println("Direct pump");
                    break;
                case "4":
                    System.out.println("Fix element");
                    break;
                case "5":
                    System.out.println("Pick up pump");
                    break;
                case "6":
                    System.out.println("Place pump");
                    break;
                case "7":
                    System.out.println("Destroy pipe");
                    break;
                case "8":
                    System.out.println("Water flows");
                    break;
                case "9":
                    System.out.println("Pipe spawns");
                    break;
                case "10":
                    System.out.println("Disconnect pipe");
                    break;
                case "11":
                    System.out.println("Connect pipe");
                    break;
                case "12":
                    System.out.println("End of round");
                    break;
                case "13":
                    System.exit(0);
                default:
                    System.out.println("Invalid input");
        }
    }
    }

}