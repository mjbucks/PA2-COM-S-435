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
         String folder = "C:\\Users\\hedgr_v6euno5\\OneDrive\\ISU Fall23\\COM S 435\\PA2\\space";
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
        double[] errorParams = {0.04, 0.07, 0.09};
        int[] k = {400, 600, 800};
        double errorParam;
        int numPermutations;

        for (double param : errorParams) {
            for (int i : k) {
                errorParam = param;
                numPermutations = i;
                System.out.println("Number of Permutations = " + numPermutations + ", Error Parameter = " + errorParam + ": " + MinHashAccuracy.accuracy(folder, numPermutations, errorParam));
            }
        }
//    }

//        MinHash minHash = new MinHash(folder, 400);
//        int[][] m = minHash.minHashMatrix();
//        for (int i = 0; i < minHash.numPermutations; i++){
//
//        }


//        System.out.println(MinHashAccuracy.accuracy(folder, 400, 0.04));

//        LSH lsh = new LSH(minHashMatrix, min.allDocs, 5);
//        System.out.println(lsh.retrieveSim("space-10.txt"));

//        double s = 0.95;
//        int k = 400;
//        boolean good;
////        MinHashSimilarities minHashSimilarities = new MinHashSimilarities(folder, k);
//        SimilarDocuments similarDocuments = new SimilarDocuments(folder, k, s);
//        ArrayList<String> simDocs = similarDocuments.similaritySearch("baseball13.txt");
//        System.out.println("baseball13.txt");
//        System.out.println(simDocs);
//        simDocs = similarDocuments.similaritySearch("space-803.txt");
//        System.out.println("space-803.txt");
//        System.out.println(simDocs);
//        simDocs = similarDocuments.similaritySearch("baseball408.txt");
//        System.out.println("baseball408.txt");
//        System.out.println(simDocs);
//        simDocs = similarDocuments.similaritySearch("hockey37.txt");
//        System.out.println("hockey37.txt");
//        System.out.println(simDocs);
//        simDocs = similarDocuments.similaritySearch("space-65.txt");
//        System.out.println("space-65.txt");
//        System.out.println(simDocs);
//        simDocs = similarDocuments.similaritySearch("space-671.txt");
//        System.out.println("space-671.txt");
//        System.out.println(simDocs);
//        simDocs = similarDocuments.similaritySearch("baseball252.txt");
//        System.out.println("baseball252.txt");
//        System.out.println(simDocs);
//        simDocs = similarDocuments.similaritySearch("hockey601.txt");
//        System.out.println("hockey601.txt");
//        System.out.println(simDocs);
//        simDocs = similarDocuments.similaritySearch("hockey956.txt");
//        System.out.println("hockey956.txt");
//        System.out.println(simDocs);
//        simDocs = similarDocuments.similaritySearch("hockey986.txt");
//        System.out.println("hockey986.txt");
//        System.out.println(simDocs);

//        for (int i = 0; i < simDocs.size(); i++) {
//            for (int j = i + 1; j < simDocs.size(); j++) {
//                good = minHashSimilarities.exactJaccard(simDocs.get(i), simDocs.get(j)) >= s;
//                System.out.println("Similarity b/t " + simDocs.get(i) + ", " + simDocs.get(j) + " " + good);
//            }
//        }

    }//        System.out.println(JaccardSimilarity.MultiSetJaccardSimilarity());
}