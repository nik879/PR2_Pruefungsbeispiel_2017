package org.campus02.ecom;

import java.util.Comparator;

public class BasketComparator implements Comparator<BasketData> {
    @Override
    public int compare(BasketData o1, BasketData o2) {
        int cmp = o1.getBuyingLocation().compareTo(o2.getBuyingLocation());
        if (o1.getBuyingLocation().equals(o2.getBuyingLocation())) {
            cmp = o2.getOrderTotal().compareTo(o1.getOrderTotal());
        }
        return cmp;
    }
}
