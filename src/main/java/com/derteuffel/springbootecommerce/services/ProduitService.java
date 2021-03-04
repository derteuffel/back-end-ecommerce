package com.derteuffel.springbootecommerce.services;

import com.derteuffel.springbootecommerce.entities.Produit;
import com.derteuffel.springbootecommerce.interfaces.ProduitInterface;
import com.derteuffel.springbootecommerce.repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProduitService implements ProduitInterface {

    @Autowired
    private ProduitRepository produitRepository;


    @Override
    public Produit save(Produit produit) {
        return produitRepository.save(produit);
    }

    @Override
    public Produit getOne(Long id) {
        Produit produit = produitRepository.getOne(id);
        return produit;
    }

    @Override
    public Produit update(Produit produit, Long id) {
        Produit existedProduit = produitRepository.getOne(id);
        if (existedProduit != null){
            existedProduit.setCategory(produit.getCategory());
            existedProduit.setQuality(produit.getQuality());
            existedProduit.setGenre(produit.getGenre());
            existedProduit.setName(produit.getName());
            existedProduit.setPrice(produit.getPrice());
        }
        return produitRepository.save(existedProduit);
    }

    @Override
    public void delete(Long id) {

        produitRepository.deleteById(id);
    }

    @Override
    public List<Produit> findAll() {
        return produitRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @Override
    public List<Produit> findAllByCategory(String category) {
        return produitRepository.findAllByCategoryOrderByIdDesc(category);
    }

    @Override
    public List<Produit> findAllByGenre(String genre) {
        return produitRepository.findAllByGenreOrderByIdDesc(genre);
    }

    @Override
    public List<Produit> findAllByQuality(String quality) {
        return produitRepository.findAllByQualityOrderByIdDesc(quality);
    }

    @Override
    public List<Produit> findAllByCategoryAndGenre(String category, String genre) {
        return produitRepository.findAllByCategoryAndGenreOrderByIdDesc(category, genre);
    }

    @Override
    public List<Produit> findAllByMarqueAndGenre(String marque, String genre) {
        return produitRepository.findAllByMarqueAndGenreOrderByIdDesc(marque,genre);
    }

    @Override
    public List<Produit> findAllByColors(String color, String genre) {
        List<Produit> lists = new ArrayList<>();
        for (Produit produit : produitRepository.findAllByGenreOrderByIdDesc(genre)){
            if (produit.getColors() != null && produit.getColors().contains(color)){
                lists.add(produit);
            }
        }
        return lists;
    }
}
