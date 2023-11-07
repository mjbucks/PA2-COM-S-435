import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MinHashSimilarities {

    MinHash minHash;
    int[][] minHashMatrix;
    int[][] termDocMatrix;
    DocumentPreprocessor documentPreprocessor;
    String folder;
    public MinHashSimilarities(String folder, int numPermutations) throws FileNotFoundException {
        this.folder = folder;
        this.minHash = new MinHash(folder, numPermutations);
        this.minHashMatrix = minHash.minHashMatrix();
        this.termDocMatrix = minHash.termDocumentMatrix();
    }

    public double exactJaccard(String file1, String file2) throws FileNotFoundException {
        documentPreprocessor = new DocumentPreprocessor(folder, file1);
        ArrayList<String> terms1 = documentPreprocessor.preProcess();

        documentPreprocessor = new DocumentPreprocessor(folder, file2);
        ArrayList<String> terms2 = documentPreprocessor.preProcess();

        JaccardSimilarity<String> jaccSim = new JaccardSimilarity<String>();

        return jaccSim.MultiSetJaccardSimilarity(terms1, terms2);
    }




}
