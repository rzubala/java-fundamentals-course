package com.bobocode.fp;

import java.math.BigDecimal;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.IntUnaryOperator;
import java.util.function.LongBinaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import com.bobocode.util.ExerciseNotCompletedException;

/**
 * {@link CrazyLambdas} is an exercise class. Each method returns a functional interface and it should be implemented
 * using either lambda or a method reference. Every method that is not implemented yet throws
 * {@link ExerciseNotCompletedException}.
 * <p>
 * TODO: remove exception and implement each method of this class using lambda or method reference
 * <p>
 * TODO: if you find this exercise valuable and you want to get more like it,
 * <a href="https://www.patreon.com/bobocode">please support us on Patreon</a>
 *
 * @author Taras Boychuk
 */
public class CrazyLambdas {

    /**
     * Returns {@link Supplier} that always supply "Hello"
     *
     * @return a string supplier
     */
    public static Supplier<String> helloSupplier() {
        return () -> "Hello";
    }

    /**
     * Returns a {@link Predicate} of string that checks if string is empty
     *
     * @return a string predicate
     */
    public static Predicate<String> isEmptyPredicate() {
        return String::isEmpty;
    }

    /**
     * Return a {@link Function} that accepts {@link String} and returns that string repeated n time, where n is passed
     * as function argument
     *
     * @return function that repeats Strings
     */
    public static BiFunction<String, Integer, String> stringMultiplier() {
    	return (value, n) -> Stream.iterate(value, (i) -> value).limit(n).reduce((a, i) -> a + i).orElse("");
    }

    /**
     * Returns a {@link Function} that converts a {@link BigDecimal} number into a {@link String} that start with
     * a dollar sign and then gets a value
     *
     * @return function that converts adds dollar sign
     */
    public static Function<BigDecimal, String> toDollarStringFunction() {
        return (value) -> "$" + value;
    }

    /**
     * Receives two parameter that represent a range and returns a {@link Predicate<String>} that verifies if string
     * length is in the specified range. E.g. min <= length < max
     *
     * @param min min length
     * @param max max length
     * @return a string predicate
     */
    public static Predicate<String> lengthInRangePredicate(int min, int max) {
        return (value) -> value.length() >= min && value.length() < max;
    }

    /**
     * Returns a {@link Supplier} of random integers
     *
     * @return int supplier
     */
    public static IntSupplier randomIntSupplier() {
        return () -> (int)(Math.random()*100);
    }

    /**
     * Returns an {@link IntUnaryOperator} that receives an int as a bound parameter, and returns a random int
     *
     * @return int operation
     */
    public static IntUnaryOperator boundedRandomIntSupplier() {
        return b -> (int)(Math.random() * b);
    }

    /**
     * Returns {@link IntUnaryOperator} that calculates an integer square
     *
     * @return square operation
     */
    public static IntUnaryOperator intSquareOperation() {
        return i -> i * i;
    }

    /**
     * Returns a {@link LongBinaryOperator} sum operation.
     *
     * @return binary sum operation
     */
    public static LongBinaryOperator longSumOperation() {
        return (a, b) -> a + b;
    }

    /**
     * Returns a {@link ToIntFunction<String>} that converts string to integer.
     *
     * @return string to int converter
     */
    public static ToIntFunction<String> stringToIntConverter() {
        return Integer::valueOf;
    }

    /**
     * Receives int parameter n, and returns a {@link Supplier} that supplies {@link IntUnaryOperator}
     * that is a function f(x) = n * x
     *
     * @param n a multiplier
     * @return a function supplier
     */
    public static Supplier<IntUnaryOperator> nMultiplyFunctionSupplier(int n) {
        return () -> x -> n*x;	
    }

    /**
     * Returns a {@link UnaryOperator} that accepts str to str function and returns the same function composed with trim
     *
     * @return function that composes functions with trim() function
     */
    public static UnaryOperator<Function<String, String>> composeWithTrimFunction() {
        return f -> s -> f.apply(s.trim());
    }

    /**
     * Receives a {@link Runnable} parameter, and returns a {@link Supplier<Thread>}. The thread will be started only
     * when you call supplier method {@link Supplier#get()}
     *
     * @param runnable the code you want to tun in new thread
     * @return a thread supplier
     */
    public static Supplier<Thread> runningThreadSupplier(Runnable runnable) {
        //throw new ExerciseNotCompletedException();
    	return () -> {
    		Thread t = new Thread(runnable);
    		t.start();
    		return t;
    	};
    }

    /**
     * Returns a {@link Consumer} that accepts {@link Runnable} as a parameter and runs in a new thread.
     *
     * @return a runnable consumer
     */
    public static Consumer<Runnable> newThreadRunnableConsumer() {
    	return r -> new Thread(r).start();
    }

    /**
     * Returns a {@link Function} that accepts an instance of {@link Runnable} and returns a {@link Supplier} of a
     * started {@link Thread} that is created from a given {@link Runnable}
     *
     * @return a function that transforms runnable into a thread supplier
     */
    public static Function<Runnable, Supplier<Thread>> runnableToThreadSupplierFunction() {
    	return r -> () -> {
    		Thread t = new Thread(r);
    		t.start();
    		return t;
    	};
    }

    /**
     * Returns a {@link BiFunction} that has two parameters. First is {@link IntUnaryOperator} which is some integer function.
     * Second is {@link IntPredicate} which is some integer condition. And the third is {@link IntUnaryOperator} which is
     * a new composed function that uses provided predicate (second parameter of binary function) to verify its input
     * parameter. If predicate returns {@code true} it applies a provided integer function
     * (first parameter of binary function) and returns a result value, otherwise it returns an element itself.
     *
     * @return a binary function that receiver predicate and function and compose them to create a new function
     */
    public static BiFunction<IntUnaryOperator, IntPredicate, IntUnaryOperator> functionToConditionalFunction() {
    	return (f, p) -> x -> p.test(x) ? f.applyAsInt(x) : x;
    }

    /**
     * Returns a {@link BiFunction} which first parameter is a {@link Map} where key is a function name, and value is some
     * {@link IntUnaryOperator}, and second parameter is a {@link String} which is a function name. If the map contains a
     * function by a given name then it is returned by high order function otherwise an identity() is returned.
     *
     * @return a high-order function that fetches a function from a function map by a given name or returns identity()
     */
    public static BiFunction<Map<String, IntUnaryOperator>, String, IntUnaryOperator> functionLoader() {
    	return (map, fname) -> x -> map.getOrDefault(fname, IntUnaryOperator.identity()).applyAsInt(x);
    }

    /**
     * Returns {@link Supplier} of {@link Supplier} of {@link Supplier} of {@link String} "WELL DONE!".
     *
     * @return a supplier instance
     */
    public static Supplier<Supplier<Supplier<String>>> trickyWellDoneSupplier() {
        return () -> () -> () -> "WELL DONE!";
    }
}

