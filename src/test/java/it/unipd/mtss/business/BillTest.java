/////////////////////////////////////////////////////////////////
// Arianna Pia De Laurentis 2008077
// Filippo Sabbadin 2010008
/////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.User;
import it.unipd.mtss.model.EItem.EItemType;

@RunWith(JUnit4.class)
public class BillTest {
    User user = null;
    BillImpl bill = null;
    ArrayList<EItem> itemsOrder = null;

    @Before
    public void before_every_test() {
        user = new User(14, "Arianna Pia", "De Laurentis", LocalDate.of(1982, 1, 2));
        bill = new BillImpl();
        itemsOrder = new ArrayList<EItem>();
    }

    @Test
    public void getOrderPrice_SumItems_TotalPrice() throws BillException {
        itemsOrder.add(new EItem("Corsair K100 RGB", EItemType.KEYBOARD, 319.9));
        itemsOrder.add(new EItem("MX Master 3", EItemType.MOUSE, 80.00));
        itemsOrder.add(new EItem("Intel i9-11900K", EItemType.PROCESSOR, 429.99));
        itemsOrder.add(new EItem("MSI mpg X570", EItemType.MOTHERBOARD, 139.99));

        assertEquals(969.88, bill.getOrderPrice(itemsOrder, user), 1e-3);
    }
}
