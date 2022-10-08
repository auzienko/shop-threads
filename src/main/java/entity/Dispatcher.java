package entity;

import java.util.concurrent.atomic.AtomicInteger;

public class Dispatcher {
    private final int plan;
    private final AtomicInteger inCount = new AtomicInteger(0);
    private final AtomicInteger outCount = new AtomicInteger(0);

    public Dispatcher(int plan) {
        this.plan = plan;
    }

    public boolean isOpen() {
        return inCount.get() < plan;
    }

    public boolean isComplete() {
        return outCount.get() == plan;
    }

    public void addCustomer() {
        inCount.getAndIncrement();
    }

    public void leaveCustomer() {
        outCount.getAndIncrement();
    }
}
