
package src;

import java.util.Objects;

public abstract class BaseEntity {

    private final long id;
    private final String name;

    protected BaseEntity(long id, String name) {
        if (id <= 0) {
            throw new IllegalArgumentException(
                    "ID повинен бути більшим за 0."
            );
        }

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Ім'я не може бути порожнім."
            );
        }

        this.id = id;
        this.name = name.trim();

        EntityIdGenerator.observe(id);
    }

    public final long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        BaseEntity other = (BaseEntity) object;
        return id == other.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), id);
    }

    @Override
    public String toString() {
        return "ID: " + id + ", ім'я: " + name;
    }
}
