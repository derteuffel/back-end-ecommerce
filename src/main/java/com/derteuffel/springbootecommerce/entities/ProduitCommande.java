package com.derteuffel.springbootecommerce.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "produitCommande")
public class ProduitCommande {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Panier panier;

    public ProduitCommande(Panier panier) {
        this.panier = panier;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Panier getPanier() {
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }
}
