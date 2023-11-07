import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MinHash {

    String folder;

    int numPerms;

    String[] allDocs;

    public MinHash(String folder, int numPermutations){
        this.folder = folder;
        numPerms = numPermutations;
        this.allDocs = allDocs();
    }

    public String[] allDocs() {
        try {
            Path folderPath = Paths.get(folder);
            long count = Files.list(folderPath).count();
            String[] allDocs = new String[(int) count];
            int i = 0;
            DirectoryStream<Path> directoryStream = Files.newDirectoryStream(folderPath);

            for (Path path : directoryStream) {
                if (Files.isRegularFile(path)) {
                    allDocs[i] = String.valueOf(path.getFileName());
                    i++;
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
        int[][] minhash = new int[numPerms][allDocs.length];
        int a, b, p, x;
        p = findPrimeLargerThanM(allDocs().length);
        int[] hashTerms;


        for (int pi = 0; pi < numPerms; pi++) {
            rand = new Random(pi);

            for (int d = 0; d < allDocs.length; d++) {

                File currFile = new File("folder" + allDocs[d]);
                Scanner scan = new Scanner(currFile);

                documentPreprocessor = new DocumentPreprocessor(currFile);
                ArrayList<String> currTerms = documentPreprocessor.preProcess();

                hashTerms = new int[currTerms.size()];


                for (int t = 0; t < currTerms.size(); t++) {
                    x = currTerms.get(t).hashCode();
                    a = rand.nextInt();
                    b = rand.nextInt();

                    hashTerms[t] = (a * x + b) % p;
                }
                minhash[pi][d] = getMin(hashTerms);
            }
        }
        return minhash;
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
            if (arr[i] <= min) {
                min = arr[i];
            }
        }
        return min;
    }

}
