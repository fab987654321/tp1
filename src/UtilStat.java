import java.util.ArrayList;
import java.util.List;

public class UtilStat {
    // Pour récupérer les bon numéros de sommet
    static public Integer convertSommet(int cardi, int element) {
        if (element >= cardi / 2)
            return (element - cardi);
        else
            return (element + 1);

    }

    static public List<Integer> convertListSommet(int cardi, List<Integer> elements) {
        List<Integer> ret = new ArrayList<>();
        for (int each : elements) {
            ret.add(UtilStat.convertSommet(cardi, each));
        }
        return ret;
    }

    /**
     * Attention ne fonctionne qu'avec des tableau de int
     * 
     * @param cardi
     * @param elements
     * @return
     */
    static public List<Integer> convertTableSommet(int cardi, Object[] elements) {
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < elements.length; i++) {
            ret.add(UtilStat.convertSommet(cardi, (int) (elements[i])));
        }

        return ret;
    }
}
