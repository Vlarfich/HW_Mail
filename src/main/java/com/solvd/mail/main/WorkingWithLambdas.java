package com.solvd.mail.main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.*;
import java.util.random.RandomGenerator;

public class WorkingWithLambdas {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("HELLO");
        list.add("HOW ARE YOU");
        list.add(null);
        list.addAll(List.of("HI", "YO", "WHAT'S UP"));
        list.add("HEHEHEHE");
        list.forEach(System.out::println);

        // Print Consumer
        Consumer<Object> print = System.out::println;
        list.forEach(print);

        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            numbers.add(RandomGenerator.getDefault().nextInt(20000));
        }
        numbers.forEach(print);
        System.out.println();

        // Print Module Consumer
        Consumer<Integer> abs = x -> System.out.print(Math.abs(x) + " ");
        numbers.forEach(abs);
        System.out.println();

        // Print *2 Consumer
        Consumer<Integer> timesTwo = x -> System.out.print(x * 2 + " ");
        numbers.forEach(timesTwo);

        // Sum  Function
        AtomicInteger sum = new AtomicInteger();
        Function<Integer, Integer> summing = sum::addAndGet;
        numbers.forEach(summing::apply);
        System.out.println("\nSum = " + sum.get());


        // String length Function
        Function<String, Integer> func = String::length;
        String s = String.valueOf(RandomGenerator.getDefault().nextInt());
        System.out.println("\n" + s + "  ---  " + func.apply(s));


        // Function  ^2
        Function<Integer, Double> square = Math::sqrt;

        // String Concat Function
        BiFunction<String, String, String> conc = String::concat;
        System.out.println(conc.apply("HI, ", "User"));

        double root = func.andThen(square).apply(s);
        System.out.println(root + "   " + Math.pow(root, 2));


        // My lambda interfaces

        IHash<Object> objectIHash = Object::hashCode;
        System.out.println(objectIHash.getHash("HOHOHO"));

        IClassName<Object> name = x -> (x != null) ? x.getClass().getSimpleName() : null;

        System.out.println(name.getClassName("null") + "  " + name.getClassName(new WorkingWithFiles()) + " " + name.getClassName(null));

        IUpper<Object> ref = (x) -> (x != null) ? x.toString().toUpperCase() : "NULL";
        System.out.println(ref.upper(null) + "  " + ref.upper("haASDfgesgs213AS") + " " + ref.upper(PostOfficeGenerator.getPilot()));


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
