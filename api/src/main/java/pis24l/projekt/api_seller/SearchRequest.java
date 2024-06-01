package pis24l.projekt.api_seller;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchRequest {

    private String search;
    private int category;
    private int subcategory;
    private double minPrice;
    private double maxPrice;
    private String location;

    // Getters and setters

    @JsonProperty("search")
    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    @JsonProperty("category")
    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    @JsonProperty("subcategory")
    public int getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(int subcategory) {
        this.subcategory = subcategory;
    }

    @JsonProperty("minPrice")
    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    @JsonProperty("maxPrice")
    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    @JsonProperty("location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "SearchRequest{" +
                "search='" + search + '\'' +
                ", category=" + category +
                ", subcategory=" + subcategory +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", location='" + location + '\'' +
                '}';
    }
}