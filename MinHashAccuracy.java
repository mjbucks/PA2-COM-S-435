import java.io.FileNotFoundException;

public class MinHashAccuracy {

    public static int accuracy(String folder, int numPermutations, double errorParam) throws FileNotFoundException {
        MinHashSimilarities minHashSimilarities = new MinHashSimilarities(folder, numPermutations);
        int allDocsSize = minHashSimilarities.minHash.allDocsSize;
        int numPairsWithSignificantDifference = 0;
        String file1, file2;
        double diff;

        // compares each pair without repeating pairs
        for (int i = 0; i < allDocsSize; i++) {
            for (int j = i + 1; j < allDocsSize; j++) {

                file1 = minHashSimilarities.minHash.allDocs[i];
                file2 = minHashSimilarities.minHash.allDocs[j];

                diff = Math.abs(minHashSimilarities.exactJaccard(file1, file2) - minHashSimilarities.approximateJaccard(file1, file2));
                if (diff > errorParam ) {
                    numPairsWithSignificantDifference++;
                }
            }
        }

        return numPairsWithSignificantDifference;
    }
}
