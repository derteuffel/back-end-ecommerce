package com.derteuffel.springbootecommerce.controller;

import com.derteuffel.springbootecommerce.entities.Boutique;
import com.derteuffel.springbootecommerce.entities.Produit;
import com.derteuffel.springbootecommerce.helpers.ProduitHelper;
import com.derteuffel.springbootecommerce.interfaces.BoutiqueInterface;
import com.derteuffel.springbootecommerce.services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/produits")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProduitController {

    @Value("${file.upload-dir}")
    private String location ;


    @Autowired
    private ProduitService produitService;

    @Autowired
    private BoutiqueInterface boutiqueInterface;



    @PostMapping("/{id}")
    public ResponseEntity<Produit> save(@RequestBody ProduitHelper produit, @PathVariable Long id) {

        Boutique boutique = boutiqueInterface.getBoutique(id);
        System.out.println(produit.getColor());
                Produit produit1 = new Produit();
                produit1.setQuality(produit.getQuality());
                produit1.setPrice(produit.getPrice());
                produit1.setName(produit.getName());
                produit1.setGenre(produit.getGenre());
                produit1.setCategory(produit.getCategory());
                produit1.setMarque(produit.getMarque());
                produit1.setDescription(produit.getDescription());
                produit1.setPictureUrl("http://localhost:8080/images/shopping-1.jpeg");
                produit1.setColors(produit.getColor());
                produit1.setBoutique(boutique);
                produitService.save(produit1);


        return new ResponseEntity<>(produit1, HttpStatus.CREATED) ;
    }



    @GetMapping("/{id}")
    public ResponseEntity<Produit> getOne(@PathVariable Long id) {

        return new ResponseEntity<>(produitService.getOne(id), HttpStatus.OK);
    }


    @PostMapping("/uploads/{id}")
    public ResponseEntity<List<Produit>> uploadFiles(@RequestParam("file") MultipartFile file, @PathVariable int id){



        Produit produit =  produitService.getOne(Long.parseLong(id+""));
        if (produit != null) {

            //post.setPicture("http://localhost:8080/uploads-file"+file.getOriginalFilename());



            try {



                        if (!(file.isEmpty())) {
                            // Get the file and save it somewhere
                            if (produit.getPictures() != null) {
                                produit.getPictures().add("http://localhost:8080/downloadFile/" + file.getOriginalFilename());
                            }else {
                                produit.setPictures(new ArrayList<>(Arrays.asList("http://localhost:8080/downloadFile/" + file.getOriginalFilename())));
                            }
                            Path path = Paths.get(location + "/" + file.getOriginalFilename());
                            byte[] bytes = file.getBytes();

                            Files.write(path, bytes);
                        }

                } catch(IOException e){
                    e.printStackTrace();
                }
                produitService.save(produit);
            for(String string : produit.getPictures()){
                System.out.println("je suis "+string);
            }

        }else {
            System.out.println("le produit n'existe pas");
        }

        return new ResponseEntity<>(produitService.findAll(),HttpStatus.OK);
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

    @GetMapping("/sort/{category}/{genre}")
    public ResponseEntity<List<Produit>> findAllByCategoryAndGenre(@PathVariable String category, @PathVariable String genre) {
        List<Produit> lists = produitService.findAllByCategoryAndGenre(category, genre);

        return new ResponseEntity<>(lists, HttpStatus.OK);
    }

    @GetMapping("/marque/{marque}/{genre}")
    public ResponseEntity<List<Produit>> findAllByMarqueAndGenre(@PathVariable String marque, @PathVariable String genre) {
        List<Produit> lists = produitService.findAllByMarqueAndGenre(marque, genre);

        return new ResponseEntity<>(lists, HttpStatus.OK);
    }

    @GetMapping("/colors/{color}/{genre}")
    public ResponseEntity<List<Produit>> findAllByColorAndGenre(@PathVariable String color, @PathVariable String genre) {
        List<Produit> lists = produitService.findAllByColors(color, genre);

        return new ResponseEntity<>(lists, HttpStatus.OK);
    }

    @GetMapping("/mobile")
    public ResponseEntity<List<Produit>> findAllMobile() {
        List<Produit> lists = produitService.findAll();
        System.out.println(lists);
        return new ResponseEntity<>(lists, HttpStatus.OK);
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
        if (produits.size() > 8){
            for (int i=0; i < 8; i++){
                lists.add(produits.get(i));
            }
        }else {
            lists.addAll(produits);
        }
        return new ResponseEntity<>(lists, HttpStatus.OK);
    }
}
