package service;

import entity.Cashier;
import entity.Customer;
import entity.Dispatcher;
import entity.Shop;
import exception.ShopException;
import utils.Generator;
import utils.Sleeper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ShopWorker extends Thread {
    public static final int THREAD_CUSTOMER_COUNT = Runtime.getRuntime().availableProcessors();
    public static final int THREAD_CASHIER_COUNT = 2;

    private final Shop shop;
    private final Dispatcher dispatcher;

    public ShopWorker(Shop shop) {
        this.shop = shop;
        dispatcher = shop.getDispatcher();
    }

    @Override
    public void run() {
        System.out.println(shop + " opened");
        ExecutorService executorCashier = Executors.newFixedThreadPool(THREAD_CASHIER_COUNT);
        for (int i = 1; i <= THREAD_CASHIER_COUNT; i++) {
            Cashier cashier = new Cashier(i);
            CashierWorker cashierWorker = new CashierWorker(cashier, shop);
            executorCashier.execute(cashierWorker);
        }
        executorCashier.shutdown();

        ExecutorService executorCustomer = Executors.newFixedThreadPool(THREAD_CUSTOMER_COUNT);
        int counter = 0;
        while (dispatcher.isOpen()) {
            int countPerSecond = Generator.get(1, 4);
            for (int i = 0; dispatcher.isOpen() && i < countPerSecond; i++) {
                Customer customer = new Customer(++counter);
                CustomerWorker customerWorker = new CustomerWorker(customer, shop);
                executorCustomer.execute(customerWorker);
            }
            Sleeper.sleep(1000);
        }
        executorCustomer.shutdown();
        try {
            if (executorCashier.awaitTermination(1, TimeUnit.DAYS)) {
                System.out.println(shop + " closed");
            }
        } catch (InterruptedException e) {
            throw new ShopException(e);
        }
    }
}
