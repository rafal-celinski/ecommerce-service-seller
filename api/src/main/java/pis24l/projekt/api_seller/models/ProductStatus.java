package pis24l.projekt.api_seller.models;

public enum ProductStatus {
    UP("Up"),
    SOLD("Sold"),
    SENT("Sent");

    private final String value;

    ProductStatus(String value) {
        this.value = value;
    }
}
