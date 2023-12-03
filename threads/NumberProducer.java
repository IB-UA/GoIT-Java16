package threads;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class NumberProducer {
    private static final Queue<String> queue = new LinkedBlockingQueue<>();
    private static final AtomicBoolean aBool = new AtomicBoolean(false);
    private static void fizz(int i) {
        if (i % 3 == 0 && i % 5 != 0) {
            queue.add("fizz");
            aBool.set(true);
        }
    }

    private static void buzz(int i) {
        if (i % 3 != 0 && i % 5 == 0) {
            queue.add("buzz");
            aBool.set(true);
        }
    }

    private static void fizzbuzz(int i) {
        if (i % 3 == 0 && i % 5 == 0) {
            queue.add("fizzbuzz");
            aBool.set(true);
        }
    }

    private static void number() {
        List<Object> inactivityList = new ArrayList<>();
        while (inactivityList.size() < 10) {
            try {
                Thread.sleep(100);
                if (queue.isEmpty()) {
                    inactivityList.add(null);
                } else {
                    inactivityList.clear();
                }
                while (!queue.isEmpty()) {
                    System.out.println(queue.poll());
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);

        List<NumberProcessor> threads = List.of(
                new NumberProcessor(NumberProducer::fizz),
                new NumberProcessor(NumberProducer::buzz),
                new NumberProcessor(NumberProducer::fizzbuzz)
        );

        new Thread(NumberProducer::number).start();

        for (NumberProcessor t: threads) {
            t.start();
        }

        for (int i = 1; i <= n; i++) {
            aBool.set(false);
            for (NumberProcessor t: threads) {
                t.setNumber(i);
            }
            while (!threads.stream().allMatch(NumberProcessor::isNumberProcessed)) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            if (!aBool.get()) {
                queue.add(Integer.toString(i));
            }
        }

        for (NumberProcessor t: threads) {
            t.setAlive(false);
        }
    }
}
