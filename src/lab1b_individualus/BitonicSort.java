/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1b_individualus;

import studijosKTU.ScreenKTU;

/**
 *
 * @author Vilius
 */
public final class BitonicSort extends SortBase{
    private static final String HEADER_STRING = "Bitoninis rikiavimo algoritmas";
    
    public BitonicSort(ScreenKTU screen, int [] arr, int topOffset, int maxInt, int div, int delay)
    {
        super(screen, arr, topOffset, maxInt, div, delay);
        showStartingArr();
        delay(500);
        shuffleArray();
        writeHeader(HEADER_STRING);
        delay(500);
        restCounts();
        sort(numCount, 1);
    }
    
    // The parameter dir indicates the sorting direction, 
    void compAndSwap(int i, int j, int dir) 
    { 
        if ( (numberArray[i] > numberArray[j] && dir == 1) || 
             (numberArray[i] < numberArray[j] && dir == 0)) 
        {  
            swapNumbers(i, j, swapDelay);
            compareCount += 4;
            accesCount += 4;
        } 
    } 
  
    // It recursively sorts a bitonic sequence in ascending 
    // order, The sequence to be sorted starts at 
    // index position low, the parameter cnt is the number 
    // of elements to be sorted.
    void bitonicMerge(int low, int cnt, int dir) 
    { 
        if (cnt>1) 
        { 
            int k = cnt/2; 
            for (int i=low; i<low+k; i++) 
                compAndSwap(i, i+k, dir); 
            bitonicMerge(low, k, dir); 
            bitonicMerge(low+k, k, dir); 
        } 
    } 
  
    // This funcion first produces a bitonic sequence by 
    // recursively sorting its two halves in opposite sorting 
    // orders, and then  calls bitonicMerge to make them in 
    // the same order 
    void bitonicSort(int low, int cnt, int dir) 
    { 
        if (cnt>1) 
        { 
            int k = cnt/2; 
  
            // sort in ascending order since dir here is 1 
            bitonicSort(low, k, 1); 
  
            // sort in descending order since dir here is 0 
            bitonicSort(low+k, k, 0); 
  
            // Will merge wole sequence in ascending order 
            // since dir=1. 
            bitonicMerge(low, cnt, dir); 
        } 
    } 
  
    //Caller of bitonicSort for sorting the entire array 
    void sort(int N, int dir) 
    { 
        bitonicSort(0, N, dir); 
    } 
  
}
