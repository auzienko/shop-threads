package entity;

public class Shop {
    private final String name;
    private final Dispatcher dispatcher;
    private final QueueCustomer queueCustomer;

    public Shop(String name, Dispatcher dispatcher, QueueCustomer queueCustomer) {
        this.name = name;
        this.dispatcher = dispatcher;
        this.queueCustomer = queueCustomer;
    }

    public String getName() {
        return name;
    }

    public Dispatcher getDispatcher() {
        return dispatcher;
    }

    public QueueCustomer getQueueCustomer() {
        return queueCustomer;
    }

    @Override
    public String toString() {
        return "Shop %s".formatted(name);
    }
}
