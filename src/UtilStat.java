public class UtilStat {
    // Pour récupérer les bon numéros de sommet
    static public String convertSommet(int cardi, int element) {
        if (element >= cardi / 2)
            return Integer.toString(element - cardi);
        else
            return Integer.toString(element + 1);

    }
}
