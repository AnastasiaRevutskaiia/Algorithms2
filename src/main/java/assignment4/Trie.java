package assignment4;

import java.util.LinkedList;
import java.util.Queue;

class Trie {
    private static final int R = 256;

    private Trie.Node root;
    private int n;

    static class Node {
        private Integer val;
        private Trie.Node[] next = new Trie.Node[R];

        Node[] getNext() {
            return next;
        }
    }

    Integer get(String key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        Trie.Node x = get(root, key, 0);
        if (x == null) return null;
        return  x.val;
    }

    boolean contains(String key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    private Trie.Node get(Trie.Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) return x;
        char c = key.charAt(d);
        return get(x.next[c], key, d+1);
    }

    void put(String key) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if (key.length() == 0) delete(key);
        else root = put(root, key, key.length(), 0);
    }

    Node getRoot() {
        return root;
    }

    private Trie.Node put(Trie.Node x, String key, Integer val, int d) {
        if (x == null) x = new Trie.Node();
        if (d == key.length()) {
            if (x.val == null) n++;
            x.val = val;
            return x;
        }
        char c = key.charAt(d);
        x.next[c] = put(x.next[c], key, val, d+1);
        return x;
    }

    int size() {
        return n;
    }

    boolean isEmpty() {
        return size() == 0;
    }

    Iterable<String> keys() {
        return keysWithPrefix("");
    }


    private Iterable<String> keysWithPrefix(String prefix) {
        Queue<String> results = new LinkedList<>();
        Trie.Node x = get(root, prefix, 0);
        collect(x, new StringBuilder(prefix), results);
        return results;
    }

    private void collect(Trie.Node x, StringBuilder prefix, Queue<String> results) {
        if (x == null) return;
        if (x.val != null) results.offer(prefix.toString());
        for (char c = 0; c < R; c++) {
            prefix.append(c);
            collect(x.next[c], prefix, results);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    void delete(String key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        root = delete(root, key, 0);
    }

    private Trie.Node delete(Trie.Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) {
            if (x.val != null) n--;
            x.val = null;
        }
        else {
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d+1);
        }

        if (x.val != null) return x;
        for (int c = 0; c < R; c++)
            if (x.next[c] != null)
                return x;
        return null;
    }
}
