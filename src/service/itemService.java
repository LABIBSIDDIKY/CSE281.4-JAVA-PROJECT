package service;

import model.item;

import java.util.ArrayList;

public class itemService {

    private ArrayList<item> items = new ArrayList<>();

    public void addItem(item item) {

        items.add(item);

        System.out.println("Item added successfully!");
    }

    public ArrayList<item> getAllItems() {
        return items;
    }

    public ArrayList<item> searchByName(String keyword) {

        ArrayList<item> result = new ArrayList<>();

        for (item item : items) {

            if (item.getName().toLowerCase()
                    .contains(keyword.toLowerCase())) {

                result.add(item);
            }
        }

        return result;
    }

    public void deleteItem(String itemId) {

        items.removeIf(item -> item.getId().equals(itemId));

        System.out.println("Item deleted successfully!");
    }
}