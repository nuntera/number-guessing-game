package com.mindera.mindswap;

/**
 * Utility class for generating random numbers
 */
public class RandomGenerator {

    /**
     * Generates a random number between min and max (inclusive)
     * @param min Minimum possible value
     * @param max Maximum possible value
     * @return Random number between min and max
     */
    public static int generate(int min, int max){
        return (int) (Math.random() * (max - min + 1) + min);
    }
}
