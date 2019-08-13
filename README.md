Algorithms Part II
=====================

Code for programming assignments in Java from the Coursera course, [Algorithms Part II](https://www.coursera.org/learn/algorithms-part2).

Week 1 - Directed and Undirected Graphs
--------------------------------
 - [**WordNet.java**](src/main/java/assignment1/WordNet.java) - is a semantic lexicon for the English language that is used extensively by computational linguists 
 and cognitive scientists; for example, it was a key component in IBM's [Watson](http://en.wikipedia.org/wiki/Watson_(computer)). 
 
 WordNet groups words into sets of synonyms called *synsets* and describes semantic relationships between them. 
 One such relationship is the *is-a* relationship, which connects a *hyponym* (more specific synset) to a *hypernym* (more general synset). 
 For example, *animal* is a hypernym of both *bird* and *fish*; *bird* is a hypernym of *eagle*, *pigeon*, and *seagull*.

Week 2 - Minimum Spanning Trees and Shortest Paths
--------------------------------
 - [**SeamCarver.java**](src/main/java/assignment2/SeamCarver.java) - is a data type that re-sizes a W-by-H image using the seam-carving technique. 
 
 Seam-carving is a content-aware image resizing technique where the image is reduced in size by one pixel of height (or width) at a time. 
 A vertical seam in an image is a path of pixels connected from the top to the bottom with one pixel in each row; a horizontal seam is a path of pixels connected 
 from the left to the right with one pixel in each column. Below left is the original 505-by-287 pixel image; below right is the result after removing 150 vertical seams, 
 resulting in a 30% narrower image. Unlike standard content-agnostic resizing techniques (such as cropping and scaling), seam carving preserves the most interest 
 features (aspect ratio, set of objects present, etc.) of the image.
 
 ![Original image][1]     ![Seam carved image][2] 
 
 [1]: src/main/resources/assignment2/HJoceanSmall.png (Original image)
 [2]: src/main/resources/assignment2/HJoceanSmallShrunk.png (Seam carved image)
 
 Although the [underlying algorithm](https://www.youtube.com/watch?v=6NcIJXTlugc) is simple and elegant, it was not discovered until 2007. Now, it is now a core feature in Adobe Photoshop and other computer graphics applications.
 