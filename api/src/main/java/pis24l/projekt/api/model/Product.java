package pis24l.projekt.api.model;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "products_test")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "date_added")
    private LocalDate date_added;

    @Column(name = "seller_name")
    private String seller_name;

    public Product() {
    }

    public Product(String name, BigDecimal price, LocalDate dateAdded, String sellerName) {
        this.name = name;
        this.price = price;
        this.date_added = dateAdded;
        this.seller_name = sellerName;
    }

}
