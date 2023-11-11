import javax.print.Doc;
import java.io.FileNotFoundException;
import java.util.ArrayList;

// TODO ask for clarification on this class
public class SimilarDocuments {
    MinHash minHash;
    LSH lsh;
    //TODO ask if we should add bands as a param as LSH need int bands
    public SimilarDocuments(String folder, int numPermutations, double s) throws FileNotFoundException {
        this.minHash = new MinHash(folder, numPermutations);
        int b;
        for (b = 1; b < numPermutations; b++) {
            if (numPermutations % b == 0 && s == Math.pow(((double) 1 /b), ((double) b /numPermutations))) {
                break;
            }
        }
        b = 10;
        this.lsh = new LSH(minHash.minHashMatrix(), minHash.allDocs, b);
    }

    /*
    TODO it says to just return retrieveSim from LSH and to not remove false positives, I dont
    think there is anything else to do
    For finding documents that are s-similar maybe run MultiSetJaccardSimilairy on retrieveSim
     */

    public ArrayList<String> similaritySearch (String docName) throws FileNotFoundException {
        return lsh.retrieveSim(docName);
    }
}
