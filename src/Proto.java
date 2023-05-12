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
                }else if (arg.equals("-t") || arg.equals("--test")) {
                    test = true;
                }
            }
        }
        if(test) verbose = false;
    }

    //kossz a segitsegert :D -Bence
        //copilot irta xd
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

    public static void main(String[] args) {
        processInput(args);
    }


}
