package utils;

import java.util.concurrent.ThreadLocalRandom;

public class Generator {

    private Generator() {
    }

    public static int get(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }
}
