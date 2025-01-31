package com.esprit.ms.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService implements IJobService{

    @Autowired
    private JobRepository jobRepository;

    @Override
    public List<Job> getAll() {
        return jobRepository.findAll();
    }

    @Override
    public Job addJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public Job updateJob(int id, Job job) {
        if (jobRepository.findById(id).isPresent()) {

            Job existingJob = jobRepository.findById(id).get();
            existingJob.setNom(job.getNom());
            existingJob.setService(job.getService());
            existingJob.setEtat(job.getEtat());

            return jobRepository.save(existingJob);
        } else
            return null;
    }

    @Override
    public Job deleteJob(int id) {
        Optional<Job> optionalJob = jobRepository.findById(id);
        if (optionalJob.isPresent()) {
            jobRepository.deleteById(id);
            return optionalJob.get();
        }
        throw new RuntimeException("Job not found with id: " + id);
    }

    @Override
    public Job getJobById(int id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found with id: " + id));
    }

    @Override
    public Job getJobByNom(String nom) {
        return jobRepository.findByNom(nom)
                .orElseThrow(() -> new RuntimeException("Job not found with name: " + nom));
    }



    @Override
    public Job updateJobEtat(int id, boolean etat) {
        return jobRepository.findById(id).map(existingJob -> {
            existingJob.setEtat(etat); // true = disponible, false = occupé
            return jobRepository.save(existingJob);
        }).orElseThrow(() -> new RuntimeException("Job not found with id: " + id));
    }

}
