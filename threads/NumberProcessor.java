package threads;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

public class NumberProcessor extends Thread {

    private boolean isAlive = true;

    private volatile AtomicBoolean numberProcessed = new AtomicBoolean(true);

    private int number;

    private final Consumer<Integer> op;

    public NumberProcessor(Consumer<Integer> op) {
        this.op = op;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean isNumberProcessed() {
        return numberProcessed.get();
    }

    public void setNumber(int number) {
        this.number = number;
        this.numberProcessed.set(false);
    }

    @Override
    public void run() {
        while (isAlive) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (numberProcessed.get()) {
                continue;
            }
            op.accept(number);
            numberProcessed.set(true);
        }
    }
}
