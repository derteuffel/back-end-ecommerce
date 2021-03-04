package com.derteuffel.springbootecommerce.helpers;

import com.derteuffel.springbootecommerce.entities.Produit;

public class ProduitCommandeDto {

    private  Produit produit;
    private Integer quantity;


    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


}
