/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1b_individualus;

import java.awt.Color;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import studijosKTU.ScreenKTU;

/**
 *
 * @author Vilius
 */
public class SortBase {
    protected int[] numberArray;
    protected final ScreenKTU screen;
    private final int topOffset;
    private final int maxInt;
    protected final int numCount; 
    protected final int screenToNum;
    protected final int swapDelay;
    
    // Changeable variables
    private int lastSwapPos1 = 0;
    private int lastSwapPos2 = 0;
    protected int swapCount = 0;
    protected int compareCount = 0;
    protected int accesCount = 0;

    public SortBase(ScreenKTU screen, int [] arr, int topOffset, int maxInt, int div, int delay) 
    {
        this.screen = screen;
        this.numberArray = arr;
        this.topOffset = topOffset;
        this.maxInt = maxInt;
        this.screenToNum = div;
        this.swapDelay = delay;
        this.numCount = arr.length;
    }
    
    
    // Resets all counters
    protected void restCounts()
    {
        swapCount = 0;
        compareCount = 0;
        accesCount = 0;
    }
    
    
    // Animated draw of starting array
    protected void showStartingArr()
    {
        for(int i = 0; i < numCount; i++)
        {
            drawColumn(numberArray[i], i, Color.WHITE);
            this.screen.refresh(3);
        }
    }
    
    
    // Delays for t miliseconds
    protected void delay(long t)
    {
        try {
            Thread.sleep(t);
        } catch (InterruptedException ex) {
            System.out.println("Something interrupted thread...");
        }
    }
    
    
    // Updates everything including headers and counter information
    protected void updateEverything(int t)
    {
        updateAcces();
        updateCompares();
        updateSwaps();
        screen.refresh(t);
    }
    
    
    // Sets window title and writes header at a top
    protected void writeHeader(String headerString)
    {
        screen.fillRect(0, 0, 4, numCount*screenToNum, Color.BLACK);
        screen.print(0,0, headerString);
        screen.setTitle(headerString);
    }
    
    
    // Draws swap count
    protected void updateSwaps()
    {
        screen.fillRect(6, 0, 4, 199, Color.BLACK);
        screen.print(6,0, "Suketimai:" + swapCount);
        screen.refresh(0);
    }
    
    
    // Draws compare count
    protected void updateCompares()
    {
        screen.fillRect(6, 200, 4, numCount*screenToNum, Color.BLACK);
        screen.print(6,200, "Palyginimai:" + compareCount);
        screen.refresh(0);
    }
    
    
    // Draws access count
    protected void updateAcces()
    {
        screen.fillRect(12, 0, 4, numCount*screenToNum, Color.BLACK);
        screen.print(12,0, "Pasiekimai:" + accesCount);
        screen.refresh(0);
    }
    
    
    protected void drawColumn(int height, int pos, Color col)
    {
        drawColumn(height, pos, col, true);
    }
    
    
    // Draws collumn or changes its color
    protected void drawColumn(int height, int pos, Color col, boolean clear)
    {
        this.screen.fillRect(topOffset , pos*screenToNum, maxInt, screenToNum, Color.BLACK);
        this.screen.fillRect(maxInt - height + topOffset , pos*screenToNum, height, screenToNum, col);
    }
            
    
    // Shuffles array for next sort
    protected void shuffleArray()
    {
        writeHeader("Maisoma...");
        Random rnd = ThreadLocalRandom.current();
        for (int i = numCount - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            swapNumbers(index, i, 5);
        }
        clearLastPos();
    }
    
    
    protected void clearLastPos()
    {
        clearLastPos(true);
    }
    
    
    // Resets last column to white
    protected void clearLastPos(boolean clear)
    {
        if(clear) drawColumn(numberArray[lastSwapPos1], lastSwapPos1, Color.WHITE);
        if(clear) drawColumn(numberArray[lastSwapPos2], lastSwapPos2, Color.WHITE);
        this.screen.refresh(0);
    }
    
    
    protected void swapNumbers(int pos1, int pos2, int delay)
    {
        swapNumbers(pos1, pos2, delay, Color.RED, Color.GREEN, true);
    }
    
    
    // Swaps numbers in array, changes acces and swap variables
    protected void swapNumbers(int pos1, int pos2, int delay, Color col1, Color col2, boolean clear)
    {
        clearLastPos(clear);
        int temp = numberArray[pos1];
        numberArray[pos1] = numberArray[pos2];
        numberArray[pos2] = temp;
        drawColumn(numberArray[pos1], pos1, col1);
        drawColumn(numberArray[pos2], pos2, col2);
        lastSwapPos1 = pos1;
        lastSwapPos2 = pos2;
        swapCount++;
        accesCount += 4;
        updateEverything(delay);
    }
    
    
    protected void highlightNumber(int pos, int delay, Color col, boolean doCount)
    {
        highlightNumber(pos, delay, col, doCount, -1);
    }
    
    
    // Highlights selected array index (column)
    protected void highlightNumber(int pos, int delay, Color col, boolean doCount, int height)
    {
        clearLastPos();
        drawColumn(height <= -1 ? numberArray[pos] : height, pos, col);
        lastSwapPos1 = pos;
        if(doCount) swapCount++;
        updateEverything(delay);
    }
}
