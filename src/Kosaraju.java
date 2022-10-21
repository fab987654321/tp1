import java.lang.reflect.Array;
import java.util.*;

public class Kosaraju {

    private Graph<String> leGraph;

    public Kosaraju(Graph<String> graph) {
        this.leGraph = graph;
    }

    /**
     * @return
     * @throws Exception
     * @throws NumberFormatException
     */

    public int[] sccs() throws NumberFormatException, Exception {

        List<String> CFC = new ArrayList<>();
        Set<Integer> visite = new HashSet<>();
        // Pile
        Stack<Integer> sortie = new Stack<>();
        Stack<Integer> chemin = new Stack<>();

        // Variable tampon
        List<Integer> tmpAdj = null;
        int intIsVisite = -1;
        int tmpSommetActuel = -1;

        // Pars d'un sommet src (ligne 0)
        chemin.add(Integer.parseInt(leGraph.getIncidency(0)[0]));

        boolean COMENTAIREP1 = false;
        // Attention, il se stop lors du break dans le catch (NoSuchElementException e)
        while (true)
            try {
                if (COMENTAIREP1)
                    System.out.println("888888888888888");

                // Sommet actuel
                tmpSommetActuel = chemin.lastElement();
                // List adj du sommet
                tmpAdj = leGraph.getAdj(tmpSommetActuel);
                // Ajoute aux visité
                visite.add(chemin.lastElement());

                if (COMENTAIREP1)
                    System.out.println("Actuel => " + tmpSommetActuel + ";" + tmpAdj + ";" + visite.toString());
                intIsVisite = -1;

                // Si au moins 1 adjacent
                if (tmpAdj.size() > 0)
                    for (int each : tmpAdj)
                        // Si au moins 1 adjacent non visité
                        if (!visite.contains(each)) {
                            // System.out.println(tmpSommetActuel + " visite " + each);
                            intIsVisite = each;
                            // Quite le for (normalement)
                            break;
                        }
                if (COMENTAIREP1)
                    System.out.println("Chemin: " + chemin.toString());
                // Si Sommet accéssible
                if (intIsVisite != -1) {
                    // Ajoute l'élément à la pile visité et au chemin
                    chemin.add(intIsVisite);
                    visite.add(intIsVisite);
                }
                // Cas où aucun adj n'est dispo
                else {
                    if (COMENTAIREP1)
                        System.out.print("Dépilage " + chemin.toString() + " -> ");
                    // Dépile le chemin et empile dans sortie
                    sortie.add(chemin.pop());
                    if (COMENTAIREP1)
                        System.out.println(chemin.toString());
                    if (COMENTAIREP1)
                        System.out.println("Sortie: " + sortie.toString());
                }

            }
            // Si chemin vide
            catch (NoSuchElementException e) {
                boolean isExiste = false;
                // Récupérer la liste des sommets et voir si ils existent tous dans les visité
                int sommets = 0;
                // Si un sommet non parcouru alors recup sa valeur dans sommets
                for (int i : leGraph.getSommets()) {
                    if (!visite.contains(i)) {
                        isExiste = true;
                        sommets = i;
                        break;
                    }
                }

                // Si existe on le met dans le chemin
                if (isExiste)
                    chemin.add(sommets);

                // Si plus de sommets accéssible
                else
                    break;// Pour sortir du while

            }

        // Affiche la pile de sortie
        System.out.print("Pile de sortie ");
        for (int i = 0; i < sortie.toArray().length; i++)
            System.out.print(
                    UtilStat.convertSommet(leGraph.order(), Integer.parseInt(sortie.toArray()[i].toString())) + " ");

        System.out.println();
        // Transposée
        Graph<String> trGraph = leGraph.getTranspose();

        visite.clear();

        for (int elem : sortie) {
            // Ne peut pas passer par des éléments déjà ajouté à une bouvcle fortement
            // connexe
            System.out.println("");
            System.out.println("***************");
            chemin.clear();
            chemin.add(elem);
            while (true)
                try {
                    if (false)
                        System.out.println("++++++++++++++++");

                    tmpSommetActuel = chemin.lastElement();
                    tmpAdj = trGraph.getAdj(tmpSommetActuel);

                    if (false)
                        System.out.println("\n" + UtilStat.convertSommet(trGraph.order(), elem) + ";"
                                + UtilStat.convertSommet(trGraph.order(), tmpSommetActuel) + ":"
                                + UtilStat.convertListSommet(trGraph.order(), tmpAdj) + ":"
                                + UtilStat.convertTableSommet(trGraph.order(), visite.toArray())
                                + CFC);
                    System.out.println(
                            "Sortie:" + UtilStat.convertTableSommet(trGraph.order(), sortie.toArray()) + "\n visite" +
                                    UtilStat.convertTableSommet(trGraph.order(), visite.toArray()));

                    if (sortie.size() == visite.size()) {
                        System.out.println("Tout à été parcouru");
                        break;
                    }

                    visite.add(chemin.get(0));
                    intIsVisite = -1;
                    System.out.println(chemin);
                    // Si au moins 1 adjacent
                    if (tmpAdj.size() > 0) {
                        for (int each : tmpAdj)
                            // Si au moins 1 adjacent non visité
                            if (!visite.contains(each)) {
                                System.out.println("0");
                                intIsVisite = each;
                                break;
                            }
                        System.out.println("1: ?visite" + intIsVisite);
                    }

                    // Si Sommet accéssible
                    if (intIsVisite != -1) {
                        System.out.println("\n" +
                                "Empile" + UtilStat.convertSommet(trGraph.order(), chemin.lastElement()) +
                                ",-->"
                                + UtilStat.convertSommet(trGraph.order(), intIsVisite));
                        chemin.add(intIsVisite);
                        visite.add(intIsVisite);
                        System.out.println("2");
                    } else {// Si sommet de départ accessible
                        // Si actuel égale source

                        System.out.println("3");
                        if (tmpSommetActuel == elem) {
                            System.out.println("3.1");
                            CFC.add((UtilStat.convertTableSommet(trGraph.order(), chemin.toArray())).toString());
                        }
                        // Si Source accessible même si déjà visité
                        if ((tmpAdj.contains(elem))) {
                            System.out.println("3.2");
                            CFC.add((UtilStat.convertTableSommet(trGraph.order(), chemin.toArray())).toString());
                            chemin.pop();
                        } else {
                            System.out.println("3.3");
                            System.out.print("\n" +
                                    "Dépile: " + UtilStat.convertSommet(trGraph.order(), chemin.lastElement()) +
                                    "-->");
                            chemin.pop();
                            System.out.println(" " + UtilStat.convertSommet(trGraph.order(),
                                    chemin.lastElement()));
                        }
                    }

                }
                // Si chemin vide
                catch (NoSuchElementException e) {
                    System.out.println("4");
                    boolean isExiste = false;
                    // Récupérer la liste des sommets et voir si ils existent tous dans les visité
                    int sommets = 0;
                    // Si un sommet non parcouru alors recup sa valeur dans sommets
                    for (int i : leGraph.getSommets()) {
                        if (!visite.contains(i)) {
                            isExiste = true;
                            sommets = i;
                            break;
                        }
                    }
                    // Si existe on le met dans le chemin
                    if (isExiste)
                        chemin.add(sommets);
                    // Si plus de sommets accéssible
                    else
                        break;// Pour sortir du while

                }
        }
        System.out.println("*****************");
        System.out.println("*****************");

        System.out.println(CFC);

        if (CFC.size() != 0) {
            System.out.println("Satisfesable");
        } else {
            System.out.println("Insatisfesable");
        }

        return new int[10];
    }

}// Class
