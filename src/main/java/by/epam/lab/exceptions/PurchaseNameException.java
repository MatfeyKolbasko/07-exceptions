package by.epam.lab.exceptions;

public class PurchaseNameException extends IllegalArgumentException {
    private final String name;

    public PurchaseNameException(String name) {
        super(String.format("Name = %s", name));
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
