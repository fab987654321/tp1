import static java.lang.System.exit;

public class Main {

    public static void main(String[] args) {

        String filename = "formulas/formule-2-sat.txt";
        if (0 < args.length) {
            filename = args[0];
        }

        Parser parser = new Parser();
        Graph<String> graph = parser.parse(filename);
        System.out.println(graph);

        Kosaraju k = new Kosaraju(graph);
        int[] composantes = k.sccs();
/*
        if (TwoSat.checkConsistency(composantes)) {
            System.out.println("Formula " + filename + ": satisfiable");
            exit(0);
        } else {
            System.out.println("Formula " + filename + ": unsatisfiable");
            exit(-1);
        }
  */
        System.out.println("Fin");
        exit(0);

    }

}
