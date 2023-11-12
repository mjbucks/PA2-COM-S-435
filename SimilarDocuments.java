import javax.print.Doc;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class SimilarDocuments {
    MinHash minHash;
    LSH lsh;
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
                        break;
                    }
                }
            }
        }
        this.lsh = new LSH(minHash.minHashMatrix(), minHash.allDocs, bands);
    }

    public ArrayList<String> similaritySearch (String docName) throws FileNotFoundException {
        return lsh.retrieveSim(docName);
    }
}
