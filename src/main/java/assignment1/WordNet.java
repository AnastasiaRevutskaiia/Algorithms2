package assignment1;

public class WordNet {
    private final String synsets;
    private final String hypernyms;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        if (synsets == null || hypernyms == null) {
            throw new NullPointerException("Argument should not be null");
        }
        this.synsets = synsets;
        this.hypernyms = hypernyms;
    }

    // do unit testing of this class
    public static void main(String[] args) {
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return null;
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        if (word == null) {
            throw new NullPointerException("Argument should not be null");
        }

        return false;
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        if (nounA == null || nounB == null) {
            throw new NullPointerException("Argument should not be null");
        }
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException("One of the arguments is not WordNet noun");
        }
        return 0;
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        if (nounA == null || nounB == null) {
            throw new NullPointerException("Argument should not be null");
        }
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException("One of the arguments is not WordNet noun");
        }
        return null;
    }
}