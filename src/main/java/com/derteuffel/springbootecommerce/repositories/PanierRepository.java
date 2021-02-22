package com.derteuffel.springbootecommerce.repositories;

import com.derteuffel.springbootecommerce.entities.Panier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PanierRepository extends JpaRepository<Panier, Long> {

    //List<Panier> findAllByStatus(String status);
}
