package com.clouway.crm.core.Tree;

public class QuickSorter {

    int[] returnArray;
    int currentIndex;

    /**
     * Constructs a QuckSorter class and sorts the given array
     * @param arr the array which to sort
     */
    public QuickSorter(int[] arr){
        doQuickSort(arr);
    }



    /**
     * Exchanges two values of two given elements
     * @param i the first element to exchange
     * @param j the second element to exchange
     */
    private void exchange(int i, int j){
        int temp = returnArray[i];
        returnArray[i] = returnArray[j];
        returnArray[j] = temp;
    }

    /**
     * Sorts an area of an array
     * @param low beginning of area or left wall
     * @param high end of area or right wall
     */
    private void quickSort(int low, int high){
        int i = low, j = high;
        int pivot = returnArray[low + (high - low)/2];

        while (i <= j){ //stop if the left and right indexes meet
            while (returnArray[i] < pivot){ //stops at first index that is bigger than the pivot and to the left of it
                i++;
            }
            while (returnArray[j] > pivot){ //stop at first index that is smaller than the pivot and to the right of it
                j--;
            }

            if(i <= j){ //once the indexes of the two candidates for exchanging are found exchange them
                exchange(i, j);
                i++; //move the index forward
                j--; //move the index back
            }

            if(low < j){ //first partition stops when it reaches the left wall + 1
                quickSort(low, j);
            }
            if(i < high){ //second partition stops when it reaches the right wall - 1
                quickSort(i, high);
            }

        }
    }

    /**
     * Begins the quickSort recursion and returns the private copy of the array
     * @param array the array passed into the sortArray method
     * @return the sorted array
     */
    public int[] doQuickSort(int[] array){
        this.returnArray = array;
        currentIndex = array.length;
        quickSort(0, currentIndex - 1);
        return returnArray;
    }

}
