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
        int mockedReturnPrice = 0;

        // TODO: implement

        return mockedReturnPrice;
    }
}
