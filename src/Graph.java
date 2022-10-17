import javax.swing.plaf.synth.SynthTextAreaUI;
import java.lang.reflect.Array;
import java.util.*;

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

        @Override
        public String toString() {
            return Integer.toString(this.source) + " --> " + Integer.toString(this.destination) +", Etiquette: "+ this.label;
        }

        public List<String> toArray() {
            List<String> a = new ArrayList<>();

            a.add(0,Integer.toString(this.source));
            a.add(1,Integer.toString(this.destination));
            a.add(2, (this.label).toString());

            return a;
        }
    }

    private int cardinal;
    private ArrayList<LinkedList<Edge>> incidency;

    /**Recupère un arc via son index
     * @param n index de l'implication 0 --> x
     * @return  String[] = {src,dest,etiquette}
     * @throws Exception index introuvable
     */
     public String[] getIncidency(int n) throws Exception{
//         System.out.println(">>>>>>>>>>>>>>>>>>>>>>"+incidency.size());+

       //Si n n'est pas un index d'arc
        if (n < 0 || n >= incidency.size())
            throw new Exception("Hors de l'index");

        //TODO utiliser ce principe pour gérer les elements vide de merde
         System.out.println("//Vérif tableau vide");
        for (int i = 0; i < incidency.size();i++){
            System.out.println(i);
            System.out.println(incidency.get(i) +" : "+ incidency.get(i).size());


        }
         System.out.println("//Fn Vérif tableau vide");
        //Convertis la linkedList en List
         List<String> ret = new ArrayList<>();
         for (int i = 1; i<this.order();i++)
             for (Edge e : incidency.get(i))
                     ret.add(e.toArray().toString().replace(",", ""));

         //Convertis la List en String[]
         String[] str = new String[ret.size()];
         for (int i = 0; i< ret.size();i++)
             str[i] = ret.get(i);


         return str[n].split(" ");
     }

    //Retourne une liste des sommets accessible par le sommet passé en paramètre
    public void getAdj(int sommet) throws Exception{
        List<Integer> listDest = new ArrayList<Integer>();

        //Récupère tt les éléments dont la src correspond à la var sommet
        //for (int i = 1; i<=this.order();i++)
          //  System.out.println(">>>>>>> "+this.getIncidency(i).toString() );
    }

    public Graph(int size) {
        cardinal = size;
        incidency = new ArrayList<LinkedList<Edge>>(size+1);
        for (int i = 0;i<this.order();i++)
            incidency.add(i, new LinkedList<Edge>());
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
        if (src < 0 ) src = src * -1 + this.order()/2;
        if (dest < 0) dest = dest * -1 + this.order()/2;
        try {
            this.addArc(src,dest, label);

           // System.out.println(incidency);

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
        Graph<String> graphTranspose = new Graph<String>(this.order());

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

        for (int i = 0; i<this.order();i++)
	        result = result.concat(i + " ");

        result = result.concat("\nArcs : \n");

        for (int i = 0; i<this.order();i++)
            for (Edge e : incidency.get(i))
                result = result.concat(e + "\n");


        return result;
    }



    
}
