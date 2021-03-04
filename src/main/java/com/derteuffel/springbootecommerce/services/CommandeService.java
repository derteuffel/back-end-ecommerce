package com.derteuffel.springbootecommerce.services;

import com.derteuffel.springbootecommerce.entities.Commande;
import com.derteuffel.springbootecommerce.interfaces.CommandeInterface;
import com.derteuffel.springbootecommerce.repositories.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class CommandeService implements CommandeInterface {

    @Autowired
    private CommandeRepository commandeRepository;


    @Override
    public List<Commande> getAllOrders() {
        return commandeRepository.findAll();
    }

    @Override
    public Commande create(@NotNull(message = "The order cannot be null.") @Valid Commande commande) {
        return commandeRepository.save(commande);
    }

    @Override
    public Commande update(@NotNull(message = "The order cannot be null.") @Valid Commande commande) {
        return commandeRepository.save(commande);
    }
}
