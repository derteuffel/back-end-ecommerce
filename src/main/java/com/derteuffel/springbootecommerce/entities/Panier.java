package com.derteuffel.springbootecommerce.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "panier")
public class Panier {

    @Id
    @GeneratedValue
    private Long id;

    private String numero;
    private Double montantTotal;

    @ManyToOne
    private User user;

    public Panier() {
    }

    public Panier(String numero, Double montantTotal, User user) {
        this.numero = numero;
        this.montantTotal = montantTotal;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Double getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(Double montantTotal) {
        this.montantTotal = montantTotal;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
