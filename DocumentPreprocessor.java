import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DocumentPreprocessor {

    File file;

    public DocumentPreprocessor(File file) {
        this.file = file;
    }

    public ArrayList<String> preProcess() throws FileNotFoundException {
        ArrayList<String> document = new ArrayList<String>();

        Scanner scan = new Scanner(file);

        while (scan.hasNext()){
                String word = scan.next();
                word = word.toLowerCase();
                word = word.replaceAll("[.,:;'']", "");
                if (word != "are" && word != "the" && word.length() > 2){
                    document.add(word);
                }
        }
        return document;
    }
}
