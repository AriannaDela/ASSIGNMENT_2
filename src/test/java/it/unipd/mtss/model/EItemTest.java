/////////////////////////////////////////////////////////////////
// Arianna Pia De Laurentis 2008077
// Filippo Sabbadin 2010008
/////////////////////////////////////////////////////////////////

package it.unipd.mtss.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import it.unipd.mtss.model.EItem.EItemType;

public class EItemTest {
    EItem item = null;

    @Before
    public void before_every_test() {
        item = new EItem("Corsair K100 RGB", EItemType.KEYBOARD, 319.9);
    }

    @Test
    public void EItem_GetTitle_Title() {
        assertEquals("Corsair K100 RGB", item.getTitle());
    }

    @Test
    public void EItem_GetType_Title() {
        assertEquals(EItemType.KEYBOARD, item.getType());
    }

    @Test(expected = IllegalArgumentException.class)
    public void EItem_NegativePrice_IllegalArgumentException() throws IllegalArgumentException {
        item = new EItem("Corsair K100 RGB", EItemType.KEYBOARD, -2.00);
    }
}