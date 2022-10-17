import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Kosaraju {

    private Graph<String> leGraph ;
    public  Kosaraju( Graph<String> graph){
        this.leGraph = graph;
    }

  public int[] sccs(){
      try {
          for (int i = 0 ; i < 40 ; i++) System.out.println(Arrays.toString(leGraph.getIncidency(i)));
          //leGraph.getAdj(1);
      } catch (Exception e) {
          System.out.println(e);
      }

      //Je pars d'un point de départ (ligne 1)
      //Mets dans la pile des visitées
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
