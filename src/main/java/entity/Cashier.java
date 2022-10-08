package entity;

import java.util.Objects;

public class Cashier {
    private final String name;

    public Cashier(int number) {
        this.name = "\tCashier %d".formatted(number);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cashier cashier = (Cashier) o;

        return Objects.equals(name, cashier.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Cashier{" +
                "name='" + name + '\'' +
                '}';
    }
}
