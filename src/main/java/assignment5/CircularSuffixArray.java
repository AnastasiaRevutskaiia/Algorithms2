package assignment5;

import java.util.Arrays;

public class CircularSuffixArray {

    private final String s;
    private final CircularSuffix[] circularSuffixes;

    // circular suffix array of s
    public CircularSuffixArray(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Cannot be null!");
        }
        this.s = s;
        circularSuffixes = new CircularSuffix[s.length()];
        for (int i = 0; i < circularSuffixes.length; i++) {
            circularSuffixes[i] = new CircularSuffix(s, i);
        }
        Arrays.sort(circularSuffixes);
    }

    // length of s
    public int length() {
        return s.length();
    }

    // returns index of ith sorted suffix
    public int index(int i) {
        if (i < 0 || i >= length()) {
            throw new IllegalArgumentException("Outside of range!");
        }
        return circularSuffixes[i].getIndex();
    }

    private static class CircularSuffix implements Comparable<CircularSuffix> {
        private final String string;
        private final int index;

        CircularSuffix(String string, int index) {
            this.string = string;
            this.index = index;
        }

        @Override
        public int compareTo(CircularSuffix circularSuffix) {
            int thisCh = this.index;
            int thatCh = circularSuffix.index;
            boolean isCycle = false;
            int start = thisCh;
            while (string.charAt(thisCh) == string.charAt(thatCh) && !isCycle) {
                thisCh++;
                if (thisCh == string.length()) {
                    thisCh = 0;
                }
                if (thisCh == start) {
                    isCycle = true;
                }
                thatCh++;
                if (thatCh == string.length()) {
                    thatCh = 0;
                }
            }
            return string.charAt(thisCh) - string.charAt(thatCh);
        }

        int getIndex() {
            return index;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        CircularSuffixArray circularSuffixArray = new CircularSuffixArray("ABRACADABRA!");
        for (CircularSuffix circularSuffix : circularSuffixArray.circularSuffixes) {
            System.out.println(circularSuffix.index);
        }
    }
}
