package com.derteuffel.springbootecommerce.repositories;

import com.derteuffel.springbootecommerce.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {

    List<Produit> findAllByCategoryOrderByIdDesc(String category);
    List<Produit> findAllByGenreOrderByIdDesc(String genre);
    List<Produit> findAllByQualityOrderByIdDesc(String quality);
    List<Produit> findAllByCategoryAndGenreOrderByIdDesc(String category, String genre);
    List<Produit> findAllByMarqueAndGenreOrderByIdDesc(String marque, String genre);


}
