package pis24l.projekt.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigDecimal price;

    private LocalDate date;

    private String sellerName;

    // Constructors, getters, and setters

    public Product() {
    }

    public Product(String name, BigDecimal price, LocalDate date, String sellerName) {
        this.name = name;
        this.price = price;
        this.date = date;
        this.sellerName = sellerName;
    }

    // Getters and setters
}
