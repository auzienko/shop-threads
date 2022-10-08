package entity;

import exception.ShopException;

import java.util.Optional;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class QueueCustomer {
    private final BlockingDeque<Customer> queue = new LinkedBlockingDeque<>(10);

    public void add(Customer customer) {
        try {
            queue.put(customer);
        } catch (InterruptedException e) {
            throw new ShopException(e);
        }
    }

    public Optional<Customer> poll() {
        return Optional.ofNullable(queue.poll());
    }
}
