/////////////////////////////////////////////////////////////////
// Arianna Pia De Laurentis 2008077
// Filippo Sabbadin 2010008
/////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import java.util.List;

import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.User;
import it.unipd.mtss.model.EItem.EItemType;

public class BillImpl implements Bill {
    int limitProcessor = 5;

    public double getOrderPrice(List<EItem> items, User user) throws BillException {
        if (user == null) {
            throw new BillException("User cannot be null");
        }

        if (items == null) {
            throw new BillException("Items List cannot be null");
        }

        if (items.isEmpty()) {
            throw new BillException("Items Ordered List cannot be null");
        }

        if (items.contains(null)) {
            throw new BillException("Item cannot be null");
        }

        // Get total price
        double totalPrice = this.getTotalPrice(items);

        // Get cheapest processor
        double cheapestProcessor = this.getCheapestByType(items, EItemType.PROCESSOR);
        // Get count processor
        int countProcessor = this.getCountByType(items, EItemType.PROCESSOR);

        // If processor limit is reached, discount 50% on the cheapest one
        if (countProcessor > limitProcessor) {
            totalPrice = totalPrice - (cheapestProcessor / 2);
        }

        return totalPrice;
    }

    public double getTotalPrice(List<EItem> items) {
        double totalPriceAcc = 0;

        for (EItem item : items) {
            totalPriceAcc += item.getPrice();
        }

        return totalPriceAcc;
    }

    public double getCheapestByType(List<EItem> items, EItemType type) {
        // Using first element of list as initial comparator for price
        double currentCheapestItem = items.get(0).getPrice();

        for (EItem item : items) {
            if (item.getPrice() < currentCheapestItem && item.getType() == type) {
                currentCheapestItem = item.getPrice();
            }
        }

        return currentCheapestItem;
    }

    public int getCountByType(List<EItem> items, EItemType type) {
        int count = 0;

        for (EItem item : items) {
            if (item.getType() == type) {
                count++;
            }
        }

        return count;
    }

}
