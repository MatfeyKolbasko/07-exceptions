package by.epam.lab;

import by.epam.lab.exceptions.AmountOfPurchasedUnits;
import by.epam.lab.exceptions.PurchaseCostException;
import by.epam.lab.exceptions.PurchaseNameException;

import java.util.Objects;

public class Purchase implements Comparable<Purchase> {
    private final String name;
    private final Byn byn;
    private final int amountOfPurchasedUnits;

    public Purchase(String name, Byn byn, int amountOfPurchasedUnits) {
        if (name.trim().isEmpty()) {
            throw new PurchaseNameException(name);
        }
        if (byn.compareTo(Byn.ZERO) == 0) {
            throw new PurchaseCostException(byn);
        }
        if (amountOfPurchasedUnits <= 0) {
            throw new AmountOfPurchasedUnits(amountOfPurchasedUnits);
        }
        this.name = name;
        this.byn = byn;
        this.amountOfPurchasedUnits = amountOfPurchasedUnits;
    }

    public Purchase(String[] fields) {
        this(
                fields[0],
                new Byn(fields[1]),
                Integer.parseInt(fields[2])
        );
    }

    public String getName() {
        return name;
    }

    public Byn getByn() {
        return byn;
    }

    public int getAmountOfPurchasedUnits() {
        return amountOfPurchasedUnits;
    }


    public Byn getCost() {
        return byn.mul(amountOfPurchasedUnits);
    }

    protected String fieldsToString() {
        return String.format("%s;%s;%s", name, byn, amountOfPurchasedUnits);
    }

    @Override
    public final String toString() {
        return String.format("%s;%s", fieldsToString(), getCost());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Purchase)) return false;
        Purchase purchase = (Purchase) o;
        return Objects.equals(name, purchase.name) && Objects.equals(byn, purchase.byn);
    }

    @Override
    public int compareTo(Purchase o) {
        int compareNames = name.compareTo(o.name);
        if (compareNames == 0) {
            return getCost().compareTo(o.getCost());
        }
        return compareNames;

    }

    @Override
    public int hashCode() {
        return Objects.hash(name, byn);
    }
}
