/////////////////////////////////////////////////////////////////
// Arianna Pia De Laurentis 2008077
// Filippo Sabbadin 2010008
/////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

        assertEquals(889.88, bill.getOrderPrice(itemsOrder, user), 1e-3);
    }

    @Test
    public void getOrderPrice_DiscountCheapestProcessorLimitBy50Perc_I3Discount50Perc()
            throws BillException {
        itemsOrder.add(new EItem("Intel i9-11900K", EItemType.PROCESSOR, 429.00));
        itemsOrder.add(new EItem("Intel i5-xxx", EItemType.PROCESSOR, 329.00));
        itemsOrder.add(new EItem("Intel i3-xxx", EItemType.PROCESSOR, 120.00));
        itemsOrder.add(new EItem("Intel i5-xxx", EItemType.PROCESSOR, 229.00));
        itemsOrder.add(new EItem("Intel i9-xxx", EItemType.PROCESSOR, 449.00));
        itemsOrder.add(new EItem("Intel i9-xxx", EItemType.PROCESSOR, 419.00));

        assertEquals(1723.5, bill.getOrderPrice(itemsOrder, user), 1e-3);
    }

    @Test
    public void getOrderPrice_DiscountCheapestMouseLimitBy100Perc_DiscountMouseBy100Perc()
            throws BillException {
        itemsOrder.add(new EItem("MX Master 3", EItemType.MOUSE, 129.00));
        itemsOrder.add(new EItem("MX Master aa", EItemType.MOUSE, 29.00));
        itemsOrder.add(new EItem("MX Master 3", EItemType.MOUSE, 129.00));
        itemsOrder.add(new EItem("MX Master aaa", EItemType.MOUSE, 39.00));
        itemsOrder.add(new EItem("MX Master 3", EItemType.MOUSE, 129.00));
        itemsOrder.add(new EItem("MX Master aaaa", EItemType.MOUSE, 49.00));
        itemsOrder.add(new EItem("MX Master 3", EItemType.MOUSE, 129.00));
        itemsOrder.add(new EItem("MX Master aaaaa", EItemType.MOUSE, 59.00));
        itemsOrder.add(new EItem("MX Master 3", EItemType.MOUSE, 129.00));
        itemsOrder.add(new EItem("MX Master aaaaaa", EItemType.MOUSE, 69.00));
        itemsOrder.add(new EItem("MX Master aaaaaaa", EItemType.MOUSE, 79.00));

        assertEquals(940.0, bill.getOrderPrice(itemsOrder, user), 1e-3);
    }

    @Test
    public void getOrderPrice_MouseCountEqualKeyboardCountDiscount_DiscountMouseBy100Perc()
            throws BillException {
        itemsOrder.add(new EItem("MX Master 3", EItemType.MOUSE, 129.00));
        itemsOrder.add(new EItem("MX Master 1", EItemType.MOUSE, 29.00));
        itemsOrder.add(new EItem("Corsair K100 RGB", EItemType.KEYBOARD, 120.00));
        itemsOrder.add(new EItem("Corsair K100 RGB - Lite", EItemType.KEYBOARD,
                129.00));
        itemsOrder.add(new EItem("Intel i9-xxx", EItemType.PROCESSOR, 419.00));

        assertEquals(797.0, bill.getOrderPrice(itemsOrder, user), 1e-3);
    }

    @Test
    public void getOrderPrice_TotalPriceDiscount_DiscountTotalPrice10Perc()
            throws BillException {
        itemsOrder.add(new EItem("Motherboard 1 - max", EItemType.MOTHERBOARD, 3000.00));
        itemsOrder.add(new EItem("Motherboard 2 - max", EItemType.MOTHERBOARD, 4000.00));
        itemsOrder.add(new EItem("Corsair K100 RGB max", EItemType.KEYBOARD,
                4000.00));

        assertEquals(9900, bill.getOrderPrice(itemsOrder, user), 1e-3);
    }

    @Test(expected = BillException.class)
    public void getOrderPrice_TestOrdersLimit_True() throws BillException {
        for (int i = 0; i < 31; i++) {
            itemsOrder.add(new EItem("Corsair K100 RGB", EItemType.KEYBOARD, 90.0));
        }

        bill.getOrderPrice(itemsOrder, user);
    }

    @Test
    public void getOrderPrice_TotalPriceCommissionFee_AddCommissionFee() throws BillException {
        itemsOrder.add(new EItem("Motherboard 1 - (from Aliexpress probably)", EItemType.MOTHERBOARD, 3.00));
        itemsOrder.add(new EItem("Motherboard 2 - (from Aliexpress probably)", EItemType.MOTHERBOARD, 4.00));

        assertEquals(9.0, bill.getOrderPrice(itemsOrder, user), 1e-3);
    }

    @Test
    public void getOrderPrice_EligibleForGiftUserOrder_True() throws BillException {
        User userFree = new User(13, "Arianna Pia Free", "De Laurentis",
                LocalDate.of(2007, 1, 2));

        LocalDateTime orderDate = LocalDateTime.of(2022, 10, 11, 18, 27, 00);

        assertEquals(true, bill.isEligibleForGift(userFree, orderDate));
    }
}
