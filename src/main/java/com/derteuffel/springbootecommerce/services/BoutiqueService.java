package com.derteuffel.springbootecommerce.services;

import com.derteuffel.springbootecommerce.entities.Boutique;
import com.derteuffel.springbootecommerce.helpers.Generations;
import com.derteuffel.springbootecommerce.interfaces.BoutiqueInterface;
import com.derteuffel.springbootecommerce.repositories.BoutiqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoutiqueService implements BoutiqueInterface{

    @Autowired
    private BoutiqueRepository boutiqueRepository;




    @Override
    public Boutique save(Boutique boutique, Long id) {
        Generations generations = new Generations();
        boutique.setActivationCode(generations.generatedString());
        System.out.println("Activation code is : "+boutique.getActivationCode());
        return boutiqueRepository.save(boutique);
    }

    @Override
    public Boutique update(Boutique boutique, Long id) {
        Boutique existedBoutique = boutiqueRepository.getOne(id);
        if (existedBoutique != null){
            existedBoutique.setLocalisation(boutique.getLocalisation());
            existedBoutique.setMtnNumber(boutique.getMtnNumber());
            existedBoutique.setName(boutique.getName());
            existedBoutique.setOrangeNumber(boutique.getOrangeNumber());
            existedBoutique.setOthernumber(boutique.getOthernumber());
            boutiqueRepository.save(existedBoutique);
        }
        return existedBoutique;
    }

    @Override
    public Boutique getBoutique(Long id) {
        return boutiqueRepository.getOne(id);
    }

    @Override
    public void deleteBoutique(Long id) {

        boutiqueRepository.deleteById(id);
    }

    @Override
    public List<Boutique> findAll() {
        return boutiqueRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }

    @Override
    public List<Boutique> findAllByUser(Long id) {
        return boutiqueRepository.findAllByUser_Id(id);
    }

    @Override
    public void activateBoutique(Long id, String code) {
        Boutique boutique = boutiqueRepository.getOne(id);
        if (boutique.getActivationCode().equals(code)){
            boutique.setStatus(true);
            boutiqueRepository.save(boutique);
        }else {
            throw new RuntimeException("the codes did not matches");
        }
    }
}
