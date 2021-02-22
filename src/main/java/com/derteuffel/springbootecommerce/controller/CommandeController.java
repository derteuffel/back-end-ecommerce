package com.derteuffel.springbootecommerce.controller;

import com.derteuffel.springbootecommerce.entities.Commande;
import com.derteuffel.springbootecommerce.entities.Panier;
import com.derteuffel.springbootecommerce.entities.Produit;
import com.derteuffel.springbootecommerce.enums.PaymentStatus;
import com.derteuffel.springbootecommerce.interfaces.CommandeInterface;
import com.derteuffel.springbootecommerce.interfaces.PanierInterface;
import com.derteuffel.springbootecommerce.interfaces.ProduitCommandeInterface;
import com.derteuffel.springbootecommerce.interfaces.ProduitInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commandes")
@CrossOrigin
public class CommandeController {

    @Autowired
    private CommandeInterface commandeInterface;

    @Autowired
    private ProduitInterface produitInterface;

    @Autowired
    private PanierInterface panierInterface;

    @Autowired
    private ProduitCommandeInterface produitCommandeInterface;

    @PostMapping("/{id}")
    public ResponseEntity<Commande> save(@RequestBody Commande commande, @PathVariable Long id) {

        Produit produit = produitInterface.getOne(id);
        //Panier panier = panierInterface.getOne(pId);
        commande.setStatus(PaymentStatus.IMPAYER.toString());
        commande.setAmount(produit.getPrice() * commande.getQuantity());
        return new ResponseEntity<>(commandeInterface.save(commande, produit.getId()), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Commande> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(commandeInterface.getOne(id), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Commande>> findAll() {
        System.out.println(commandeInterface.findAll());
        return new ResponseEntity<>(commandeInterface.findAll(), HttpStatus.OK);
    }

    @GetMapping("/panier/{id}")
    public List<Commande> findByPanier(@PathVariable Long id) {
        return commandeInterface.findByPanier(id);
    }
}
