package com.esprit.ms.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    // Récupérer tous les jobs
    @GetMapping("/getAll")
    public List<Job> getAllJobs() {
        return jobService.getAll();
    }

    // Ajouter un job
    @PostMapping
    public Job addJob(@RequestBody Job job) {
        return jobService.addJob(job);
    }

    // Mettre à jour un job
    @PutMapping("/{id}")
    public Job updateJob(@PathVariable int id, @RequestBody Job job) {
        return jobService.updateJob(id, job);
    }

    // Supprimer un job
    @DeleteMapping("/{id}")
    public Job deleteJob(@PathVariable int id) {
        return jobService.deleteJob(id);
    }

    // Récupérer un job par ID
    @GetMapping("/{id}")
    public Job getJobById(@PathVariable int id) {
        return jobService.getJobById(id);
    }

    // Récupérer un job par nom
    @GetMapping("/search")
    public Job getJobByNom(@RequestParam String nom) {
        return jobService.getJobByNom(nom);
    }

    // Mettre à jour l’état du job (disponible = true, occupé = false)
    @PutMapping("/{id}/etat")
    public Job updateJobEtat(@PathVariable int id, @RequestParam boolean etat) {
        return jobService.updateJobEtat(id, etat);
    }
}
