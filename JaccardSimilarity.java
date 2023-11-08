import java.util.*;

public class JaccardSimilarity {

    public static Set<String> union(ArrayList<String> A, ArrayList<String> B) {
        Set<String> U = new HashSet<String>();
        U.addAll(A);
        U.addAll(B);
        return U;
    }

    public static int termFrequency(ArrayList<String> E, String x) {
        return Collections.frequency(E, x);
    }

    public static double intersectCardinality(ArrayList<String> A, ArrayList<String> B, Set<String> U) {
        int sum = 0;
        for (String x : U) {
            sum = sum + Math.min(termFrequency(A, x), termFrequency(B, x));
        }
        return sum;
    }

    private static double unionCardinality (ArrayList<String> A, ArrayList<String> B, Set<String> U) {
        int sum = 0;
        for (String x : U) {
            sum = sum + Math.max(termFrequency(A, x), termFrequency(B, x));
        }
        return sum;
    }

    public static double MultiSetJaccardSimilarity(ArrayList<String> A, ArrayList<String> B) {
        Set<String> U = union(A, B);
        return intersectCardinality(A, B, U) / unionCardinality(A, B, U);
    }

}
