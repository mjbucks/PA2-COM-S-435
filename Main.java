import javax.print.Doc;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        DocumentPreprocessor documentPreprocessor = new DocumentPreprocessor(new File("C:\\Users\\Maxwe\\Downloads\\articles\\articles\\baseball145.txt"));
        ArrayList<String> terms = documentPreprocessor.preProcess();
        for(int i = 0; i < terms.size(); i ++ ) {
            System.out.print(terms.get(i) + " ");
        }

        ArrayList<Integer> A = new ArrayList<Integer>(List.of(1, 2, 3, 4));
        ArrayList<Integer> B = new ArrayList<Integer>(List.of(5, 6, 3, 4));
        JaccardSimilarity<Integer> jaccSim = new JaccardSimilarity<>();

    }
}
