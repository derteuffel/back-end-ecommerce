package com.derteuffel.springbootecommerce.services;

import com.derteuffel.springbootecommerce.entities.Panier;
import com.derteuffel.springbootecommerce.interfaces.PanierInterface;
import com.derteuffel.springbootecommerce.repositories.PanierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PanierService implements PanierInterface {

    @Autowired
    private PanierRepository panierRepository;

    @Override
    public Panier save(Panier panier) {
        panier.setNumero("#PA#"+(panierRepository.findAll().size()+1));
        return panierRepository.save(panier);
    }

    @Override
    public Panier getOne(Long id) {
        return panierRepository.getOne(id);
    }



    @Override
    public void delete(Long id) {

        panierRepository.deleteById(id);
    }

    @Override
    public List<Panier> findAll() {
        return panierRepository.findAll();
    }

    @Override
    public List<Panier> findAllByStatus(String status) {
        return null;
    }
}
