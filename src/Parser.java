import java.io.File;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {

    public Graph<String> parser(){

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
        int nbLigne = 0;

        String ligne = "";

        //Ouvrir le fichier formule-2-sat.txt
        Scanner fichier = openFile(filename);

        //Récupérer les données dans une variable
        while(fichier.hasNextLine()) {
            ligne += fichier.nextLine() + "\n";
            nbLigne++;
        }

        //Ferme le  fichier
        fichier.close();


        System.out.println(filename +":"+ nbLigne +"\n"+ligne);

        //Parser les valeurs pour générer un obj de type Graph

        return new Graph<String>(nbLigne);
    }

}
