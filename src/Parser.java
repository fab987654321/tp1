import java.io.File;
import java.util.Scanner;

public class Parser {

    public Parser() {

    }

    private Scanner openFile(String cheminFichier) {
        Scanner scanner = null;
        try {
            File file = new File(cheminFichier);
            scanner = new Scanner(file);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return scanner;
    }

    public Graph<String> parse(String filename) {

        // Ouvrir le fichier formule-2-sat.txt
        Scanner fichier = openFile(filename);

        int nb_literaux;
        int nb_Clause;
        String ligne;
        Graph<String> leGraph = null;
        int numLigne = 0;

        // Boucle sur les ligne du fichier
        while (fichier.hasNextLine()) {
            ligne = fichier.nextLine();
            numLigne += 1;
            // Compare les début de ligne
            if (ligne.startsWith("p")) {
                // Recup les deux int séparé
                String[] paramSplit = ligne.split(" ");
                nb_literaux = Integer.parseInt(paramSplit[2]);
                nb_Clause = Integer.parseInt(paramSplit[3]);
                // Génére le graph
                leGraph = new Graph<String>(nb_literaux * 2);
            }
            // ^[0-9\-]
            else if (Character.isDigit(ligne.charAt(0)) | ligne.charAt(0) == '-') {
                String[] tLigne = ligne.split(" ");
                assert leGraph != null;
                leGraph.addClauseArc(Integer.parseInt(tLigne[0]),
                        Integer.parseInt(tLigne[1]), Integer.toString(numLigne));
            }

        } // while

        fichier.close();

        return leGraph;
    }

}
