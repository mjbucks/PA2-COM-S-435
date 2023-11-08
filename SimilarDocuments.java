import javax.print.Doc;
import java.io.FileNotFoundException;
import java.util.ArrayList;

// TODO ask for clarification on this class
public class SimilarDocuments {
    double s;
    MinHash minHash;
    LSH lsh;
    //TODO ask if we should add bands as a param as LSH need int bands
    public SimilarDocuments(String folder, int numPermutations, int bands, double s) throws FileNotFoundException {
        this.minHash = new MinHash(folder, numPermutations);
        this.lsh = new LSH(minHash.minHashMatrix(), minHash.allDocs, bands);
        this.s = s;
    }

    /*
    TODO it says to just return retrieveSim from LSH and to not remove false positives, I dont
    think there is anything else to do
    For finding documents that are s-similar maybe run MultiSetJaccardSimilairy on retrieveSim
     */

    public ArrayList<String> similaritySearch (String docName) throws FileNotFoundException {
        ArrayList<String> sSimDocs = lsh.retrieveSim(docName);



//        for (String doc : sSimDocs) {
//            if (JaccardSimilarity.MultiSetJaccardSimilarity())
//        }

        return sSimDocs;
    }
}
