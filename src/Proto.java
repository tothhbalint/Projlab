import java.util.ArrayList;
import java.util.LinkedList;

public class Proto {
    static boolean verbose = false;

    static boolean test = false;

    static private class Situation {
        String name;

        String[] options;

        public Situation(String name, String[] options) {
            this.name = name;
            this.options = options;
        }
    }

    static ArrayList<Situation> situations = new ArrayList<Situation>();

    public static void processInput(String[] args) {
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                String arg = args[i];
                if (arg.charAt(0) != '-') {
                    ArrayList<String> options = new ArrayList<>();
                    int x = ++i;
                    while (args[x].charAt(0) == '-') {
                        options.add(args[x].replace("-", ""));
                        x++;
                        if (x == args.length) break;
                    }
                    Situation situation = new Situation(arg, options.toArray(new String[options.size()]));
                    situations.add(situation);
                } else if (arg.equals("-v") || arg.equals("--verbose")) {
                    verbose = true;
                } else if (arg.equals("-h") || arg.equals("--help")) {
                    printHelp();
                } else if (arg.equals("-t") || arg.equals("--test")) {
                    test = true;
                }
            }
        }
        if (test) verbose = false;
    }

    public static void printHelp() {
        System.out.println("Usage: java Proto [options] [situations] [situation options]");
        //TODO add list of situations and options
        //dont make test show up in help
        System.out.println("Options:");
        System.out.println("""
                -v, --verbose: verbose mode
                -h, --help: print this help
                """);
        System.out.println("Situations:");
        System.out.println("""
                step : Move $who to $where
                     -who -where
                pickup : Pick up $what with $who
                     -who -what
                place : Place $what with $who
                     -who -what
                break : Break $what with $who
                     -who -what
                fix : Fix $who-s position
                     -who
                place : Place $what with $who
                     -who -what
                oil : Make a pipe slippery with $who
                     -who
                glue : Make a pipe sticky with $who
                     -who
                flow : Simulate water flow from $from
                     -from
                 """);
    }

    static Game gameHandle = new Game();

    public static void main(String[] args) {
        processInput(args);

        for (Situation situation : situations) {
            processSituation(situation);
        }
    }

    //TODO draw out the map in ascii for navigation
    private static void drawMap() {
        if(!verbose) throw new RuntimeException("Cannot draw map in non-verbose mode");

       NetworkMap networkMap = gameHandle.getMap();

        for (NetworkElement networkElement : networkMap.getElements()) {
            networkElement.printMatrix();
        }
    }


    private static void processSituation(Situation situation) {
        switch (situation.name) {
            case "step":
                step(situation.options);
                break;
            case "pickup":
                pickup(situation.options);
                break;
            case "place":
                place(situation.options);
                break;
            case "break":
                break_(situation.options);
                break;
            case "fix":
                fix(situation.options);
                break;
            case "oil":
                oil(situation.options);
                break;
            case "glue":
                glue(situation.options);
                break;
            case "flow":
                flow(situation.options);
                break;
            default:
                print("Invalid command: " + situation.name);
                break;
        }
    }

    private static void step(String[] options) {
        String who = options[0];
        String where = options[1];
        print("step " + who + " " + where);

        String[] whoSplit = who.split("(?<=\\D)(?=\\d)");
        who = whoSplit[0];
        int whoId = Integer.parseInt(whoSplit[1]);

        String[] whereSplit = where.split("(?<=\\D)(?=\\d)");
        where = whereSplit[0];
        int whereId = Integer.parseInt(whereSplit[1]);

        if (who.equals("plumber")) {
            gameHandle.getPlumberTeam().getPlayer(whoId).getPosition();
        } else if (who.equals("nomad")) {
            gameHandle.getNomadTeam().getPlayer(whoId).getPosition();
        } else {
            print("Invalid team: " + who);
        }

    }

    private static void pickup(String[] options) {
        String who = options[0];
        String what = options[1];
        print("pickup " + who + " " + what);
    }

    private static void place(String[] options) {
        String who = options[0];
        String what = options[1];
        print("place " + who + " " + what);
    }

    private static void break_(String[] options) {
        String who = options[0];
        String what = options[1];
        print("break " + who + " " + what);
    }

    private static void fix(String[] options) {
        String who = options[0];
        print("fix " + who);
    }

    private static void oil(String[] options) {
        String who = options[0];
        print("oil " + who);
    }

    private static void glue(String[] options) {
        String who = options[0];
        print("glue " + who);
    }

    private static void flow(String[] options) {
        String from = options[0];
        print("flow " + from);
    }

    public static void print(String arg) {
        if (verbose) System.out.println(arg);
    }


}
