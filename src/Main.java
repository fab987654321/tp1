
import static java.lang.System.exit;

public class Main {

    public static void main(String[] args) {

        // String filename = "formulas/testSet0/formula2.txt";
        // String filename = "formulas/testSet1/formula4.txt";
        String filename = "formulas/formule-2-sat.txt";
        if (0 < args.length) {
            filename = args[0];
        }

        Parser parser = new Parser();
        Graph<String> graph = parser.parse(filename);
        System.out.println(graph);

        if (false)
            for (int i = 0; i < graph.getListArc().size(); i++) {
                int s = i;
                graph.getAdj(s).forEach(t -> System.out
                        .println(UtilStat.convertSommet(graph.order(), s) + " -> "
                                + UtilStat.convertSommet(graph.order(), t)));

            }

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
