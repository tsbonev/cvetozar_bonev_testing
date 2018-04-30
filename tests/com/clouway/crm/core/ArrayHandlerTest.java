package com.clouway.crm.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayHandlerTest {

    ArrayHandler handler = new ArrayHandler();

    @Test
    void reverseArray() {
        assertArrayEquals(new int[] {1, 2, 3}, handler.reverseArray(new int[] {3, 2, 1}));
    }

    @Test
    void doQuickSort() {
        int[] testArr = {1, 2, 3};
        int[] testSort = {1, 3, 2};
        assertArrayEquals(testArr, handler.doQuickSort(testSort, new int[]{}));
    }

    @Test
    void getMinElement() {

        int[] testArr = {1, 2, 3};
        assertEquals(1, handler.getMinElement(testArr));

    }

    @Test
    void getSum() {
        int[] testArr = {1, 2, 3};
        assertEquals(6, handler.getSum(testArr));
    }

    @Test
    void printArray() {
        int[] testArr = {1, 2, 3};
        String testStr = "[1, 2, 3]";
        assertEquals(testStr, handler.printArray(testArr));
    }
}