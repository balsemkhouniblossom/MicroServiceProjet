package com.ms.candidat.mscandidat;

import java.util.List;

public interface ICandidatService {
    public List<Candidat> getAll();
     Candidat addCandidat(Candidat c);
    Candidat updateCandidat(int id, Candidat c);
    String deleteCandidat(int id);
}
