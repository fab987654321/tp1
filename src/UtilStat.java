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
        for (int each: elements) {
            ret.add(UtilStat.convertSommet(cardi,each));
        }
        return ret;
    }
}
