package by.epam.lab;


import by.epam.lab.exceptions.DiscountValueException;

public class PriceDiscountPurchase extends Purchase {
    private final Byn discount;

    public PriceDiscountPurchase(String name, Byn byn, int amountOfPurchasedUnits, Byn discount) {
        super(name, byn, amountOfPurchasedUnits);
        if (discount.compareTo(Byn.ZERO) <= 0 || (discount.compareTo(byn) >= 0)) {
            throw new DiscountValueException(byn, discount);
        }
        this.discount = discount;
    }

    public PriceDiscountPurchase(String[] fields) {
        this(
                fields[0],
                new Byn(fields[1]),
                Integer.parseInt(fields[2]),
                new Byn(fields[3])
        );
    }

    @Override
    public Byn getCost() {
        return super.getCost().sub(discount.mul(getAmountOfPurchasedUnits()));
    }

    @Override
    protected String fieldsToString() {
        return String.format("%s;%s", super.fieldsToString(), discount);
    }

}
