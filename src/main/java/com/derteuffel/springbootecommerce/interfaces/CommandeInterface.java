package com.derteuffel.springbootecommerce.interfaces;

import com.derteuffel.springbootecommerce.entities.Commande;

import java.util.List;

public interface CommandeInterface {

    Commande save(Commande commande, Long id);
    Commande getOne(Long id);
    Commande update(Commande commande, Long id);
    void delete(Long id);
    List<Commande> findAll();
    List<Commande> findByPanier(Long id);
}
