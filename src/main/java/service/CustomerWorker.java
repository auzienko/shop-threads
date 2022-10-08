package service;

import entity.Customer;
import entity.Dispatcher;
import entity.QueueCustomer;
import entity.Shop;
import exception.ShopException;
import utils.Generator;
import utils.Sleeper;

public class CustomerWorker implements Runnable, CustomerAction {

    private final Customer customer;
    private final Shop shop;
    private final Dispatcher dispatcher;
    private final QueueCustomer queueCustomer;

    public CustomerWorker(Customer customer, Shop shop) {
        this.customer = customer;
        this.shop = shop;
        dispatcher = shop.getDispatcher();
        dispatcher.addCustomer();
        queueCustomer = shop.getQueueCustomer();
    }

    @Override
    public void run() {
        inToShop();
        chooseGoods();
        goToQueue();
        leaveShop();
        dispatcher.leaveCustomer();
    }

    @Override
    public void inToShop() {
        System.out.println(customer + " in to " + shop);
    }

    @Override
    public void chooseGoods() {
        System.out.println(customer + " started to choose goods");
        int chooseTimeout = Generator.get(2000, 3000);
        Sleeper.sleep(chooseTimeout);
        System.out.println(customer + " finished to choose goods");
    }

    @Override
    public void goToQueue() {
        Object monitor = customer.getMonitor();
        synchronized (monitor) {
            System.out.println(customer + " go to the queue");
            queueCustomer.add(customer);
            customer.setWaitFlag(true);
            try {
                while (customer.isWaitFlag()) {
                    monitor.wait();
                }
            } catch (InterruptedException e) {
                throw new ShopException(e);
            }
            System.out.println(customer + " leaved the queue");
        }
    }

    @Override
    public void leaveShop() {
        System.out.println(customer + " leaved " + shop);
    }
}
