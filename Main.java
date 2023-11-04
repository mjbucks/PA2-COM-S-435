import javax.print.Doc;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        DocumentPreprocessor documentPreprocessor = new DocumentPreprocessor(new File("C:\\Users\\Maxwe\\Downloads\\articles\\articles\\baseball145.txt"));
        ArrayList<String> terms = documentPreprocessor.preProcess();
        for(int i = 0; i < terms.size(); i ++ ) {
            System.out.print(terms.get(i) + " ");
        }
    }
}
