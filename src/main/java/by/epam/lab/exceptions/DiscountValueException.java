package by.epam.lab.exceptions;

import by.epam.lab.Byn;

public class DiscountValueException extends IllegalArgumentException {
    private final Byn cost;
    private final Byn discount;

    public DiscountValueException(Byn cost, Byn discount) {
        super(String.format("Cost = %s, discount = %s", cost, discount));
        this.cost = cost;
        this.discount = discount;
    }

    public Byn getCost() {
        return cost;
    }

    public Byn getDiscount() {
        return discount;
    }
}
