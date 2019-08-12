package assignment2;

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.Picture;

import java.awt.*;

public class SeamCarver {
    private static final double BORDER_VALUE = 1000;
    private Picture picture;


    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture) {
        if (picture == null) {
            throw new IllegalArgumentException("Cant be null!");
        }
        this.picture = new Picture(picture);
    }

    // current picture
    public Picture picture() {
        return new Picture(picture);
    }

    // width of current picture
    public int width() {
        return picture.width();
    }

    // height of current picture
    public int height() {
        return picture.height();
    }

    // energy of pixel at column x and row y
    public double energy(int x, int y) {
        if (x == 0 || y == 0 || x == width() - 1 || y == height() - 1) {
            return BORDER_VALUE;
        }
        return Math.sqrt(delta(x - 1, y, x + 1, y) + delta(x, y - 1, x, y + 1));
    }

    private double delta(int x1, int y1, int x2, int y2) {
        Color c1 = picture.get(x1, y1);
        Color c2 = picture.get(x2, y2);
        int red = c2.getRed() - c1.getRed();
        int green = c2.getGreen() - c1.getGreen();
        int blue = c2.getBlue() - c1.getBlue();
        return red * red + green * green + blue * blue;
    }

    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        return null;
    }

    private double getWeight(DirectedEdge[][] matrix, int i, int j) {
        return matrix[i][j] != null ? matrix[i][j].weight() : 0;
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        DirectedEdge[][] matrix = new DirectedEdge[height()][width()];
        for (int i = 1; i < matrix.length - 1; i++) {
            for (int j = 1; j < matrix[i].length - 1; j++) {
                double prevLeft = getWeight(matrix, i - 1, j - 1);
                double prev = getWeight(matrix, i - 1, j);
                double prevRight = getWeight(matrix, i - 1, j + 1);
                double current = energy(j, i);
                if (prev <= prevLeft && prev <= prevRight) {
                    matrix[i][j] = new DirectedEdge(j, j, prev + current);
                } else if (prevLeft <= prevRight && prevLeft <= prev) {
                    matrix[i][j] = new DirectedEdge(j - 1, j, prevLeft + current);
                } else {
                    matrix[i][j] = new DirectedEdge(j + 1, j, prevRight + current);
                }
            }
        }
        double min = Double.MAX_VALUE;
        int index = 0;
        for (int i = 0; i < matrix[0].length - 1; i++) {
            if (matrix[height() - 1][i].weight() < min) {
                min = matrix[height() - 1][i].weight();
                index = i;
            }
        }
        int[] verticalSeam = new int[height()];
        verticalSeam[verticalSeam.length - 1] = index;
        for (int i = verticalSeam.length - 2; i >=0; i--) {
            verticalSeam[i] = matrix[i][index].to();
            index = matrix[i][index].from();
        }
        return verticalSeam;
    }

    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {
        if (seam == null) {
            throw new IllegalArgumentException("Cant be null!");
        }
    }

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {
        if (seam == null) {
            throw new IllegalArgumentException("Cant be null!");
        }
    }

    //  unit testing (optional)
    public static void main(String[] args) {

    }

}
