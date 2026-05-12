package model;

import java.time.LocalDate;

public class claimRequest {

    private String claimId;
    private String userId;
    private String itemId;
    private claimStatus status;
    private LocalDate submittedDate;

    public claimRequest(String claimId,
                        String userId,
                        String itemId,
                        claimStatus pending, LocalDate submittedDate) {

        this.claimId = claimId;
        this.userId = userId;
        this.itemId = itemId;
        this.submittedDate = submittedDate;
        this.status = claimStatus.PENDING;
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

    public claimStatus getStatus() {
        return status;
    }

    public void approveClaim() {
        status = claimStatus.APPROVED;
    }

    public void rejectClaim() {
        status = claimStatus.REJECTED;
    }

    public LocalDate getSubmittedDate() {
        return submittedDate;
    }
} 
