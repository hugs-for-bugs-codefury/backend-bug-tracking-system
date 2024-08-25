package org.example.exceptions.bugs;


public enum NotAssignableReason {
    BUG_NOT_OPEN("Bug is not open"),
    BUG_NOT_IN_USER_PROJECTS("Bug is not in user's project");
    public final String label;

    private NotAssignableReason(String label) {
        this.label = label;
    }
}