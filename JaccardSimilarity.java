import java.util.*;

public class JaccardSimilarity<T> {
    int fA;
    int fB;
    Set<T> U = new HashSet<T>();

    ArrayList<T> A = new ArrayList<>();
    ArrayList<T> B = new ArrayList<>();

    public JaccardSimilarity() {
    }

    public void union (ArrayList<T> A, ArrayList<T> B) {
        U.addAll(A);
        U.addAll(B);
    }

    public int termFrequency (ArrayList<T> E, T x) {
        return Collections.frequency(E, x);
    }

    public int intersectCardinality () {
        return 1;
    }

    private int unionCardinality () {
        return 1;
    }

}
