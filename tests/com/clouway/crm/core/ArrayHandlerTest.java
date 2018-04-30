package com.clouway.crm.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayHandlerTest {

    static ArrayHandler handler = new ArrayHandler();

    @Test
    void testReverseArray() {
        int[] testArr = {1, 2, 3};
        int[] testReverse = {3, 2, 1};
        assertArrayEquals(testArr, handler.reverseArray(testReverse));
    }

    @Test
    void testDoQuickSort() {
        int[] testArr = {1, 2, 3};
        int[] testSort = {1, 3, 2};
        assertArrayEquals(testArr, handler.doQuickSort(testSort));
    }

    @Test
    void testGetMinElement() {

        int[] testArr = {1, 2, 3};
        assertEquals(1, handler.getMinElement(testArr));

    }

    @Test
    void testGetSum() {
        int[] testArr = {1, 2, 3};
        assertEquals(6, handler.getSum(testArr));
    }

    @Test
    void testPrintArray() {
        int[] testArr = {1, 2, 3};
        String testStr = "123";
        assertEquals(testStr, handler.printArray(testArr));
    }
}