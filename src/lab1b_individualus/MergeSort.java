/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1b_individualus;

import java.awt.Color;
import studijosKTU.ScreenKTU;

/**
 *
 * @author Vilius
 */
public final class MergeSort extends SortBase{
        private static final String HEADER_STRING = "Salajos rikiavimo algoritmas";
        
    public MergeSort(ScreenKTU screen, int [] arr, int topOffset, int maxInt, int div, int delay)
    {
        super(screen, arr, topOffset, maxInt, div, delay);
        showStartingArr();
        delay(500);
        shuffleArray();
        writeHeader(HEADER_STRING);
        delay(500);
        restCounts();
        mergeSort(0, numCount-1);
    }
    
    
    private void merge(int l, int m, int r) 
    { 
        // Find sizes of two subarrays to be merged 
        int n1 = m - l + 1; 
        int n2 = r - m; 
  
        /* Create temp arrays */
        int L[] = new int [n1]; 
        int R[] = new int [n2]; 
  
        /*Copy data to temp arrays*/
        for (int i=0; i<n1; ++i)
        {
            L[i] = numberArray[l + i];
            highlightNumber(l + i, swapDelay, Color.BLUE, true);
            accesCount++;
        }
        for (int j=0; j<n2; ++j) 
        { 
            R[j] = numberArray[m + 1+ j];
            highlightNumber(m + 1+j, swapDelay, Color.MAGENTA, true);
            accesCount++;
        } 
  
  
        /* Merge the temp arrays */
  
        // Initial indexes of first and second subarrays 
        int i = 0, j = 0; 
  
        // Initial index of merged subarry array 
        int k = l; 
        while (i < n1 && j < n2) 
        { 
            if (L[i] <= R[j]) 
            { 
                numberArray[k] = L[i];
                highlightNumber(k, swapDelay, Color.CYAN, true);
                i++;
            } 
            else
            { 
                numberArray[k] = R[j]; 
                highlightNumber(k, swapDelay, Color.GREEN, true);
                j++; 
            }
            accesCount++;
            compareCount++;
            k++; 
        } 
  
        /* Copy remaining elements of L[] if any */
        while (i < n1) 
        { 
            numberArray[k] = L[i]; 
            accesCount++;
            highlightNumber(k, swapDelay, Color.RED, true);
            i++; 
            k++; 
        } 
  
        /* Copy remaining elements of R[] if any */
        while (j < n2) 
        { 
            numberArray[k] = R[j];
            accesCount++;
            highlightNumber(k, swapDelay, Color.MAGENTA, true);
            j++; 
            k++; 
        } 
    } 
  
    // Main function that sorts array[l..r] using 
    // merge() 
    private void mergeSort(int l, int r) 
    { 
        if (l < r) 
        { 
            // Find the middle point 
            int m = (l+r)/2; 
  
            // Sort first and second halves 
            mergeSort(l, m); 
            mergeSort( m+1, r); 
  
            // Merge the sorted halves 
            merge(l, m, r); 
        } 
    } 
}
