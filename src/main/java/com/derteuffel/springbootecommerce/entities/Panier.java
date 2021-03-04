package com.derteuffel.springbootecommerce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class Panier implements Serializable{

    @JsonBackReference
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "commande_id")
    private Commande commande;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "produit_id")
    private Produit produit;

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Panier other = (Panier) obj;
        if (commande == null) {
            if (other.commande != null) {
                return false;
            }
        } else if (!commande.equals(other.commande)) {
            return false;
        }

        if (produit == null) {
            if (other.produit != null) {
                return false;
            }
        } else if (!produit.equals(other.produit)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;

        result = prime * result + ((commande.getId() == null)
                ? 0
                : commande
                .getId()
                .hashCode());
        result = prime * result + ((produit.getId() == null)
                ? 0
                : produit
                .getId()
                .hashCode());

        return result;
    }
}
