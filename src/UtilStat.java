public class UtilStat {
    // Pour récupérer les bon numéros de sommet
    static public Integer convertSommet(int cardi, int element) {
        if (element >= cardi / 2)
            return (element - cardi);
        else
            return (element + 1);

    }
}
