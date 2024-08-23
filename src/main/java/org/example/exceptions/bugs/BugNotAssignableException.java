package org.example.exceptions.bugs;



public class BugNotAssignableException extends RuntimeException {
    int bugId;
    NotAssignableReason reason;


    public BugNotAssignableException(NotAssignableReason reason, int bugId) {
        super("Bug with id " + bugId + " is not assignable because: " + reason.label);
        this.reason = reason;
        this.bugId = bugId;

    }
}
