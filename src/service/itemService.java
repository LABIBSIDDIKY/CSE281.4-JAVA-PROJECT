package service;

import exception.DuplicateItemException;
import exception.EmptyFieldException;
import model.foundItem;
import model.item;
import model.lostItem;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class itemService {

    private ArrayList<item> items = new ArrayList<>();
    private final String FILE_PATH = "src/data/items.txt";

    public itemService() {
        loadItemsFromFile();
    }

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
        saveItemToFile(newItem);
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

        boolean removed = items.removeIf(item -> item.getId().equals(itemId));

        if (removed) {
            rewriteFile();
        }

        return removed;
    }

    private void saveItemToFile(item newItem) {
        try {
            FileWriter writer = new FileWriter(FILE_PATH, true);
            writer.write(convertItemToLine(newItem));
            writer.close();

        } catch (IOException e) {
            System.out.println("Error saving item to file: " + e.getMessage());
        }
    }

    private void rewriteFile() {
        try {
            FileWriter writer = new FileWriter(FILE_PATH, false);

            for (item item : items) {
                writer.write(convertItemToLine(item));
            }

            writer.close();

        } catch (IOException e) {
            System.out.println("Error rewriting item file: " + e.getMessage());
        }
    }

    private String convertItemToLine(item newItem) {

        if (newItem instanceof foundItem found) {
            return "FOUND," +
                    found.getId() + "," +
                    found.getName() + "," +
                    found.getDescription() + "," +
                    found.getCategory() + "," +
                    found.getDate() + "," +
                    found.getFoundLocation() + "," +
                    found.getCurrentHolder() + "," +
                    found.getStoredAt() +
                    "\n";
        }

        if (newItem instanceof lostItem lost) {
            return "LOST," +
                    lost.getId() + "," +
                    lost.getName() + "," +
                    lost.getDescription() + "," +
                    lost.getCategory() + "," +
                    lost.getDate() + "," +
                    lost.getLostLocation() + "," +
                    lost.isRewardOffered() + "," +
                    lost.getContactInfo() +
                    "\n";
        }

        return "";
    }

    private void loadItemsFromFile() {

        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return;
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                if (data.length < 9) {
                    continue;
                }

                if (data[0].equals("FOUND")) {

                    foundItem item = new foundItem(
                            data[1],
                            data[2],
                            data[3],
                            data[4],
                            LocalDate.parse(data[5]),
                            data[6],
                            data[7],
                            data[8]
                    );

                    items.add(item);
                }

                else if (data[0].equals("LOST")) {

                    lostItem item = new lostItem(
                            data[1],
                            data[2],
                            data[3],
                            data[4],
                            LocalDate.parse(data[5]),
                            data[6],
                            Boolean.parseBoolean(data[7]),
                            data[8]
                    );

                    items.add(item);
                }
            }

            reader.close();

        } catch (Exception e) {
            System.out.println("Error loading items from file: " + e.getMessage());
        }
    }
}