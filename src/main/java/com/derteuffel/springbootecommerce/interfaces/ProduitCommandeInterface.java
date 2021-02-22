package com.derteuffel.springbootecommerce.interfaces;

import com.derteuffel.springbootecommerce.entities.ProduitCommande;

import java.util.List;

public interface ProduitCommandeInterface {

    ProduitCommande save(ProduitCommande produitCommande, Long id);
    ProduitCommande getOne(Long id);
    void delete(Long id);
    List<ProduitCommande> findByPanier(Long id);
}
