package hw3.hash;

import java.util.HashSet;
import java.util.Set;
import hw3.hash.HashTableDrawingUtility;
import hw3.hash.SimpleOomage;
import java.util.Iterator;
public class HashTableVisualizer {

    public static void main(String[] args) {
        /* scale: StdDraw scale
           N:     number of items
           M:     number of buckets */
/**
        double scale = 1.0;
        int N = 50;
        int M = 10;
*/

        double scale = 0.5;
        int N = 2000;
        int M = 100;

        HashTableDrawingUtility.setScale(scale);
        Set<ComplexOomage> oomies = new HashSet<ComplexOomage>();
        for (int i = 0; i < N; i += 1) {
            oomies.add(ComplexOomage.randomComplexOomage());
        }
        visualize(oomies, M, scale);
    }

    public static void visualize(Set<ComplexOomage> set, int M, double scale) {
        HashTableDrawingUtility.drawLabels(M);

        /* TODO: Create a visualization of the given hash table. Use
           du.xCoord and du.yCoord to figure out where to draw
           Oomages.
         */

        int[] arr = new int[M];
        int i = 0;
        while (i!=M){arr[i]=0;i++;}
        /**has to use an iterator to go through the hashset*/

        for (Iterator<ComplexOomage> SO = set.iterator(); SO.hasNext(); ) {
            ComplexOomage f = SO.next();
            int y = (f.hashCode() & 0x7FFFFFFF) % M;
            arr[y]++;
            f.draw(HashTableDrawingUtility.xCoord(arr[y]), HashTableDrawingUtility.yCoord(y, M), scale);
        }

        /* When done with visualizer, be sure to try 
           scale = 0.5, N = 2000, M = 100. */           
    }


} 
