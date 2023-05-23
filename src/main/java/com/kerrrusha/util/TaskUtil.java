package com.kerrrusha.util;

import com.kerrrusha.model.ScheduleElement;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static java.util.stream.Collectors.toList;

public class TaskUtil {

    public static int getRandomInt(int fromInclusive, int toExclusive) {
        return new Random().nextInt(toExclusive - fromInclusive) + fromInclusive;
    }

    public static String factorial(int n) {
        if (n <= 12) {
            return ""+factorialInt(n);
        }
        if (n <= 19) {
            return ""+factorialLong(n);
        }
        return factorialBigInteger(n).toString();
    }

    public static int factorialInt(int n) {
        return (n == 0)
                ? 1
                : (n * factorialInt(n - 1));
    }

    public static long factorialLong(long n) {
        return (n == 0)
                ? 1
                : (n * factorialLong(n - 1));
    }

    public static BigInteger factorialBigInteger(int n)
    {
        BigInteger result = BigInteger.valueOf(1);
        for (int i = 2; i <= n; i++){
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    public static <R> int getElementEntriesCount(List<R> list, R element) {
        return (int) list
                .stream()
                .filter(e -> Objects.equals(e, element))
                .count();
    }

    public static int getDuplicatingTimeValue(List<ScheduleElement> list) {
        List<Integer> timeValues = list
                .stream()
                .map(ScheduleElement::getT)
                .collect(toList());
        return timeValues
                .stream()
                .filter(e -> getElementEntriesCount(timeValues, e) > 1)
                .findFirst().orElse(Integer.MIN_VALUE);
    }

}
