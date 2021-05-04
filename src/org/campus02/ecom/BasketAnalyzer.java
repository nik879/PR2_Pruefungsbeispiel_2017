package org.campus02.ecom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BasketAnalyzer {
    private ArrayList<BasketData> baskets;

    public BasketAnalyzer(ArrayList<BasketData> baskets) {
        this.baskets = baskets;
    }

    public ArrayList<BasketData> getBaskets() {
        return baskets;
    }

    public ArrayList<BasketData> getEveryNthBasket(int n) throws DataFileException {
        int count = 0;
        for (BasketData basket : baskets) {
            if (count == n) {
                return baskets;
            }
        }
        throw new DataFileException();
    }

    public ArrayList<BasketData> filterBaskets(String paymentType, Double from, Double to) throws DataFileException {
        for (BasketData basket : baskets) {
            if (basket.getPaymentType().equals(paymentType) && from <= basket.getOrderTotal() && basket.getOrderTotal() <= to) {
                return baskets;
            }
        }
        throw new DataFileException();
    }

    public HashMap<String, ArrayList<Double>> groupByProductCategory() {
        HashMap<String, ArrayList<Double>> groupByProductCategory = new HashMap<>();
        ArrayList<Double> orderTotals;
        for (BasketData basket : baskets) {
            orderTotals = new ArrayList<>();
            if (groupByProductCategory.containsKey(basket.getProductCategory())) {
                orderTotals = groupByProductCategory.get(basket.getProductCategory());
                orderTotals.add(basket.getOrderTotal());
                groupByProductCategory.put(basket.getProductCategory(), orderTotals);
            } else {
                orderTotals.add(basket.getOrderTotal());
                groupByProductCategory.put(basket.getProductCategory(), orderTotals);
            }
        }
        return groupByProductCategory;


    }


}
