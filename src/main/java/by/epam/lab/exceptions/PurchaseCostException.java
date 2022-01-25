package by.epam.lab.exceptions;

import by.epam.lab.Byn;

public class PurchaseCostException extends IllegalArgumentException {
    private final Byn cost;

    public PurchaseCostException(Byn cost) {
        super(String.format("Cost = %s", cost));
        this.cost = cost;
    }

    public Byn getCost() {
        return cost;
    }
}
