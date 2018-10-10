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
public final class HeapSort extends SortBase 
{
    private static final String HEADER_STRING = "Krūvos rikiavimo algoritmas";
    
    public HeapSort(ScreenKTU screen, int [] arr, int topOffset, int maxInt, int div, int delay)
    {
        super(screen, arr, topOffset, maxInt, div, delay);
        showStartingArr();
        delay(500);
        shuffleArray();
        writeHeader(HEADER_STRING);
        delay(500);
        restCounts();
        heapSort();
    }
    
    
    private void heapSort() 
    { 
        // Build heap (rearrange array) 
        for (int i = numCount / 2 - 1; i >= 0; i--) 
            heapify(numCount, i); 
  
        // One by one extract an element from heap 
        for (int i=numCount-1; i>=0; i--) 
        { 
            // Move current root to end
            swapNumbers(0, i, swapDelay);
  
            // call max heapify on the reduced heap 
            heapify(i, 0); 
        } 
    } 
  

    private void heapify(int n, int i) 
    { 
        int largest = i; // Initialize largest as root 
        int l = 2*i + 1; // left = 2*i + 1 
        int r = 2*i + 2; // right = 2*i + 2 
  
        // If left child is larger than root 
        if (l < n && numberArray[l] > numberArray[largest]) 
            largest = l; 
  
        // If right child is larger than largest so far 
        if (r < n && numberArray[r] > numberArray[largest]) 
            largest = r; 
        
        // If largest is not root 
        if (largest != i) 
        { 
            swapNumbers(i, largest, swapDelay, ScreenKTU.colors[i%12], ScreenKTU.colors[i%12], true);
  
            // Recursively heapify the affected sub-tree 
            heapify( n, largest); 
        } 
        compareCount += 5;
        accesCount += 4;
    } 
}
