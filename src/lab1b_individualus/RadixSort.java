/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1b_individualus;

import java.awt.Color;
import java.util.Arrays;
import studijosKTU.ScreenKTU;

/**
 *
 * @author Vilius
 */
public final class RadixSort extends SortBase
{
    private static final String HEADER_STRING = "Skaitmeninis rikiavimo algoritmas";
    private static final int SORT_BASE = 10;
    
    public RadixSort(ScreenKTU screen, int [] arr, int topOffset, int maxInt, int div, int delay)
    {
        super(screen, arr, topOffset, maxInt, div, delay);
        showStartingArr();
        delay(500);
        shuffleArray();
        writeHeader(HEADER_STRING);
        delay(500);
        restCounts();
        radixsort();
    }
    
    // A function to find max of array
    private int getMax() 
    { 
        int mx = numberArray[0]; 
        for (int i = 1; i < numCount; i++) 
            if (numberArray[i] > mx) 
                mx = numberArray[i]; 
        return mx; 
    } 
  
    // A function to do counting sort of array according to 
    // the digit represented by exp. 
    private void countSort(int exp) 
    { 
        int output[] = new int[numCount]; // output array 
        int i; 
        int count[] = new int[SORT_BASE]; 
        Arrays.fill(count,0); 
  
        // Store count of occurrences in count[] 
        for (i = 0; i < numCount; i++){
            count[ (numberArray[i]/exp)%SORT_BASE ]++; 
            accesCount++;
        }
        // Change count[i] so that count[i] now contains 
        // actual position of this digit in output[] 
        for (i = 1; i < SORT_BASE; i++) 
            count[i] += count[i - 1]; 
  
        // Build the output array 
        for (i = numCount - 1; i >= 0; i--) 
        { 
            output[count[ (numberArray[i]/exp)%SORT_BASE ] - 1] = numberArray[i];
            highlightNumber(count[ (numberArray[i]/exp)%SORT_BASE ] - 1, swapDelay, Color.BLUE, false);
            highlightNumber(i, swapDelay, Color.CYAN, false);
            count[ (numberArray[i]/exp)%SORT_BASE ]--; 
            accesCount += 3;
        } 
  
        // Copy the output array to array, so that array now 
        // contains sorted numbers according to curent digit 
        for (i = 0; i < numCount; i++) 
        {
            numberArray[i] = output[i]; 
            accesCount++;
            highlightNumber(i, swapDelay, Color.RED, true);
        }
    } 
  
    // The main function to that sorts array of using Radix Sort 
    private void radixsort() 
    { 
        // Find the maximum number to know number of digits 
        int m = getMax(); 
  
        // Do counting sort for every digit.
        for (int exp = 1; m/exp > 0; exp *= SORT_BASE) 
            countSort(exp); 
    }    
}
