package org.example.exceptions.bugs;

public class BugNotFoundException extends RuntimeException {
    int bugId;
    public BugNotFoundException(int bugId) {
        super("Bug with id " + bugId + " not found");
        this.bugId = bugId;

    }
}