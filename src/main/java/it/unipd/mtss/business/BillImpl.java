/////////////////////////////////////////////////////////////////
// Arianna Pia De Laurentis 2008077
// Filippo Sabbadin 2010008
/////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import java.util.List;

import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.User;

public class BillImpl implements Bill {
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

        return totalPrice;
    }

    public double getTotalPrice(List<EItem> items) {
        double totalPriceAcc = 0;

        for (EItem item : items) {
            totalPriceAcc += item.getPrice();
        }

        return totalPriceAcc;
    }
}
