package pl.creative.rental_server.entities;



public enum Role {
    ADMIN("ADMIN"), USER("USER"), MODERATOR("MODERATOR");
    private final String code;

    Role(String role) {
        code = role;
    }

    public String getCode() {
        return code;
    }
}
