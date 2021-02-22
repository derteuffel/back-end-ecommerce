package com.derteuffel.springbootecommerce.repositories;

import com.derteuffel.springbootecommerce.entities.ProduitCommande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduitCommandeRepository extends JpaRepository<ProduitCommande, Long> {

    List<ProduitCommande> findAllByPanier_Id(Long id);
}
