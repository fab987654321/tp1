
import static java.lang.System.exit;

public class Main {

    public static void main(String[] args) {
        // TODO mettre ton nom de famille
        System.out.println("Code réalisé par Fabien AUTARD et Bryce");

        String filename = "formulas/testSet0/formula0.txt"; // non ok S
        // String filename = "formulas/testSet0/formula1.txt";// non ok S
        // String filename = "formulas/testSet0/formula2.txt";//OK I
        // String filename = "formulas/testSet0/formula3.txt";//non OK S
        // String filename = "formulas/testSet0/formula4.txt";//ok S
        // String filename = "formulas/testSet0/formula5.txt";//ok S
        // String filename = "formulas/testSet0/formula6.txt";//ok S
        // String filename = "formulas/testSet0/formula7.txt";//ok S
        // String filename = "formulas/testSet0/formula8.txt";//non ok I
        // String filename = "formulas/testSet0/formula9.txt";//ok S

        // String filename = "formulas/testSet1/formula8.txt";
        // String filename = "formulas/formule-2-sat.txt";
        if (0 < args.length) {
            filename = args[0];
        }

        Parser parser = new Parser();
        Graph<String> graph = parser.parse(filename);
        System.out.println(graph);

        Kosaraju k = new Kosaraju(graph);
        try {
            int[] composantes = k.sccs();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // System.out.println((graph.getTranspose()).toString());
        /*
         * if (TwoSat.checkConsistency(composantes)) {
         * System.out.println("Formula " + filename + ": satisfiable");
         * exit(0);
         * } else {
         * System.out.println("Formula " + filename + ": unsatisfiable");
         * exit(-1);
         * }
         */
        System.out.println("Fin");
        exit(0);

    }

}
