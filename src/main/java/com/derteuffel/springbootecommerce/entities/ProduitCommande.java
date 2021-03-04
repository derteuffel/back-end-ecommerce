package com.derteuffel.springbootecommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@Table(name = "produitCommande")
public class ProduitCommande {

    @EmbeddedId
    @JsonIgnore
    private Panier panier;

    @Column(nullable = false)
    private Integer quantity;

    public ProduitCommande(Produit produit, Commande commande, Integer quantity) {
        panier = new Panier();
        panier.setCommande(commande);
        panier.setProduit(produit);
        this.quantity = quantity;
    }

    @Transient
    public Produit getProduit(){
        return this.panier.getProduit();
    }

    @Transient
    public Double getTotalPrice(){
        return getProduit().getPrice() * getQuantity();
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Panier getPanier() {
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProduitCommande that = (ProduitCommande) o;
        return Objects.equals(panier, that.panier) &&
                Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {

        return Objects.hash(panier, quantity);
    }
}
