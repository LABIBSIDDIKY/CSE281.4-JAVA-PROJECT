package model;

import java.time.LocalDate;

public class lostItem extends item {

    private final String lostLocation;
    private final boolean rewardOffered;
    private final String contactInfo;

    public lostItem(String id,
                    String name,
                    String description,
                    String category,
                    LocalDate date,
                    String lostLocation,
                    boolean rewardOffered,
                    String contactInfo) {

        super(id, name, description, category, date);

        this.lostLocation = lostLocation;
        this.rewardOffered = rewardOffered;
        this.contactInfo = contactInfo;
    }

    @Override
    public String displayDetails() {

        return "Lost Item: " + getName()
                + " | Lost At: " + lostLocation;
    }

    public String getLostLocation() {
        return lostLocation;
    }

    public boolean isRewardOffered() {
        return rewardOffered;
    }

    public String getContactInfo() {
        return contactInfo;
    }
}