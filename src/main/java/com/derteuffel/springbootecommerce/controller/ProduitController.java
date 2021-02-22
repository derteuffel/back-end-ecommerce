package com.derteuffel.springbootecommerce.controller;

import com.derteuffel.springbootecommerce.entities.Produit;
import com.derteuffel.springbootecommerce.interfaces.PanierInterface;
import com.derteuffel.springbootecommerce.services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/produits")
@CrossOrigin
public class ProduitController {

    @Value("${file.upload-dir}")
    private String location ;


    @Autowired
    private ProduitService produitService;

    @Autowired
    private PanierInterface panierInterface;

    @PostMapping("")
    public ResponseEntity<Produit> save(@RequestBody Produit produit) {

        produit.setPictureUrl("http://localhost:8080/images/default.jpg");
        return new ResponseEntity<>(produitService.save(produit), HttpStatus.CREATED) ;
    }



    @GetMapping("/{id}")
    public ResponseEntity<Produit> getOne(@PathVariable Long id) {

        return new ResponseEntity<>(produitService.getOne(id), HttpStatus.OK);
    }


    @PostMapping("/upload/{id}")
    public ResponseEntity<List<Produit>> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable int id){



        Produit produit =  produitService.getOne(Long.parseLong(id+""));
        if (produit != null) {

            //post.setPicture("http://localhost:8080/uploads-file"+file.getOriginalFilename());


            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/downloadFile/")
                    .path(file.getOriginalFilename())
                    .toUriString();

            try {

                if (!(file.isEmpty())) {

                            // Get the file and save it somewhere
                            produit.setPictureUrl("http://localhost:8080/downloadFile/"+ file.getOriginalFilename());
                            Path path = Paths.get(location +"/"+ file.getOriginalFilename());
                            byte[] bytes = file.getBytes();

                            Files.write(path, bytes);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            produitService.save(produit);
            System.out.println(produit.getPictureUrl());
        }else {
            System.out.println("le produit n'existe pas");
        }

        return new ResponseEntity<>(produitService.findAll(),HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Produit> update(@RequestBody Produit produit, @PathVariable Long id) {
        return new ResponseEntity<>(produitService.update(produit, id), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        produitService.delete(id);
    }

    @GetMapping("")
    public ResponseEntity<List<Produit>> findAll() {
        List<Produit> lists = produitService.findAll();
        List<Produit> productList = new ArrayList<>();
        if (lists.size() > 6) {
            for (int i = 0; i < 6; i++) {
                productList.add(lists.get(i));
            }
        }else {
            productList.addAll(lists);
        }
        System.out.println(productList);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/admin")
    public ResponseEntity<List<Produit>> findAllAdmin() {
        List<Produit> lists = produitService.findAll();
        System.out.println(lists);
        return new ResponseEntity<>(lists, HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Produit>> findAllByCategory(@PathVariable String category) {
        System.out.println(category);
        System.out.println(produitService.findAllByCategory(category));
        List<Produit> productList = produitService.findAllByCategory(category);
        List<Produit> lists = new ArrayList<>();
        if (productList.size() > 6) {
            for (int i = 0; i < 6; i++) {
                lists.add(productList.get(i));
            }
        }else {
            lists.addAll(productList);
        }
        return new ResponseEntity<>(lists, HttpStatus.OK);
    }
    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<Produit>> findAllByGenre(@PathVariable String genre) {
        System.out.println(genre);
        System.out.println(produitService.findAllByGenre(genre));
        return new ResponseEntity<>(produitService.findAllByGenre(genre), HttpStatus.OK);
    }

    @GetMapping("/quality/{quality}")
    public ResponseEntity<List<Produit>> findAllByQuality(@PathVariable String quality) {
        System.out.println(quality);
        System.out.println(produitService.findAllByQuality(quality));
        List<Produit> lists = new ArrayList<>();
        List<Produit> produits = produitService.findAllByQuality(quality);
        if (produits.size() > 6){
            for (int i=0; i < 6; i++){
                lists.add(produits.get(i));
            }
        }else {
            lists.addAll(produits);
        }
        return new ResponseEntity<>(lists, HttpStatus.OK);
    }
}
