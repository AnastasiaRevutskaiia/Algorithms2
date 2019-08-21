package assignment4;

class Trie {
    private static final int R = 26;

    private Trie.Node root;

    static class Node {
        private Trie.Node[] next = new Trie.Node[R];

        Node getNext(char ch) {
            return next['A' - ch];
        }
    }

    void put(String key) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        else root = put(root, key, 0);
    }

    Node getRoot() {
        return root;
    }

    private Trie.Node put(Trie.Node x, String key, int d) {
        if (x == null) x = new Trie.Node();
        if (d == key.length()) {
            return x;
        }
        char c = key.charAt(d);
        x.next['A' - c] = put(x.getNext(c), key, d + 1);
        return x;
    }
}
