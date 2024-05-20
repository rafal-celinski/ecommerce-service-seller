package pis24l.projekt.api.model;

import org.springframework.boot.convert.DataSizeUnit;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.Size;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "products")
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
    private LocalDateTime dateAdded;

    @Column(name = "image_url")
    private String imageUrl;

    private Long category;
    private Long subcategory;

    protected Product() {}
    public Product(String title, BigDecimal price, String location, LocalDateTime dateAdded, String imageUrl, Long subcategory, Long category) {
        this.title = title;
        this.price = price;
        this.location = location;
        this.dateAdded = dateAdded;
        this.imageUrl = imageUrl;
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
        return dateAdded;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Long getCategory() {
        return category;
    }

    public Long getSubcategory() {
        return subcategory;
    }
}