package assignment5;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;


public class BurrowsWheeler {

    private static final int R = 256;

    // apply Burrows-Wheeler transform,
    // reading from standard input and writing to standard output
    public static void transform() {
        String input = BinaryStdIn.readString();
        BinaryStdIn.close();

        CircularSuffixArray circularSuffixArray = new CircularSuffixArray(input);
        int first = 0;
        char[] output = new char[input.length()];
        for (int i = 0; i < circularSuffixArray.length(); i++) {
            if (circularSuffixArray.index(i) == 0) {
                first = i;
                output[i] = input.charAt(circularSuffixArray.length() - 1);
            } else {
                output[i] = input.charAt(circularSuffixArray.index(i) - 1);
            }
        }

        BinaryStdOut.write(first);
        BinaryStdOut.write(new String(output));
        BinaryStdOut.close();
    }

    // apply Burrows-Wheeler inverse transform,
    // reading from standard input and writing to standard output
    public static void inverseTransform() {
        int first = BinaryStdIn.readInt();
        String s = BinaryStdIn.readString();
        BinaryStdIn.close();

        char[] firstChars = s.toCharArray();
        indexSort(firstChars);

        Map<Character, Queue<Integer>> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            Queue<Integer> queue = map.getOrDefault(ch, new LinkedList<>());
            if (queue == null) {
                queue = new LinkedList<>();
            }
            queue.add(i);
            map.put(ch, queue);
        }

        int[] next = new int[s.length()];
        for (int i = 0; i < next.length; i++) {
            // noinspection ConstantConditions
            next[i] = map.get(firstChars[i]).poll();
        }

        for (int i = 0; i < s.length(); i++) {
            BinaryStdOut.write(firstChars[first]);
            first = next[first];
        }
        BinaryStdOut.close();
    }

    private static void indexSort(char[] array) {
        int[] count = new int[R + 1];
        char[] aux = new char[array.length];

        for (char c : array) {
            count[c + 1]++;
        }
        for (int r = 0; r < R; r++) {
            count[r + 1] += count[r];
        }

        for (char c : array) {
            aux[count[c]++] = c;
        }
        System.arraycopy(aux, 0, array, 0, array.length);
    }

    public static void main(String[] args) {
        if (args[0].equals("-")) {
            transform();
        } else {
            inverseTransform();
        }
    }
}
