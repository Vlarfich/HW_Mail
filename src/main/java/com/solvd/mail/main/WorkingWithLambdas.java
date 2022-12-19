package com.solvd.mail.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.*;
import java.util.random.RandomGenerator;

public class WorkingWithLambdas {
    public static void main(String[] args) {
        Logger logger = LogManager.getLogger(WorkingWithLambdas.class);

        List<String> list = new ArrayList<>();
        list.add("HELLO");
        list.add("HOW ARE YOU");
        list.add(null);
        list.addAll(List.of("HI", "YO", "WHAT'S UP"));
        list.add("HEHEHEHE");
        list.forEach(logger::info);

        // Print Consumer
        Consumer<Object> print = logger::info;
        list.forEach(print);
        logger.info("\n");

        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            numbers.add(RandomGenerator.getDefault().nextInt(20000));
        }
        numbers.forEach(print);
        logger.info("\n");

        // Print Module Consumer
        Consumer<Integer> abs = x -> logger.info(Math.abs(x) + " ");
        numbers.forEach(abs);
        logger.info("\n");

        // Print *2 Consumer
        Consumer<Integer> timesTwo = x -> logger.info(x * 2 + " ");
        numbers.forEach(timesTwo);

        // Sum  Function
        AtomicInteger sum = new AtomicInteger();
        Function<Integer, Integer> summing = sum::addAndGet;
        numbers.forEach(summing::apply);
        logger.info("Sum = " + sum.get());


        // String length Function
        Function<String, Integer> func = String::length;
        String s = String.valueOf(RandomGenerator.getDefault().nextInt());
        logger.info(s + "  ---  " + func.apply(s));


        // Function  ^2
        Function<Integer, Double> square = Math::sqrt;

        // String Concat Function
        BiFunction<String, String, String> conc = String::concat;
        logger.info(conc.apply("HI, ", "User"));

        double root = func.andThen(square).apply(s);
        logger.info(root + "   " + Math.pow(root, 2));


        // My lambda interfaces

        IHash<Object> objectIHash = Object::hashCode;
        logger.info(objectIHash.getHash("HOHOHO"));

        IClassName<Object> name = x -> (x != null) ? x.getClass().getSimpleName() : null;

        logger.info(name.getClassName("null") + "  " + name.getClassName(new WorkingWithFiles()) + " " + name.getClassName(null));

        IUpper<Object> ref = (x) -> (x != null) ? x.toString().toUpperCase() : "NULL";
        logger.info(ref.upper(null) + "  " + ref.upper("haASDfgesgs213AS") + " " + ref.upper(PostOfficeGenerator.getPilot()));


    }

    @FunctionalInterface
    public interface IHash<T> {
        int getHash(T t);
    }

    @FunctionalInterface
    public interface IClassName<T> {
        String getClassName(T t);
    }

    @FunctionalInterface
    public interface IUpper<T> {
        String upper(T t);
    }

}
