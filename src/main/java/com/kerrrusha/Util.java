package com.kerrrusha;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Util {

    public static int getRandomInt(int fromInclusive, int toExclusive) {
        return new Random().nextInt(toExclusive - fromInclusive) + fromInclusive;
    }

    public static int factorial(int n) {
        return (n == 0)
                ? 1
                : (n * factorial(n - 1));
    }

    public static <R> int getElementEntriesCount(List<R> list, R element) {
        return (int) list
                .stream()
                .filter(e -> Objects.equals(e, element))
                .count();
    }

}
