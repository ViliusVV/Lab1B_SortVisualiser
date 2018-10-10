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
public final class BubbleSort extends SortBase
{
    private static final String HEADER_STRING = "Burbulo rikiavimo algoritmas";
            
    public BubbleSort(ScreenKTU screen, int [] arr, int topOffset, int maxInt, int div, int delay)
    {
        super(screen, arr, topOffset, maxInt, div, delay);
        showStartingArr();
        delay(500);
        shuffleArray();
        writeHeader(HEADER_STRING);
        delay(500);
        restCounts();
        bubbleSort();
    }
    
    
    void bubbleSort() 
    {   
     for(int i=0; i < numCount; i++){  
        for(int j=1; j < (numCount-i); j++){  
            if(numberArray[j-1] > numberArray[j]){  
                swapNumbers(j-1, j, swapDelay);
                compareCount += 1;
                accesCount += 2;
            }  
        }
    }
        clearLastPos();
    }     
}
