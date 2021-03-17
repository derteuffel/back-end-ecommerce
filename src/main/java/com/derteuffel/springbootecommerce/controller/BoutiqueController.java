package com.derteuffel.springbootecommerce.controller;

import com.derteuffel.springbootecommerce.entities.Boutique;
import com.derteuffel.springbootecommerce.interfaces.BoutiqueInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/boutiques")
@CrossOrigin(origins = "*", maxAge = 3600)
public class BoutiqueController {


    @Autowired
    private BoutiqueInterface boutiqueInterface;


    @PostMapping("")
    public ResponseEntity<Boutique> save(@RequestBody Boutique boutique,  Long id) {

        try {
            Boutique savedBoutique = boutiqueInterface.save(boutique, id);
            return new ResponseEntity<>(savedBoutique, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>((Boutique) null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boutique> update(@RequestBody Boutique boutique, @PathVariable Long id) {

        Boutique updatedBoutique = boutiqueInterface.update(boutique, id);
        try {
            if (updatedBoutique != null) {
                return new ResponseEntity<>(updatedBoutique, HttpStatus.ACCEPTED);
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>((Boutique) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Boutique> getBoutique(@PathVariable Long id) {
        Boutique boutique = boutiqueInterface.getBoutique(id);
       try {
           if (boutique != null) {
               return new ResponseEntity<>(boutique, HttpStatus.OK);
           } else {
               return new ResponseEntity<>(HttpStatus.NOT_FOUND);
           }
       }catch (Exception e){
           return new ResponseEntity<>((Boutique) null, HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBoutique(@PathVariable Long id) {
        try{
           boutiqueInterface.deleteBoutique(id);
           return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Boutique>> findAll() {
        List<Boutique> lists = new ArrayList<>();
        try{
            boutiqueInterface.findAll().forEach(lists :: add);
            if (lists.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else {
                return new ResponseEntity<>(lists, HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>((List<Boutique>) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/users")
    public ResponseEntity<List<Boutique>> findAllByUser() {
        List<Boutique> lists = new ArrayList<>();
        try{
            boutiqueInterface.findAllByUser(1L).forEach(lists :: add);
            if (lists.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else {
                return new ResponseEntity<>(lists, HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>((List<Boutique>) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/activation/{id}")
    public ResponseEntity<HttpStatus> activateBoutique(@PathVariable Long id, String code){
        try{
            boutiqueInterface.activateBoutique(id, code);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
