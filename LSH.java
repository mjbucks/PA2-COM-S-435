import java.util.*;

public class LSH {
    private String[] docNames;
    private HashMap<Integer, List<String>>[] hashTables;

    public LSH(int[][] minHashMatrix, String[] docNames, int bands) {
        this.docNames = docNames;
        this.hashTables = new HashMap[bands];

        for (int i = 0; i < bands; i++) {
            this.hashTables[i] = new HashMap<>();
        }

        int rowsPerBand = minHashMatrix.length / bands;
        for (int i = 0; i < docNames.length; i++) {
            for (int j = 0; j < bands; j++) {
                int[] band = Arrays.copyOfRange(getColumn(minHashMatrix, i), j * rowsPerBand, (j + 1) * rowsPerBand);
                int hashValue = Arrays.hashCode(band);

                if (!hashTables[j].containsKey(hashValue)) {
                    hashTables[j].put(hashValue, new ArrayList<>());
                }
                hashTables[j].get(hashValue).add(docNames[i]);
            }
        }
    }

    private int[] getColumn(int[][] matrix, int column) {
        return Arrays.stream(matrix)
                .mapToInt(row -> row[column])
                .toArray();
    }

    public ArrayList<String> retrieveSim(String docName) {
        ArrayList<String> similarDocs = new ArrayList<>();
        int docIndex = Arrays.asList(docNames).indexOf(docName);

        if (docIndex != -1) {
            for (HashMap<Integer, List<String>> hashTable : hashTables) {
                for (List<String> docs : hashTable.values()) {
                    if (docs.contains(docName)) {
                        for (String doc : docs) {
                            if (!doc.equals(docName) && !similarDocs.contains(doc)) {
                                similarDocs.add(doc);
                            }
                        }
                    }
                }
            }
        }
        return similarDocs;
    }
}
