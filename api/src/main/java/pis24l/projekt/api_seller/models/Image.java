package pis24l.projekt.api_seller.models;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;

@Document(collection = "image")
public class Image {

    @Id
    private String id;

    private String productId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Image(String productId) {
        this.productId = productId;
    }
    protected Image() { }
}

