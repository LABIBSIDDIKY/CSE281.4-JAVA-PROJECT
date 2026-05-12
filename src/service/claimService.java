package service;

import model.claimRequest;

import java.util.ArrayList;

public class claimService {

    private ArrayList<claimRequest> claims = new ArrayList<>();

    public void submitClaim(claimRequest claim) {

        claims.add(claim);

        System.out.println("Claim submitted successfully!");
    }

    public void approveClaim(String claimId) {

        for (claimRequest claim : claims) {

            if (claim.getClaimId().equals(claimId)) {

                claim.approveClaim();

                System.out.println("Claim approved!");
            }
        }
    }

    public void rejectClaim(String claimId) {

        for (claimRequest claim : claims) {

            if (claim.getClaimId().equals(claimId)) {

                claim.rejectClaim();

                System.out.println("Claim rejected!");
            }
        }
    }

    public ArrayList<claimRequest> getAllClaims() {
        return claims;
    }
}