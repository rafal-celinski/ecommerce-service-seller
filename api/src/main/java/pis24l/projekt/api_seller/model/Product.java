package pis24l.projekt.api_seller.model;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.Size;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Title cannot be null")
    @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
    private String title;

    @NotNull(message = "Price cannot be null")
    @Min(value = 0, message = "Price must be greater than or equal to 0")
    private BigDecimal price;

    @NotNull(message = "Location cannot be null")
    @Size(min = 1, max = 255, message = "Location must be between 1 and 255 characters")
    private String location;

    @Column(name = "date")
    private LocalDateTime date;

    @NotNull(message = "Category cannot be null")
    private Long category;


    @NotNull(message = "Subcategory cannot be null")
    private Long subcategory;

    @Column(name="description")
    private String description;

    @PrePersist
    protected void onCreate() {
        date = LocalDateTime.now();
    }

    protected Product() {}

    public Product(String title, BigDecimal price, String location, Long subcategory, Long category, String description) {
        this.title = title;
        this.price = price;
        this.location = location;
        this.category = category;
        this.subcategory = subcategory;
        this.description = description;
    }

    public Product(Long id, String title, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }
  
    @Transient
    private List<String> imageUrls; // Add this field

    // Getters and setters for the new field
    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }
        public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() { return description; }

    public BigDecimal getPrice() {
        return price;
    }

    public String getLocation() {
        return location;
    }

    public LocalDateTime getDate() {
        return date;
    }


    public Long getCategory() {
        return category;
    }

  
    public Long getSubcategory() {
        return subcategory;
    }

    public void setId(Long id) {
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

    public void setCategory(Long category) {
        this.category = category;
    }

    public void setSubcategory(Long subcategory) {
        this.subcategory = subcategory;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}