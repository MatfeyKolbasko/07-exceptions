package by.epam.lab.exceptions;

import java.text.ParseException;

public class CsvLineException extends ParseException {
    private final int amountOfArguments;

    public CsvLineException(String s, int amountOfArguments) {
        super(String.format("Amount of arguments is %d", amountOfArguments), 0);
        this.amountOfArguments = amountOfArguments;
    }

    public int getAmountOfArguments() {
        return amountOfArguments;
    }
}
