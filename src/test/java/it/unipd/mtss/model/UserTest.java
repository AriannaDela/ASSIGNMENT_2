/////////////////////////////////////////////////////////////////
// Arianna Pia De Laurentis 2008077
// Filippo Sabbadin 2014008
/////////////////////////////////////////////////////////////////

package it.unipd.mtss.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.time.LocalDate;

@RunWith(JUnit4.class)
public class UserTest {
    User user = null;

    @Before
    public void before_every_test() {
        user = new User(14, "Arianna Pia", "Test", LocalDate.of(1991, 9, 17));
    }

    @Test(expected = IllegalArgumentException.class)
    public void User_NegativeId_IllegalArgumentException() {
        new User(-14, "Arianna Pia", "Test", LocalDate.of(1991, 9, 17));
    }

    @Test(expected = IllegalArgumentException.class)
    public void User_NullName_IllegalArgumentException() {
        new User(14, null, "Test", LocalDate.of(1991, 9, 17));
    }

    @Test(expected = IllegalArgumentException.class)
    public void User_NullSurname_IllegalArgumentException() {
        new User(14, "Arianna Pia", null, LocalDate.of(1991, 9, 17));
    }

    @Test(expected = IllegalArgumentException.class)
    public void User_NullBirthdate_IllegalArgumentException() {
        new User(14, "Arianna Pia", "Test", null);
    }
}