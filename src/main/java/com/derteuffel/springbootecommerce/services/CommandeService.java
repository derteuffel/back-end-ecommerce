package com.derteuffel.springbootecommerce.services;

import com.derteuffel.springbootecommerce.entities.Commande;
import com.derteuffel.springbootecommerce.entities.Panier;
import com.derteuffel.springbootecommerce.entities.Produit;
import com.derteuffel.springbootecommerce.entities.ProduitCommande;
import com.derteuffel.springbootecommerce.interfaces.CommandeInterface;
import com.derteuffel.springbootecommerce.repositories.CommandeRepository;
import com.derteuffel.springbootecommerce.repositories.PanierRepository;
import com.derteuffel.springbootecommerce.repositories.ProduitCommandeRepository;
import com.derteuffel.springbootecommerce.repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandeService implements CommandeInterface {

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private PanierRepository panierRepository;

    @Override
    public Commande save(Commande commande, Long id) {

        Produit produit = produitRepository.getOne(id);
        //Panier panier = panierRepository.getOne(pId);

        commande.setProduit(produit);
        //commande.setPanier(panier);
        commande.setNumero("#CO#"+(commandeRepository.findAll().size()+1));
        return commandeRepository.save(commande);
    }

    @Override
    public Commande getOne(Long id) {
        return commandeRepository.getOne(id);
    }

    @Override
    public Commande update(Commande commande, Long id) {

        Commande existedCommande = commandeRepository.getOne(id);
        if (existedCommande != null){
            existedCommande.setColor(commande.getColor());
            existedCommande.setQuantity(commande.getQuantity());
            existedCommande.setSize(commande.getSize());
        }
        return commandeRepository.save(existedCommande);
    }

    @Override
    public void delete(Long id) {

        commandeRepository.deleteById(id);
    }

    @Override
    public List<Commande> findAll() {
        return commandeRepository.findAll();
    }

    @Override
    public List<Commande> findByPanier(Long id) {

        return commandeRepository.findAllByPanier_Id(id);
    }
}
