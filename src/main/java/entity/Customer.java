package entity;

import java.util.Objects;

public class Customer {
    private final String name;
    private boolean waitFlag;

    public Customer(int number) {
        this.name = "Customer %d".formatted(number);
        waitFlag = false;
    }

    public String getName() {
        return name;
    }

    public Object getMonitor() {
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        return Objects.equals(name, customer.name);
    }

    public boolean isWaitFlag() {
        return waitFlag;
    }

    public void setWaitFlag(boolean waitFlag) {
        this.waitFlag = waitFlag;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                '}';
    }
}
