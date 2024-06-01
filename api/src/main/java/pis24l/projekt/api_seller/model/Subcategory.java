package pis24l.projekt.api_seller.model;

import javax.persistence.*;

    @Entity
    @Table(name = "subcategory")
    public class Subcategory {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;

        @ManyToOne
        @JoinColumn(name = "category_id")
        private Category category;

        public Subcategory() {
        }

        public Subcategory(Long id, String name, Category category) {
            this.id = id;
            this.name = name;
            this.category = category;
        }

        // Getters and setters
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setCategory(Category category) {
            this.category = category;
        }
    }

