package assignment5;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {

    // apply move-to-front encoding, reading from standard input and writing to standard output
    private static final int R = 256;

    // Move-to-front encoding
    public static void encode() {
        char[] chars = new char[R];
        for (char i = 0; i < R; i++)
            chars[i] = i;

        char[] input = BinaryStdIn.readString().toCharArray();
        int index;
        for (char current : input) {
            index = 0;
            while (chars[index] != current) {
                index++;
            }
            BinaryStdOut.write(index);
            while (index > 0) {
                chars[index] = chars[index - 1];
                index--;
            }
            chars[0] = current;
        }
        BinaryStdOut.close();
    }

    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode() {
        char[] chars = new char[R];
        for (char i = 0; i < R; i++) {
            chars[i] = i;
        }
        char[] input = BinaryStdIn.readString().toCharArray();
        int index;
        for (char current : input) {
            index = current;
            BinaryStdOut.write(chars[index]);
            char first = chars[index];
            while (index > 0) {
                chars[index] = chars[index - 1];
                index--;
            }
            chars[0] = first;
        }
        BinaryStdOut.close();
    }

    public static void main(String[] args) {
        if (args[0].equals("-")) {
            encode();
        } else {
            decode();
        }
    }
}
