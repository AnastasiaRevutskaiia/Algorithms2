Algorithms Part II
=====================

Code for programming assignments in Java from the Coursera course, [Algorithms Part II](https://www.coursera.org/learn/algorithms-part2).

Week 1 - Directed and Undirected Graphs
--------------------------------
 - [**WordNet.java**](src/main/java/assignment1/WordNet.java) - a semantic lexicon for the English language that is used extensively by computational linguists 
 and cognitive scientists; for example, it was a key component in IBM's [Watson](http://en.wikipedia.org/wiki/Watson_(computer)). 
 
 WordNet groups words into sets of synonyms called *synsets* and describes semantic relationships between them. 
 One such relationship is the *is-a* relationship, which connects a *hyponym* (more specific synset) to a *hypernym* (more general synset). 
 For example, *animal* is a hypernym of both *bird* and *fish*; *bird* is a hypernym of *eagle*, *pigeon*, and *seagull*.

Week 2 - Minimum Spanning Trees and Shortest Paths
--------------------------------
 - [**SeamCarver.java**](src/main/java/assignment2/SeamCarver.java) - a data type that re-sizes a W-by-H image using the seam-carving technique. 
 
 Seam-carving is a content-aware image resizing technique where the image is reduced in size by one pixel of height (or width) at a time. 
 A vertical seam in an image is a path of pixels connected from the top to the bottom with one pixel in each row; a horizontal seam is a path of pixels connected 
 from the left to the right with one pixel in each column. Below left is the original 505-by-287 pixel image; below right is the result after removing 150 vertical seams, 
 resulting in a 30% narrower image. Unlike standard content-agnostic resizing techniques (such as cropping and scaling), seam carving preserves the most interest 
 features (aspect ratio, set of objects present, etc.) of the image.
 
 ![Original image][1]     ![Seam carved image][2] 
 
 [1]: src/main/resources/assignment2/HJoceanSmall.png (Original image)
 [2]: src/main/resources/assignment2/HJoceanSmallShrunk.png (Seam carved image)
 
 Although the [underlying algorithm](https://www.youtube.com/watch?v=6NcIJXTlugc) is simple and elegant, it was not discovered until 2007. Now, it is now a core feature in Adobe Photoshop and other computer graphics applications.
 
 Week 3 - Maximum Flow and Minimum Cut
 --------------------------------
  - [**BaseballElimination.java**](src/main/java/assignment3/BaseballElimination.java) - a data type that solves baseball elimination problem. 
  
  In the [baseball elimination problem](https://en.wikipedia.org/wiki/Maximum_flow_problem#Baseball_elimination), there is a division consisting of `n` teams. 
  At some point during the season, team `i` has `w[i]` wins, 
  `l[i]` losses, `r[i]` remaining games, and `g[i][j]` games left to play against team `j`. A team is mathematically eliminated if it cannot 
  possibly finish the season in (or tied for) first place. The goal is to determine exactly which teams are mathematically eliminated. 
  For simplicity, we assume that no games end in a tie (as is the case in Major League Baseball) and that there are no rainouts (i.e., 
  every scheduled game is played).
  
  The problem is not as easy as many sports writers would have you believe, in part because the answer depends not only on the number 
  of games won and left to play, but also on the schedule of remaining games. To see the complication, consider the following scenario:
  
  | i |  team        | wins | loss | left | Atl | Phi | NY | Mon |
  |:---:| :---:    |:---:|:---:|:---:|:---:|:---:|:---:|:---:|
  | 0 | Atlanta      | 83   | 71   | 8    | -   | 1   | 6  | 1   |
  | 1 | Philadelphia | 80   | 79   | 3    | 1   | -   | 0  | 2   |
  | 2 | New York     | 78   | 78   | 6    | 6   | 0   | -  | 0   |
  | 3 | Montreal     | 77   | 82   | 3    | 1   | 2   | 0  | -   |
  
  Montreal is mathematically eliminated since it can finish with at most 80 wins and Atlanta already has 83 wins. 
  This is the simplest reason for elimination. However, there can be more complicated reasons. For example, Philadelphia 
  is also mathematically eliminated. It can finish the season with as many as 83 wins, which appears to be enough to tie 
  Atlanta. But this would require Atlanta to lose all of its remaining games, including the 6 against New York, in which 
  case New York would finish with 84 wins. We note that New York is not yet mathematically eliminated despite the fact that 
  it has fewer wins than Philadelphia.
  
Week 4 - Tries and Substring Search
--------------------------------
- [**BoggleSolver.java**](src/main/java/assignment4/BoggleSolver.java) - a data type that wins the Boggle word game. 
- [**Trie.java**](src/main/java/assignment4/Trie.java) - custom trie data structure implementation for optimized search of prefixes in the set of strings.

 [Boggle](https://en.wikipedia.org/wiki/Boggle) is a word game designed by Allan Turoff and distributed by Hasbro. 
 It involves a board made up of 16 cubic dice, where each die has a letter printed on each of its 6 sides. 
 At the beginning of the game, the 16 dice are shaken and randomly distributed into a 4-by-4 tray, with only the top 
 sides of the dice visible. The player wins if they find all the letter combinations which are the valid words. 
 See the examples for `pins` and `pines` below.
 
 ![pins][3]     ![pines][4] 
  
  [3]: src/main/resources/assignment4/pins.png ("pins")
  [4]: src/main/resources/assignment4/pines.png ("pines")
  