package assignment2;

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.Picture;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

public class SeamCarver {
    private static final double BORDER_VALUE = 1000;
    private Picture picture;
    private boolean isTransposed = false;


    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture) {
        if (picture == null) {
            throw new IllegalArgumentException("Cant be null!");
        }
        this.picture = new Picture(picture);
    }

    // current picture
    public Picture picture() {
        if (isTransposed) {
            return new Picture(transpose(picture));
        }
        return new Picture(picture);
    }

    // width of current picture
    public int width() {
        if (isTransposed) {
            return picture.height();
        }
        return picture.width();
    }

    // height of current picture
    public int height() {
        if (isTransposed) {
            return picture.width();
        }
        return picture.height();
    }

    // energy of pixel at column x and row y
    public double energy(int x, int y) {
        if (isTransposed) {
            // noinspection SuspiciousNameCombination
            return energy(picture, y, x);
        }
        return energy(picture, x, y);
    }

    private double energy(Picture somePicture, int x, int y) {
        if (x < 0 || x >= somePicture.width() || y < 0 || y >= somePicture.height()) {
            throw new IllegalArgumentException("x should be in the range [0, width), y should be in the range [0, height)");
        }
        if (x == 0 || y == 0 || x == somePicture.width() - 1 || y == somePicture.height() - 1) {
            return BORDER_VALUE;
        }
        return Math.sqrt(delta(somePicture, x - 1, y, x + 1, y) + delta(somePicture, x, y - 1, x, y + 1));
    }

    private double delta(Picture somePicture, int x1, int y1, int x2, int y2) {
        int rgb1 = somePicture.get(x1, y1).getRGB();
        int rgb2 = somePicture.get(x2, y2).getRGB();

        int red1 = (rgb1 >> 16) & 0xFF;
        int red2 = (rgb2 >> 16) & 0xFF;
        int red = red2 - red1;

        int green1 = (rgb1 >> 8) & 0xFF;
        int green2 = (rgb2 >> 8) & 0xFF;
        int green = green2 - green1;

        int blue1 = (rgb1) & 0xFF;
        int blue2 = (rgb2) & 0xFF;
        int blue = blue2 - blue1;

        return red * red + green * green + blue * blue;
    }

    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        // finding vertical seam for transposed picture == finding horizontal seam for the original one
        if (isTransposed) {
            return findVerticalSeam(picture);
        }
        return findVerticalSeam(transpose(picture));
    }

    private double getWeight(DirectedEdge[][] matrix, int i, int j) {
        return matrix[i][j] != null ? matrix[i][j].weight() : Double.POSITIVE_INFINITY;
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        if (isTransposed) {
            return findVerticalSeam(transpose(picture));
        }
        return findVerticalSeam(picture);
    }

    private int[] findVerticalSeam(Picture somePicture) {
        if (somePicture.width() == 1) {
            int[] oneColumnArray = new int[somePicture.height()];
            Arrays.fill(oneColumnArray, 0);
            return oneColumnArray;
        }
        // building paths through energy matrix;
        // we can move only down, down left or down right from each particular pixel (for vertical carving)
        // accumulating weight from the previous minimum path, which makes this problem a DP one
        DirectedEdge[][] matrix = new DirectedEdge[somePicture.height()][somePicture.width()];
        for (int i = 1; i < matrix.length - 1; i++) {
            for (int j = 1; j < matrix[i].length - 1; j++) {
                double prevLeft = getWeight(matrix, i - 1, j - 1);
                double prev = getWeight(matrix, i - 1, j);
                double prevRight = getWeight(matrix, i - 1, j + 1);
                double current = energy(somePicture, j, i);

                int from;
                double weight;
                if (hasMinWeight(prev, prevLeft, prevRight)) {
                    from = j;
                    weight = prev + current;
                } else if (hasMinWeight(prevLeft, prevRight, prev)) {
                    from = j - 1;
                    weight = prevLeft + current;
                } else if (hasMinWeight(prevRight, prevLeft, prev)) {
                    from = j + 1;
                    weight = prevRight + current;
                } else {
                    from = j;
                    weight = current;
                }
                matrix[i][j] = new DirectedEdge(from, j, weight);
            }
        }

        // finding the path with minimum weight
        int index = somePicture.height() <= 2 ? 0 : IntStream.range(1, matrix[somePicture.height() - 2].length - 1).boxed()
                .min(Comparator.comparingDouble(i -> matrix[somePicture.height() - 2][i].weight()))
                .orElse(0);

        // building vertical seam by traversing minimum path edge by edge from the end to the beginning
        int[] verticalSeam = new int[somePicture.height()];
        verticalSeam[verticalSeam.length - 1] = index > 0 ? index - 1 : index;

        for (int i = verticalSeam.length - 2; i >= 1; i--) {
            verticalSeam[i] = matrix[i][index].to();
            index = matrix[i][index].from();
        }
        verticalSeam[0] = index > 0 ? index - 1 : index;
        return verticalSeam;
    }

    private boolean hasMinWeight(double original, double firstToCompare, double secondToCompare) {
        return original != Double.POSITIVE_INFINITY && original <= firstToCompare && original <= secondToCompare;
    }

    private Picture transpose(Picture original) {
        int height = original.height();
        int width = original.width();

        // noinspection SuspiciousNameCombination
        Picture transposed = new Picture(height, width);
        for (int y = 0; y < transposed.height(); y++) {
            for (int x = 0; x < transposed.width(); x++) {
                transposed.set(x, y, original.get(y, x));
            }
        }
        this.picture = transposed;
        this.isTransposed = !this.isTransposed;
        return transposed;
    }

    // remove horizontal seam from current picture
    // remove vertical seam from transposed picture == remove horizontal seam from original one
    public void removeHorizontalSeam(int[] seam) {
        Picture pictureToResize = this.picture;
        if (!isTransposed) {
            pictureToResize = transpose(this.picture);
        }
        removeVerticalSeam(pictureToResize, seam);
    }

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {
        Picture pictureToResize = this.picture;
        if (isTransposed) {
            pictureToResize = transpose(this.picture);
        }
        removeVerticalSeam(pictureToResize, seam);
    }

    private void removeVerticalSeam(Picture original, int[] seam) {
        if (seam == null || seam.length != original.height() || original.width() <= 1) {
            throw new IllegalArgumentException("Cant perform vertical seam removal");
        }
        Picture resized = new Picture(original.width() - 1, original.height());
        int prev = seam[0];
        for (int y = 0; y < original.height(); y++) {
            int current = seam[y];
            if (Math.abs(current - prev) > 1 || current < 0 || current >= original.width()) {
                throw new IllegalArgumentException("Invalid seam");
            }
            for (int x = 0; x < original.width() - 1; x++) {
                int xToSet = x;
                if (x >= current) {
                    xToSet += 1;
                }
                resized.set(x, y, original.get(xToSet, y));
            }
            prev = current;
        }
        this.picture = resized;
    }
}
