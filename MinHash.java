import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class MinHash {

    String folder;

    int numPermutations;

    String[] allDocs;

    int[][] minHashMatrix;

    int[][] termDocumentMatrix;

    ArrayList<String> allTermsInDocset;
    int allDocsSize;
    int permutationDomain;

    public MinHash(String folder, int numPermutations) throws FileNotFoundException {
        this.folder = folder;
        this.numPermutations = numPermutations;
        this.allDocs = allDocs();
        allDocsSize = allDocs.length;
        allTermsInDocset = getAllTerms();
        this.permutationDomain = findPrimeLargerThanM(allTermsInDocset.size());
    }

    public String[] allDocs() {
        try {
            Path folderPath = Paths.get(folder);
            long count = Files.list(folderPath).count();
            String[] allDocs = new String[(int) count];
            int i = 0;
            try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(folderPath)) {
                for (Path path : directoryStream) {
                    if (Files.isRegularFile(path)) {
                        allDocs[i] = String.valueOf(path.getFileName());
                        i++;
                    }
                }
            }
            return allDocs;
        } catch (IOException e) {
            System.err.println("An I/O error occurred: " + e.getMessage());
        }
        return null;
    }

    public int[][] minHashMatrix() throws FileNotFoundException {

        DocumentPreprocessor documentPreprocessor;
        Random rand;
        int[][] minhash = new int[numPermutations][allDocsSize];
        int a, b, p, x;
        p = permutationDomain;
        ArrayList<String> currTerms;

        for (int pi = 0; pi < numPermutations; pi++) {
            rand = new Random(pi);
            a = Math.abs(rand.nextInt());
            b = Math.abs(rand.nextInt());
            int min, hashTerm;

            for (int d = 0; d < allDocsSize; d++) {

                documentPreprocessor = new DocumentPreprocessor(folder, allDocs[d]);
                currTerms = documentPreprocessor.preProcess();

                min = Integer.MAX_VALUE; // max value of int

                for (String currTerm : currTerms) {
                    x = currTerm.hashCode();

                    hashTerm = (a * x + b) % p;
                    if (hashTerm < min) {
                        min = hashTerm;
                    }
                }
                minhash[pi][d] = min;
            }
        }
        return minhash;
    }

    // TODO Professor mentioned counting how many times a term appears rather than doing 0 or 1 if appears
    public int[][] termDocumentMatrix() throws FileNotFoundException {
        DocumentPreprocessor documentPreprocessor;
        int[][] termDocMatrix = new int[allTermsInDocset.size()][allDocsSize];

        for (int i = 0; i < allDocsSize; i++) {
            documentPreprocessor = new DocumentPreprocessor(folder, allDocs[i]);
            ArrayList<String> currTerms = documentPreprocessor.preProcess();
            for (int t = 0; t < allTermsInDocset.size(); t++){
                termDocMatrix[t][i] = Collections.frequency(currTerms, allTermsInDocset.get(t));;
            }
        }
        return termDocMatrix;
    }

    public boolean isPrime(int n){
        boolean flag = true;
        for (int i = 2; i <= n / 2; ++i) {
            if (n % i == 0) {
                flag = false;
            }
        }
        return flag;
    }

    public int findPrimeLargerThanM(int m){
        Random rand = new Random(m);
        BigInteger prime = BigInteger.valueOf(rand.nextInt(m/10) + m);
        while(!prime.isProbablePrime(100)){
            prime = BigInteger.valueOf(rand.nextInt(m/10) + m);
        }
        return prime.intValue();
    }

    public int getMin (int[] arr) {
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        return min;
    }

    public int permutationDomain() {
        return permutationDomain;
    }

    public ArrayList<String> getAllTerms() throws FileNotFoundException {
        ArrayList<String> termsList = new ArrayList<String>();
        DocumentPreprocessor documentPreprocessor;
        for(int i = 0; i < allDocsSize; i++){
            documentPreprocessor = new DocumentPreprocessor(folder, allDocs[i]);
            ArrayList<String> currTerms = documentPreprocessor.preProcess();
            for (String currTerm : currTerms) {
                if (!termsList.contains(currTerm)) {
                    termsList.add(currTerm);
                }
            }
        }
        return termsList;
    }

}
