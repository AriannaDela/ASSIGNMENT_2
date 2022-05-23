/////////////////////////////////////////////////////////////////
// Arianna Pia De Laurentis 2008077
// Filippo Sabbadin 2010008
/////////////////////////////////////////////////////////////////

package it.unipd.mtss.model;

import java.time.LocalDate;
import java.time.Period;

public class User {
    private int id;
    private String name, surname;
    private LocalDate birthDate;

    public User(int id, String name, String surname, LocalDate birthDate) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID cannot be negative");
        }
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        if (surname == null) {
            throw new IllegalArgumentException("Surname cannot be null");
        }
        if (birthDate == null) {
            throw new IllegalArgumentException("Birthdate cannot be null");

        }

        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return name + " " + surname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public int getCurrentAge() {
        return Period.between(this.birthDate, LocalDate.now()).getYears();
    }

    // Setters are not needed by requirements
}
