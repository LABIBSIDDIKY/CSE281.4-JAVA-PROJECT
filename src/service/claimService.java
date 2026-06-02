package service;

import exception.EmptyFieldException;
import exception.InvalidClaimException;
import model.claimRequest;
import model.claimStatus;

import java.util.ArrayList;

public class claimService {

    private ArrayList<claimRequest> claims = new ArrayList<>();

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
    }

    public void approveClaim(String claimId)
            throws EmptyFieldException, InvalidClaimException {

        if (claimId == null || claimId.trim().isEmpty()) {
            throw new EmptyFieldException("Claim ID cannot be empty.");
        }

        for (claimRequest claim : claims) {
            if (claim.getClaimId().equals(claimId)) {
                claim.approveClaim();
                return;
            }
        }

        throw new InvalidClaimException("Claim not found with ID: " + claimId);
    }

    public void rejectClaim(String claimId)
            throws EmptyFieldException, InvalidClaimException {

        if (claimId == null || claimId.trim().isEmpty()) {
            throw new EmptyFieldException("Claim ID cannot be empty.");
        }

        for (claimRequest claim : claims) {
            if (claim.getClaimId().equals(claimId)) {
                claim.rejectClaim();
                return;
            }
        }

        throw new InvalidClaimException("Claim not found with ID: " + claimId);
    }

    public ArrayList<claimRequest> getAllClaims() {
        return claims;
    }
}