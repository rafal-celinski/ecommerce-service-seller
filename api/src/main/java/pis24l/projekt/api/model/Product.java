package pis24l.projekt.api.model;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    public String getName() {
        return name;
    }

    public Date getDate_added() {
        return date_added;
    }

    public String getSeller_name() {
        return seller_name;
    }
    public Long getId() {
        return this.id;
    }

    @Column(name="date_added")
    private Date date_added;

    private String seller_name;

    public Product() {
    }

    public Product(String name, BigDecimal price, Date dateAdded, String sellerName) {
        this.name = name;
        this.date_added = dateAdded;
        this.seller_name = sellerName;
    }

}
