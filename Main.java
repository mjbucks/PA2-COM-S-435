import javax.print.Doc;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
         String folder = "C:\\Users\\hedgr_v6euno5\\OneDrive\\ISU Fall23\\COM S 435\\PA2\\doc subset";
//        String folder = "C:\\Users\\Maxwe\\Downloads\\space\\space";

//        DocumentPreprocessor documentPreprocessor = new DocumentPreprocessor(new File("C:\\Users\\Maxwe\\Downloads\\articles\\articles\\baseball264.txt"));
//        DocumentPreprocessor documentPreprocessor = new DocumentPreprocessor(folder, "space-4.txt");
//        ArrayList<String> terms = documentPreprocessor.preProcess();
//        documentPreprocessor = new DocumentPreprocessor(folder, "space-10.txt");
//        ArrayList<String> terms2 = documentPreprocessor.preProcess();
//        System.out.println(JaccardSimilarity.MultiSetJaccardSimilarity(terms, terms2));


        //        for(int i = 0; i < terms.size(); i ++ ) {
//            System.out.print(terms.get(i) + " ");
//        }
//        System.out.println();
//
//
        MinHash min = new MinHash(folder, 400);
        int[][] minHashMatrix = min.minHashMatrix();
//        String[] allDocs = min.allDocs();
//        for(int i = 0; i < minHashMatrix.length; i ++ ) {
//            for(int j = 0; j < minHashMatrix[i].length; j ++ ) {
//                System.out.print(minHashMatrix[i][j] + " ");
//            }
//            System.out.println();
//        }
//        int[][] termMatrix = min.termDocumentMatrix();
//        for(int i = 0; i < termMatrix.length; i ++ ) {
//            for(int j = 0; j < termMatrix[i].length; j ++ ) {
//                System.out.print(termMatrix[i][j] + " ");
//            }
//            System.out.println();
//        }

        MinHashSimilarities minHashSimilarities = new MinHashSimilarities(folder, 20);
        System.out.println(minHashSimilarities.exactJaccard("space-44.txt", "space-45.txt"));
        System.out.println(minHashSimilarities.approximateJaccard("space-44.txt", "space-45.txt"));
        System.out.println(Arrays.toString(minHashSimilarities.minHashSig("space-44.txt")));
//    System.out.println(MinHashAccuracy.accuracy(folder, 400, .04));

//        LSH lsh = new LSH(minHashMatrix, min.allDocs, 5);
//        System.out.println(lsh.retrieveSim("space-10.txt"));

    }
}
