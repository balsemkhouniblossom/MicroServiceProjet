package com.ms.candidat.mscandidat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidatService implements ICandidatService {

    @Autowired
    private  CandidatRespository candidatRespository;


    @Override
    public List<Candidat> getAll() {
        return candidatRespository.findAll();
    }

    @Override
    public Candidat addCandidat(Candidat c) {
        return candidatRespository.save(c);
    }


    public Candidat updateCandidat(int id, Candidat c) { if (candidatRespository.findById(id).isPresent()) {

        Candidat existingCandidat = candidatRespository.findById(id).get(); existingCandidat.setNom(c.getNom()); existingCandidat.setPrenom(c.getPrenom()); existingCandidat.setEmail(c.getEmail());

        return candidatRespository.save(existingCandidat);
    } else
        return null;
    }

    public String deleteCandidat(int id) {
        if (candidatRespository.findById(id).isPresent()) { candidatRespository.deleteById(id);
            return "candidat supprimé";
        } else
            return "candidat non supprimé";
    }



}



