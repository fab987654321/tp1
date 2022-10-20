import java.util.ArrayList;
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
        visite.add(chemin.get(0));

        boolean COMENTAIREP1 = false;
        // Attention, il se stop lors du break dans le catch (NoSuchElementException e)
        while (true)
            try {
                if (COMENTAIREP1)
                    System.out.println("---------------");
                // Sommet actuel
                tmpSommetActuel = chemin.lastElement();
                // List adj du sommet
                tmpAdj = leGraph.getAdj(tmpSommetActuel);

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

            } catch (NoSuchElementException e) {
                boolean isexiste = false;
                // Récupérer la liste des sommets et voir si ils existent tous dans les visité
                // SI chemin vide alors vérifier que tt les sommets soient parcourus
                System.out.println(leGraph.getSommets().toString());
                // Si plus de sommets accéssible
                break;// Pour sortir du while
            }

        // Affiche la pile de sortie
        System.out.print("Pile de sortie ");
        for (int i = 0; i < sortie.toArray().length; i++)
            System.out.print(
                    UtilStat.convertSommet(leGraph.order(), Integer.parseInt(sortie.toArray()[i].toString())) + " ");

        System.out.println();

        // Transposée
        Graph trGraph = leGraph.getTranspose();
        // Faire un algo pour gérer le dfs dans la transposé avec la pile de sortie
        // Utiliser la pile de sortie pour naviguer dans le graph

        // Pour vider les visites
        visite.clear();

        for (int elem : sortie) {
            chemin.clear();
            chemin.add(elem);
            while (true) {
                try {
                    tmpSommetActuel = chemin.lastElement();
                    tmpAdj = trGraph.getAdj(tmpSommetActuel);
                    intIsVisite = -1;

                    // Si au moins 1 adjacent
                    if (tmpAdj.size() > 0)
                        for (int each : tmpAdj)
                            // Si au moins 1 adjacent non visité
                            if (!visite.contains(each)) {
                                intIsVisite = each;
                                break;
                            }

                    // Si Sommet accéssible
                    if (intIsVisite != -1) {
                        // Ajoute l'élément à la pile visité et au chemin
                        chemin.add(intIsVisite);
                        visite.add(intIsVisite);
                    }
                    // Cas où aucun sommet adj n'est dispo
                    else {
                        // System.out.println("Aucun adj dispo " + elem + ":" + tmpSommetActuel + ":" +
                        // tmpAdj.toString());

                        if (tmpAdj.contains(elem)) {
                            // System.out.println("Eléments formtement conexe:" + chemin.toString());
                            CFC.add(chemin.toString());
                            chemin.clear();
                        } else {
                            // System.out.println("Depile ");
                            chemin.pop();

                        }
                    }

                } catch (Exception e) {
                    // System.out.println("Quite le while");
                    break;// Pour sortir du while
                }
            }

        }
        if (CFC.size() != 0) {
            System.out.println("Satisfesable:\n" + CFC.toString());
        } else {
            System.out.println("Insatisfesable: " + CFC.toString());
        }

        return new int[10];
    }

    // TODO
    // "Parcour en profondeur"
    String[] DFS(Graph unGraph) {

        // Retourne un tableau contennant les composantes fortement connexe
        return new String[2];
    }

}// Class
