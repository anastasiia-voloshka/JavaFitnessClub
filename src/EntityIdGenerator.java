
package src;

import java.util.concurrent.atomic.AtomicLong;

public final class EntityIdGenerator {

    private static final AtomicLong NEXT_ID = new AtomicLong(1);

    private EntityIdGenerator() {
    }

    public static long nextId() {
        return NEXT_ID.getAndIncrement();
    }

    public static void observe(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException(
                    "ID повинен бути більшим за 0."
            );
        }

        NEXT_ID.updateAndGet(current -> Math.max(current, id + 1));
    }
}
