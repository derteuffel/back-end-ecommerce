package com.derteuffel.springbootecommerce.interfaces;

import com.derteuffel.springbootecommerce.entities.Produit;

import java.util.List;

public interface ProduitInterface {

    Produit save(Produit produit);
    Produit getOne(Long id);
    Produit update(Produit produit, Long id);
    void delete(Long id);
    List<Produit> findAll();
    List<Produit> findAllByCategory(String category);
    List<Produit> findAllByGenre(String genre);
    List<Produit> findAllByQuality(String quality);

}
