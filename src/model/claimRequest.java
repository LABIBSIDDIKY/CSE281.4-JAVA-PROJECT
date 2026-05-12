package model;

import java.time.LocalDate;

public class claimRequest {

    private String claimId;
    private String userId;
    private String itemId;
    private ClaimStatus status;
    private LocalDate submittedDate;

    public claimRequest(String claimId,
                        String userId,
                        String itemId,
                        LocalDate submittedDate) {

        this.claimId = claimId;
        this.userId = userId;
        this.itemId = itemId;
        this.submittedDate = submittedDate;
        this.status = ClaimStatus.PENDING;
    }

    public String getClaimId() {
        return claimId;
    }

    public String getUserId() {
        return userId;
    }

    public String getItemId() {
        return itemId;
    }

    public ClaimStatus getStatus() {
        return status;
    }

    public void approveClaim() {
        status = ClaimStatus.APPROVED;
    }

    public void rejectClaim() {
        status = ClaimStatus.REJECTED;
    }

    public LocalDate getSubmittedDate() {
        return submittedDate;
    }
} 
