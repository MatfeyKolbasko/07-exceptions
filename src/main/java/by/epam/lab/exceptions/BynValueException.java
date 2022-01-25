package by.epam.lab.exceptions;

public class BynValueException extends IllegalArgumentException {
    private final int rubs;
    private final int coins;

    public BynValueException(int rubs, int coins) {
        super(String.format("Byn value: %d.02%d", Math.abs(rubs), Math.abs(coins)));
        this.rubs = rubs;
        this.coins = coins;
    }

    public int getRubs() {
        return rubs;
    }

    public int getCoins() {
        return coins;
    }
}
