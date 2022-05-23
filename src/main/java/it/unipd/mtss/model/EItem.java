/////////////////////////////////////////////////////////////////
// Arianna Pia De Laurentis 2008077
// Filippo Sabbadin 2010008
/////////////////////////////////////////////////////////////////

package it.unipd.mtss.model;

public class EItem {
    public enum EItemType {
        MOTHERBOARD, MOUSE, KEYBOARD, PROCESSOR
    }

    private EItemType itemType;
    private String title;
    private double price;

    public EItem(String title, EItemType itemType, double price) {
        this.itemType = itemType;
        this.title = title;

        if (price >= 0) {
            this.price = price;
        } else {
            throw new IllegalArgumentException("Price must be positive");
        }
    }

    public String getTitle() {
        return title;
    }

    public EItemType getType() {
        return itemType;
    }

    public double getPrice() {
        return price;
    }

    // Setters are not needed by requirements
}
