package com.example.factorization;

import java.math.BigInteger;
import java.util.ArrayList;

public class Factorization {
    long startTime;
    long maxTime;
    boolean is;

    Factorization(long time){
        maxTime = time;
        is = true;
    }

    public ArrayList<Long> fermaCalculate(long n) {
        ArrayList<Long> res = new ArrayList<>();

        if (is){
            startTime =  System.currentTimeMillis();
            is = false;
        }

        if (System.currentTimeMillis() - startTime > maxTime){
            return null;
        }

        if(isPrime(n)) {
            res.add(n);
            return res;
        }

        long x = (long)Math.sqrt(n);

        while (Math.sqrt(x*x - n) != (long) Math.sqrt(x*x - n)) {
            x++;
        }

        long y = (long)Math.sqrt(x*x - n);

        ArrayList<Long> r1 = fermaCalculate(x + y);
        if (r1 != null)res.addAll(r1);
        else return null;

        ArrayList<Long> r2 = fermaCalculate(x - y);
        if (r1 != null)res.addAll(r1);
        else return null;

        //res.addAll(fermaCalculate(x + y));
        //res.addAll(fermaCalculate(x - y));

        return res;
    }

    private boolean isPrime(long n) {
        BigInteger bigInteger = BigInteger.valueOf(n);
        return bigInteger.isProbablePrime((int) Math.log(n));
    }
}