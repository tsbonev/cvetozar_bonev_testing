package com.clouway.crm.core.Sumator;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Sumator {

    public int sum(int a, int b){
        return  a + b;
    }

    public double sum(double a, double b){
        return a + b;
    }

    public String sum(String a, String b)
            throws NumberFormatException {

        boolean success = false;
        String res = "";

        try {
            int A = Integer.parseInt(a);
            int B = Integer.parseInt(b);
            res = Integer.toString(A + B);
            success = true;
            return res;
        }
        catch (NumberFormatException e){
            throw e;
        }

    }

    /**
     * Sums two big integers
     * @param a first bigint to sum
     * @param b second bigint to sum
     * @return sum of the big integers
     */
    public BigInteger sum(BigInteger a, BigInteger b){
        return a.add(b);
    }

    /**
     * Sums two big decimal
     * @param a first bigdec to sum
     * @param b second bigdec to sum
     * @return sum of the big decimals
     */
    public BigDecimal sum(BigDecimal a, BigDecimal b){
        return a.add(b);
    }

}
