package pl.creative.rental_server.entities;

public enum StatusOfItem {
    NEW("New"),
    USED("Used"),
    DAMAGED("Damaged"),
    DESTROYED("Destroyed"),
    EJECTED("Ejected");

    private final String code;

    StatusOfItem(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
