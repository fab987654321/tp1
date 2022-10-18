import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Kosaraju {

    private Graph<String> leGraph ;
    public  Kosaraju( Graph<String> graph){
        this.leGraph = graph;
    }

  public int[] sccs(){
          try {

           // System.out.println( leGraph.getListArc());
            
          //leGraph.getAdj(0);
      } catch (Exception e) {
         // System.out.println(e);
      }

       
      Set<Integer> visite = new HashSet<>();
      //Pile
      Stack<Integer> sortie = new Stack<>();
      Stack<Integer> chemin = new Stack<>(); 

      //Je pars d'un point de départ (ligne 1)
      chemin.add(leGraph.getAdj(1).get(0));
      //Mets dans la pile des visitées
      visite.add(leGraph.getAdj(1).get(0));
      
      for (int i = 0; i < leGraph.order();i++) {
        //Int en haut de la pile
        chemin.lastElement();
        //List<int> des adjacents
        leGraph.getAdj(chemin.lastElement());
        
        //recup list adj
        //Si pas adjacent non visité alors
                //ajoute au stack sortie 
                //recommence la boucle
        //prendre element à la position 1
        //Ajoute le dis élément à la pile visité 
        //Ajoute le dis élément au chemin
            //Si pas adjacent non visité alors
                //ajoute au stack sortie 
                //recommence la boucle
      }

      //Boucle
        //Regarde les sommets accécibles
            //Si exite sommet accessible non visité
                //Avance au dis sommet
            //Sinon
                //Mets dans la pile des sommets fini (noir)
                //Recule au sommet précedent

        return new int[10];
  }

    //TODO
    //"Parcour en profondeur"
    String[] DFS(Graph unGraph){


        //Retourne un tableau contennant les composantes fortement connexe
        return new String[2];
    }

    Stack SortTime(){


        return new Stack();
    }
    //1 Sortime
    //2 Transpose
    //3 DFS par rapport au temps de sortie (sur la transposé)

        //Avec empilage et génération de "foret d'arborescence"
}//Class
