package com.derteuffel.springbootecommerce.interfaces;

import com.derteuffel.springbootecommerce.entities.Commande;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface CommandeInterface {

    List<Commande> getAllOrders();
    Commande create(@NotNull(message = "The order cannot be null.") @Valid Commande commande);
    Commande update(@NotNull(message = "The order cannot be null.") @Valid Commande commande);
}
