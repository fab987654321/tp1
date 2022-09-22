import java.io.File;
import java.io.Serial;
import java.util.Scanner;

public class Parser {

    public Graph<String> parser(){
        //Ouvrir le fichier formule-2-sat.txt
        //Récupérer les données dans une variable String (J'imagine)
        //Parser les valeurs pour générer un obj de type Graph

        return new Graph<String>(1);
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
        int nbLigne = 4;
        return new Graph<String>(nbLigne);
    }

}
