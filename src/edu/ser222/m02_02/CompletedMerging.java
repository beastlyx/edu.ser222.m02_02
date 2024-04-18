package edu.ser222.m02_02;

/**
 * Implements various divide and conquer algorithms.
 *
 * Last updated 4/2/2022.
 *
 * Completion time: 4 hours
 *
 * @author Borys Banaszkiewicz, Acuna, Sedgewick and Wayne
 * @verison 1.0
 */
import java.util.Random;

public class CompletedMerging implements MergingAlgorithms {

    //TODO: implement interface methods.
    /**
     * Merges sorted queues. Takes two sorted queues as input, and returns a queue containing the
     * elements in sorted order. Input queues may be emptied afterward. Assumes input does not
     * contain nulls.
     * @param q1 first queue
     * @param q2 second queue
     * @return merged queue
     */
    @Override
    public <T extends Comparable> Queue<T> mergeQueues(Queue<T> q1, Queue<T> q2) {
        Queue<T> result = new ListQueue<>();
        
        // iterates through both queues until one of them is empty
        while (!q1.isEmpty() && !q2.isEmpty()) {
            if (less(q1.peek(),q2.peek())) result.enqueue(q1.dequeue());
            else result.enqueue(q2.dequeue());
        }
        
        // iterates through the remaining elements in the non-empty queues 
        while (!q1.isEmpty()) result.enqueue(q1.dequeue());
        while (!q2.isEmpty()) result.enqueue(q2.dequeue());
        
        return result;
    }

    /**
     * Sorts the contents of an array. Array is sorted in-place. Uses a mergesort approach involving
     * helper methods mergesort(), and merge(). Assumes input does not contain nulls.
     * @param a Array to sort.
     */
    @Override
    public void sort(Comparable[] a) {
        // serves as an entry point for the mergesort algorithm
        mergesort(a);
    }

    /**
     * Implementation of mergesort. Only uses arrays as parameters, not integer indices. Assumes
     * input does not contain nulls.
     *
     * @param a array to sort
     * @return sorted array
     */
    @Override
    public Comparable[] mergesort(Comparable[] a) {
        //base case 
        if (a.length <= 1) return a;
        
        // splits array 'a' in half
        int mid = a.length / 2;
        Comparable[] arr1 = new Comparable[mid];
        Comparable[] arr2 = new Comparable[a.length - mid];
        
        System.arraycopy(a, 0, arr1, 0, mid);
        System.arraycopy(a, mid, arr2, 0, a.length - mid);

        // recursive call to merge and mergesort to sort and merge the 2 halves
        Comparable[] result = merge(mergesort(arr1), mergesort(arr2));
        
        // copies contents from the sorted result array back to original array
        System.arraycopy(result, 0, a, 0, a.length);
        
        return a;
    }

    /**
     * Merges two sorted arrays into one. Types must match between input arrays. Input arrays must
     * not change, or be returned as the result. Assumes input does not contain nulls.
     * @param a first array to merge
     * @param b second array to merge
     * @return merged array
     */
    @Override
    public Comparable[] merge(Comparable[] a, Comparable[] b) {
        Comparable[] result = new Comparable[a.length + b.length];
        
        int ptr1 = 0;
        int ptr2 = 0;
        int index = 0;
        
        // iterates through both arrays until one of them is "empty", filling
        // "result" array with elements in sorted order
        while (ptr1 < a.length && ptr2 < b.length) {
            if (less(a[ptr1], b[ptr2])) result[index++] = a[ptr1++];
            else result[index++] = b[ptr2++];
        }
        
        // iterates through the remaining elements in the "non-empty" arrays 
        while (ptr1 < a.length) result[index++] = a[ptr1++];
        while (ptr2 < b.length) result[index++] = b[ptr2++];
        
        return result;
    }

    /**
     * Shuffles an array in nlog(n) time using a recursive merging mechanism. It must be possible
     * for any element to reposition to any other position. Assumes input does not contain nulls.
     * @param a an array
     */
    @Override
    public void shuffle(Object[] a) {
        // serves as an entry point for the shuffle merge algorithm
        recursiveShuffle(a);
    }

    
    public Object[] recursiveShuffle(Object[] a) {
        //base case 
        if (a.length <= 1) return a;
        
        // splits array 'a' in half
        int mid = a.length / 2;
        Object[] arr1 = new Object[mid];
        Object[] arr2 = new Object[a.length - mid];
        
        System.arraycopy(a, 0, arr1, 0, mid);
        System.arraycopy(a, mid, arr2, 0, a.length - mid);
        
        Random random = new Random();
        
        // recursive call to shuffleMerge and recursiveShuffle to merge and
        // shuffle the two halves
        Object[] result = shuffleMerge(recursiveShuffle(arr1), recursiveShuffle(arr2), random);
        
        // copies contents from the shuffled result array back to original array
        System.arraycopy(result, 0, a, 0, a.length);
        
        return a;
    }
    
    
    public Object[] shuffleMerge(Object[] a, Object[] b, Random random) {
        // combines the two input arrays into 1
        int new_size = a.length + b.length;
        Object[] result = new Object[new_size];
        
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        
        // iterates through merged array and uses javas Random class to pick a 
        // random index "swap" and swaps elements with the element at index "i"
        for (int i = 0; i < new_size; i++) {
            int swap = i + random.nextInt(new_size - i); // adds random operation to i to ensure swap is not at the same index
            Object temp = result[i];
            result[i] = result[swap];
            result[swap] = temp;
        }
        return result;
    }
    /**
     * entry point for sample output.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Queue<String> q1 = new ListQueue<>(); q1.enqueue("E"); q1.enqueue("L"); q1.enqueue("O"); q1.enqueue("R"); q1.enqueue("T");
        Queue<String> q2 = new ListQueue<>(); q2.enqueue("A"); q2.enqueue("E"); q2.enqueue("M"); q2.enqueue("P"); q2.enqueue("S"); q2.enqueue("X");
        Queue<Integer> q3 = new ListQueue<>(); q3.enqueue(5); q3.enqueue(12); q3.enqueue(15); q3.enqueue(17); q3.enqueue(20);
        Queue<Integer> q4 = new ListQueue<>(); q4.enqueue(1); q4.enqueue(4); q4.enqueue(12); q4.enqueue(13); q4.enqueue(16); q4.enqueue(18);

        MergingAlgorithms ma = new CompletedMerging();

        //Q1 - sample test cases
        Queue merged1 = ma.mergeQueues(q1, q2);
        System.out.println(merged1.toString());
        Queue merged2 = ma.mergeQueues(q3, q4);
        System.out.println(merged2.toString());
        
        //Q2 - sample test cases
        String[] a = {"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        ma.sort(a);
        assert isSorted(a);
        show(a);
        
        //Q3 - sample test cases
        String[] b = {"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        ma.shuffle(b);
        show(b);
        
        ma.shuffle(b);
        show(b);
    }

    //below are utilities functions, please do not change them.
        
    //sorting helper from text
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    //sorting helper from text
    private static void show(Comparable[] a) {
        for (Comparable a1 : a)
            System.out.print(a1 + " ");

        System.out.println();
    }
    
    //sorting helper from text
    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1]))
                return false;
        
        return true;
    }
}
