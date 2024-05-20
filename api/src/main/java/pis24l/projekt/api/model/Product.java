package pis24l.projekt.api.model;

import org.springframework.boot.convert.DataSizeUnit;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    @Column(name = "image")
    private String image;

    @NotNull(message = "Category cannot be null")
    private Long category;

    @NotNull(message = "Subcategory cannot be null")
    private Long subcategory;

    protected Product() {}
    public Product(String title, BigDecimal price, String location, LocalDateTime date, String image, Long subcategory, Long category) {
        this.title = title;
        this.price = price;
        this.location = location;
        this.date = date;
        this.image = image;
        this.category = category;
        this.subcategory = subcategory;
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

    public LocalDateTime getDateAdded() {
        return date;
    }

    public String getImageUrl() {
        return image;
    }

    public Long getCategory() {
        return category;
    }

    public Long getSubcategory() {
        return subcategory;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.date = dateAdded;
    }

    public void setImageUrl(String imageUrl) {
        this.image = imageUrl;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public void setSubcategory(Long subcategory) {
        this.subcategory = subcategory;
    }
}