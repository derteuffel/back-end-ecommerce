package com.derteuffel.springbootecommerce.repositories;

import com.derteuffel.springbootecommerce.entities.Boutique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoutiqueRepository extends JpaRepository<Boutique, Long>{

    List<Boutique> findAllByUser_Id(Long id);
}
