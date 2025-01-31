package com.esprit.ms.job;

import java.util.List;

public interface IJobService {
    public List<Job> getAll();
    Job addJob(Job job);
    Job updateJob(int id,Job job);
    Job deleteJob(int id);
    Job getJobById(int id);
    Job getJobByNom(String nom);
    Job updateJobEtat(int id, boolean etat);

}
