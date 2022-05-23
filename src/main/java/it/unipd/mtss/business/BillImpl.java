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
    int limitProcessor = 5, limitMouse = 10, limitItemsCount = 30;
    double maxPriceDiscount = 1000.0, minPriceCommission = 10.0;

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

        if (items.size() > limitItemsCount) {
            throw new BillException("No more than 30 elements");
        }

        // Get total price
        double totalPrice = this.getTotalPrice(items);

        // Get cheapest item by type
        double cheapestProcessor = this.getCheapestByType(items, EItemType.PROCESSOR);
        double cheapestMouse = this.getCheapestByType(items, EItemType.MOUSE);
        double cheapestKeyboard = this.getCheapestByType(items, EItemType.KEYBOARD);

        // Get count by type
        int countProcessor = this.getCountByType(items, EItemType.PROCESSOR);
        int countMouse = this.getCountByType(items, EItemType.MOUSE);
        int countKeyboard = this.getCountByType(items, EItemType.KEYBOARD);

        // If totalPrice is less than minPriceCommission, add commission
        if (totalPrice < minPriceCommission) {
            totalPrice += 2.0;
        }

        // If processor limit is reached, discount 50% on the cheapest one
        if (countProcessor > limitProcessor) {
            totalPrice = totalPrice - (cheapestProcessor / 2);
        }

        // If mouse limit reached, discount 100% on the cheapest one
        if (countMouse > limitMouse) {
            totalPrice -= cheapestMouse;
        }

        // If mouse count is equal to keyboard count, discount 100% the cheapest one
        if (countMouse == countKeyboard && countMouse > 0) {
            totalPrice -= Math.min(cheapestKeyboard, cheapestMouse);
        }

        // If totalPrice is greater than maxPriceDiscount, discount 10%
        if (totalPrice > maxPriceDiscount) {
            totalPrice = totalPrice - (0.1 * totalPrice);
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
