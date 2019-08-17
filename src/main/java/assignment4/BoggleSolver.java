package assignment4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.TrieST;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class BoggleSolver {
    private static final String Q = "Q";
    private static final String QU = "QU";
    private static final int[] SCORES = {0, 0, 0, 1, 1, 2, 3, 5, 11};

    private final TrieST<String> trie = new TrieST<>();
    private final Set<String> dictionary = new HashSet<>();

    // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BoggleSolver(String[] dictionary) {
        Arrays.stream(dictionary)
                .forEach(word -> {
                    if (word.length() >= 3) {
                        trie.put(word, word);
                        this.dictionary.add(word);
                    }
                });
    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board) {
        Set<String> total = new HashSet<>();
        boolean[] visited = new boolean[board.rows() * board.cols()];
        for (int i = 0; i < board.rows(); i++) {
            for (int j = 0; j < board.cols(); j++) {
                buildWords("", i, j, total, visited, board, trie);
                visited[i * board.cols() + j] = false;
            }
        }
        return total;
    }

    private void buildWords(String currentWord, int row, int col, Set<String> result, boolean[] visited, BoggleBoard board, TrieST<String> localTrie) {
        String ch = Character.toString(board.getLetter(row, col));
        String newWord = currentWord + (ch.equals(Q) ? QU : ch);
        if (newWord.length() >= 3) {
            localTrie = trieWithPrefix(newWord, localTrie);
            if (localTrie.isEmpty()) {
                return;
            }
        }
        if (newWord.length() >= 3 && dictionary.contains(newWord)) {
            result.add(newWord);
        }

        visited[row * board.cols() + col] = true;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i == row && j == col) {
                    continue;
                }
                if (i >= 0 && j >=0 && i < board.rows() && j < board.cols() && !visited[i * board.cols() + j]) {
                    buildWords(newWord, i, j, result, visited, board, localTrie);
                }
            }
        }
        visited[row * board.cols() + col] = false;
    }

    private TrieST<String> trieWithPrefix(String prefix, TrieST<String> original) {
        TrieST<String> newTrie = new TrieST<>();
        original.keysWithPrefix(prefix).forEach(w -> newTrie.put(w, w));
        return newTrie;
    }

    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word) {
        if (word == null || word.length() < 3 || !dictionary.contains(word)) {
            return 0;
        }
        return word.length() >= SCORES.length ? SCORES[8] : SCORES[word.length()];
    }

    public static void main(String[] args) {
        String fileName = args[0];
        System.out.println(args[0]);
        In in = new In(BoggleSolver.class.getClassLoader()
                .getResource("assignment4/" + fileName).getFile());

        String[] dictionary = in.readAllStrings();
        BoggleSolver solver = new BoggleSolver(dictionary);

        String boardFile = BoggleSolver.class.getClassLoader()
                .getResource("assignment4/" + args[1]).getFile();
        BoggleBoard board = new BoggleBoard(boardFile);
        System.out.println(board);
        int score = 0;
        for (String word : solver.getAllValidWords(board)) {
            StdOut.println(word + " = " + solver.scoreOf(word));
            score += solver.scoreOf(word);
        }
        StdOut.println("Score = " + score);
    }

}
