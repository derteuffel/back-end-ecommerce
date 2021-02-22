package com.derteuffel.springbootecommerce.interfaces;

import com.derteuffel.springbootecommerce.entities.Panier;

import java.util.List;

public interface PanierInterface {

    Panier save(Panier panier);
    Panier getOne(Long id);
    void delete( Long id);
    List<Panier> findAll();
    List<Panier> findAllByStatus(String status);
}
