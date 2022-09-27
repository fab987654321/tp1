import java.io.File;
import java.io.Serial;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Parser {

    public  Parser(){

    }

    private Scanner openFile(String cheminFichier){
        Scanner scanner = null;
        try
        {
            File file = new File(cheminFichier);
            scanner = new Scanner(file);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return  scanner;
    }

    public Graph<String> parse(String filename){

        List<String> ligne = new ArrayList<String>();

        //Ouvrir le fichier formule-2-sat.txt
        Scanner fichier = openFile(filename);

        //Récupérer les données dans une variable
        while(fichier.hasNextLine()) {
            ligne.add(fichier.nextLine());
        }

        //Ferme le  fichier
        fichier.close();

        int nbLignes = ligne.size();//Pour le for
        int nbVariable = 0;

        List Asupprimer = new ArrayList();
        String LigneParam = "/Na/";

        //Pour le graph
        int nb_literaux = 0;
        int nb_Clause = 0;

        //Parser les valeurs pour générer un obj de type Graph
        for (int i = 0;i < nbLignes;i++) {
            if(ligne.get(i).startsWith("c")) {
                Asupprimer.add(i);
            }
            if(ligne.get(i).startsWith("p")){
                Asupprimer.add(i);
                LigneParam = ligne.get(i);

                //Recup les deux int séparé
                String[] paramSplit = LigneParam.split(" ");
                 nb_literaux = Integer.parseInt(paramSplit[2]);
                 nb_Clause = Integer.parseInt(paramSplit[3]);
            }

        }

        Graph<String> leGraph = new Graph<String>(nb_literaux);

        return leGraph;
    }

}
