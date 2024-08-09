package it.uniroma3.siw.utils;

public enum UserRole {
    DEFAULT("DEFAULT"),
    ADMIN("ADMIN");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
