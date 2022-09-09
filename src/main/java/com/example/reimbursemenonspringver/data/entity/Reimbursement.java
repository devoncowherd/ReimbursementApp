package com.example.reimbursemenonspringver.data.entity;

public class Reimbursement {
    int transactionId,
            employeeId;
    double amount;
    String receiptLink,
     comment,
     status,
     requestTimestamp;


    public int getTransactionId() {
        return transactionId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public double getAmount() {
        return amount;
    }

    public String getReceiptLink() {
        return receiptLink;
    }

    public String getComment() {
        return comment;
    }

    public String getStatus() {
        return status;
    }

    public String getRequestTimestamp() {
        return requestTimestamp;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setReceiptLink(String receiptLink) {
        this.receiptLink = receiptLink;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRequestTimestamp(String requestTimestamp) {
        this.requestTimestamp = requestTimestamp;
    }

    @Override
    public String toString(){
        return "Transaction ID: " + transactionId
                + "\nEmployee ID: " + employeeId
                + "\nAmount: " + amount
                + "\nReceipt Link: " + receiptLink
                + "\nDetails: " + comment
                + "\nStatus: " + status;
    }
}
