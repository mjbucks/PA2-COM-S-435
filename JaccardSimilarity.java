import java.util.*;

public class JaccardSimilarity<T> {

    public JaccardSimilarity() {
    }

    public Set<T> union (ArrayList<T> A, ArrayList<T> B) {
        Set<T> U = new HashSet<T>();
        U.addAll(A);
        U.addAll(B);
        return U;
    }

    public int termFrequency (ArrayList<T> E, T x) {
        return Collections.frequency(E, x);
    }

    public double intersectCardinality (ArrayList<T> A, ArrayList<T> B, Set<T> U) {
        int sum = 0;
        for (T x : U) {
            sum = sum + Math.min(termFrequency(A, x), termFrequency(B, x));
        }
        return sum;
    }

    private double unionCardinality (ArrayList<T> A, ArrayList<T> B, Set<T> U) {
        int sum = 0;
        for (T x : U) {
            sum = sum + Math.max(termFrequency(A, x), termFrequency(B, x));
        }
        return sum;
    }

    public double MultiSetJaccardSimilarity (ArrayList<T> A, ArrayList<T> B) {
        Set<T> U = union(A, B);
        return intersectCardinality(A, B, U) / unionCardinality(A, B, U);
    }

}
