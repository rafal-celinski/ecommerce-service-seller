package pis24l.projekt.api_seller.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.Size;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Document(indexName = "products")
@org.springframework.data.mongodb.core.mapping.Document(collection = "product")
public class Product {

    @Id
    private String id;

    @NotNull(message = "Title cannot be null")
    @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
    private String title;

    @NotNull(message = "Price cannot be null")
    @Min(value = 0, message = "Price must be greater than or equal to 0")
    private BigDecimal price;

    @NotNull(message = "Location cannot be null")
    @Size(min = 1, max = 255, message = "Location must be between 1 and 255 characters")
    private String location;

    @Field(type = FieldType.Date, format = DateFormat.date)
    private LocalDate date;

    @NotNull(message = "Category cannot be null")
    private String category;

    @NotNull(message = "Subcategory cannot be null")
    private String subcategory;

    private String description;

    private List<String> imageUrls;

    public Product() {
        this.date = LocalDate.now();
    }

    public Product(String title, BigDecimal price, String location, String subcategory, String category, String description) {
        this.title = title;
        this.price = price;
        this.location = location;
        this.category = category;
        this.subcategory = subcategory;
        this.description = description;
        this.date = LocalDate.now();
    }

    public Product(String id, String title, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public Product(String id, String title, BigDecimal price, String location, String subcategory, String category, String description, List<String> imageUrls) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.location = location;
        this.category = category;
        this.subcategory = subcategory;
        this.description = description;
        this.imageUrls = imageUrls;
        this.date = LocalDate.now();
    }

    // Getters and setters

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getLocation() {
        return location;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }
}
