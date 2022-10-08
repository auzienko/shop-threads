package service;

import entity.*;
import utils.Generator;
import utils.Sleeper;

import java.util.Optional;

public class CashierWorker implements Runnable, CashierAction {

    private static final int MIN_SERVICE = 3000;
    private static final int MAX_SERVICE = 5000;
    private final Cashier cashier;
    private final Shop shop;
    private final Dispatcher dispatcher;
    private final QueueCustomer queueCustomer;

    public CashierWorker(Cashier cashier, Shop shop) {
        this.cashier = cashier;
        this.shop = shop;
        dispatcher = shop.getDispatcher();
        queueCustomer = shop.getQueueCustomer();
    }

    @Override
    public void run() {
        System.out.println(cashier + " opened");
        while (!dispatcher.isComplete()) {
            Optional<Customer> optionalCustomer = queueCustomer.poll();
            if (optionalCustomer.isPresent()) {
                Customer customer = optionalCustomer.get();
                System.out.println(cashier + " started service of " + customer);
                int timeout = Generator.get(MIN_SERVICE, MAX_SERVICE);
                Sleeper.sleep(timeout);
                System.out.println(cashier + " finished service of " + customer);
                synchronized (customer.getMonitor()) {
                    customer.setWaitFlag(false);
                    customer.getMonitor().notify();
                }
            } else {
                Thread.onSpinWait();
            }
        }
        System.out.println(cashier + " closed");
    }
}
