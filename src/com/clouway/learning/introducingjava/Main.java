package com.clouway.learning.introducingjava;

import com.clouway.crm.core.ArrayHandler;

import java.util.concurrent.ThreadLocalRandom;

public class Main {

    /**
     * Calculates the largest common divisor
     * @param a first number to be calculated
     * @param b second number to be calculated
     * @return the largest common divisor
     */
    public static int CommonDivisor(int a, int b){

        if(a == b){
            return a;
        }

        if(a > b){
            a -= b;
        }
        else{
            b -= a;
        }

        return CommonDivisor(a, b);
    }

    /**
     * Calculates the smallest common multiple of two numbers
     * @param a first number to calculate
     * @param b second number to calculate
     * @return the smallest common multiple of the numbers
     */
    public static int SmallestCommonMultiple(int a, int b){

        int divisor = CommonDivisor(a, b);

        if(divisor == a){
            return a;
        }
        else if (divisor == b){
            return b;
        }

        return (a / divisor) * (b / divisor);

    }

    /**
     * Creates a random string in the char range of 33 - 126 for a given length
     * @param len the length of the string
     * @return the randomized string
     */
    public static String RandomString (int len){
        char[] charArray = new char[len];

        for (int i = 0; i < len; i++){
            charArray[i] = (char)ThreadLocalRandom.current().nextInt(33, 126);
        }

        String randomString = new String(charArray);

        return randomString;
    }


    public static void main(String[] args) {

        int result = CommonDivisor(12, 4);

        System.out.println(result);

        result = SmallestCommonMultiple(5, 10);

        System.out.println(result);

        int[] array = {1, 6, 4, 1, 0, 8};
        ArrayHandler handler = new ArrayHandler();

        handler.printArray(array);

        int min = handler.getMinElement(array);
        System.out.println(min);

        int sum = handler.getSum(array);
        System.out.println(sum);

        handler.printArray(handler.doQuickSort(array));

        handler.printArray(handler.reverseArray(array));

        System.out.println(RandomString(15));
        System.out.println(RandomString(10));
        System.out.println(RandomString(4));

    }
}
