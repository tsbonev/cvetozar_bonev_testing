package com.clouway.crm.core;

public class ArrayHandler {

    private int[] returnArray; //handler's own copy of an array
    private int currentIndex; // handler's own value of an index

    /**
     * Reverses a given array
     * @param array the array to reverse
     * @return reversed array
     */
    public int[] reverseArray(int[]array){

        for (int i = 0; i < array.length/2; i++){
            int temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
        return array;
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
        this.returnArray = array.clone();
        currentIndex = array.length;
        quickSort(0, currentIndex - 1);
        return returnArray;
    }



    /**
     * Finds the smallest element in the array
     * @param array the array to find the smalles element in
     * @return the smallest element
     */
    public int getMinElement(int[] array){

        int smallest = Integer.MAX_VALUE;

        for (int i : array) {
            if(smallest > i) smallest = i;
        }

        return smallest;
    }

    /**
     * Sums the elements of an array
     * @param array the array whose elements to sum
     * @return the sum of the array
     */
    public int getSum(int[] array){

        int sum = 0;

        for (int i : array) {
            sum += i;
        }

        return sum;
    }

    /**
     * Prints out the elements of the array on one line
     * @param array the array to be printed
     */
    public String printArray(int [] array){

        String result = "";
        for (int i : array) {
            result += i;
        }
        return result;
    }

}
