package by.epam.lab;

import java.io.*;
import java.util.*;

public class PurchaseList {
    private final List<Purchase> purchases = new ArrayList<>();
    private final Comparator<Purchase> comparator;
    private boolean sorted = true;

    public PurchaseList(String filename) {
        this(filename, null);
    }

    public PurchaseList(String filename, Comparator<Purchase> comparator) {
        this.comparator = comparator;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            bufferedReader.lines()
                    .map(PurchaseFactory::getPurchase)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .forEach(purchases::add);
            if (!purchases.isEmpty()) {
                sorted = false;
            }
        } catch (FileNotFoundException e) {
            System.err.printf("File %s was not found%n", filename);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void add(int index, Purchase purchase) {
        if (index < 0) index = 0;
        if (index > purchases.size()) index = purchases.size();
        purchases.add(index, purchase);
        sorted = false;
    }

    public void deleteRange(int from, int to) {
        if (from >= 0 && to <= purchases.size() && from < to) {
            purchases.subList(from, to).clear();
        }
    }

    public Byn getTotalCost() {
        return purchases.stream()
                .map(Purchase::getCost)
                .reduce(new Byn(0), Byn::add);
    }

    public void sort() {
        purchases.sort(comparator);
        sorted = true;
    }

    public int binarySearch(Purchase desiredPurchase) {
        if (!sorted) {
            sort();
        }
        return Collections.binarySearch(purchases, desiredPurchase, comparator);
    }

    public List<Purchase> getPurchases() {
        return new ArrayList<>(purchases);
    }

    @Override
    public String toString() {
        return purchases.toString();
    }
}
