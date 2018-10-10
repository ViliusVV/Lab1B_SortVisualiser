/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1b_individualus;
import java.awt.Color;
import java.awt.event.MouseEvent;
import studijosKTU.ScreenKTU;

/**
 *
 * @author Vilius
 */
public class Lab1B_Individualus extends ScreenKTU
{   
    // Class constants
    private static final int MAX_INT = 200;
    private static final int TOP_OFFSET = 20;
    private static final int SCREEN_WIDTH = 512;
    private static final int SCREEN_HEIGHT = MAX_INT + TOP_OFFSET;
    private static final int CELL_SIZE = 4;
    private static final int NUMBER_DIV = 8; // Divides screen width, to fatten columns
    private static final int SWAP_DELAY = 50; 
    
    private static ScreenKTU screen1;
    private static String selectedAction = "";
    private static int [] theArray;
    
    
    public Lab1B_Individualus() throws InterruptedException{
        super(CELL_SIZE, (int)(CELL_SIZE/1.5), SCREEN_HEIGHT, SCREEN_WIDTH);
        screen1 = (ScreenKTU)this;
        runProgram();
    }

    
    public static void main(String[] args) throws InterruptedException 
    {
        
        new Lab1B_Individualus();
    }
    
    // Main method where all actions are done
    private void runProgram() throws InterruptedException
    {
        // Creating array
        theArray = genArray();

        // Polling selectedAction
        while(true)
        {
            createMenu();
            selectAction(selectedAction);
            selectedAction = "";
            Thread.sleep(250);
        }
        
    }
    
    // Does selected action (not best a best implementation, because
    // Objects can't be properly created int mouseClicked event)
    private static void selectAction(String algorithmString)
    {
        switch(algorithmString)
        {
            case "Bubble":
                BubbleSort s2 = new BubbleSort(screen1, theArray, TOP_OFFSET, MAX_INT, NUMBER_DIV, SWAP_DELAY);
                break;
            case "LSD":
                RadixSort s3 = new RadixSort(screen1, theArray, TOP_OFFSET, MAX_INT, NUMBER_DIV, SWAP_DELAY);
                break;
            case "Merge":
                MergeSort s4 = new MergeSort(screen1, theArray, TOP_OFFSET, MAX_INT, NUMBER_DIV, SWAP_DELAY);
                break;
            case "Heap":
                HeapSort s5 = new HeapSort(screen1, theArray, TOP_OFFSET, MAX_INT, NUMBER_DIV, SWAP_DELAY);
                break;
            case "Bitonic":
                BitonicSort s1 = new BitonicSort(screen1, theArray, TOP_OFFSET, MAX_INT, NUMBER_DIV, SWAP_DELAY);
                break;
            case "Exit":
                System.exit(0);
                break;
            default:
                System.err.println("Polling..");
        }
    }
    
    // Mouse click event changes selectedAction variable
    @Override
    public void mouseClicked(MouseEvent e) {
        int offsetXx = SCREEN_WIDTH / 2 - 90; 
        int offsetYy = SCREEN_HEIGHT / 2 - 24;
        // Getting current click position
        int r = e.getY()/cellH, c = e.getX()/cellW;
        if(offsetXx <= c && c <= offsetXx+180)
        {
            if(88 <= r)    
            {
                if(r <= offsetYy+8)
                {
                    System.err.println("Bubble");
                    selectedAction = "Bubble";
                }
                else if(r <= offsetYy + 16)
                {
                    System.err.println("LSD");
                    selectedAction = "LSD";
                }
                else if(r <= offsetYy + 24)
                {
                    System.err.println("Merge");
                    selectedAction = "Merge";
                }
                else if(r <= offsetYy + 32)
                {
                    System.err.println("Bitonic");
                    selectedAction = "Bitonic";
                }
                else if(r <= offsetYy + 40)
                {
                    System.err.println("Heap");
                    selectedAction = "Heap";
                }
                else if(r <= offsetYy + 48)
                {
                    System.err.println("EXIT");
                    selectedAction = "Exit";
                }
            }
        }
            
        refresh();
    }
    
    // A function which draws action menu
    private static void createMenu()
    {
        // Geting center pos of the screen to align menu properly
        int offsetXx = SCREEN_WIDTH / 2 - 90;
        int offsetYy = SCREEN_HEIGHT / 2 - 24;
        screen1.setBackColor(Color.GRAY);
        screen1.fillRect(offsetYy, offsetXx, 7, 180, Color.GRAY);
        screen1.print(offsetYy, offsetXx, "Bubble Sort");
        screen1.setBackColor(Color.DARK_GRAY);
        screen1.fillRect(offsetYy+8, offsetXx, 7, 180, Color.DARK_GRAY);
        screen1.print(offsetYy + 8, offsetXx, "Radix (LSD) Sort");
        screen1.setBackColor(Color.GRAY);
        screen1.fillRect(offsetYy+16, offsetXx, 7, 180, Color.GRAY);
        screen1.print(offsetYy + 16, offsetXx, "Merge Sort");
        screen1.setBackColor(Color.DARK_GRAY);
        screen1.fillRect(offsetYy+24, offsetXx, 7, 180, Color.DARK_GRAY);
        screen1.print(offsetYy + 24, offsetXx, "Bitonic Sort");
        screen1.setBackColor(Color.GRAY);
        screen1.fillRect(offsetYy+32, offsetXx, 7, 180, Color.GRAY);
        screen1.print(offsetYy + 32, offsetXx, "Heap Sort");
        screen1.setBackColor(Color.DARK_GRAY);
        screen1.fillRect(offsetYy+40, offsetXx, 7, 180, Color.DARK_GRAY);
        screen1.print(offsetYy + 40, offsetXx, "Exit (Quit)");
        screen1.refresh(0);
        screen1.setBackColor(Color.BLACK);
    }
    
    // Prints array for debbuging purposes
    private static void printArray(int[] arr)
    {
        for(int i = 0; i < arr.length; i++)
        {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    
    // A function which generates array which is constrainded
    // by screen and value (height)
    private static int[] genArray()
    {
        int numCount = SCREEN_WIDTH/NUMBER_DIV;
        int[] arr = new int[numCount];
        for(int i = 0; i <= (int)Math.ceil(numCount / (MAX_INT/NUMBER_DIV)); i++)
        {
            for(int j = 0; j < MAX_INT/NUMBER_DIV; j++)
            {
                if(i*MAX_INT/NUMBER_DIV+j <= numCount-1)
                {
                    arr[i*MAX_INT/NUMBER_DIV+j] = (j + 1) * NUMBER_DIV ;
                }
                else
                {
                    break;
                }
            }
        }
        return arr;
    }
}
