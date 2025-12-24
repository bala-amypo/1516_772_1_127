package com.example.demo.dto;

public class ComplaintResponse {

    private Long complaintId;
    private String status;
    private Integer priorityScore;
    private String message;

    public ComplaintResponse() {
    }

    public ComplaintResponse(Long complaintId, String status,
                             Integer priorityScore, String message) {
        this.complaintId = complaintId;
        this.status = status;
        this.priorityScore = priorityScore;
        this.message = message;
    }

    public Long getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(Long complaintId) {
        this.complaintId = complaintId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPriorityScore() {
        return priorityScore;
    }

    public void setPriorityScore(Integer priorityScore) {
        this.priorityScore = priorityScore;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
