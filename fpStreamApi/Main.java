package fpStreamApi;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    private static class RandomAlg {
        private final long a;
        private final long m;
        private long c;

        private AtomicLong x;

        private RandomAlg(long a, long m) {
            this.a = a;
            this.m = m;
        }

        public RandomAlg withSeed(long c) {
            this.c = c;
            x = new AtomicLong(c);
            return this;
        }

        public long next() {
            return (a * x.getAndIncrement() + c) % m;
        }
    }

    public static String eachOddToString(List<String> list) {
        return IntStream.range(0, list.size())
                .filter(i -> i % 2 != 0)
                .mapToObj(i -> String.format("%d. %s", i, list.get(i)))
                .collect(Collectors.joining(", "));

    }

    public static List<String> toUpperCaseAndSortReverse(List<String> list) {
        return list.stream()
                .map(String::toUpperCase)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    public static String sortNumbersInStrings(String[] nums) {
        final String delimiter = ", ";
        return Arrays.stream(nums)
                .flatMap(s -> Arrays.stream(s.split(delimiter)))
                .map(Integer::parseInt)
                .sorted()
                .map(Object::toString)
                .collect(Collectors.joining(delimiter));
    }

    public static Stream<Long> getLinearCongruentGenerator(long a, long c, long m) {
        RandomAlg ra = new RandomAlg(a, m);
        return  Stream.iterate(c, (seed) -> ra.withSeed(seed).next());
    }

    public static <T> Stream<T> zipStreams(Stream<T> firstStream, Stream<T> secondStream) {
        Iterator<T> firstIterator = firstStream.iterator();
        Iterator<T> secondIterator = secondStream.iterator();
        Stream.Builder<T> result = Stream.builder();

        while (firstIterator.hasNext() && secondIterator.hasNext()) {
            result.add(firstIterator.next()).add(secondIterator.next());
        }

        return result.build();
    }

    public static void main(String[] args) {
        List<String> list = List.of("foo", "buzz", "bill", "each");
        System.out.println(eachOddToString(list));
        System.out.println(toUpperCaseAndSortReverse(list));
        System.out.println(sortNumbersInStrings(new String[]{"1, 2, 0", "4, 5"}));

        System.out.println(
                zipStreams(
                        Stream.of(1, 8),
                        Stream.of(3, 4, 5, 7)
                ).collect(Collectors.toList())
        );
        System.out.println(
                getLinearCongruentGenerator(
                        25214903917L,
                        11,
                        2 ^ 48
                )
                        .limit(10)
                        .collect(Collectors.toList())
        );
    }
}
