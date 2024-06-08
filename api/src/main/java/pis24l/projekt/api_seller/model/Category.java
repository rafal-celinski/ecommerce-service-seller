package pis24l.projekt.api_seller.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "category")
public class Category {

    @Id
    private String id;
    private String name;

    // Domyślny konstruktor
    public Category() {}

    // Konstruktor z parametrami
    public Category(String id, String name) {
        this.name = name;
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
}