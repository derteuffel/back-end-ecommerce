package com.derteuffel.springbootecommerce.controller;

import com.derteuffel.springbootecommerce.entities.Commande;
import com.derteuffel.springbootecommerce.entities.ProduitCommande;
import com.derteuffel.springbootecommerce.enums.PaymentStatus;
import com.derteuffel.springbootecommerce.exceptions.ResourceNotFoundException;
import com.derteuffel.springbootecommerce.helpers.ProduitCommandeDto;
import com.derteuffel.springbootecommerce.interfaces.CommandeInterface;
import com.derteuffel.springbootecommerce.interfaces.ProduitCommandeInterface;
import com.derteuffel.springbootecommerce.interfaces.ProduitInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/commandes")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CommandeController {

    @Autowired
    private CommandeInterface commandeInterface;

    @Autowired
    private ProduitInterface produitInterface;


    @Autowired
    private ProduitCommandeInterface produitCommandeInterface;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public @NotNull Iterable<Commande> list() {
        return this.commandeInterface.getAllOrders();
    }

    @PostMapping
    public ResponseEntity<Commande> create(@RequestBody OrderForm form) {

        List<ProduitCommandeDto> formDtos = form.getProductOrders();
        System.out.println("Je contien :"+formDtos.size()+" elements");
        for (ProduitCommandeDto pc : formDtos) {
            System.out.println(pc.getProduit());
            System.out.println(pc.getQuantity());
        }
        validateProductsExistence(formDtos);
        Commande order = new Commande();
        order.setStatus(PaymentStatus.PAYER.name());
        int i= commandeInterface.getAllOrders().size();
        order.setNumero("#CO#"+(i++));
        order = this.commandeInterface.create(order);

        List<ProduitCommande> orderProducts = new ArrayList<>();
        for (ProduitCommandeDto dto : formDtos) {
            orderProducts.add(produitCommandeInterface.create(new ProduitCommande(produitInterface.getOne(dto.getProduit().getId()),order,
                    dto.getQuantity())));
        }

        order.setProduitCommandes(orderProducts);

        this.commandeInterface.update(order);

        String uri = ServletUriComponentsBuilder
                .fromCurrentServletMapping()
                .path("/commandes/{id}")
                .buildAndExpand(order.getId())
                .toString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", uri);

        return new ResponseEntity<>(order, headers, HttpStatus.CREATED);
    }

    private void validateProductsExistence(List<ProduitCommandeDto> orderProducts) {
        for (ProduitCommandeDto pc: orderProducts){
            System.out.println(pc.getProduit().getName());
        }
        List<ProduitCommandeDto> list = orderProducts
                .stream()
                .filter(op -> Objects.isNull(produitInterface.getOne(op
                        .getProduit()
                        .getId())))
                .collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(list)) {
            new ResourceNotFoundException("Product not found");
        }
    }

    public static class OrderForm {

        private List<ProduitCommandeDto> productOrders;

        public List<ProduitCommandeDto> getProductOrders() {
            return productOrders;
        }

        public void setProductOrders(List<ProduitCommandeDto> productOrders) {
            this.productOrders = productOrders;
        }
    }
}
