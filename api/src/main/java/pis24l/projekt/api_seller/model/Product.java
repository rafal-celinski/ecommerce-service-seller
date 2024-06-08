package pis24l.projekt.api_seller.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "product")
public class Product {

    @Id
    private String id;
    private String category;
    private String subcategory;
    private String title;
    private String description;
    private Long price;
    private Date date;
    private String location;

    // Gettery i settery
    public Product(String category,
                   String subcategory,
                   String title,
                   String description,
                   Long price,
                   String location) {
        this.category = category;
        this.subcategory = subcategory;
        this.title = title;
        this.description = description;
        this.price = price;
        this.location = location;
        this.date = new Date();
    }

    private List<String> imageUrls; // Add this field

    // Getters and setters for the new field
    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
