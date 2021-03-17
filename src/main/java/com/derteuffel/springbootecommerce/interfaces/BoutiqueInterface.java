package com.derteuffel.springbootecommerce.interfaces;

import com.derteuffel.springbootecommerce.entities.Boutique;

import java.util.List;

public interface BoutiqueInterface {

    Boutique save(Boutique boutique, Long id);
    Boutique update(Boutique boutique, Long id);
    Boutique getBoutique(Long id);
    void deleteBoutique(Long id);
    List<Boutique> findAll();
    List<Boutique> findAllByUser(Long id);
    void activateBoutique(Long id, String code);
}
