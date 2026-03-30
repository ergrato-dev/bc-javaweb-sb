package com.bootcamp.elibrary.domain;

public enum LoanStatus {
    ACTIVE,    // Book is checked out
    RETURNED,  // Book has been returned
    OVERDUE    // Past due date, not yet returned
}
