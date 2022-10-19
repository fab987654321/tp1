import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Graph<Label> {

    private class Edge {
        public int source;
        public int destination;
        public Label label;

        public Edge(int from, int to, Label label) {
            this.source = from;
            this.destination = to;
            this.label = label;
        }

        @Override
        public String toString() {
            return Integer.toString(this.source) + " --> " + Integer.toString(this.destination) + ", Etiquette: "
                    + this.label;
        }

        public String toString(int cardi) {
            return Integer.toString((this.source > cardi / 2) ? this.source - cardi : this.source)
                    + " -->>"
                    + Integer.toString((this.destination > cardi / 2) ? this.destination - cardi : this.destination)
                    + ", Etiquette: "
                    + this.label;
        }

        public List<String> toList() {
            List<String> a = new ArrayList<>();

            a.add(0, Integer.toString(this.source));
            a.add(1, Integer.toString(this.destination));
            a.add(2, (this.label).toString());

            return a;
        }
    }

    private int cardinal;
    private ArrayList<LinkedList<Edge>> incidency;

    /**
     * Recupère un arc via son index
     * 
     * @param n index de l'implication 0 --> n
     * @return String[] = {src,dest,etiquette}
     * @throws Exception
     */
    public String[] getIncidency(int n) throws Exception {

        // Si n n'est pas un index d'arc
        if (n < 0 || n >= incidency.size())
            throw new Exception("Hors de l'index");

        // Recup la liste des arc
        List<String> ret = this.getListArc();

        // Si sort de la liste
        if (n >= ret.size())
            throw new Exception("Hors de l'index");

        return ret.get(n).split(" ");
    }

    // Retourne une liste des sommets accessible par le sommet passé en paramètre
    public List<Integer> getAdj(int sommet) {
        List<Integer> listDest = new ArrayList<Integer>();
        List<String> tout = getListArc();

        for (int i = 0; i < tout.size(); i++) {
            String[] ligne = tout.get(i).split(" ");
            if (Integer.parseInt(ligne[0]) == sommet) {
                listDest.add(Integer.parseInt(ligne[1]));
                // System.out.println(">>>"+listDest.toString());
            }
        }
        return listDest;
    }

    /**
     * @return List contennant tous les arcs
     */
    public List<String> getListArc() {
        List<String> ret = new ArrayList<>();
        for (int i = 0; i < this.order(); i++)
            for (Edge e : incidency.get(i))
                if (e.toList().size() != 0)
                    ret.add(e.toList().toString().replace(",", "").replace("[", "").replace("]", ""));
        return ret;
    }

    /**
     * 
     * @param size correspondant au nombre de sommets
     */
    public Graph(int size) {
        cardinal = size;
        incidency = new ArrayList<LinkedList<Edge>>(size + 1);
        for (int i = 0; i < this.order(); i++)
            incidency.add(i, new LinkedList<Edge>());
    }

    public int order() {
        return cardinal;
    }

    public void addArc(int source, int dest, Label label) throws Exception {
        if (Math.max(source, dest) >= this.cardinal) {
            throw new Exception(
                    "Sommets trop gros pour la taille du graphe" + this.cardinal + " : " + source + "--->" + dest);
        }
        incidency.get(source).addLast(new Edge(source, dest, label));
    }

    // Pour gérer les nombres négatifs
    private void addArcControl(int src, int dest, Label label) {
        if (src < 0)
            src = src + this.order() + 1;
        if (dest < 0)
            dest = dest + this.order() + 1;
        try {
            this.addArc(src, dest, label);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Pour générer les implication à partir d'une clause
    public void addClauseArc(int l1, int l2, Label lab) {
        try {
            System.out.println("-------------");
            System.out.println("c:" + l1 + ":" + l2);
            l1--;
            l2--;
            System.out.println("c:" + l1 + ":" + l2);

            this.addArcControl(-l1, l2, lab);
            this.addArcControl(-l2, l1, lab);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public Graph<String> getTranspose() {
        Graph<String> graphTranspose = new Graph<String>(this.order());
        for (int i = 0; i < cardinal; i++)
            for (Edge e : incidency.get(i))
                try {
                    graphTranspose.addArc(e.destination, e.source, e.label.toString());
                } catch (Exception exep) {
                    System.out.println(exep);
                }

        return graphTranspose;
    }

    @Override
    public String toString() {
        String result = new String("");
        result = result.concat("Nombre sommets : " + cardinal + "\n");
        result = result.concat("Sommets : \n");

        for (int i = 0; i < this.order(); i++)
            result = result.concat(((i > this.order() / 2) ? i - this.order() : i) + " ");

        result = result.concat("\nArcs : \n");

        for (int i = 0; i < this.order(); i++)
            for (Edge e : incidency.get(i))
                result = result.concat(e.toString(this.order()) + "\n");

        return result;
    }

}
