import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MinHash {

    String folder;

    int numPerms;

    public MinHash(String folder, int numPermutations){
        this.folder = folder;
        numPerms = numPermutations;
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

    public int[][] minHashMatrix() {

    }
}
