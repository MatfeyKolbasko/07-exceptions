package by.epam.lab.exceptions;

public class AmountOfPurchasedUnits extends IllegalArgumentException {
    private final int amount;

    public AmountOfPurchasedUnits(int amount) {
        super(String.format("Amount of purchased units: %d", amount));
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
