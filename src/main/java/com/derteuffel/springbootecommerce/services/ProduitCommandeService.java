package com.derteuffel.springbootecommerce.services;

import com.derteuffel.springbootecommerce.entities.ProduitCommande;
import com.derteuffel.springbootecommerce.interfaces.ProduitCommandeInterface;
import com.derteuffel.springbootecommerce.repositories.ProduitCommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Service
public class ProduitCommandeService implements ProduitCommandeInterface {

    @Autowired
    private ProduitCommandeRepository produitCommandeRepository;

    @Override
    public ProduitCommande create(@NotNull(message = "The products for order cannot be null.") @Valid ProduitCommande produitCommande) {
        return produitCommandeRepository.save(produitCommande);
    }
}
