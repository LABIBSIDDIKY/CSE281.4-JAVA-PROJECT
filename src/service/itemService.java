package service;

import exception.DuplicateItemException;
import exception.EmptyFieldException;
import model.item;

import java.util.ArrayList;

public class itemService {

    private ArrayList<item> items = new ArrayList<>();

    public void addItem(item newItem) throws EmptyFieldException, DuplicateItemException {

        if (newItem == null) {
            throw new EmptyFieldException("Item cannot be null.");
        }

        if (newItem.getName() == null || newItem.getName().trim().isEmpty()) {
            throw new EmptyFieldException("Item name cannot be empty.");
        }

        if (newItem.getCategory() == null || newItem.getCategory().trim().isEmpty()) {
            throw new EmptyFieldException("Item category cannot be empty.");
        }

        for (item existingItem : items) {
            if (existingItem.getName().equalsIgnoreCase(newItem.getName())
                    && existingItem.getDate().equals(newItem.getDate())) {

                throw new DuplicateItemException("Duplicate item found: same name and date.");
            }
        }

        items.add(newItem);
    }

    public ArrayList<item> getAllItems() {
        return items;
    }

    public ArrayList<item> searchByName(String keyword) throws EmptyFieldException {

        if (keyword == null || keyword.trim().isEmpty()) {
            throw new EmptyFieldException("Search keyword cannot be empty.");
        }

        ArrayList<item> result = new ArrayList<>();

        for (item item : items) {
            if (item.getName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(item);
            }
        }

        return result;
    }

    public boolean deleteItem(String itemId) throws EmptyFieldException {

        if (itemId == null || itemId.trim().isEmpty()) {
            throw new EmptyFieldException("Item ID cannot be empty.");
        }

        return items.removeIf(item -> item.getId().equals(itemId));
    }
}