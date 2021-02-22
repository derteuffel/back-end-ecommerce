package com.derteuffel.springbootecommerce.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Data
@Table(name = "produit")
public class Produit {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String category;
    private String genre;
    private String pictureUrl;
    private Double price;

    private String quality;


    public Produit() {
    }

    public Produit(String name, String category, String genre, String pictureUrl, Double price, String quality) {
        this.name = name;
        this.category = category;
        this.genre = genre;
        this.pictureUrl = pictureUrl;
        this.price = price;
        this.quality = quality;
    }

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }
}
