package pis24l.projekt.api_seller.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "subcategory")
public class Subcategory {

    @Id
    private String id;
    private String name;
    private String categoryId;

    // Domyślny konstruktor
    public Subcategory() {}

    // Konstruktor z parametrami
    public Subcategory(String id, String name, String categoryId) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
    }

    // Gettery i settery
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
