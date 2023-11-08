import java.io.FileNotFoundException;
import java.util.ArrayList;

// TODO ask for clarification on this class
public class SimilarDocuments {
    double s;
    LSH lsh;
    //TODO ask if we should add bands as a param as LSH need int bands
    public SimilarDocuments(String folder, int numPermutations, int bands, double s) throws FileNotFoundException {
        MinHash minHash = new MinHash(folder, numPermutations);
        this.lsh = new LSH(minHash.minHashMatrix(), minHash.allDocs, bands);
        this.s = s;
    }

    /*
    TODO it says to just return retrieveSim from LSH and to not remove false positives, I dont
    think there is anything else to do
     */

    public ArrayList<String> similaritySearch (String docName) {
        return lsh.retrieveSim(docName);
    }
}
