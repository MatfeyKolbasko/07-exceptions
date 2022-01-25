package by.epam.lab;

import by.epam.lab.exceptions.*;

import java.util.Optional;
import java.util.function.Function;

public class PurchaseFactory {

    public static Optional<Purchase> getPurchase(String line) {
        try {
            String[] values = line.split(";");
            switch (values.length) {
                case 3: {
                    return Optional.of(PurchaseKind.PURCHASE.getPurchase(values));
                }
                case 4: {
                    return Optional.of(PurchaseKind.DISCOUNT_PURCHASE.getPurchase(values));
                }
                default: {
                    throw new CsvLineException(line, values.length);
                }
            }
        } catch (NumberFormatException |
                DiscountValueException |
                PurchaseNameException |
                PurchaseCostException |
                BynValueException |
                AmountOfPurchasedUnits |
                CsvLineException e) {
            System.err.println(line);
            return Optional.empty();
        }
    }

    private enum PurchaseKind {
        PURCHASE(Purchase::new),
        DISCOUNT_PURCHASE(PriceDiscountPurchase::new);

        private final Function<String[], Purchase> function;

        PurchaseKind(Function<String[], Purchase> function) {
            this.function = function;
        }

        public Purchase getPurchase(String[] fields) {
            return function.apply(fields);
        }
    }
}
