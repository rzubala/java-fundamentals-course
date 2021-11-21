package com.bobocode.fp;

/**
 * An util class that provides a factory method for creating an instance of a {@link FunctionMap} filled with a list
 * of functions.
 * <p>
 * TODO: implement a method and verify it by running {@link FunctionsTest}
 * <p>
 * TODO: if you find this exercise valuable and you want to get more like it, <a href="https://www.patreon.com/bobocode"> 
 *     please support us on Patreon</a>
 *
 * @author Taras Boychuk
 */
public class Functions {
    private Functions() {
    }

    /**
     * A static factory method that creates an integer function map with basic functions:
     * - abs (absolute value)
     * - sgn (signum function)
     * - increment
     * - decrement
     * - square
     *
     * @return an instance of {@link FunctionMap} that contains all listed functions
     */
    public static FunctionMap<Integer, Integer> intFunctionMap() {
        FunctionMap<Integer, Integer> intFunctionMap = new FunctionMap<>();

        intFunctionMap.addFunction("square", i -> i * i);
        intFunctionMap.addFunction("increment", i -> i + 1);
        intFunctionMap.addFunction("decrement", i -> i - 1);
        intFunctionMap.addFunction("abs", Math::abs);
        intFunctionMap.addFunction("sgn", i -> {
        	if (i < 0) {
        		return -1;
        	} else if (i > 0) {
        		return 1;
        	} else {
        		return 0;
        	}
        });   
        return intFunctionMap;
    }
}
