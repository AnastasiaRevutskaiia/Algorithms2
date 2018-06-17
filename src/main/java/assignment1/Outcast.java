package assignment1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

@SuppressWarnings("WeakerAccess")
public class Outcast {
    private final WordNet wordNet;

    public Outcast(WordNet wordNet) {
        this.wordNet = wordNet;
    }

    public String outcast(String[] nouns) {
        int maxDistance = Integer.MIN_VALUE;
        String outCast = null;
        for (int i = 0; i < nouns.length; i++) {
            for (int j = i + 1; j < nouns.length; j++) {
                int distance = wordNet.distance(nouns[i], nouns[j]);
                System.out.println("Distance = " + distance + " nouns: " + nouns[i] + " & " + nouns[j] + " ancestor: "
                        + wordNet.sap(nouns[i], nouns[j]));
                if (distance >= maxDistance) {
                    System.out.println(true);
                    maxDistance = distance;
                    outCast = nouns[j];
                }
            }
        }
        return outCast;
    }

    public static void main(String[] args) {
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }
}
