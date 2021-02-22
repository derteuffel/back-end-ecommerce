package com.derteuffel.springbootecommerce.repositories;

import com.derteuffel.springbootecommerce.entities.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {

    List<Commande> findAllByPanier_Id(Long id);
}
