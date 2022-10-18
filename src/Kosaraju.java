import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Stack;

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
        visite.add(chemin.get(0));

        // Attention, il se stop lors du break dans le catch (NoSuchElementException e)
        while (true)
            try {
                System.out.println("---------------");
                // Sommet actuel
                tmpSommetActuel = chemin.lastElement();
                // List adj du sommet
                tmpAdj = leGraph.getAdj(tmpSommetActuel);

                System.out.println(tmpSommetActuel + ";" + tmpAdj);
                intIsVisite = -1;

                // Si au moins 1 adjacent
                if (tmpAdj.size() > 0)
                    for (int each : tmpAdj)
                        // Si au moins 1 adjacent non visité
                        if (!visite.contains(each)) {
                            System.out.println(tmpSommetActuel + " visite " + each);
                            intIsVisite = each;
                            // Quite le for (normalement)
                            break;
                        }
                System.out.println("Aller à " + intIsVisite);
                // Si Sommet accéssible
                if (intIsVisite != -1) {
                    // Ajoute l'élément à la pile visité et au chemin
                    chemin.add(intIsVisite);
                    visite.add(intIsVisite);
                }
                // Cas où aucun adj n'est dispo
                else {
                    System.out.println("Dépilage de " + tmpSommetActuel);
                    // Dépile le chemin et empile dans sortie
                    sortie.add(chemin.pop());
                }

            } catch (NoSuchElementException e) {
                break;// Pour sortir du while
            }

        System.out.println("Pile de sortie 1° passe" + sortie.toString());

        // Faire la transposé
        Graph trGraph = leGraph.getTranspose();
        // Faire un algo pour gérer le dfs dans la transposé avec la pile de sortie
        // Utiliser la pile de sortie pour naviguer dans le graph

        return new int[10];
    }

    // TODO
    // "Parcour en profondeur"
    String[] DFS(Graph unGraph) {

        // Retourne un tableau contennant les composantes fortement connexe
        return new String[2];
    }

    Stack SortTime() {

        return new Stack();
    }
    // 1 Sortime
    // 2 Transpose
    // 3 DFS par rapport au temps de sortie (sur la transposé)

    // Avec empilage et génération de "foret d'arborescence"
}// Class
