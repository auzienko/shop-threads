package utils;

import exception.ShopException;

public class Sleeper {
    private static final int SCALE = 100;

    private Sleeper() {
    }

    public static void sleep(int timeout) {
        try {
            Thread.sleep(timeout / SCALE);
        } catch (InterruptedException e) {
            throw new ShopException(e);
        }
    }
}
