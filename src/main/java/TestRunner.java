import by.epam.lab.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class TestRunner {
    private final String DELIMITER = ";";

    @Test
    public void testPurchaseFactory1() {
        String name = "testPurchase";
        int cost = 1000;
        int amount = 5;

        String line = String.join(DELIMITER, name, String.valueOf(cost), String.valueOf(amount));
        Purchase expectedPurchase = new Purchase(name, new Byn(cost), amount);
        Optional<Purchase> optionalPurchase = PurchaseFactory.getPurchase(line);

        Assert.assertTrue(optionalPurchase.isPresent());
        Assert.assertEquals(expectedPurchase, optionalPurchase.get());
    }

    @Test
    public void testPurchaseFactory2() {
        String name = "testPurchase";
        int cost = 1000;
        int amount = 5;
        int discount = 130;

        String line = String.join(DELIMITER, name, String.valueOf(cost), String.valueOf(amount), String.valueOf(discount));
        Purchase expectedPurchase = new PriceDiscountPurchase(name, new Byn(cost), amount, new Byn(discount));
        Optional<Purchase> optionalPurchase = PurchaseFactory.getPurchase(line);

        Assert.assertTrue(optionalPurchase.isPresent());
        Assert.assertEquals(expectedPurchase, optionalPurchase.get());
    }


    @Test
    public void testPurchaseFactory3() {
        String name = "testPurchase";
        int cost = 1000;
        int amount = -5;
        int discount = 130;

        String line = String.join(DELIMITER, name, String.valueOf(cost), String.valueOf(amount), String.valueOf(discount));
        Optional<Purchase> optionalPurchase = PurchaseFactory.getPurchase(line);

        Assert.assertFalse(optionalPurchase.isPresent());
    }

    @Test
    public void testPurchaseListConstructor() {
        PurchaseList purchaseList = new PurchaseList("src/in.csv");
        int expectedSize = 8;
        Assert.assertEquals(expectedSize, purchaseList.getPurchases().size());
    }

    @Test
    public void testAdd() {
        PurchaseList purchaseList = new PurchaseList("");
        int sizeOfEmptyList = 0;
        Assert.assertEquals(sizeOfEmptyList, purchaseList.getPurchases().size());

        purchaseList.add(0, new Purchase(new String[]{"cheese", "147", "11"}));
        purchaseList.add(-10, new Purchase(new String[]{"milk", "93", "4"}));
        purchaseList.add(500, new Purchase(new String[]{"bread", "44", "9"}));
        purchaseList.add(0, new PriceDiscountPurchase(new String[]{"yogurt", "500", "6", "200"}));

        int size = 4;
        Assert.assertEquals(size, purchaseList.getPurchases().size());
    }

    @Test
    public void testDelete() {
        PurchaseList purchaseList = new PurchaseList("");

        purchaseList.add(0, new Purchase(new String[]{"cheese", "147", "11"}));
        purchaseList.add(0, new Purchase(new String[]{"milk", "93", "4"}));
        purchaseList.add(0, new Purchase(new String[]{"bread", "44", "9"}));
        purchaseList.add(0, new PriceDiscountPurchase(new String[]{"yogurt", "500", "6", "200"}));

        purchaseList.deleteRange(1, 3);
        int size = 2;
        Assert.assertEquals(size, purchaseList.getPurchases().size());
    }

    @Test
    public void testTotalCost() {
        PurchaseList purchaseList = new PurchaseList("src/in2.csv");

        Byn expectedCost = new Byn(60, 62);
        Assert.assertEquals(expectedCost, purchaseList.getTotalCost());
    }

    @Test
    public void testSort() {
        PurchaseList purchaseList = new PurchaseList("src/in2.csv");

        List<Purchase> purchases = purchaseList.getPurchases();
        Collections.sort(purchases);
        Assert.assertNotEquals(purchases, purchaseList.getPurchases());

        purchaseList.sort();
        Assert.assertEquals(purchases, purchaseList.getPurchases());
    }

    @Test
    public void testBinarySearchSuccess() {
        PurchaseList purchaseList = new PurchaseList("src/in2.csv");

        purchaseList.sort();
        Purchase target = new PriceDiscountPurchase("potato", new Byn(100), 2, new Byn(41));
        int index = purchaseList.binarySearch(target);
        int expectedIndex = 4;
        Assert.assertEquals(expectedIndex, index);
    }

}
