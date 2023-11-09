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

    int[][] termByPerm;

    public MinHash(String folder, int numPermutations) throws FileNotFoundException {
        this.folder = folder;
        this.numPermutations = numPermutations;
        this.allDocs = allDocs();
        allDocsSize = allDocs.length;
        allTermsInDocset = getAllTerms();
        termByPerm = getTermByPermMatrix();

//        minHashMatrix = minHashMatrix();
//        termDocumentMatrix = termDocumentMatrix();
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
        int[][] minhash = new int[numPermutations][allDocsSize];
        int index;

        for (int pi = 0; pi < numPermutations; pi++) {
            int min;

            for (int d = 0; d < allDocsSize; d++) {

                documentPreprocessor = new DocumentPreprocessor(folder, allDocs[d]);
                ArrayList<String> currTerms = documentPreprocessor.preProcess();

                min = 2147483647; // max value of int

                for (int t = 0; t < currTerms.size(); t++) {
                    index = allTermsInDocset.indexOf(currTerms.get(t));
                    if (termByPerm[pi][index] < min){
                        min = termByPerm[pi][index];
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
                if (currTerms.contains(allTermsInDocset.get(t))){
                    termDocMatrix[t][i] = 1;
                }
                else{
                    termDocMatrix[t][i] = 0;
                }
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

    public int permutationDomain() {
        return allTermsInDocset.size();
    }

    public ArrayList<String> getAllTerms() throws FileNotFoundException {
        ArrayList<String> termsList = new ArrayList<String>();
        DocumentPreprocessor documentPreprocessor;
        for(int i = 0; i < allDocsSize; i++){
            //File currFile = new File(folder + "\\" + allDocs[i]);
            documentPreprocessor = new DocumentPreprocessor(folder, allDocs[i]);
            ArrayList<String> currTerms = documentPreprocessor.preProcess();
            for(int j = 0; j < currTerms.size(); j++){
                if (!termsList.contains(currTerms.get(j))){
                    termsList.add(currTerms.get(j));
                }
            }
        }
        return termsList;
    }

    public int[][] getTermByPermMatrix(){
        int[][] termByPerm = new int[numPermutations][allTermsInDocset.size()];
        Random rand;
        int a, b, x, min, hashTerm;
        int p = findPrimeLargerThanM(allDocsSize);

        for(int i = 0; i < numPermutations; i++){
            rand = new Random(i);
            a = rand.nextInt();
            b = rand.nextInt();
            for (int j = 0; j < allTermsInDocset.size(); j++){
                x = allTermsInDocset.get(j).hashCode();

                hashTerm = Math.abs((a * x + b) % p);
                termByPerm[i][j] = hashTerm;
            }
        }

        return termByPerm;
    }

}
