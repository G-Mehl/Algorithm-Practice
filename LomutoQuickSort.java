import java.util.Arrays;
import java.util.Random;

public class LomutoQuickSort {

    // Static variables to track key comparisons and swaps
    private static int comparisons = 0;
    private static int swaps = 0;

    // Quicksort function using Lomuto's partition scheme
    public static void quicksort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = lomutoPartition(arr, low, high);
            quicksort(arr, low, pivotIndex - 1);
            quicksort(arr, pivotIndex + 1, high);
        }
    }

    // Lomuto's partition scheme
    public static int lomutoPartition(int[] arr, int low, int high) {
        int pivot = arr[high];  // Use the last element as the pivot
        int i = low - 1;

        for (int j = low; j < high; j++) {
            comparisons++;  // Increment comparisons for each comparison with pivot
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);  // Swap elements when they are <= pivot
            }
        }

        swap(arr, i + 1, high);  // Swap the pivot element to its correct position
        return i + 1;
    }

    // Utility method to swap elements in the array
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        swaps++;  // Increment swap counter
    }

    // Function to reset counters
    private static void resetCounters() {
        comparisons = 0;
        swaps = 0;
    }

    // Function to generate an array in ascending order
    public static int[] generateAscendingArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i + 1;
        }
        return arr;
    }

    // Function to generate an array in descending order
    public static int[] generateDescendingArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = size - i;
        }
        return arr;
    }

    // Function to generate an array with all elements the same
    public static int[] generateSameNumberArray(int size, int number) {
        int[] arr = new int[size];
        Arrays.fill(arr, number);
        return arr;
    }

    // Function to generate a random array
    public static int[] generateRandomArray(int size, int bound) {
        int[] arr = new int[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(bound) + 1; // Random number between 1 and bound
        }
        return arr;
    }

    // Function to test an array and print results
    public static void testArray(int[] arr, String description) {
        resetCounters(); // Reset the comparison and swap counters
        quicksort(arr, 0, arr.length - 1);
        System.out.println("Testing " + description + " of size " + arr.length);
        System.out.println("Total comparisons: " + comparisons);
        System.out.println("Total swaps: " + swaps);
        System.out.println();
    }

    // Main method to run tests on different array configurations
    public static void main(String[] args) {
        int[] sizes = {100, 1000, 10000};

        for (int size : sizes) {
            // Test ascending array
            int[] ascendingArray = generateAscendingArray(size);
            testArray(ascendingArray, "ascending array");

            // Test descending array
            int[] descendingArray = generateDescendingArray(size);
            testArray(descendingArray, "descending array");

            // Test array with all elements the same
            int[] sameNumberArray = generateSameNumberArray(size, 5);
            testArray(sameNumberArray, "same number array");

            // Test random array
            int[] randomArray = generateRandomArray(size, 100000);
            testArray(randomArray, "random array");
        }
    }
}