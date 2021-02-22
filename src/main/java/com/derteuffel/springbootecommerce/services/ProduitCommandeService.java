package com.derteuffel.springbootecommerce.services;

import com.derteuffel.springbootecommerce.entities.Panier;
import com.derteuffel.springbootecommerce.entities.ProduitCommande;
import com.derteuffel.springbootecommerce.interfaces.ProduitCommandeInterface;
import com.derteuffel.springbootecommerce.repositories.PanierRepository;
import com.derteuffel.springbootecommerce.repositories.ProduitCommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitCommandeService implements ProduitCommandeInterface {

    @Autowired
    private ProduitCommandeRepository produitCommandeRepository;
    @Autowired
    private PanierRepository panierRepository;


    @Override
    public ProduitCommande save(ProduitCommande produitCommande, Long id) {

        Panier panier = panierRepository.getOne(id);
        produitCommande.setPanier(panier);
        return produitCommandeRepository.save(produitCommande);
    }

    @Override
    public ProduitCommande getOne(Long id) {
        return produitCommandeRepository.getOne(id);
    }


    @Override
    public void delete(Long id) {
        produitCommandeRepository.deleteById(id);
    }

    @Override
    public List<ProduitCommande> findByPanier(Long id) {
        return produitCommandeRepository.findAllByPanier_Id(id);
    }
}
