package model;

public class foundItem extends item {

    private String foundLocation;
    private String currentHolder;
    private String storedAt;

    public foundItem(String id, String name, String description,
                     String category, java.time.LocalDate date,
                     String foundLocation,
                     String currentHolder,
                     String storedAt) {

        super(id, name, description, category, date);

        this.foundLocation = foundLocation;
        this.currentHolder = currentHolder;
        this.storedAt = storedAt;
    }

    @Override
    public String displayDetails() {
        return "Found Item: " + getName()
                + " found at " + foundLocation;
    }

    public String getFoundLocation() {
        return foundLocation;
    }

    public String getCurrentHolder() {
        return currentHolder;
    }

    public String getStoredAt() {
        return storedAt;
    }
}