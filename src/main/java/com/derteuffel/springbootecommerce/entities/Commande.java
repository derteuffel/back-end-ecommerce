package com.derteuffel.springbootecommerce.entities;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "commande")
public class Commande {

    @Id
    @GeneratedValue
    private Long id;
    private int quantity;
    private String color;
    private String size;
    private String numero;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm")
    private Date orderDate = new Date();

    private String status;
    private Double amount;

    @ManyToOne
    private Produit produit;
    @ManyToOne
    private Panier panier;

    public Commande() {
    }

    public Commande(int quantity, String color, String size) {
        this.quantity = quantity;
        this.color = color;
        this.size = size;
    }

    public Commande(int quantity, String color, String size, Panier panier, Produit produit) {
        this.quantity = quantity;
        this.color = color;
        this.size = size;
        this.produit = produit;
        this.panier = panier;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }


    public Panier getPanier() {
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
