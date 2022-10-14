import java.util.ArrayList;
import java.util.LinkedList;

public class Graph<Label>  {

    private class Edge {
        public int source;
        public int destination;
        public Label label;

        public Edge(int from, int to, Label label) {
            this.source = from;
            this.destination = to;
            this.label = label;
        }
    }

    private int cardinal;
    private ArrayList<LinkedList<Edge>> incidency;


    public Graph(int size) {
        cardinal = size;
        incidency = new ArrayList<LinkedList<Edge>>(size+1);
        for (int i = 0;i<cardinal;i++) {
            incidency.add(i, new LinkedList<Edge>());
        }
    }

    public int order() {
        return cardinal;
    }

    public void addArc(int source, int dest, Label label) throws Exception {
	if (Math.max(source,dest) >= this.cardinal){
	    throw new Exception("Sommets trop gros pour la taille du graphe");
	}
        incidency.get(source).addLast(new Edge(source,dest,label));
    }
//Pour gérer les nombres négatifs
    private void addArcControl(int src,int dest,Label label){
        if (src < 0 ) src = src * -1 + cardinal/2;
        if (dest < 0) dest = dest * -1 + cardinal/2;
        try {
            this.addArc(src,dest, label);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //Pour générer les implication à partir d'une clause
    public void addClauseArc(int l1, int l2,Label lab) {
        try {
            // P V Q : (nP => Q) et (nQ => P)
            if(l1 > 0 && l2 > 0){
                this.addArcControl(-l1,l2,lab);
                this.addArcControl(-l2,l1,lab);
            }
            // nP V nQ : (P => nQ) et (Q => nP)
            else if(l1 < 0 && l2 < 0){
                this.addArcControl(-l1,l2,lab);
                this.addArcControl(-l2,l1,lab);
            }
            // P V nQ : Q => P
            else if(l1 > 0 && l2 < 0){
                this.addArcControl(-l2,l1,lab);
            }
            // nP V Q : P => Q
            else if(l1 < 0 && l2 > 0){
                this.addArcControl(-l1,l2,lab);
            }
        }catch (Exception e){
            System.out.println(e);
        }

    }

    public Graph<String> getTranspose(){
        Graph<String> graphTranspose = new Graph<String>(cardinal);

        for (int i = 0; i<cardinal;i++)
            for (Edge e : incidency.get(i))
                try {
                    graphTranspose.addArc(e.destination,e.source,e.label.toString());
                }catch (Exception exep) {System.out.println(exep);}

        return graphTranspose;
    }

    public String toString() {
        String result = new String("");
        result = result.concat("Nombre sommets : " + cardinal + "\n");
        result = result.concat("Sommets : \n");
        for (int i = 0; i<cardinal;i++) {
	    result = result.concat(i + " ");
		}
        result = result.concat("\nArcs : \n");
        for (int i = 0; i<cardinal;i++) {
            for (Edge e : incidency.get(i)) {
                result = result.concat(e.source + " -> " + e.destination + ", étiquette : "
				       + e.label.toString() + "\n");
            }
        }
        return result;
    }



    
}
