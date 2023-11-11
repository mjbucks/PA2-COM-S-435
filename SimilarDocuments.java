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
        double w;
        int bands = 1;
        int backwards;
        for (b = 1; b < numPermutations; b++) {
            w = Math.abs((s - Math.pow(((double) 1 /b), ((double) b /numPermutations))));
            if (w <= 0.01) {
                for (backwards = b; backwards > 0; backwards--){
                    if(numPermutations % backwards == 0){
                        bands = backwards;
                        System.out.println(bands);
                        break;
                    }
                }
            }
        }
        this.lsh = new LSH(minHash.minHashMatrix(), minHash.allDocs, bands);
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
