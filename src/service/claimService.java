package service;

import exception.EmptyFieldException;
import exception.InvalidClaimException;
import model.claimRequest;
import model.claimStatus;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class claimService {

    private ArrayList<claimRequest> claims = new ArrayList<>();
    private final String FILE_PATH = "src/data/claims.txt";

    public claimService() {
        loadClaimsFromFile();
    }

    public void submitClaim(claimRequest claim)
            throws EmptyFieldException, InvalidClaimException {

        if (claim == null) {
            throw new EmptyFieldException("Claim cannot be null.");
        }

        if (claim.getClaimId() == null || claim.getClaimId().trim().isEmpty()) {
            throw new EmptyFieldException("Claim ID cannot be empty.");
        }

        if (claim.getUserId() == null || claim.getUserId().trim().isEmpty()) {
            throw new EmptyFieldException("User ID cannot be empty.");
        }

        if (claim.getItemId() == null || claim.getItemId().trim().isEmpty()) {
            throw new EmptyFieldException("Item ID cannot be empty.");
        }

        for (claimRequest existingClaim : claims) {
            if (existingClaim.getItemId().equals(claim.getItemId())
                    && existingClaim.getStatus() == claimStatus.APPROVED) {
                throw new InvalidClaimException("This item has already been claimed.");
            }
        }

        claims.add(claim);
        rewriteFile();
    }

    public void approveClaim(String claimId)
            throws EmptyFieldException, InvalidClaimException {

        claimRequest claim = findClaimById(claimId);
        claim.approveClaim();
        rewriteFile();
    }

    public void rejectClaim(String claimId)
            throws EmptyFieldException, InvalidClaimException {

        claimRequest claim = findClaimById(claimId);
        claim.rejectClaim();
        rewriteFile();
    }

    public ArrayList<claimRequest> getAllClaims() {
        return claims;
    }

    private claimRequest findClaimById(String claimId)
            throws EmptyFieldException, InvalidClaimException {

        if (claimId == null || claimId.trim().isEmpty()) {
            throw new EmptyFieldException("Claim ID cannot be empty.");
        }

        for (claimRequest claim : claims) {
            if (claim.getClaimId().equals(claimId)) {
                return claim;
            }
        }

        throw new InvalidClaimException("Claim not found with ID: " + claimId);
    }

    private void rewriteFile() {
        try {
            FileWriter writer = new FileWriter(FILE_PATH, false);

            for (claimRequest claim : claims) {
                writer.write(
                        claim.getClaimId() + "," +
                                claim.getUserId() + "," +
                                claim.getItemId() + "," +
                                claim.getStatus() + "," +
                                claim.getSubmittedDate() +
                                "\n"
                );
            }

            writer.close();

        } catch (IOException e) {
            System.out.println("Error saving claims: " + e.getMessage());
        }
    }

    private void loadClaimsFromFile() {

        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return;
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                if (data.length < 5) {
                    continue;
                }

                claimRequest claim = new claimRequest(
                        data[0],
                        data[1],
                        data[2],
                        claimStatus.PENDING,
                        LocalDate.parse(data[4])
                );

                if (data[3].equals("APPROVED")) {
                    claim.approveClaim();
                } else if (data[3].equals("REJECTED")) {
                    claim.rejectClaim();
                }

                claims.add(claim);
            }

            reader.close();

        } catch (Exception e) {
            System.out.println("Error loading claims: " + e.getMessage());
        }
    }
}