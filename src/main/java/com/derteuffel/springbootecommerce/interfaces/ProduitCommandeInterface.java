package com.derteuffel.springbootecommerce.interfaces;

import com.derteuffel.springbootecommerce.entities.ProduitCommande;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface ProduitCommandeInterface {

    ProduitCommande create(@NotNull(message = "The products for order cannot be null.") @Valid ProduitCommande produitCommande);
}
