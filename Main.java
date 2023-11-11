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
         // String folder = "C:\\Users\\hedgr_v6euno5\\OneDrive\\ISU Fall23\\COM S 435\\PA2\\space";
        String folder = "C:\\Users\\Maxwe\\Downloads\\space\\space";

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
//        MinHash min = new MinHash(folder, 400);
//        int[][] minHashMatrix = min.minHashMatrix();
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

//        MinHashSimilarities minHashSimilarities = new MinHashSimilarities(folder, 20);
//        System.out.println(minHashSimilarities.exactJaccard("space-1.txt", "space-2.txt"));
//        System.out.println(minHashSimilarities.approximateJaccard("space-1.txt", "space-2.txt"));
//        System.out.println(Arrays.toString(minHashSimilarities.minHashSig("space-44.txt")));
//        int numPermutations = 400;
//        double errorParam = 0.04;
//    System.out.println("Number of Permutations = " + numPermutations + ", Error Parameter = " + errorParam + ": " + MinHashAccuracy.accuracy(folder, numPermutations, errorParam));
//    }



//        LSH lsh = new LSH(minHashMatrix, min.allDocs, 5);
//        System.out.println(lsh.retrieveSim("space-10.txt"));

        double s = 0.08;
        int k = 400;
        boolean good;
        MinHashSimilarities minHashSimilarities = new MinHashSimilarities(folder, k);
        SimilarDocuments similarDocuments = new SimilarDocuments(folder, k, s);
        ArrayList<String> simDocs = similarDocuments.similaritySearch("space-44.txt");
//        System.out.println(simDocs);

//        for(int i = 0; i < minHashSimilarities.minHash.allDocsSize; i++){
//            if ( minHashSimilarities.exactJaccard("space-44.txt", minHashSimilarities.minHash.allDocs[i]) >= 0.08){
//                System.out.println(minHashSimilarities.minHash.allDocs[i]);
//            }
//        }

        for (int i = 0; i < simDocs.size(); i++) {
            for (int j = i + 1; j < simDocs.size(); j++) {
                good = minHashSimilarities.exactJaccard(simDocs.get(i), simDocs.get(j)) >= s;
                System.out.println("Similarity b/t " + simDocs.get(i) + ", " + simDocs.get(j) + " " + good);
            }
        }

    }//        System.out.println(JaccardSimilarity.MultiSetJaccardSimilarity());
}