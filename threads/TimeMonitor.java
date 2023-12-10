package threads;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimeMonitor {
    public static void main(String[] args) {
        Instant startDate = Instant.now();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

        scheduler.scheduleAtFixedRate(() -> {
            StringBuilder message = new StringBuilder();
            Duration duration = Duration.between(startDate, Instant.now());
            if (duration.toHoursPart() != 0) {
                message.append(String.format("%d days, ", duration.toHoursPart()));
            }
            if (duration.toMinutesPart() != 0) {
                message.append(String.format("%d min, ", duration.toMinutesPart()));
            }
            if (duration.toSecondsPart() != 0) {
                message.append(String.format("%d seconds ", duration.toSecondsPart()));
            }

            message.append("was gone after program start");

            System.out.println(message.toString());
        },1, 1, TimeUnit.SECONDS);

        scheduler.scheduleAtFixedRate(
                () -> System.out.println("Минуло 5 секунд"),
                5,
                5,
                TimeUnit.SECONDS
        );
    }
}
