package by.epam.lab;

import by.epam.lab.exceptions.BynValueException;

import java.util.Objects;

public class Byn implements Comparable<Byn> {
    public static final Byn ZERO = new Byn(0);
    private final int value;

    public Byn(int value) {
        if (value < 0) {
            throw new BynValueException(value / 100, value % 100);
        }
        this.value = value;
    }

    public Byn(String value) {
        this(Integer.parseInt(value));
    }

    public Byn(int rubs, int coins) {
        this(rubs * 100 + coins);
        if (rubs < 0 || coins < 0 || coins >= 100) {
            throw new BynValueException(rubs, coins);
        }
    }

    public Byn(Byn byn) {
        this(byn.value);
    }

    public Byn add(Byn other) {
        return new Byn(value + other.value);
    }

    public Byn sub(Byn other) {
        return new Byn(value - other.value);
    }

    public Byn mul(int factor) {
        return new Byn(value * factor);
    }

    public Byn mul(double factor, RoundMethod roundMethod, int d) {
        return new Byn(roundMethod.round(value * factor, d));
    }

    public Byn round(RoundMethod roundMethod, int d) {
        return new Byn(roundMethod.round(value, d));
    }

    @Override
    public String toString() {
        return String.format("%d.%02d", value / 100, value % 100);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Byn byn = (Byn) o;
        return value == byn.value;
    }

    @Override
    public int compareTo(Byn o) {
        return value - o.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
