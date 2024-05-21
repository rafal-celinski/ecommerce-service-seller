package pis24l.projekt.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private BigDecimal price;

    private String description;

    private Long category;

    private Long subcategory;

    protected Product() {}
    public Product(String title, BigDecimal price, String location, LocalDateTime dateAdded,String description, Long subcategory, Long category) {
        this.title = title;
        this.price = price;
        this.location = location;
        this.date = dateAdded;
        this.category = category;
        this.subcategory = subcategory;
        this.description = description;
    }

    public Product(Long id, String title, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    @NotNull
    private String location;

    @Column(name="date")
    private LocalDateTime date;
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

    public BigDecimal getPrice() {
        return price;
    }

    public String getLocation() {
        return location;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getDescription() { return description; }

    public Long getCategory() {
        return category;
    }

    public Long getSubcategory() {
        return subcategory;
    }

}