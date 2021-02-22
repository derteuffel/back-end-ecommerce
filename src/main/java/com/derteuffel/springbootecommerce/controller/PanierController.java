package com.derteuffel.springbootecommerce.controller;

import com.derteuffel.springbootecommerce.entities.Panier;
import com.derteuffel.springbootecommerce.interfaces.PanierInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/paniers")
@CrossOrigin
public class PanierController {

    @Autowired
    private PanierInterface panierInterface;

    public Panier save(Panier panier) {
        return panierInterface.save(panier);
    }

    public Panier getOne(Long id) {
        return panierInterface.getOne(id);
    }

    public void delete(Long id) {
        panierInterface.delete(id);
    }

    public List<Panier> findAll() {
        return panierInterface.findAll();
    }

    public List<Panier> findAllByStatus(String status) {
        return panierInterface.findAllByStatus(status);
    }
}
