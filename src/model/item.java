package model;

import java.time.LocalDate;

public abstract class item {

    private String id;
    private String name;
    private String description;
    private String category;
    private LocalDate date;

    public item(String id, String name, String description, String category, LocalDate date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.date = date;
    }

    public abstract String displayDetails();

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public LocalDate getDate() {
        return date;
    }
}