package main;

import model.claimRequest;
import model.claimStatus;
import model.foundItem;
import model.lostItem;
import model.user;
import service.claimService;
import service.itemService;

import java.time.LocalDate;

public class App {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" LFMS PROJECT STARTED ");
        System.out.println("=================================\n");

        itemService itemService = new itemService();
        claimService claimService = new claimService();

        user user1 = new user(
                "U001",
                "Tareq",
                "tareq@gmail.com",
                "01700000000",
                "1234"
        );

        user user2 = new user(
                "U002",
                "Rayhan",
                "rayhan@gmail.com",
                "01800000000",
                "5678"
        );

        lostItem lostItem1 = new lostItem(
                "L001",
                "Wallet",
                "Black leather wallet",
                "Accessories",
                LocalDate.of(2026, 5, 1),
                "Cafeteria",
                true,
                "01711111111"
        );

        lostItem lostItem2 = new lostItem(
                "L002",
                "Student ID Card",
                "Green university ID card",
                "Documents",
                LocalDate.of(2026, 5, 2),
                "Library",
                false,
                "01822222222"
        );

        foundItem foundItem1 = new foundItem(
                "F001",
                "iPhone 13",
                "Black iPhone found near gate",
                "Electronics",
                LocalDate.of(2026, 5, 3),
                "Main Gate",
                "Security Officer",
                "Lost & Found Office"
        );

        foundItem foundItem2 = new foundItem(
                "F002",
                "Water Bottle",
                "Blue steel bottle",
                "Accessories",
                LocalDate.of(2026, 5, 4),
                "Gymnasium",
                "Reception Desk",
                "Storage Room"
        );

        itemService.addItem(lostItem1);
        itemService.addItem(lostItem2);
        itemService.addItem(foundItem1);
        itemService.addItem(foundItem2);

        System.out.println("\n========== ALL ITEMS ==========\n");

        for (var item : itemService.getAllItems()) {
            System.out.println(item.displayDetails());
            System.out.println("-----------------------------------");
        }

        System.out.println("\n========== SEARCH RESULTS ==========\n");

        var results = itemService.searchByName("iphone");

        for (var item : results) {
            System.out.println(item.displayDetails());
        }

        claimRequest claim1 = new claimRequest(
                "C001",
                user1.getUserId(),
                foundItem1.getId(),
                claimStatus.PENDING,
                LocalDate.now()
        );

        claimService.submitClaim(claim1);

        System.out.println("\n========== CLAIM REQUESTS ==========\n");

        for (var claim : claimService.getAllClaims()) {

            System.out.println("Claim ID: " + claim.getClaimId());
            System.out.println("User ID: " + claim.getUserId());
            System.out.println("Item ID: " + claim.getItemId());
            System.out.println("Status : " + claim.getStatus());

            System.out.println("-----------------------------------");
        }

        claimService.approveClaim("C001");

        System.out.println("\n========== AFTER APPROVAL ==========\n");

        for (var claim : claimService.getAllClaims()) {

            System.out.println("Claim ID: " + claim.getClaimId());
            System.out.println("Status   : " + claim.getStatus());

            System.out.println("-----------------------------------");
        }

        System.out.println("\n=================================");
        System.out.println(" LFMS PROJECT FINISHED ");
        System.out.println("=================================");
    }
}