package pis24l.projekt.api.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private BigDecimal price;
    private String location;
    @Column(name="date")
    private LocalDateTime date;
    @Column(name="image_url")
    private String image;

    private Long category;
    private Long subcategory;

    protected Product() {}
    public Product(String title, BigDecimal price, String location, LocalDateTime dateAdded, String imageUrl, Long subcategory, Long category) {
        this.title = title;
        this.price = price;
        this.location = location;
        this.date = dateAdded;
        this.image = imageUrl;
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

    public LocalDateTime getDate() {
        return date;
    }

    public String getImage() {
        return image;
    }

}
