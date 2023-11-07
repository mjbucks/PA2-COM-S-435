import javax.print.Doc;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        DocumentPreprocessor documentPreprocessor = new DocumentPreprocessor(new File("C:\\Users\\Maxwe\\Downloads\\articles\\articles\\baseball264.txt"));
        ArrayList<String> terms = documentPreprocessor.preProcess();
        for(int i = 0; i < terms.size(); i ++ ) {
            System.out.print(terms.get(i) + " ");
        }
        System.out.println();


        MinHash min = new MinHash("C:\\Users\\Maxwe\\Downloads\\articles\\articles", 10);
        int[][] minHashMatrix = min.minHashMatrix();
        String[] allDocs = min.allDocs();
        for(int i = 0; i < minHashMatrix.length; i ++ ) {
            for(int j = 0; j < minHashMatrix[i].length; j ++ ) {
                System.out.print(minHashMatrix[i][j] + " ");
            }
            System.out.println();
        }
        int[][] termMatrix = min.termDocumentMatrix();
        for(int i = 0; i < termMatrix.length; i ++ ) {
            for(int j = 0; j < termMatrix[i].length; j ++ ) {
                System.out.print(termMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
