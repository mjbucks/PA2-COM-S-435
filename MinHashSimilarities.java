import java.io.FileNotFoundException;
import java.util.*;

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
        double intersectCardinality = 0;
        double unionCardinality = 0;

        int file1Index = Arrays.asList(minHash.allDocs).indexOf(file1);
        int file2Index = Arrays.asList(minHash.allDocs).indexOf(file2);


        for (int t = 0; t < termDocMatrix.length; t++) {
            if (termDocMatrix[t][file1Index] >= 1 && termDocMatrix[t][file2Index] >= 1) {
                intersectCardinality++;
                unionCardinality++;
            }
            else if (termDocMatrix[t][file1Index] >= 1 || termDocMatrix[t][file2Index] >= 1) {
                unionCardinality++;
            }
        }
        return intersectCardinality/unionCardinality;
    }

    public double approximateJaccard(String file1, String file2) {
        double intersectCardinality = 0;
        double unionCardinality = minHash.numPermutations;

        int[] sigOne = minHashSig(file1);
        int[] sigTwo = minHashSig(file2);

        for (int i = 0; i < sigOne.length; i++) {
            if (sigOne[i] == sigTwo[i]) {
                intersectCardinality++;
            }
        }

        return intersectCardinality/unionCardinality;
    }

    public int[] minHashSig(String fileName) {
        int[] signature = new int[minHash.numPermutations];
        int fileIndex = Arrays.asList(minHash.allDocs).indexOf(fileName);

        for (int p = 0; p < minHash.numPermutations; p++){
            signature[p] = minHashMatrix[p][fileIndex];
        }

        return signature;
    }

}
